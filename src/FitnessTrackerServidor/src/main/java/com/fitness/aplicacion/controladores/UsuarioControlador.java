package com.fitness.aplicacion.controladores;

import java.util.Optional;

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

import com.fitness.aplicacion.dto.UsuarioInfo;
import com.fitness.aplicacion.dto.UsuarioInsertar;
import com.fitness.aplicacion.dto.UsuarioVerificar;
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
	public ResponseEntity<Boolean> verificar(@RequestBody UsuarioVerificar user){
		// Respuesta por defecto: error de solicitud
		ResponseEntity<Boolean> respuesta = new ResponseEntity<Boolean>(false,HttpStatus.BAD_REQUEST);
		// Llamada al método del servicio para verificar las credenciales del usuario
		Boolean resultado = DAOS.verificarUsuario(user);
		
		// Si la verificación fue exitosa, cambia la respuesta a OK
		if(resultado)
			respuesta = ResponseEntity.ok(true);

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
	
	
}
