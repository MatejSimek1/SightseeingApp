package hr.tis.hackaton.sightseeingapp.service.impl;

import hr.tis.hackaton.sightseeingapp.dto.FavouritesDto;
import hr.tis.hackaton.sightseeingapp.dto.UserEntityDto;
import hr.tis.hackaton.sightseeingapp.mapper.UserEntityMapper;
import hr.tis.hackaton.sightseeingapp.model.UserEntity;
import hr.tis.hackaton.sightseeingapp.repository.UserEntityRepository;
import hr.tis.hackaton.sightseeingapp.service.UserEntityService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
public class UserEntityServiceImpl implements UserEntityService {

    private final UserEntityRepository userEntityRepository;
    private final UserEntityMapper userEntityMapper;

    public UserEntityServiceImpl(UserEntityRepository userEntityRepository,
                                 UserEntityMapper userEntityMapper
    ) {
        this.userEntityRepository = userEntityRepository;
        this.userEntityMapper = userEntityMapper;
    }


    @Override
    public List<UserEntityDto> getAllUsers() {
        return userEntityRepository.findAll().stream()
                .map(userEntityMapper::toDto)
                .toList();
    }

    @Override
    public UserEntityDto getUserById(Long id) {
        return userEntityMapper.toDto(userEntityRepository.findById(id).orElse(null));
    }

    @Override
    public Long saveUser(UserEntityDto userEntityDto) {
        if(userEntityRepository.existsByEmail(userEntityDto.getEmail())) {
            return null;
        }
        UserEntity userEntity = userEntityMapper.toEntity(userEntityDto);
        return userEntityRepository.save(userEntity).getId();
    }

    @Override
    public UserEntityDto updateUser(Long id, UserEntityDto userEntityDto) {
        UserEntity userEntity = userEntityMapper.toEntity(userEntityDto);
        userEntity.setId(id);
        return userEntityMapper.toDto(userEntityRepository.save(userEntity));
    }

    @Override
    public List<FavouritesDto> getUserFavourites(Long id) {
        List<FavouritesDto> favouritesDtos = new ArrayList<>();
        Optional<UserEntity> userEntityOptional = userEntityRepository.findById(id);

        if(userEntityOptional.isEmpty()) {
            return null;
        }
        userEntityOptional.ifPresent(userEntity -> {
            userEntity.getFavoriteAttractions().forEach(attraction -> {
                FavouritesDto favouritesDto = new FavouritesDto();
                favouritesDto.setAttractionName(attraction.getName());
                favouritesDto.setLocation(attraction.getLocation()); //TODO to se mora promijeniti kad će se dodativeza
                favouritesDtos.add(favouritesDto);
            });
        });

        return favouritesDtos;
    }

    @Override
    public FavouritesDto addFavourite(Long id, FavouritesDto favouritesDto) {
        Optional<UserEntity> userEntityOptional = userEntityRepository.findById(id);
        if(userEntityOptional.isEmpty()) {
            return null;
        }
        //TODO dodati repositroy provjeru preko attrraction repository kako bi našli postoji li zapravo takav attraction
        AtomicBoolean attractionfavoriteExists = new AtomicBoolean(false);
        userEntityOptional.ifPresent(userEntity -> {
            userEntity.getFavoriteAttractions()
                    .forEach(attraction -> {
                        if(attraction.getName().equals(favouritesDto.getAttractionName())
                             || attraction.getLocation().equals(favouritesDto.getLocation())) {
                            attractionfavoriteExists.set(true);
                        }
                    });

        });
        if(attractionfavoriteExists.get()) {
            return null;
        }
        else{
            UserEntity userEntity = userEntityOptional.get();
            userEntityRepository.save(userEntity);
            return favouritesDto;
        }
    }
}
