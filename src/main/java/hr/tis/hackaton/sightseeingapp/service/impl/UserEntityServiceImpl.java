package hr.tis.hackaton.sightseeingapp.service.impl;

import hr.tis.hackaton.sightseeingapp.dto.FavouritesDto;
import hr.tis.hackaton.sightseeingapp.dto.UserEntityDto;
import hr.tis.hackaton.sightseeingapp.exception.NoAttractionFoundException;
import hr.tis.hackaton.sightseeingapp.exception.UserEntityNotFoundException;
import hr.tis.hackaton.sightseeingapp.mapper.UserEntityMapper;
import hr.tis.hackaton.sightseeingapp.model.Attraction;
import hr.tis.hackaton.sightseeingapp.model.UserEntity;
import hr.tis.hackaton.sightseeingapp.repository.AttractionRepositoryJpa;
import hr.tis.hackaton.sightseeingapp.repository.UserEntityRepository;
import hr.tis.hackaton.sightseeingapp.service.UserEntityService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserEntityServiceImpl implements UserEntityService {

    private final UserEntityRepository userEntityRepository;
    private final UserEntityMapper userEntityMapper;
    private final AttractionRepositoryJpa attractionRepository;

    public UserEntityServiceImpl(UserEntityRepository userEntityRepository,
                                 UserEntityMapper userEntityMapper,
                                    AttractionRepositoryJpa attractionRepository
    ) {
        this.userEntityRepository = userEntityRepository;
        this.userEntityMapper = userEntityMapper;
        this.attractionRepository = attractionRepository;
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

    @Transactional
    @Override
    public Long saveUser(UserEntityDto userEntityDto) {
        if(userEntityRepository.existsByEmail(userEntityDto.getEmail())) {
            return null;
        }
        UserEntity userEntity = userEntityMapper.toEntity(userEntityDto);
        UserEntity savedUserEntity = userEntityRepository.save(userEntity);
        return savedUserEntity.getId();
    }

    @Transactional
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
                favouritesDto.setLocation(attraction.getLocation().getName()); //TODO to se mora promijeniti kad će se dodativeza
                favouritesDtos.add(favouritesDto);
            });
        });

        return favouritesDtos;
    }

    @Transactional
    @Override
    public FavouritesDto addFavourite(Long id, FavouritesDto favouritesDto) {
        UserEntity userEntity = userEntityRepository.findById(id).orElseThrow(() -> new UserEntityNotFoundException("User with id " + id + " not found"));

        Attraction attraction = attractionRepository
                .findByAttractionNameAndLocationName(favouritesDto.getAttractionName(), favouritesDto.getLocation());
        if(attraction == null) {
            throw new NoAttractionFoundException("Attraction with name " + favouritesDto.getAttractionName() + " not found");
        }
        boolean isFavourite = userEntity.getFavoriteAttractions()
                .stream()
                .anyMatch(attraction1 ->
                            attraction1.getId().equals(attraction.getId() //dodaje se vrijednost u isFavourite na temelju usporedbi
                        )
        );
        if (isFavourite){
            throw new NoAttractionFoundException("Attraction is already in favourites");
        }
        userEntity.getFavoriteAttractions().add(attraction);
        userEntityRepository.save(userEntity);
        return favouritesDto;
    }
}
