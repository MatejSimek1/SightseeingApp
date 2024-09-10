package hr.tis.hackaton.sightseeingapp.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
public class UserEntityDto {

    @NotNull
    @NotEmpty
    @NotBlank
    private String name;
    @NotNull
    @Email
    private String email;

    public @NotNull @NotEmpty @NotBlank String getName() {
        return name;
    }

    public void setName(@NotNull @NotEmpty @NotBlank String name) {
        this.name = name;
    }

    public @NotNull @Email String getEmail() {
        return email;
    }

    public void setEmail(@NotNull @Email String email) {
        this.email = email;
    }
}
