package com.fitness.aplicacion.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseCambiarPassword extends BaseResponse {

    @JsonProperty(value = "change_date")
    private LocalDateTime changeDate;
}
