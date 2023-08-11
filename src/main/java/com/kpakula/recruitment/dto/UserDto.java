package com.kpakula.recruitment.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserDto {
    @NotBlank(message = "invalid firstname - can not be empty")
    @NotNull(message = "invalid firstname - can not be null")
    @Size(min = 1, max = 50, message = "invalid firstname - must be of 1 - 50 characters")
    private String firstname;

    @NotBlank(message = "invalid lastname - can not be empty")
    @NotNull(message = "invalid lastname - can not be null")
    @Size(min = 1, max = 50, message = "invalid lastname - must be of 1 - 50 characters")
    private String lastname;

    @NotBlank(message = "invalid PESEL - can not be empty")
    @NotNull(message = "invalid PESEL - can not be null")
    @Size(min = 11, max = 11, message = "invalid PESEL - must be 11 digits")
    private String pesel;
}
