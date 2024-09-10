package hr.tis.hackaton.sightseeingapp.service;

import hr.tis.hackaton.sightseeingapp.dto.FavouritesDto;
import hr.tis.hackaton.sightseeingapp.dto.UserEntityDto;

import java.util.List;

public interface UserEntityService {

    List<UserEntityDto> getAllUsers();
    UserEntityDto getUserById(Long id);
    Long saveUser(UserEntityDto userEntityDto);
    UserEntityDto updateUser(Long id, UserEntityDto userEntityDto);
    List<FavouritesDto> getUserFavourites(Long id);
    FavouritesDto addFavourite(Long id, FavouritesDto favouritesDto);

}
