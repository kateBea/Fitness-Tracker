package com.fitness.aplicacion.dto;

import com.fitness.aplicacion.dto.data.ResponseGetDatosUsuarioData;
import lombok.*;
import lombok.experimental.SuperBuilder;

/**
 * Modelo de respuesta para obtener los datos de un Usuario
 * @version 1.0
 * */

@Data
@SuperBuilder
@AllArgsConstructor

public class ResponseGetDatosUsuario extends BaseResponse<ResponseGetDatosUsuarioData> {

}
