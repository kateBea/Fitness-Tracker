package com.fitness.aplicacion.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class RequestCambiarPassword {

    @JsonProperty(value = "email")
    private String email;

    @JsonProperty(value = "new_password")
    private String newPassword;

    @JsonProperty(value = "old_password")
    private String oldPassword;

    @JsonProperty(value = "attempt_change_date")
    private LocalDateTime attemptChangeDate;
}
