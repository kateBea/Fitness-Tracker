package com.fitness.aplicacion.servicio;

import java.time.LocalDateTime;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

import com.fitness.aplicacion.documentos.*;
import com.fitness.aplicacion.dto.*;
import com.fitness.aplicacion.repositorio.IComidaRepositorio;
import com.fitness.aplicacion.repositorio.IDietaRepositorio;
import com.fitness.aplicacion.repositorio.IRutinaRepositorio;
import com.fitness.aplicacion.utilidades.UtilidadesFechas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.fitness.aplicacion.mapeo.ObjectMapperUtils;
import com.fitness.aplicacion.repositorio.IUsuarioRepositorio;
import org.springframework.transaction.annotation.Transactional;

import static com.fitness.aplicacion.dto.ResponseGetDatosUsuario.ResponseGetDatosUsuarioData;


@Service
public class UsuarioServicioImpl implements IUsuarioServicio {

    // Inyección del repositorio de usuarios
    @Autowired
    IUsuarioRepositorio DAOS;

    @Autowired
    IComidaRepositorio comidaRepositorio;

    @Autowired
    IDietaRepositorio dietaRepositorio;

    @Autowired
    IRutinaRepositorio rutinaRepositorio;


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
        boolean result = true;
        Optional<Usuario> info = DAOS.findById(model.getEmail());

        if (info.isPresent()) {
            boolean match = cifrar.matches(model.getOldPassword(), info.get().getContrasena());

            if (match) {
                info.get().setContrasena(cifrar.encode(model.getNewPassword()));
                DAOS.save(info.get());
            } else {
                result = false;
            }
        }

