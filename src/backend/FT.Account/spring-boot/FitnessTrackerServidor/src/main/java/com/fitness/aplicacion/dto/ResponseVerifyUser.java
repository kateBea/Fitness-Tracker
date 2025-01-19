package com.fitness.aplicacion.dto;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import com.fitness.aplicacion.dto.data.ResponseVerifyData;


/**
 * Modelo de respuesta para Verificación de usuario
 * @version 1.0
 * */

@Data
@SuperBuilder
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)

public class ResponseVerifyUser extends BaseResponse<ResponseVerifyData> {

}
