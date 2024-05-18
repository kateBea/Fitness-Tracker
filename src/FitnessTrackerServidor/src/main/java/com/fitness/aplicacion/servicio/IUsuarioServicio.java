package com.fitness.aplicacion.servicio;

import java.util.Optional;

import com.fitness.aplicacion.dto.UsuarioInfo;
import com.fitness.aplicacion.dto.UsuarioInsertar;
import com.fitness.aplicacion.dto.UsuarioVerificar;

public interface IUsuarioServicio {
	Boolean insertarUsuario(UsuarioInsertar user);
	Optional<UsuarioInfo> verificarUsuario(UsuarioVerificar user);
	Optional<UsuarioInfo> informacionUsuario(String email);
	Boolean actualizarUsuario(UsuarioInsertar user);
	Boolean borrarUsuario(UsuarioVerificar user);
}