package com.fitness.aplicacion.controladores;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.fitness.aplicacion.dto.*;
import com.fitness.aplicacion.servicio.IUsuarioServicio;
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

@RestController
@RequestMapping("api/fitnesstracker")
public class UsuarioControlador {

	@Autowired // Inyección de dependencia del servicio de usuario
	@Qualifier("usuarioServicioImpl")
	IUsuarioServicio usuarioServicio;
	
	// Peticion para insertar un nuevo usuario
	@PostMapping("insertar")
	public ResponseEntity<Boolean> insertar(@RequestBody UsuarioInsertar user){
		// Respuesta por defecto: error de solicitud
		ResponseEntity<Boolean> respuesta = new ResponseEntity<>(false,HttpStatus.BAD_REQUEST);
		// Llamada al método del servicio para insertar un usuario
		Boolean resultado = usuarioServicio.insertarUsuario(user);

		// Si la inserción fue exitosa, cambia la respuesta a OK
		if(resultado)
			respuesta = ResponseEntity.ok(true);
		
		return respuesta;
	}
	
	// Peticion para verificar las credenciales de inicio de sesión
	@PostMapping("verificar")
	public ResponseEntity<UsuarioInfo> verificar(@RequestBody UsuarioVerificar user){
		// Respuesta por defecto: error de solicitud
		ResponseEntity<UsuarioInfo> respuesta = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		// Llamada al método del servicio para verificar las credenciales del usuario
		Optional<UsuarioInfo> resultado = usuarioServicio.verificarUsuario(user);
		
		// Si la verificación fue exitosa, cambia la respuesta a OK
		if(resultado.isPresent())
			respuesta = ResponseEntity.ok(resultado.get());

		return respuesta;
	}
	
	// Peticion para obtener información de un usuario por su correo electrónico
	@GetMapping("info/{email}")
	public ResponseEntity<UsuarioInfo> info(@PathVariable String email){
		// Respuesta por defecto: error de solicitud
		ResponseEntity<UsuarioInfo> respuesta = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		// Llamada al método del servicio para obtener información del usuario
		Optional<UsuarioInfo> resultado = usuarioServicio.informacionUsuario(email);

		// Si se encuentra la información del usuario, cambia la respuesta a Accepted
		if(resultado.isPresent())
			respuesta = new ResponseEntity<>(resultado.get(), HttpStatus.ACCEPTED);
		
		return respuesta;
	}
	
	@PutMapping("actualizar")
	public ResponseEntity<Boolean> actualizar(@RequestBody UsuarioInsertar user){
		// Respuesta por defecto: error de solicitud
		ResponseEntity<Boolean> respuesta = new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
		// Llamada al método del servicio para actualizar el usuario
		Boolean resultado = usuarioServicio.actualizarUsuario(user);
		
		// Si la verificación fue exitosa, cambia la respuesta a OK
		if(resultado)
			respuesta = ResponseEntity.ok(true);

		return respuesta;
	}
	
	@DeleteMapping("borrar")
	public ResponseEntity<Boolean> borrar(@RequestBody UsuarioVerificar user){
		// Respuesta por defecto: error de solicitud
		ResponseEntity<Boolean> respuesta = new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
		// Llamada al método del servicio para borrar el usuario
		Boolean resultado = usuarioServicio.borrarUsuario(user);
		
		// Si la verificación fue exitosa, cambia la respuesta a OK
		if(resultado)
			respuesta = ResponseEntity.ok(true);

		return respuesta;
	}

