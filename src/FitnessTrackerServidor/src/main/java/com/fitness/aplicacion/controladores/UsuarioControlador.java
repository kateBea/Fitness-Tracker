package com.fitness.aplicacion.controladores;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.fitness.aplicacion.dto.*;
import com.fitness.aplicacion.servicio.IUsuarioServicio;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
import static com.fitness.aplicacion.dto.ResponseGetDatosUsuario.ResponseGetDatosUsuarioData;
import static com.fitness.aplicacion.dto.ResponseLogin.ResponseLoginData;

/**
 * API REST Usuario
 *
 * @version 1.0
 * */
@RestController
@RequestMapping("api/fitnesstracker")
@Api(tags = "Usuario REST API")
public class UsuarioControlador {

	@Autowired
	@Qualifier("usuarioServicioImpl")
	IUsuarioServicio usuarioServicio;
	
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
			var resultado = usuarioServicio.insertarUsuario(model);

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
	
	@PostMapping("verificar")
	@Operation(summary = "Verifica un usuario",
			description = "Verifica un usuario utilizando los datos del modelo pasado, retorna la información del usuario si la verificación es exitosa.",
			responses = {
					@ApiResponse(responseCode = "200", description = "Operación exitosa", content = @Content(schema = @Schema(implementation = UsuarioInfo.class))),
					@ApiResponse(responseCode = "400", description = "El modelo de datos no es válido"),
					@ApiResponse(responseCode = "500", description = "Error interno del servidor")
			})
	public ResponseEntity<UsuarioInfo> verificar(@RequestBody UsuarioVerificar user){
		ResponseEntity<UsuarioInfo> respuesta = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		Optional<UsuarioInfo> resultado = usuarioServicio.verificarUsuario(user);
		
		if(resultado.isPresent()) {
			respuesta = ResponseEntity.ok(resultado.get());
		}

		return respuesta;
	}
	
	// Petición para obtener información de un usuario por su correo electrónico
	@GetMapping("info/{email}")
	@Operation(summary = "Obtiene información del usuario",
			description = "Obtiene la información del usuario basado en el correo electrónico proporcionado.",
			responses = {
					@ApiResponse(responseCode = "200", description = "Operación exitosa", content = @Content(schema = @Schema(implementation = UsuarioInfo.class))),
					@ApiResponse(responseCode = "400", description = "El modelo de datos no es válido"),
					@ApiResponse(responseCode = "500", description = "Error interno del servidor")
			})
	public ResponseEntity<UsuarioInfo> info(@PathVariable String email){
		ResponseEntity<UsuarioInfo> respuesta = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		Optional<UsuarioInfo> resultado = usuarioServicio.informacionUsuario(email);

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
		Boolean resultado = usuarioServicio.actualizarUsuario(user);
		
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
		Boolean resultado = usuarioServicio.borrarUsuario(user);
		
		if(resultado) {
			respuesta = ResponseEntity.ok(true);
		}

		return respuesta;
	}

