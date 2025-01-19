package com.fitness.aplicacion.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;

/**
 * Modelo de respuesta para Error en validaciones
 * @version 1.0
 * */

@Data
@SuperBuilder
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)

public class BaseResponseBadRequest extends BaseResponse<Object> {

}
