package com.fitness.aplicacion.servicio;

import java.util.Optional;

import com.fitness.aplicacion.documentos.Usuario;
import com.fitness.aplicacion.dto.RequestCambiarPassword;
import com.fitness.aplicacion.dto.UsuarioInfo;
import com.fitness.aplicacion.dto.UsuarioInsertar;
import com.fitness.aplicacion.dto.UsuarioVerificar;
import org.springframework.stereotype.Service;

@Service
public interface IUsuarioServicio {
	Boolean insertarUsuario(UsuarioInsertar user);

	/**
	 * Servicio para depuración. Mete los usuarios tal cual,
	 * es para el cargar datos y tener algo en la base de datos.
	 * Por lo normal usar insertarUsuario() que usa los DTOs.
	 * */
	Optional<Usuario> insertarDebug(Usuario user);

	Optional<UsuarioInfo> verificarUsuario(UsuarioVerificar user);
	Optional<UsuarioInfo> informacionUsuario(String email);
	Boolean actualizarUsuario(UsuarioInsertar user);
	Boolean borrarUsuario(UsuarioVerificar user);

	boolean cambiarPassword(RequestCambiarPassword model);
}