	@PostMapping("login")
	@Operation(summary = "Inicia sesión",
			description = "Inicia sesión utilizando los datos del modelo pasado, retorna un objeto de respuesta de inicio de sesión.",
			responses = {
					@ApiResponse(responseCode = "200", description = "Operación exitosa", content = @Content(schema = @Schema(implementation = ResponseLogin.class))),
					@ApiResponse(responseCode = "400", description = "El modelo de datos no es válido"),
					@ApiResponse(responseCode = "500", description = "Error interno del servidor")
			})
	ResponseEntity<ResponseLogin> login(@RequestBody RequestLogin model) {
		ResponseLogin responseWrapper = ResponseLogin.builder()
				.data(null)
				.build();

		ResponseLoginData responseData;
		ResponseEntity<ResponseLogin> response;

		try {
			responseData = usuarioServicio.login(model);

			responseWrapper.setData(responseData);
			responseWrapper.setSuccess(true);
			responseWrapper.setResponseDescription("Credenciales válidos.");

			response = new ResponseEntity<>(responseWrapper, HttpStatus.OK);
		} catch (RuntimeException re) {
			responseWrapper.setSuccess(false);
			responseWrapper.setResponseDescription(re.getMessage());

			response = new ResponseEntity<>(responseWrapper, HttpStatus.BAD_REQUEST);
		}
		catch (Exception e) {
			responseWrapper.setSuccess(false);
			responseWrapper.setResponseDescription(e.getMessage());

			response = new ResponseEntity<>(responseWrapper, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return response;
	}

	@PutMapping("cambiarpassword")
	@Operation(summary = "Cambia la contraseña",
			description = "Cambia la contraseña utilizando los datos del modelo pasado, retorna un objeto de respuesta de cambio de contraseña.",
			responses = {
					@ApiResponse(responseCode = "200", description = "Operación exitosa", content = @Content(schema = @Schema(implementation = ResponseCambiarPassword.class))),
					@ApiResponse(responseCode = "400", description = "El modelo de datos no es válido"),
					@ApiResponse(responseCode = "500", description = "Error interno del servidor")
			})
	ResponseEntity<ResponseCambiarPassword> cambiarPassword(@RequestBody RequestCambiarPassword model) {
		ResponseCambiarPassword data = ResponseCambiarPassword.builder().build();
		ResponseEntity<ResponseCambiarPassword> response;

		try {
			var result = usuarioServicio.cambiarPassword(model);
			data = ResponseCambiarPassword
					.builder()
					.success(result)
					.responseDescription(result ? "Contraseña cambiada con éxito" : "Contraseña o email inválidos")
					.changeDate(LocalDateTime.now())
					.build();

			response = new ResponseEntity<>(data, HttpStatus.OK);

		}catch (RuntimeException re) {
			data.setSuccess(false);
			data.setResponseDescription(re.getMessage());
			response = new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);

		} catch (Exception e) {
			data.setSuccess(false);
			data.setResponseDescription(e.getMessage());
			response = new ResponseEntity<>(data, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return response;
	}

	@PostMapping("getdatosusuario")
	@Operation(summary = "Obtiene datos del usuario",
			description = "Obtiene los datos del usuario utilizando los datos del modelo pasado, retorna un objeto de respuesta con los datos del usuario.",
			responses = {
					@ApiResponse(responseCode = "200", description = "Operación exitosa", content = @Content(schema = @Schema(implementation = ResponseGetDatosUsuario.class))),
					@ApiResponse(responseCode = "400", description = "El modelo de datos no es válido"),
					@ApiResponse(responseCode = "500", description = "Error interno del servidor")
			})
	ResponseEntity<ResponseGetDatosUsuario> getDatos(@RequestBody RequestGetDatosUsuario model) {
		ResponseGetDatosUsuario responseData = ResponseGetDatosUsuario.builder().data(null).build();
		ResponseEntity<ResponseGetDatosUsuario> response;

		try {
			Optional<ResponseGetDatosUsuarioData> result = usuarioServicio.consultar(model);
			responseData.setSuccess(true);

			if (result.isPresent()) {
				responseData.setData(result.get());
				responseData.setResponseDescription("Usuario localizado");

				response = new ResponseEntity<>(responseData, HttpStatus.OK);
			} else {
				responseData.setSuccess(false);
				responseData.setResponseDescription("El usuario no existe.");
				response = new ResponseEntity<>(responseData, HttpStatus.OK);
			}
		} catch (Exception e) {
			responseData.setSuccess(false);
			responseData.setResponseDescription(e.getMessage());
			response = new ResponseEntity<>(responseData, HttpStatus.INTERNAL_SERVER_ERROR);
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
			Boolean result = usuarioServicio.modificar(model);
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
			responseData = usuarioServicio.registrarDieta(model);
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
			Boolean result = usuarioServicio.modificar(model);
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
					@ApiResponse(responseCode = "200", description = "Operación exitosa", content = @Content(schema = @Schema(implementation = ResponseGetDietaUsuario.class))),
					@ApiResponse(responseCode = "400", description = "El modelo de datos no es válido"),
					@ApiResponse(responseCode = "500", description = "Error interno del servidor")
			})
	ResponseEntity<ResponseGetDietaUsuario> getDieta(@RequestBody RequestGetDietaUsuario model) {
		ResponseGetDietaUsuario responseData = ResponseGetDietaUsuario.builder().data(null).build();
		ResponseEntity<ResponseGetDietaUsuario> response;

		try {
			Optional<ResponseGetDietaUsuario.ResponseGetDietaUsuarioData> result = usuarioServicio.getDieta(model);
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
			List<ResponseGetDietaUsuario.ResponseGetDietaUsuarioData> result = usuarioServicio.getListDietas(model);
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
			responseData = usuarioServicio.registrarRutina(model);
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
			Boolean result = usuarioServicio.modificarRutina(model);
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
			Optional<ResponseGetRutina.ResponseGetRutinaData> result = usuarioServicio.getRutina(model);
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
			List<ResponseGetRutina.ResponseGetRutinaData> result = usuarioServicio.getListRutinas(model);
			responseData.setSuccess(true);
			responseData.setData(result);
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
			responseData = usuarioServicio.getListAlimentos(model);
			response = new ResponseEntity<>(responseData, HttpStatus.OK);

		} catch (Exception e) {
			responseData.setSuccess(false);
			responseData.setResponseDescription(e.getMessage());
			response = new ResponseEntity<>(responseData, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return response;
	}
}
