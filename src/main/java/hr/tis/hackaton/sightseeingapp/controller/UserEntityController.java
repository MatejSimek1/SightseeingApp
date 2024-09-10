package hr.tis.hackaton.sightseeingapp.controller;

import hr.tis.hackaton.sightseeingapp.dto.FavouritesDto;
import hr.tis.hackaton.sightseeingapp.dto.UserAlreadyExistsResponse;
import hr.tis.hackaton.sightseeingapp.dto.UserEntityDto;
import hr.tis.hackaton.sightseeingapp.exception.UserEntityNotFoundException;
import hr.tis.hackaton.sightseeingapp.service.UserEntityService;
import jakarta.validation.Valid;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserEntityController {

    private final UserEntityService userEntityService;

    public UserEntityController(UserEntityService userEntityService) {
        this.userEntityService = userEntityService;
    }

    @PostMapping
    public ResponseEntity<?> saveUser(
            @Valid UserEntityDto userEntityDto
    ) {
        Long id = userEntityService.saveUser(userEntityDto);

        if(id == null) {
            UserAlreadyExistsResponse response =
                    new UserAlreadyExistsResponse("Već postoji korisnik s tim emailom", LocalDateTime.now());
            return ResponseEntity.status(400).body(response);
        }

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Location", "/user/" + id);

        return ResponseEntity.status(201)
                .headers(responseHeaders)
                .body("User successfully created with id: " + id);
        //return ResponseEntity.status(201).header("Location", Array.); //TODO je li ovo u redu s obzirom na zadatak?!
    }

    @GetMapping("/{user_id}")
    public ResponseEntity<UserEntityDto> getUserById(
            @PathVariable Long user_id
    ) {
        UserEntityDto userEntityDto = userEntityService.getUserById(user_id);
        if(userEntityDto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(userEntityDto);
    }

    @GetMapping("/{user_id}/favourites")
    public ResponseEntity<List<FavouritesDto>> getUserFavourites(
            @PathVariable Long user_id
    ) {
        List<FavouritesDto> favouritesDto = userEntityService.getUserFavourites(user_id);
        if(favouritesDto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(favouritesDto);
    }

    @PostMapping("/{user_id}/favourites")
    public ResponseEntity<?> addFavourite(
            @PathVariable Long user_id,
            @RequestBody FavouritesDto favouritesDto
    ) {
        try {
            FavouritesDto favDto = userEntityService.addFavourite(user_id, favouritesDto);

            if (favDto == null) {
                return ResponseEntity.badRequest().build();
            }
            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.set("Content-Location", "/user/" + user_id + "/favourites" );

            return ResponseEntity.status(201)
                    .headers(responseHeaders)
                    .body(favDto);
        }
        catch (Exception e) {
            if(e instanceof UserEntityNotFoundException) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.status(500).build();
        }
    }

}
