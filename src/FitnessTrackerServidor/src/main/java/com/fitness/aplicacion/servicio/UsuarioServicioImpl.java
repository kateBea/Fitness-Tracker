package com.fitness.aplicacion.servicio;

import java.util.Optional;

import com.fitness.aplicacion.dto.RequestCambiarPassword;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.fitness.aplicacion.documentos.Usuario;
import com.fitness.aplicacion.dto.UsuarioInfo;
import com.fitness.aplicacion.dto.UsuarioInsertar;
import com.fitness.aplicacion.dto.UsuarioVerificar;
import com.fitness.aplicacion.mapeo.ObjectMapperUtils;
import com.fitness.aplicacion.repositorio.IUsuarioRepositorio;

import javax.swing.text.html.Option;

@Service
public class UsuarioServicioImpl implements IUsuarioServicio {

    // Inyección del repositorio de usuarios
    @Autowired
    IUsuarioRepositorio DAOS;

    // Instancia el encoder para cifrar contraseñas
    BCryptPasswordEncoder cifrar = new BCryptPasswordEncoder();
    
    // Método para insertar un nuevo usuario en la base de datos
    @Override
    public Boolean insertarUsuario(UsuarioInsertar user) {
        // Buscar si ya existe un usuario con el mismo correo electrónico
        Optional<Usuario> userDB = DAOS.findById(user.getEmail());
        boolean introducido = false;
        
        // Si no existe un usuario con el mismo correo electrónico, se procede a insertar
        if(userDB.isEmpty()) {
            // Mapear los datos del usuario
            Usuario userInsertar = ObjectMapperUtils.map(user, Usuario.class);
            
            // Cifrar la contraseña antes de almacenarla en la base de datos
            String contrasenaCifrada = cifrar.encode(userInsertar.getContrasena());
            userInsertar.setContrasena(contrasenaCifrada);
            
            // Guardar el nuevo usuario en la base de datos
            DAOS.save(userInsertar);
            introducido = true;
        }
        
        return introducido;
    }

    @Override
    public Optional<Usuario> insertarDebug(Usuario user) {
        return Optional.of(DAOS.insert(user));
    }

    // Método para verificar las credenciales de un usuario durante el inicio de sesión
    @Override
    public Optional<UsuarioInfo> verificarUsuario(UsuarioVerificar user) {
        // Buscar el usuario en la base de datos por su correo electrónico
        Optional<Usuario> userDB = DAOS.findById(user.getEmail());
        boolean verificado = false;
        
        // Si se encuentra el usuario en la base de datos, verificar la contraseña
        if(userDB.isPresent()) {
            // Verificar si la contraseña proporcionada coincide con la contraseña cifrada almacenada
            verificado = cifrar.matches(user.getContrasena(), userDB.get().getContrasena());
            
            if(!verificado) {
            	userDB = Optional.empty();
            }
        }else {
        	userDB = Optional.empty();
        }
        
        Optional<UsuarioInfo> userAct = Optional.of(ObjectMapperUtils.map(userDB, UsuarioInfo.class));
        
        return userAct;
    }

    // Método para obtener la información de un usuario por su correo electrónico
    @Override
    public Optional<UsuarioInfo> informacionUsuario(String email) {
        // Buscar el usuario en la base de datos por su correo electrónico
        Optional<Usuario> userDB = DAOS.findById(email);
        Optional<UsuarioInfo> retornar = Optional.empty();
        
        // Si se encuentra el usuario en la base de datos, mapear sus datos a UsuarioInfo y devolverlo
        if(userDB.isPresent()) {
            retornar = Optional.of(ObjectMapperUtils.map(userDB.get(), UsuarioInfo.class));
        }
        
        return retornar;
    }

    @Override
    public Boolean actualizarUsuario(UsuarioInsertar user) {
        // Buscar el usuario en la base de datos por su correo electrónico
        Optional<Usuario> userDB = DAOS.findById(user.getEmail());
        Boolean exito = false;
        
        // Verificar si el usuario existe en la base de datos
        if(userDB.isPresent()) {
            // Verificar si la contraseña proporcionada coincide con la contraseña almacenada
            if(cifrar.matches(user.getContrasena(), userDB.get().getContrasena())) {
                // Mapear los datos del usuario recibido al objeto Usuario
                Usuario userAct = ObjectMapperUtils.map(user, Usuario.class);
                
                //Volvemos a encriptar la contraseña
                String cifrado = cifrar.encode(userAct.getContrasena());
                userAct.setContrasena(cifrado);
                
                // Guardar los cambios en la base de datos
                DAOS.save(userAct);
                exito = true;
            }
        }
        
        return exito;
    }

    @Override
    public Boolean borrarUsuario(UsuarioVerificar user) {
        // Buscar el usuario en la base de datos por su correo electrónico
        Optional<Usuario> userDB = DAOS.findById(user.getEmail());
        Boolean exito = false;
        
        // Verificar si el usuario existe en la base de datos
        if(userDB.isPresent()) {
            // Verificar si la contraseña proporcionada coincide con la contraseña almacenada
            if(cifrar.matches(user.getContrasena(), userDB.get().getContrasena())) {
                // Eliminar el usuario de la base de datos
                DAOS.deleteById(userDB.get().getEmail());
                exito = true;
            }
        }
        
        return exito;
    }

    @Override
    public boolean cambiarPassword(RequestCambiarPassword model) {
        return false;
    }
}