        return result;
    }

    @Override
    public Optional<ResponseGetDatosUsuarioData> consultar(RequestGetDatosUsuario model) {
        Optional<Usuario> usuario = DAOS.findById(model.getEmail());
        Optional<ResponseGetDatosUsuarioData> respuesta = Optional.empty();

        if (usuario.isPresent()) {
            ResponseGetDatosUsuarioData respuestaData = ObjectMapperUtils
                    .map(usuario, ResponseGetDatosUsuarioData.class);

            respuesta = Optional.of(respuestaData);
        }

        return respuesta;
    }

    @Override
    public Boolean modificar(RequestModificarDatosUsuario model) {
        if (!DAOS.existsById(model.getEmail())) {
            return false;
        }

        Usuario modificado = ObjectMapperUtils.map(model, Usuario.class);
        modificado.setFechaUltimaModificacion(LocalDateTime.now());

        DAOS.save(modificado);

        return true;
    }

    @Override
    public Boolean registrarDieta(RequestRegistrarDieta model) {
        Optional<Usuario> usuario = DAOS.findById(model.getEmail());

        if (usuario.isEmpty()) {
            return false;
        }

        Dieta nueva = ObjectMapperUtils.map(model, Dieta.class);
        nueva.setFechaRegistro(LocalDateTime.now());
        nueva.setFechaUltimaModificacion(LocalDateTime.now());

        List<Comida> comidas = new ArrayList<>();

        for (String comidaId : model.getComidasSugeridas()) {
            comidaRepositorio
                    .findById(comidaId)
                    .ifPresent(comidas::add);
        }

        nueva.setComidasSugeridas(comidas);

        List<Dieta> dietas = usuario
                .get()
                .getDietas();

        dietas.add(nueva);

        usuario.get().setDietas(dietas);

        DAOS.save(usuario.get());

        return true;
    }

    @Override
    public Boolean modificar(RequestModificarDieta model) {
        RequestRegistrarDieta data = ObjectMapperUtils.map(model, RequestRegistrarDieta.class);
        return registrarDieta(data);
    }

    @Override
    public Optional<ResponseGetDietaUsuario.ResponseGetDietaUsuarioData> getDieta(RequestGetDietaUsuario model) {
        if (!DAOS.existsById(model.getEmail())) {
            return Optional.empty();
        }

        Optional<Dieta> dieta = dietaRepositorio.findById(model.getIdDieta());

        ResponseGetDietaUsuario.ResponseGetDietaUsuarioData result = ObjectMapperUtils
                .map(dieta, ResponseGetDietaUsuario.ResponseGetDietaUsuarioData.class);

        return Optional.of(result);
    }

    @Override
    public List<ResponseGetDietaUsuario.ResponseGetDietaUsuarioData> getListDietas(RequestGetListDietas model) {
        Optional<Usuario> usuario = DAOS.findById(model.getEmail());

        if (usuario.isEmpty()) {
            throw new RuntimeException("El usuario no existe");
        }

        List<Dieta> dietas = usuario.get().getDietas();
        List<ResponseGetDietaUsuario.ResponseGetDietaUsuarioData> result = new ArrayList<>();

        for (Dieta dieta : dietas) {
            result.add(ObjectMapperUtils.map(dieta, ResponseGetDietaUsuario.ResponseGetDietaUsuarioData.class));
        }

        return result;
    }

    @Override
    public Boolean registrarRutina(RequestRegistrarRutina model) {
        Optional<Usuario> usuario = DAOS.findById(model.getEmail());

        if (usuario.isEmpty()) {
            return false;
        }

        Rutina nueva = ObjectMapperUtils.map(model, Rutina.class);
        nueva.setFechaSeguimiento(LocalDateTime.now());
        nueva.setFechaUltimaModificacion(LocalDateTime.now());

        List<Rutina> rutinas = usuario.get().getRutinas();
        rutinas.add(nueva);

        // Los alimentos se registran de forma manual desde otro endpoint
        // Porque estos se van registrando a lo largo del día
        usuario.get().setRutinas(rutinas);

        DAOS.save(usuario.get());

        return true;
    }

    @Override
    @Transactional
    public Boolean modificarRutina(RequestModificarRutina model) {
        Optional<Usuario> usuario = DAOS.findById(model.getEmail());
        Optional<Rutina> rutina = rutinaRepositorio.findById(model.getRutinaId());

        if (usuario.isEmpty() || rutina.isEmpty()) {
            return false;
        }

        rutina.get().setTiempoDeSuenio(model.getTiempoDeSuenio());
        rutina.get().setCaloriasQuemadas(model.getCaloriasQuemadas());
        rutina.get().setPasosRealizados(model.getPasosRealizados());
        rutina.get().setFrecuenciaCardiaca(model.getFrecuenciaCardiaca());
        rutina.get().setPresionArterial(model.getPresionArterial());
        rutina.get().setFechaUltimaModificacion(LocalDateTime.now());

        // cargar alimento alimentos
        List<Alimento> alimentos = new ArrayList<>();
        for (RequestModificarRutina.AlimentoInfo alimentoInfo : model.getAlimentoInfos()) {
            Optional<Comida> comida = comidaRepositorio.findById(alimentoInfo.getComidaId());

            comida.ifPresent(value -> alimentos.add(
                    Alimento
                            .builder()
                            .comida(value)
                            .tipo(Tipo.fromStr(alimentoInfo.getTipo()))
                            .orden(Orden.fromStr(alimentoInfo.getOrden()))
                            .fechaRegistro(LocalDateTime.now())
                            .fechaUltimaModificacion(LocalDateTime.now())
                            .horaConsumo(alimentoInfo.getHoraConsumo())
                            .build()
            ));
        }

        rutina.get().setComidasConsumidas(alimentos);
        rutinaRepositorio.save(rutina.get());

        DAOS.save(usuario.get());

        return true;
    }

    @Override
    public Optional<ResponseGetRutina.ResponseGetRutinaData> getRutina(RequestGetRutina model) {
        Optional<Usuario> usuario = DAOS.findById(model.getEmail());
        Optional<Rutina> rutina = rutinaRepositorio.findById(model.getRutinaId());

        if (usuario.isEmpty() || rutina.isEmpty()) {
            return Optional.empty();
        }

        List<RequestModificarRutina.AlimentoInfo> alimentoInfos =
                rutina.get().getComidasConsumidas().stream()
                        .map(comida -> ObjectMapperUtils.map(comida, RequestModificarRutina.AlimentoInfo.class))
                        .toList();

        ResponseGetRutina.ResponseGetRutinaData result =
                ObjectMapperUtils.map(rutina.get(), ResponseGetRutina.ResponseGetRutinaData.class);
        result.setComidasConsumidas(alimentoInfos);

        return Optional.of(result);
    }

    @Override
    public List<ResponseGetRutina.ResponseGetRutinaData> getListRutinas(RequestGetListRutinas model) {
        Optional<Usuario> usuario = DAOS.findById(model.getEmail());

        if (usuario.isEmpty()) {
            throw new RuntimeException("El usuario con el email especificado no existe.");
        }

        List<ResponseGetRutina.ResponseGetRutinaData> result;

        if (model.isFetchAll() && (model.getFechaInicio() == null || model.getFechaFin() == null)) {
            throw new RuntimeException("Si se piden rutinas en un rango de fechas, ambos extremos son obligatorios.");
        }

        if (model.isFetchAll()) {
            result = usuario.get().getRutinas().stream()
                    .filter(rutina -> UtilidadesFechas
                        .isBetween(rutina.getFechaSeguimiento(), model.getFechaInicio(), model.getFechaFin()))
                    .map(rutina -> ObjectMapperUtils.map(rutina, ResponseGetRutina.ResponseGetRutinaData.class))
                    .toList();
        } else {
            result = usuario.get().getRutinas().stream()
                    .map(rutina -> ObjectMapperUtils.map(rutina, ResponseGetRutina.ResponseGetRutinaData.class))
                    .toList();
        }

        return result;
    }
}
