package com.fitness.aplicacion.controladores;

import java.util.Optional;

import com.fitness.aplicacion.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
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

import com.fitness.aplicacion.servicio.UsuarioServicioImpl;

@RestController
@RequestMapping("Usuarios")
public class UsuarioControlador {
	
	@Autowired // Inyección de dependencia del servicio de usuario
	UsuarioServicioImpl DAOS;
	
	// Peticion para insertar un nuevo usuario
	@PostMapping("insertar")
	public ResponseEntity<Boolean> insertar(@RequestBody UsuarioInsertar user){
		// Respuesta por defecto: error de solicitud
		ResponseEntity<Boolean> respuesta = new ResponseEntity<Boolean>(false,HttpStatus.BAD_REQUEST);
		// Llamada al método del servicio para insertar un usuario
		Boolean resultado = DAOS.insertarUsuario(user);

		// Si la inserción fue exitosa, cambia la respuesta a OK
		if(resultado)
			respuesta = ResponseEntity.ok(true);
		
		return respuesta;
	}
	
	// Peticion para verificar las credenciales de inicio de sesión
	@PostMapping("verificar")
	public ResponseEntity<UsuarioInfo> verificar(@RequestBody UsuarioVerificar user){
		// Respuesta por defecto: error de solicitud
		ResponseEntity<UsuarioInfo> respuesta = new ResponseEntity<UsuarioInfo>(HttpStatus.BAD_REQUEST);
		// Llamada al método del servicio para verificar las credenciales del usuario
		Optional<UsuarioInfo> resultado = DAOS.verificarUsuario(user);
		
		// Si la verificación fue exitosa, cambia la respuesta a OK
		if(resultado.isPresent())
			respuesta = ResponseEntity.ok(resultado.get());

		return respuesta;
	}
	
	// Peticion para obtener información de un usuario por su correo electrónico
	@GetMapping("info/{email}")
	public ResponseEntity<UsuarioInfo> info(@PathVariable String email){
		// Respuesta por defecto: error de solicitud
		ResponseEntity<UsuarioInfo> respuesta = new ResponseEntity<UsuarioInfo>(HttpStatus.BAD_REQUEST);
		// Llamada al método del servicio para obtener información del usuario
		Optional<UsuarioInfo> resultado = DAOS.informacionUsuario(email);

		// Si se encuentra la información del usuario, cambia la respuesta a Accepted
		if(resultado.isPresent())
			respuesta = new ResponseEntity<UsuarioInfo>(resultado.get(),HttpStatus.ACCEPTED);
		
		return respuesta;
	}
	
	@PutMapping("actualizar")
	public ResponseEntity<Boolean> actualizar(@RequestBody UsuarioInsertar user){
		// Respuesta por defecto: error de solicitud
		ResponseEntity<Boolean> respuesta = new ResponseEntity<Boolean>(false,HttpStatus.BAD_REQUEST);
		// Llamada al método del servicio para actualizar el usuario
		Boolean resultado = DAOS.actualizarUsuario(user);
		
		// Si la verificación fue exitosa, cambia la respuesta a OK
		if(resultado)
			respuesta = ResponseEntity.ok(true);

		return respuesta;
	}
	
	@DeleteMapping("borrar")
	public ResponseEntity<Boolean> borrar(@RequestBody UsuarioVerificar user){
		// Respuesta por defecto: error de solicitud
		ResponseEntity<Boolean> respuesta = new ResponseEntity<Boolean>(false,HttpStatus.BAD_REQUEST);
		// Llamada al método del servicio para borrar el usuario
		Boolean resultado = DAOS.borrarUsuario(user);
		
		// Si la verificación fue exitosa, cambia la respuesta a OK
		if(resultado)
			respuesta = ResponseEntity.ok(true);

		return respuesta;
	}

	@PutMapping("cambiarpassword")
	ResponseEntity<ResponseCambiarPassword> cambiarPassword(@RequestBody RequestCambiarPassword model) {
		ResponseCambiarPassword data = null;
		ResponseEntity<ResponseCambiarPassword> response = null;

		var result = DAOS.cambiarPassword(model);
		data = ResponseCambiarPassword
				.builder()
				.success(result)
				.build();

		response = new ResponseEntity<>(data, HttpStatus.OK);

		return response;
	}

	@PostMapping("registrardieta")
	ResponseEntity<ResponseRegistrarDieta> registrarDieta(@RequestBody RequestRegistrarDieta model) {
		ResponseEntity<ResponseRegistrarDieta> response = new ResponseEntity<>(HttpStatus.OK);



		return response;
	}

	@PutMapping("modificardieta")
	ResponseEntity<ResponseModificarDieta> modificarDieta(@RequestBody RequestModificarDieta model) {
		ResponseEntity<ResponseModificarDieta> response = new ResponseEntity<>(HttpStatus.OK);



		return response;
	}

	@PostMapping("getdatosusuario")
	ResponseEntity<ResponseGetDatosUsuario> getDatos(@RequestBody RequestGetDatosUsuario model) {
		ResponseEntity<ResponseGetDatosUsuario> response = new ResponseEntity<>(HttpStatus.OK);

		return response;
	}

	@PutMapping("modificardatosusuario")
	ResponseEntity<ResponseModificarDatosUsuario> borrar(@RequestBody RequestModificarDatosUsuario model) {
		ResponseEntity<ResponseModificarDatosUsuario> response = new ResponseEntity<>(HttpStatus.OK);


		return response;
	}

	@PostMapping("getdietausuario")
	ResponseEntity<ResponseGetDietaUsuario> getDieta(@RequestBody RequestGetDietaUsuario model) {
		ResponseEntity<ResponseGetDietaUsuario> response = new ResponseEntity<>(HttpStatus.OK);


		return response;
	}

	@PutMapping("modificarrutina")
	ResponseEntity<ResponseModificarRutina> modificarRutina(@RequestBody RequestModificarRutina model) {
		ResponseEntity<ResponseModificarRutina> response = new ResponseEntity<>(HttpStatus.OK);



		return response;
	}

	@PostMapping("getlistdietasusuario")
	ResponseEntity<ResponseGetListDietas> getListDietas(@RequestBody RequestGetListDietas model) {
		ResponseEntity<ResponseGetListDietas> response = new ResponseEntity<>(HttpStatus.OK);


		return response;
	}

	@PostMapping("getrutina")
	ResponseEntity<ResponseGetRutina> getRutina(@RequestBody RequestGetRutina model) {
		ResponseEntity<ResponseGetRutina> response = new ResponseEntity<>(HttpStatus.OK);


		return response;
	}

	@PostMapping("getlistrutinas")
	ResponseEntity<ResponseGetListRutinas> getListRutinas(@RequestBody RequestGetListRutinas model) {
		ResponseEntity<ResponseGetListRutinas> response = new ResponseEntity<>(HttpStatus.OK);


		return response;
	}
	
}
