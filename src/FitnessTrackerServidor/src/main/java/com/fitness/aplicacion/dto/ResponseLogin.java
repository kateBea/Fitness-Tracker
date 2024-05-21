package com.fitness.aplicacion.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor

public class ResponseLogin extends BaseResponse {

    @JsonProperty(value = "email")
    private String email;

    @JsonProperty(value = "name")
    private String name;

    @JsonProperty(value = "first_name")
    private String firstSurname;

    @JsonProperty(value = "second_name")
    private String secondSurname;

    @JsonProperty(value = "token")
    private String token;

    @JsonProperty(value = "token_expiration_date")
    private LocalDateTime tokenExpiration;

    @JsonProperty(value = "token_duration")
    private int tokenDuration;

    @JsonProperty(value = "logged_at")
    private LocalDateTime loggedAt;

}
