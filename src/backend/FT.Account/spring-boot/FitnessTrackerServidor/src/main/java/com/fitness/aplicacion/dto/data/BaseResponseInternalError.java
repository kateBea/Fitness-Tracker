package com.fitness.aplicacion.dto.data;

import com.fitness.aplicacion.dto.BaseResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

/**
 * Modelo de respuesta para Error en validaciones
 * @version 1.0
 * */

@Data
@SuperBuilder
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)

public class BaseResponseInternalError extends BaseResponse<Object> {

}