	@PostMapping("login")
	ResponseEntity<ResponseLogin> login(@RequestBody RequestLogin model) {
		ResponseLogin responseData = ResponseLogin.builder().build();
		ResponseEntity<ResponseLogin> response;

		try {
			responseData = usuarioServicio.login(model);
			response = new ResponseEntity<>(responseData, responseData.isSuccess() ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			responseData.setSuccess(false);
			responseData.setResponseDescription(e.getMessage());
			response = new ResponseEntity<>(responseData, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return response;
	}

	@PutMapping("cambiarpassword")
	ResponseEntity<ResponseCambiarPassword> cambiarPassword(@RequestBody RequestCambiarPassword model) {
		ResponseCambiarPassword data;
		ResponseEntity<ResponseCambiarPassword> response;

		var result = usuarioServicio.cambiarPassword(model);
		data = ResponseCambiarPassword
				.builder()
				.success(result)
				.responseDescription(result ? "Contraseña cambiada con éxito" : "Contraseña o usuario inválidos")
				.changeDate(LocalDateTime.now())
				.build();

		response = new ResponseEntity<>(data, result ? HttpStatus.OK : HttpStatus.BAD_REQUEST);

		return response;
	}

	@PostMapping("getdatosusuario")
	ResponseEntity<ResponseGetDatosUsuario> getDatos(@RequestBody RequestGetDatosUsuario model) {
		ResponseGetDatosUsuario responseData = ResponseGetDatosUsuario.builder().data(null).build();
		ResponseEntity<ResponseGetDatosUsuario> response;

		try {
			Optional<ResponseGetDatosUsuarioData> result = usuarioServicio.consultar(model);
			responseData.setSuccess(true);

			if (result.isPresent()) {
				responseData.setData(result.get());
				responseData.setResponseDescription("Usuario localizado");

				response = new ResponseEntity<>(responseData, HttpStatus.FOUND);
			} else {
				responseData.setSuccess(false);
				responseData.setResponseDescription("El usuario no existe.");
				response = new ResponseEntity<>(responseData, HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			responseData.setSuccess(false);
			responseData.setResponseDescription(e.getMessage());
			response = new ResponseEntity<>(responseData, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return response;
	}

	@PutMapping("modificardatosusuario")
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
				response = new ResponseEntity<>(responseData, HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			responseData.setSuccess(false);
			responseData.setResponseDescription(e.getMessage());
			response = new ResponseEntity<>(responseData, HttpStatus.INTERNAL_SERVER_ERROR);
		}


		return response;
	}

	@PostMapping("registrardieta")
	ResponseEntity<ResponseRegistrarDieta> registrarDieta(@RequestBody RequestRegistrarDieta model) {
		ResponseRegistrarDieta responseData = ResponseRegistrarDieta.builder().build();
		ResponseEntity<ResponseRegistrarDieta> response;

		try {
			Boolean result = usuarioServicio.registrarDieta(model);
			responseData.setSuccess(true);

			if (result) {
				responseData.setCreatedAt(LocalDateTime.now());
				responseData.setResponseDescription("Dieta registrada con éxito");

				response = new ResponseEntity<>(responseData, HttpStatus.OK);
			} else {
				responseData.setSuccess(false);
				responseData.setResponseDescription("No se pudo registrar la dieta. Esta es inválida o el usuario no existe.");
				response = new ResponseEntity<>(responseData, HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			responseData.setSuccess(false);
			responseData.setResponseDescription(e.getMessage());
			response = new ResponseEntity<>(responseData, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return response;
	}

	@PutMapping("modificardieta")
	ResponseEntity<ResponseModificarDieta> modificarDieta(@RequestBody RequestModificarDieta model) {
		ResponseModificarDieta responseData = ResponseModificarDieta.builder().build();
		ResponseEntity<ResponseModificarDieta> response;

		try {
			Boolean result = usuarioServicio.modificar(model);
			responseData.setSuccess(true);

			if (result) {
				responseData.setModifiedAt(LocalDateTime.now());
				responseData.setResponseDescription("Dieta modificada con éxito");

				response = new ResponseEntity<>(responseData, HttpStatus.OK);
			} else {
				responseData.setSuccess(false);
				responseData.setResponseDescription("No se pudo registrar la dieta. Esta es inválida o el usuario no existe.");
				response = new ResponseEntity<>(responseData, HttpStatus.BAD_REQUEST);
			}
		} catch (RuntimeException except) {
			responseData.setSuccess(false);
			responseData.setResponseDescription(except.getMessage());
			response = new ResponseEntity<>(responseData, HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			responseData.setSuccess(false);
			responseData.setResponseDescription(e.getMessage());
			response = new ResponseEntity<>(responseData, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return response;
	}

	@PostMapping("getdietausuario")
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
				response = new ResponseEntity<>(responseData, HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			responseData.setSuccess(false);
			responseData.setResponseDescription(e.getMessage());
			response = new ResponseEntity<>(responseData, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return response;
	}

	@PostMapping("getlistdietasusuario")
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
			response = new ResponseEntity<>(responseData, HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			responseData.setSuccess(false);
			responseData.setResponseDescription(e.getMessage());
			response = new ResponseEntity<>(responseData, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return response;
	}

	@PostMapping("registrarrutina")
	ResponseEntity<ResponseRegistrarRutina> modificarDieta(@RequestBody RequestRegistrarRutina model) {
		ResponseRegistrarRutina responseData = ResponseRegistrarRutina.builder().build();
		ResponseEntity<ResponseRegistrarRutina> response;

		try {
			Boolean result = usuarioServicio.registrarRutina(model);
			responseData.setSuccess(true);

			if (result) {
				responseData.setCreatedAt(LocalDateTime.now());
				responseData.setResponseDescription("Rutina registrada con éxito");

				response = new ResponseEntity<>(responseData, HttpStatus.OK);
			} else {
				responseData.setSuccess(false);
				responseData.setResponseDescription("No se pudo registrar la rutina. Esta es inválida o el usuario no existe.");
				response = new ResponseEntity<>(responseData, HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			responseData.setSuccess(false);
			responseData.setResponseDescription(e.getMessage());
			response = new ResponseEntity<>(responseData, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return response;
	}

	@PutMapping("modificarrutina")
	ResponseEntity<ResponseModificarRutina> modificarRutina(@RequestBody RequestModificarRutina model) {
		ResponseModificarRutina responseData = ResponseModificarRutina.builder().build();
		ResponseEntity<ResponseModificarRutina> response;

		try {
			Boolean result = usuarioServicio.modificarRutina(model);
			responseData.setSuccess(true);

			if (result) {
				responseData.setModifiedAt(LocalDateTime.now());
				responseData.setResponseDescription("Rutina modificada con éxito");

				response = new ResponseEntity<>(responseData, HttpStatus.OK);
			} else {
				responseData.setSuccess(false);
				responseData.setResponseDescription("No se pudo modificar la rutina. Esta es inválida o el usuario no existe.");
				response = new ResponseEntity<>(responseData, HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			responseData.setSuccess(false);
			responseData.setResponseDescription(e.getMessage());
			response = new ResponseEntity<>(responseData, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return response;
	}

	@PostMapping("getrutina")
	ResponseEntity<ResponseGetRutina> getRutina(@RequestBody RequestGetRutina model) {
		ResponseGetRutina responseData = ResponseGetRutina.builder().data(null).build();
		ResponseEntity<ResponseGetRutina> response;

		try {
			Optional<ResponseGetRutina.ResponseGetRutinaData> result = usuarioServicio.getRutina(model);
			responseData.setSuccess(true);

			if (result.isPresent()) {
				responseData.setData(result.get());
				responseData.setResponseDescription("Rutina localizada con éxito");

				response = new ResponseEntity<>(responseData, HttpStatus.OK);
			} else {
				responseData.setSuccess(false);
				responseData.setResponseDescription("No se pudo localizar la rutina o el usuario no existe.");
				response = new ResponseEntity<>(responseData, HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			responseData.setSuccess(false);
			responseData.setResponseDescription(e.getMessage());
			response = new ResponseEntity<>(responseData, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return response;
	}

	@PostMapping("getlistrutinas")
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
			response = new ResponseEntity<>(responseData, HttpStatus.NOT_FOUND);

		} catch (Exception e) {
			responseData.setSuccess(false);
			responseData.setResponseDescription(e.getMessage());
			response = new ResponseEntity<>(responseData, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return response;
	}
}
