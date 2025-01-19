package com.fitness.aplicacion.controladores;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.fitness.aplicacion.dto.*;
import com.fitness.aplicacion.dto.data.*;
import com.fitness.aplicacion.servicio.IUsuarioServicio;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


/**
 * API REST Usuario
 * @version 1.1
 * */
@RestController
@RequestMapping("api/fitnesstracker")
@Api(tags = "Usuario REST API")
public class UsuarioControlador {

	final
	IUsuarioServicio _usuarioServicio;

	/**
	 * Constructor por inyección de dependencias.
	 * @param usuarioServicio servicio de usuarios
	 * */
	public UsuarioControlador(IUsuarioServicio usuarioServicio) {
		_usuarioServicio = usuarioServicio;
	}

	@PostMapping("insertar")
	@Operation(summary = "Registra un nuevo usuario",
			description = "Registra un nuevo usuario utilizando los datos del modelo pasado, retorna cierto si la operación fue exitosa.",
			responses = {
					@ApiResponse(responseCode = "200", description = "Operación exitosa", content = @Content(schema = @Schema(implementation = Boolean.class))),
					@ApiResponse(responseCode = "400", description = "El modelo de datos no es válido"),
					@ApiResponse(responseCode = "500", description = "Error interno del servidor")
			})
	public ResponseEntity<ResponseRegistraUsuario> registraUsuario(@RequestBody RequestRegistrarUsuario model) {
		ResponseRegistraUsuario data = ResponseRegistraUsuario.builder().success(false).build();
		ResponseEntity<ResponseRegistraUsuario> response;

		try {
			var resultado = _usuarioServicio.insertarUsuario(model);

			data.setSuccess(resultado);
			data.setResponseDescription("Usuario registrado con éxito");
			response = new ResponseEntity<>(data, HttpStatus.OK);
		} catch (RuntimeException re) {
			data.setSuccess(false);
			data.setResponseDescription(re.getMessage());
			response = new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			data.setResponseDescription(e.getMessage());
			response = new ResponseEntity<>(data, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return response;
	}

	@Deprecated(since = "1.1")
	@PostMapping("verificar-deprecated")
	@Operation(summary = "Verifica un usuario",
			deprecated = true,
			description = "Verifica un usuario utilizando los datos del modelo pasado, retorna la información del usuario si la verificación es exitosa.",
			responses = {
					@ApiResponse(responseCode = "200", description = "Operación exitosa", content = @Content(schema = @Schema(implementation = UsuarioInfo.class))),
					@ApiResponse(responseCode = "400", description = "El modelo de datos no es válido"),
					@ApiResponse(responseCode = "500", description = "Error interno del servidor")
			})
	public ResponseEntity<UsuarioInfo> verificar(@RequestBody UsuarioVerificar user){
		ResponseEntity<UsuarioInfo> respuesta = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		Optional<UsuarioInfo> resultado = _usuarioServicio.verificarUsuario(user);
		
		if(resultado.isPresent()) {
			respuesta = ResponseEntity.ok(resultado.get());
		}

		return respuesta;
	}

	@Deprecated(since = "1.1")
	@GetMapping("getUserInfo-deprecated/{email}")
	@Operation(summary = "Obtiene información del usuario",
			deprecated = true,
			description = "Obtiene la información del usuario basado en el correo electrónico proporcionado.",
			responses = {
					@ApiResponse(responseCode = "200", description = "Operación exitosa", content = @Content(schema = @Schema(implementation = UsuarioInfo.class))),
					@ApiResponse(responseCode = "400", description = "El modelo de datos no es válido"),
					@ApiResponse(responseCode = "500", description = "Error interno del servidor")
			})
	public ResponseEntity<UsuarioInfo> getUserInfo(@PathVariable String email){
		ResponseEntity<UsuarioInfo> respuesta = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		Optional<UsuarioInfo> resultado = _usuarioServicio.informacionUsuario(email);

		if(resultado.isPresent()) {
			respuesta = new ResponseEntity<>(resultado.get(), HttpStatus.ACCEPTED);
		}
		
		return respuesta;
	}
	
	@PutMapping("actualizar")
	@Operation(summary = "Actualiza un usuario",
			description = "Actualiza un usuario utilizando los datos del modelo pasado, retorna cierto si la operación fue exitosa.",
			responses = {
					@ApiResponse(responseCode = "200", description = "Operación exitosa", content = @Content(schema = @Schema(implementation = Boolean.class))),
					@ApiResponse(responseCode = "400", description = "El modelo de datos no es válido"),
					@ApiResponse(responseCode = "500", description = "Error interno del servidor")
			})
	public ResponseEntity<Boolean> actualizar(@RequestBody RequestRegistrarUsuario user){
		ResponseEntity<Boolean> respuesta = new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
		Boolean resultado = _usuarioServicio.actualizarUsuario(user);
		
		if(resultado) {
			respuesta = ResponseEntity.ok(true);
		}

		return respuesta;
	}
	
	@DeleteMapping("borrar")
	@Operation(summary = "Borra un usuario",
			description = "Borra un usuario utilizando los datos del modelo pasado, retorna cierto si la operación fue exitosa.",
			responses = {
					@ApiResponse(responseCode = "200", description = "Operación exitosa", content = @Content(schema = @Schema(implementation = Boolean.class))),
					@ApiResponse(responseCode = "400", description = "El modelo de datos no es válido"),
					@ApiResponse(responseCode = "500", description = "Error interno del servidor")
			})
	public ResponseEntity<Boolean> borrar(@RequestBody UsuarioVerificar user){
		ResponseEntity<Boolean> respuesta = new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
		Boolean resultado = _usuarioServicio.borrarUsuario(user);
		
		if(resultado) {
			respuesta = ResponseEntity.ok(true);
		}

		return respuesta;
	}

	@PostMapping("verify")
	@Operation(summary = "Verificar",
			description = "Verifica la existencia del usuario utilizando los datos del modelo pasado, retorna un objeto de respuesta de inicio de sesión.",
			responses = {
					@ApiResponse(responseCode = "200", description = "Operación exitosa", content = @Content(schema = @Schema(implementation = ResponseVerifyUser.class))),
					@ApiResponse(responseCode = "400", description = "El modelo de datos no es válido", content = @Content(schema = @Schema(implementation = BaseResponseBadRequest.class))),
					@ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content(schema = @Schema(implementation = ResponseVerifyUser.class)))
			})
	ResponseEntity<ResponseVerifyUser> verify(@Valid @RequestBody RequestVerify model) {
		ResponseVerifyUser responseWrapper = ResponseVerifyUser
				.builder()
				.data(null)
				.build();

		ResponseEntity<ResponseVerifyUser> response;

		try {
			Optional<ResponseVerifyData> loginResponse = _usuarioServicio.login(model);

			ResponseVerifyData body = loginResponse.orElse(null);
			String message = loginResponse.isPresent() ?
					"Credenciales válidos." :
					"La combinación de usuario y contraseña no es válida. Verifique que esté registrado.";

			responseWrapper.setupOk(message, body);
			response = new ResponseEntity<>(responseWrapper, HttpStatus.OK);
		} catch (RuntimeException re) {
			responseWrapper.setupBadRequest(re.getMessage(), Collections.emptyList());
			response = new ResponseEntity<>(responseWrapper, HttpStatus.BAD_REQUEST);
		}
		catch (Exception e) {
			responseWrapper.setupInternalError(e.getMessage(), Collections.emptyList());
			response = new ResponseEntity<>(responseWrapper, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return response;
	}

	@PutMapping("changepassword")
	@Operation(summary = "Cambia la contraseña",
			description = "Cambia la contraseña utilizando los datos del modelo pasado, retorna un objeto de respuesta de cambio de contraseña.",
			responses = {
					@ApiResponse(responseCode = "200", description = "Operación exitosa", content = @Content(schema = @Schema(implementation = ResponseCambiarPassword.class))),
					@ApiResponse(responseCode = "400", description = "El modelo de datos no es válido", content = @Content(schema = @Schema(implementation = BaseResponseBadRequest.class))),
					@ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content(schema = @Schema(implementation = ResponseCambiarPassword.class)))
			})
	ResponseEntity<ResponseCambiarPassword> cambiarPassword(@Valid @RequestBody RequestCambiarPassword model) {
		ResponseCambiarPassword responseWrapper = ResponseCambiarPassword
				.builder()
				.data(null)
				.build();

		ResponseEntity<ResponseCambiarPassword> response;

		try {
			boolean changePasswordResponse = _usuarioServicio.changePassword(model);

			String message = changePasswordResponse ?
					"Contraseña cambiada con éxito." :
					"La combinación de contraseña o email es inválida o el usuario no existe.";

			responseWrapper.setupOk(message, changePasswordResponse);
			response = new ResponseEntity<>(responseWrapper, HttpStatus.OK);

		}catch (RuntimeException re) {
			responseWrapper.setupBadRequest(re.getMessage(), Collections.emptyList());
			response = new ResponseEntity<>(responseWrapper, HttpStatus.BAD_REQUEST);

		} catch (Exception e) {
			responseWrapper.setupInternalError(e.getMessage(), Collections.emptyList());
			response = new ResponseEntity<>(responseWrapper, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return response;
	}

	@PostMapping("getuserinfo")
	@Operation(summary = "Obtiene datos del usuario",
			description = "Obtiene los datos del usuario utilizando los datos del modelo pasado, retorna un objeto de respuesta con los datos del usuario.",
			responses = {
					@ApiResponse(responseCode = "200", description = "Operación exitosa", content = @Content(schema = @Schema(implementation = ResponseGetDatosUsuario.class))),
					@ApiResponse(responseCode = "400", description = "El modelo de datos no es válido", content = @Content(schema = @Schema(implementation = BaseResponseBadRequest.class))),
					@ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content(schema = @Schema(implementation = ResponseGetDatosUsuario.class)))
			})
	ResponseEntity<ResponseGetDatosUsuario> getDatos(@Valid @RequestBody RequestGetDatosUsuario model) {
		ResponseGetDatosUsuario responseWrapper = ResponseGetDatosUsuario
				.builder()
				.data(null)
				.build();

		ResponseEntity<ResponseGetDatosUsuario> response;

		try {
			Optional<ResponseGetDatosUsuarioData> getUserInfoResponse = _usuarioServicio.consultar(model);

			ResponseGetDatosUsuarioData body = getUserInfoResponse.orElse(null);
			String message = getUserInfoResponse.isPresent() ?
					"OK." :
					"El usuario no existe, asegúrese de haberse registrado previamente por favor.";

			responseWrapper.setupOk(message, body);
			response = new ResponseEntity<>(responseWrapper, HttpStatus.OK);
		} catch (RuntimeException re) {
			responseWrapper.setupBadRequest(re.getMessage(), Collections.emptyList());
			response = new ResponseEntity<>(responseWrapper, HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			responseWrapper.setupInternalError(e.getMessage(), Collections.emptyList());
			response = new ResponseEntity<>(responseWrapper, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return response;
	}

	@PutMapping("modificardatosusuario")
	@Operation(summary = "Modifica datos del usuario",
			description = "Modifica los datos del usuario utilizando los datos del modelo pasado, retorna un objeto de respuesta con los datos modificados del usuario.",
			responses = {
					@ApiResponse(responseCode = "200", description = "Operación exitosa", content = @Content(schema = @Schema(implementation = ResponseModificarDatosUsuario.class))),
					@ApiResponse(responseCode = "400", description = "El modelo de datos no es válido"),
					@ApiResponse(responseCode = "500", description = "Error interno del servidor")
			})
	ResponseEntity<ResponseModificarDatosUsuario> modificarUsuario(@RequestBody RequestModificarDatosUsuario model) {
		ResponseModificarDatosUsuario responseData = ResponseModificarDatosUsuario.builder().build();
		ResponseEntity<ResponseModificarDatosUsuario> response;

		try {
			Boolean result = _usuarioServicio.modificar(model);
			responseData.setSuccess(true);

			if (result) {
				responseData.setModifiedAt(LocalDateTime.now());
				responseData.setResponseDescription("Usuario modificado con éxito");

				response = new ResponseEntity<>(responseData, HttpStatus.OK);
			} else {
				responseData.setSuccess(false);
				responseData.setResponseDescription("El usuario no existe.");
				response = new ResponseEntity<>(responseData, HttpStatus.OK);
			}
		}catch (RuntimeException e) {
			responseData.setSuccess(false);
			responseData.setResponseDescription(e.getMessage());
			response = new ResponseEntity<>(responseData, HttpStatus.OK);
		} catch (Exception e) {
			responseData.setSuccess(false);
			responseData.setResponseDescription(e.getMessage());
			response = new ResponseEntity<>(responseData, HttpStatus.INTERNAL_SERVER_ERROR);
		}


		return response;
	}

	@PostMapping("registrardieta")
	@Operation(summary = "Registra una nueva dieta",
			description = "Registra una nueva dieta utilizando los datos del modelo pasado, retorna un objeto de respuesta con los detalles de la dieta registrada.",
			responses = {
					@ApiResponse(responseCode = "200", description = "Operación exitosa", content = @Content(schema = @Schema(implementation = ResponseRegistrarDieta.class))),
					@ApiResponse(responseCode = "400", description = "El modelo de datos no es válido"),
					@ApiResponse(responseCode = "500", description = "Error interno del servidor")
			})
	ResponseEntity<ResponseRegistrarDieta> registrarDieta(@RequestBody RequestRegistrarDieta model) {
		ResponseRegistrarDieta responseData = ResponseRegistrarDieta.builder().build();
		ResponseEntity<ResponseRegistrarDieta> response;

		try {
			responseData = _usuarioServicio.registrarDieta(model);
			response = new ResponseEntity<>(responseData, HttpStatus.OK);
		} catch (RuntimeException except) {
			responseData.setSuccess(false);
			responseData.setResponseDescription(except.getMessage());
			response = new ResponseEntity<>(responseData, HttpStatus.OK);
		}catch (Exception e) {
			responseData.setSuccess(false);
			responseData.setResponseDescription(e.getMessage());
			response = new ResponseEntity<>(responseData, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return response;
	}

	@PutMapping("modificardieta")
	@Operation(summary = "Modifica una dieta",
			description = "Modifica una dieta utilizando los datos del modelo pasado, retorna un objeto de respuesta con los detalles de la dieta modificada.",
			responses = {
					@ApiResponse(responseCode = "200", description = "Operación exitosa", content = @Content(schema = @Schema(implementation = ResponseModificarDieta.class))),
					@ApiResponse(responseCode = "400", description = "El modelo de datos no es válido"),
					@ApiResponse(responseCode = "500", description = "Error interno del servidor")
			})
	ResponseEntity<ResponseModificarDieta> modificarDieta(@RequestBody RequestModificarDieta model) {
		ResponseModificarDieta responseData = ResponseModificarDieta.builder().build();
		ResponseEntity<ResponseModificarDieta> response;

		try {
			Boolean result = _usuarioServicio.modificar(model);
			responseData.setSuccess(true);

			if (result) {
				responseData.setModifiedAt(LocalDateTime.now());
				responseData.setResponseDescription("Dieta modificada con éxito");

			} else {
				responseData.setSuccess(false);
				responseData.setResponseDescription("No se pudo modificar la dieta. Esta es inválida o el usuario no existe.");
			}

			response = new ResponseEntity<>(responseData, HttpStatus.OK);
		} catch (RuntimeException except) {
			responseData.setSuccess(false);
			responseData.setResponseDescription(except.getMessage());
			response = new ResponseEntity<>(responseData, HttpStatus.OK);
		} catch (Exception e) {
			responseData.setSuccess(false);
			responseData.setResponseDescription(e.getMessage());
			response = new ResponseEntity<>(responseData, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return response;
	}

	@PostMapping("getdietausuario")
	@Operation(summary = "Obtiene una dieta del usuario",
			description = "Obtiene los detalles de una dieta del usuario utilizando los datos del modelo pasado, retorna un objeto de respuesta con los detalles de la dieta.",
			responses = {
					@ApiResponse(responseCode = "200", description = "Operación exitosa.", content = @Content(schema = @Schema(implementation = ResponseGetDietaUsuario.class))),
					@ApiResponse(responseCode = "400", description = "El modelo de datos no es válido."),
					@ApiResponse(responseCode = "500", description = "Error interno del servidor.")
			})
	ResponseEntity<ResponseGetDietaUsuario> getDieta(@RequestBody RequestGetDietaUsuario model) {
		ResponseGetDietaUsuario responseData = ResponseGetDietaUsuario.builder().data(null).build();
		ResponseEntity<ResponseGetDietaUsuario> response;

		try {
			Optional<ResponseGetDietaUsuarioData> result = _usuarioServicio.getDieta(model);
			responseData.setSuccess(true);

			if (result.isPresent()) {
				responseData.setData(result.get());
				responseData.setResponseDescription("Dieta localizada con éxito");

				response = new ResponseEntity<>(responseData, HttpStatus.OK);
			} else {
				responseData.setSuccess(false);
				responseData.setResponseDescription("No se pudo localizar la dieta o el usuario no existe.");
				response = new ResponseEntity<>(responseData, HttpStatus.OK);
			}
		} catch (Exception e) {
			responseData.setSuccess(false);
			responseData.setResponseDescription(e.getMessage());
			response = new ResponseEntity<>(responseData, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return response;
	}

	@PostMapping("getlistdietasusuario")
	@Operation(summary = "Obtiene la lista de dietas del usuario",
			description = "Obtiene la lista de dietas del usuario utilizando los datos del modelo pasado, retorna un objeto de respuesta con la lista de dietas.",
			responses = {
					@ApiResponse(responseCode = "200", description = "Operación exitosa", content = @Content(schema = @Schema(implementation = ResponseGetListDietas.class))),
					@ApiResponse(responseCode = "400", description = "El modelo de datos no es válido"),
					@ApiResponse(responseCode = "500", description = "Error interno del servidor")
			})
	ResponseEntity<ResponseGetListDietas> getListDietas(@RequestBody RequestGetListDietas model) {
		ResponseGetListDietas responseData = ResponseGetListDietas.builder().dietas(null).build();
		ResponseEntity<ResponseGetListDietas> response;

		try {
			List<ResponseGetDietaUsuarioData> result = _usuarioServicio.getListDietas(model);
			responseData.setSuccess(true);
			responseData.setDietas(result);

			response = new ResponseEntity<>(responseData, HttpStatus.OK);
			responseData.setResponseDescription("Dietas localizadas con éxito");

		} catch (RuntimeException usuarioNotFound) {
			responseData.setSuccess(false);
			responseData.setResponseDescription(usuarioNotFound.getMessage());
			response = new ResponseEntity<>(responseData, HttpStatus.OK);
		} catch (Exception e) {
			responseData.setSuccess(false);
			responseData.setResponseDescription(e.getMessage());
			response = new ResponseEntity<>(responseData, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return response;
	}

	@PostMapping("registrarrutina")
	@Operation(summary = "Registra una nueva rutina",
			description = "Registra una nueva rutina utilizando los datos del modelo pasado, retorna un objeto de respuesta con los detalles de la rutina registrada.",
			responses = {
					@ApiResponse(responseCode = "200", description = "Operación exitosa", content = @Content(schema = @Schema(implementation = ResponseRegistrarRutina.class))),
					@ApiResponse(responseCode = "400", description = "El modelo de datos no es válido"),
					@ApiResponse(responseCode = "500", description = "Error interno del servidor")
			})
	ResponseEntity<ResponseRegistrarRutina> registrarRutina(@RequestBody RequestRegistrarRutina model) {
		ResponseRegistrarRutina responseData = ResponseRegistrarRutina.builder().build();
		ResponseEntity<ResponseRegistrarRutina> response;

		try {
			responseData = _usuarioServicio.registrarRutina(model);
			response = new ResponseEntity<>(responseData, HttpStatus.OK);
		} catch (Exception e) {
			responseData.setSuccess(false);
			responseData.setResponseDescription(e.getMessage());
			response = new ResponseEntity<>(responseData, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return response;
	}

	@PutMapping("modificarrutina")
	@Operation(summary = "Modifica una rutina",
			description = "Modifica una rutina utilizando los datos del modelo pasado, retorna un objeto de respuesta con los detalles de la rutina modificada.",
			responses = {
					@ApiResponse(responseCode = "200", description = "Operación exitosa", content = @Content(schema = @Schema(implementation = ResponseModificarRutina.class))),
					@ApiResponse(responseCode = "400", description = "El modelo de datos no es válido"),
					@ApiResponse(responseCode = "500", description = "Error interno del servidor")
			})
	ResponseEntity<ResponseModificarRutina> modificarRutina(@RequestBody RequestModificarRutina model) {
		ResponseModificarRutina responseData = ResponseModificarRutina.builder().build();
		ResponseEntity<ResponseModificarRutina> response;

		try {
			Boolean result = _usuarioServicio.modificarRutina(model);
			responseData.setSuccess(true);

			if (result) {
				responseData.setModifiedAt(LocalDateTime.now());
				responseData.setResponseDescription("Rutina modificada con éxito");

			} else {
				responseData.setSuccess(false);
				responseData.setResponseDescription("No se pudo modificar la rutina. Esta es inválida o el usuario no existe.");
			}

			response = new ResponseEntity<>(responseData, HttpStatus.OK);
		} catch (Exception e) {
			responseData.setSuccess(false);
			responseData.setResponseDescription(e.getMessage());
			response = new ResponseEntity<>(responseData, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return response;
	}

	@PostMapping("getrutina")
	@Operation(summary = "Obtiene una rutina",
			description = "Obtiene los detalles de una rutina utilizando los datos del modelo pasado, retorna un objeto de respuesta con los detalles de la rutina.",
			responses = {
					@ApiResponse(responseCode = "200", description = "Operación exitosa", content = @Content(schema = @Schema(implementation = ResponseGetRutina.class))),
					@ApiResponse(responseCode = "400", description = "El modelo de datos no es válido"),
					@ApiResponse(responseCode = "500", description = "Error interno del servidor")
			})
	ResponseEntity<ResponseGetRutina> getRutina(@RequestBody RequestGetRutina model) {
		ResponseGetRutina responseData = ResponseGetRutina.builder().data(null).build();
		ResponseEntity<ResponseGetRutina> response;

		try {
			Optional<ResponseGetRutinaData> result = _usuarioServicio.getRutina(model);
			responseData.setSuccess(true);

			if (result.isPresent()) {
				responseData.setData(result.get());
				responseData.setResponseDescription("Rutina localizada con éxito");

			} else {
				responseData.setSuccess(false);
				responseData.setResponseDescription("No se pudo localizar la rutina o el usuario no existe.");
			}

			response = new ResponseEntity<>(responseData, HttpStatus.OK);
		} catch (Exception e) {
			responseData.setSuccess(false);
			responseData.setResponseDescription(e.getMessage());
			response = new ResponseEntity<>(responseData, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return response;
	}

	@PostMapping("getlistrutinas")
	@Operation(summary = "Obtiene la lista de rutinas",
			description = "Obtiene la lista de rutinas utilizando los datos del modelo pasado, retorna un objeto de respuesta con la lista de rutinas.",
			responses = {
			@ApiResponse(responseCode = "200", description = "Operación exitosa", content = @Content(schema = @Schema(implementation = ResponseGetListRutinas.class))),
			@ApiResponse(responseCode = "400", description = "El modelo de datos no es válido"),
			@ApiResponse(responseCode = "500", description = "Error interno del servidor")
	})
	ResponseEntity<ResponseGetListRutinas> getListRutinas(@RequestBody RequestGetListRutinas model) {
		ResponseGetListRutinas responseData = ResponseGetListRutinas.builder().data(null).build();
		ResponseEntity<ResponseGetListRutinas> response;

		try {
			List<ResponseGetRutinaData> result = _usuarioServicio.getListRutinas(model);
			responseData.setSuccess(true);
			responseData.setRutinas(result);
			responseData.setResponseDescription("Rutinas localizadas con éxito");

			response = new ResponseEntity<>(responseData, HttpStatus.OK);
		} catch (RuntimeException usuarioNotFound) {
			responseData.setSuccess(false);
			responseData.setResponseDescription(usuarioNotFound.getMessage());
			response = new ResponseEntity<>(responseData, HttpStatus.OK);

		} catch (Exception e) {
			responseData.setSuccess(false);
			responseData.setResponseDescription(e.getMessage());
			response = new ResponseEntity<>(responseData, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return response;
	}

	@PostMapping("getalimentos")
	@Operation(summary = "Obtiene la lista de alimentos",
			description = "Obtiene la lista de alimentos utilizando los datos del modelo pasado, retorna un objeto de respuesta con la lista de alimentos.",
			responses = {
					@ApiResponse(responseCode = "200", description = "Operación exitosa", content = @Content(schema = @Schema(implementation = UsuarioInfo.class))),
					@ApiResponse(responseCode = "400", description = "El modelo de datos no es válido"),
					@ApiResponse(responseCode = "500", description = "Error interno del servidor")
			})
	ResponseEntity<ResponseGetAlimentos> getAlimentos(@RequestBody RequestGetAlimentos model) {
		ResponseGetAlimentos responseData = ResponseGetAlimentos.builder().data(null).build();
		ResponseEntity<ResponseGetAlimentos> response;

		try {
			responseData = _usuarioServicio.getListAlimentos(model);
			response = new ResponseEntity<>(responseData, HttpStatus.OK);

		} catch (Exception e) {
			responseData.setSuccess(false);
			responseData.setResponseDescription(e.getMessage());
			response = new ResponseEntity<>(responseData, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return response;
	}
}
