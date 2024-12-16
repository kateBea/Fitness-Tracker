package com.fitness.aplicacion.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fitness.aplicacion.dto.data.ResponseGetDatosUsuarioData;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@SuperBuilder
@AllArgsConstructor

public class ResponseGetDatosUsuario extends BaseResponse<ResponseGetDatosUsuarioData> {

}
