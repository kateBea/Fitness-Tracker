package com.fitness.aplicacion.servicio;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

import com.fitness.aplicacion.documentos.*;
import com.fitness.aplicacion.dto.*;
import com.fitness.aplicacion.repositorio.IComidaRepositorio;
import com.fitness.aplicacion.repositorio.IDietaRepositorio;
import com.fitness.aplicacion.repositorio.IRutinaRepositorio;
import com.fitness.aplicacion.utilidades.UtilidadesFechas;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.fitness.aplicacion.mapeo.ObjectMapperUtils;
import com.fitness.aplicacion.repositorio.IUsuarioRepositorio;
import org.springframework.transaction.annotation.Transactional;
import static com.fitness.aplicacion.dto.RequestRegistrarDieta.ComidaSugeridaData;

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

            userInsertar.setSexo(Sexo.fromStr(user.getSexo()));
            userInsertar.setFechaRegistro(LocalDateTime.now());
            userInsertar.setFechaUltimaModificacion(LocalDateTime.now());

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
            boolean match =
                    cifrar.matches(model.getOldPassword(), info.get().getContrasena());

            boolean oldMatchesNew =
                    cifrar.matches(model.getNewPassword(), info.get().getContrasena());

            if (match && oldMatchesNew) {
                throw new RuntimeException("La contraseña nueva debe ser diferente a la antigua");
            }

            if (match) {
                info.get().setContrasena(cifrar.encode(model.getNewPassword()));
                DAOS.save(info.get());
            } else {
                result = false;
            }
        } else {
            result = false;
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
        Optional<Usuario> usuario = DAOS.findById(model.getEmail());

        if (usuario.isEmpty()) {
            return false;
        }

        usuario.get().setNombre(model.getNombre());
        usuario.get().setNombreUsuario(model.getNombreUsuario());
        usuario.get().setPrimerApellido(model.getPrimerApellido());
        usuario.get().setSegundoApellido(model.getSegundoApellido());
        usuario.get().setFechaDeNacimiento(model.getFechaDeNacimiento());
        usuario.get().setAltura(model.getAltura());
        usuario.get().setPeso(model.getPeso());
        usuario.get().setSexo(Sexo.fromStr(model.getSexo()));

        usuario.get().setFechaUltimaModificacion(LocalDateTime.now());

        DAOS.save(usuario.get());

        return true;
    }

    @Override
    public Boolean registrarDieta(RequestRegistrarDieta model) {
        Optional<Usuario> usuario = DAOS.findById(model.getEmail());

        if (usuario.isEmpty()) {
            return false;
        }

        Dieta nueva = ObjectMapperUtils.map(model, Dieta.class);

        nueva.setId(new ObjectId().toString());
        nueva.setFechaRegistro(LocalDateTime.now());
        nueva.setFechaUltimaModificacion(LocalDateTime.now());

        // no hay otra dieta activa en el rango de fechas
        dietasActivasSolapan(usuario, model.getFechaInicio(), model.getFechaFin(), model.isActiva());

        List<ComidaSugerida> comidasSugeridasDietaNueva = new ArrayList<>();
        List<Dieta> dietasDelUsuario = usuario.get().getDietas();
        List<Comida> comidasRegistradasDelUsuario = usuario.get().getComidasRegistradas();

        for (ComidaSugeridaData comidaNueva : model.getComidasSugeridas()) {
            // Comida a insertar en caso de que no exista en la lista de registros del usuario
            Comida aInsertar = ObjectMapperUtils.map(comidaNueva, Comida.class);

            Optional<Comida> comidaRegistrada = comidasRegistradasDelUsuario.stream()
                    .filter(comida -> comida.equals(aInsertar))
                    .findFirst();

            // La comida no está registrada en la lista de comidas del usuario
            if (comidaRegistrada.isEmpty() && (comidaNueva.getId() == null || comidaNueva.getId().isEmpty())) {
                aInsertar.setId(new ObjectId().toString());
                aInsertar.setFechaRegistro(LocalDateTime.now());
                aInsertar.setFechaUltimaModificacion(LocalDateTime.now());

                comidasRegistradasDelUsuario.add(aInsertar);
            }

            // Cojo el id de la nueva comida porque es la que voy a insertar siempre
            // tanto cuando el usuario la tiene registrada como cuando no la tiene registrada
            comidasSugeridasDietaNueva.add(ComidaSugerida.builder()
                    .id(aInsertar.getId())
                    .orden(Orden.fromStr(comidaNueva.getOrden()))
                    .tipo(Tipo.fromStr(comidaNueva.getTipo())).build());
        }

        // actualizo las comidas sugeridas de la nueva dieta
        nueva.setComidasSugeridas(comidasSugeridasDietaNueva);

        // añado la nueva dieta
        dietasDelUsuario.add(nueva);

        // actualizo las comidas registradas del usuario y las dietas
        usuario.get().setDietas(dietasDelUsuario);
        usuario.get().setComidasRegistradas(comidasRegistradasDelUsuario);

        DAOS.save(usuario.get());

        return true;
    }

    /**
     * Asume que el usuario no está vació
     * */
    private static void dietasActivasSolapan(Optional<Usuario> usuario, LocalDateTime fechaInicio, LocalDateTime fechaFin, boolean activa) {
        boolean dietasActivasSolapan = usuario.get().getDietas().stream()
            .anyMatch(dieta ->
                    Stream.of(fechaInicio, fechaFin).anyMatch(Objects::isNull) ||
                            (UtilidadesFechas.intervalsOverlap(
                        fechaInicio, fechaFin,
                        dieta.getFechaInicio(), dieta.getFechaFin()) && dieta.isActiva() && activa));

        if (dietasActivasSolapan) {
            throw new RuntimeException("Ya existe una dieta activa en el intervalo de fechas indicado o las fechas no son válidas.");
        }
    }

    @Override
    public Boolean modificar(RequestModificarDieta model) {
        Optional<Usuario> usuario = DAOS.findById(model.getEmail());

        if (usuario.isEmpty()) {
            return false;
        }

        dietasActivasSolapan(usuario, model.getFechaInicio(), model.getFechaFin(), model.isActiva());

        Optional<Dieta> aModificar = usuario.get().getDietas().stream()
                .filter(dieta -> model.getDietaId().equals(dieta.getId()))
                .findFirst();

        if (aModificar.isEmpty()) {
            return false;
        }

        aModificar.get().setCaloriasTarget(model.getCaloriasTarget());
        aModificar.get().setConsumoDeAgua(model.getConsumoDeAgua());
        aModificar.get().setActiva(model.isActiva());
        aModificar.get().setFechaFin(model.getFechaFin());
        aModificar.get().setFechaInicio(model.getFechaInicio());
        aModificar.get().setFechaUltimaModificacion(LocalDateTime.now());

        DAOS.save(usuario.get());

        return true;
    }

    @Override
    public Optional<ResponseGetDietaUsuario.ResponseGetDietaUsuarioData> getDieta(RequestGetDietaUsuario model) {
        Optional<Usuario> usuario = DAOS.findById(model.getEmail());

        if (usuario.isEmpty()) {
            return Optional.empty();
        }

        Optional<Dieta> dietaEncontrada = usuario.get().getDietas().stream()
                .filter(target -> target.getId().equals(model.getIdDieta()))
                .findFirst();

        if (dietaEncontrada.isEmpty()) {
            return Optional.empty();
        }

        ResponseGetDietaUsuario.ResponseGetDietaUsuarioData result =
                ObjectMapperUtils.map(dietaEncontrada.get(), ResponseGetDietaUsuario.ResponseGetDietaUsuarioData.class);

        List<ResponseGetDietaUsuario.ResponseGetDietaUsuarioDataComida> comidasSugeridasResult = new ArrayList<>();

        for (ComidaSugerida comidaSugerida : dietaEncontrada.get().getComidasSugeridas()) {
            Optional<Comida> comidaResult = usuario.get().getComidasRegistradas().stream()
                    .filter(c -> c.getId().equals(comidaSugerida.getId()))
                    .findFirst();

            comidaResult.ifPresent(comida -> {
                var toAdd = ObjectMapperUtils.map(comida, ResponseGetDietaUsuario.ResponseGetDietaUsuarioDataComida.class);

                toAdd.setTipo(comidaSugerida.getTipo().name());
                toAdd.setOrden(comidaSugerida.getOrden().name());

                comidasSugeridasResult.add(toAdd);
            });
        }

        result.setComidasSugeridasResult(comidasSugeridasResult);

        return Optional.of(result);
    }

    @Override
    public List<ResponseGetDietaUsuario.ResponseGetDietaUsuarioData> getListDietas(RequestGetListDietas model) {
        Optional<Usuario> usuario = DAOS.findById(model.getEmail());

        if (usuario.isEmpty()) {
            throw new RuntimeException("El usuario no existe");
        }

        List<ResponseGetDietaUsuario.ResponseGetDietaUsuarioData> result =
                usuario.get().getDietas().stream()
                .map(dietaEncontrada -> {
                    ResponseGetDietaUsuario.ResponseGetDietaUsuarioData singleDietaData =
                            ObjectMapperUtils.map(dietaEncontrada, ResponseGetDietaUsuario.ResponseGetDietaUsuarioData.class);

                    List<ResponseGetDietaUsuario.ResponseGetDietaUsuarioDataComida> comidasSugeridasResult = new ArrayList<>();


                    for (ComidaSugerida comidaSugerida : dietaEncontrada.getComidasSugeridas()) {
                        Optional<Comida> comidaResult = usuario.get().getComidasRegistradas().stream()
                                .filter(c -> c.getId().equals(comidaSugerida.getId()))
                                .findFirst();

                        comidaResult.ifPresent(comida -> {
                            var toAdd = ObjectMapperUtils.map(comida, ResponseGetDietaUsuario.ResponseGetDietaUsuarioDataComida.class);

                            toAdd.setTipo(comidaSugerida.getTipo().name());
                            toAdd.setOrden(comidaSugerida.getOrden().name());

                            comidasSugeridasResult.add(toAdd);
                        });
                    }

                    singleDietaData.setComidasSugeridasResult(comidasSugeridasResult);

                    return singleDietaData;
                }).toList();

        return result;
    }

    @Override
    public Boolean registrarRutina(RequestRegistrarRutina model) {
        Optional<Usuario> usuario = DAOS.findById(model.getEmail());

        if (usuario.isEmpty()) {
            return false;
        }

        Rutina nueva = ObjectMapperUtils.map(model, Rutina.class);

        nueva.setId(new ObjectId().toString());
        nueva.setFechaSeguimiento(LocalDate.now());
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

        if (usuario.isEmpty()) {
            return false;
        }

        Optional<Rutina> rutina = usuario.get().getRutinas().stream()
                .filter(rutina1 -> rutina1.getId().equalsIgnoreCase(model.getRutinaId()))
                .findFirst();

        if (rutina.isEmpty()) {
            return false;
        }

        rutina.get().setTiempoDeSuenio(model.getTiempoDeSuenio());
        rutina.get().setCaloriasQuemadas(model.getCaloriasQuemadas());
        rutina.get().setPasosRealizados(model.getPasosRealizados());
        rutina.get().setFrecuenciaCardiaca(model.getFrecuenciaCardiaca());
        rutina.get().setNivelOxigenoSangre(model.getNivelOxigenoSangre());
        rutina.get().setFechaUltimaModificacion(LocalDateTime.now());

        // cargar alimento alimentos
        List<Comida> comidasRegistradasUsuario = usuario.get().getComidasRegistradas().isEmpty() ?
                new ArrayList<>() : usuario.get().getComidasRegistradas();

        List<Alimento> alimentos = rutina.get().getComidasConsumidas().isEmpty() ?
                new ArrayList<>() : rutina.get().getComidasConsumidas();
        for (RequestModificarRutina.AlimentoInfo alimentoInfo : model.getAlimentoInfos()) {
            // Buscamos la comida con el id de alimentoInfo en el repertorio de comidas que tiene registrado el usuario
            Optional<Comida> comida = comidasRegistradasUsuario.stream()
                            .filter(c -> c.getId().equals(alimentoInfo.getComidaId()))
                            .findFirst();

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

            // Si la comida que se intenta añadir no existe se da de alta
            if (comida.isEmpty()) {
                comidasRegistradasUsuario.add(
                        Comida.builder()
                                .id(alimentoInfo.getComidaId())
                                .nombre(alimentoInfo.getNombre())
                                .fechaRegistro(LocalDateTime.now())
                                .fechaUltimaModificacion(LocalDateTime.now())
                                .descripcion("Sin descripción")
                                .build()
                );
            }
        }

        rutina.get().setComidasConsumidas(alimentos);
        rutinaRepositorio.save(rutina.get());

        usuario.get().setComidasRegistradas(comidasRegistradasUsuario);
        DAOS.save(usuario.get());

        return true;
    }

    @Override
    public Optional<ResponseGetRutina.ResponseGetRutinaData> getRutina(RequestGetRutina model) {
        Optional<Usuario> usuario = DAOS.findById(model.getEmail());

        if (usuario.isEmpty()) {
            return Optional.empty();
        }

        Optional<Rutina> rutina = usuario.get().getRutinas().stream()
                .filter(rutina1 -> rutina1.getId().equalsIgnoreCase(model.getRutinaId()))
                .findFirst();

        if (rutina.isEmpty()) {
            return Optional.empty();
        }

        List<RequestModificarRutina.AlimentoInfo> alimentoInfos =
                rutina.get().getComidasConsumidas().stream()
                        .map(comida -> {
                            RequestModificarRutina.AlimentoInfo res = ObjectMapperUtils.map(comida, RequestModificarRutina.AlimentoInfo.class);

                            List<Comida> comidasRegistradasUsuario = usuario.get().getComidasRegistradas();

                            Optional<Comida> comidaRegistrada = comidasRegistradasUsuario.stream()
                                    .filter(item -> item.getId().equals(comida.getId()))
                                    .findFirst();

                            return getAlimentoInfo(res, comidaRegistrada);
                        })
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

        if (!model.isFetchAll() && (model.getFechaInicio() == null || model.getFechaFin() == null)) {
            throw new RuntimeException("Si se piden rutinas en un rango de fechas, ambos extremos son obligatorios.");
        }

        if (!model.isFetchAll()) {
            result = usuario.get().getRutinas().stream()
                    .filter(rutina -> UtilidadesFechas
                        .isBetween(rutina.getFechaSeguimiento(), model.getFechaInicio(), model.getFechaFin()))
                    .map(rutina -> {
                        ResponseGetRutina.ResponseGetRutinaData res = ObjectMapperUtils.map(rutina, ResponseGetRutina.ResponseGetRutinaData.class);

                        List<RequestModificarRutina.AlimentoInfo> alimentoInfos = res.getComidasConsumidas();

                        if (alimentoInfos == null) {
                            return res;
                        }

                        alimentoInfos = alimentoInfos.stream()
                                        .map(alimentoInfo -> {

                                            List<Comida> comidasRegistradasUsuario = usuario.get().getComidasRegistradas();

                                            Optional<Comida> comidaRegistrada = comidasRegistradasUsuario.stream()
                                                    .filter(item -> item.getId().equals(alimentoInfo.getComidaId()))
                                                    .findFirst();

                                            return getAlimentoInfo(alimentoInfo, comidaRegistrada);
                                        })
                                .toList();

                        res.setComidasConsumidas(alimentoInfos != null ? alimentoInfos : new ArrayList<>());

                        return res;
                    })
                    .toList();
        } else {
            result = usuario.get().getRutinas().stream()
                    .map(rutina -> {
                        ResponseGetRutina.ResponseGetRutinaData res = ObjectMapperUtils.map(rutina, ResponseGetRutina.ResponseGetRutinaData.class);

                        List<RequestModificarRutina.AlimentoInfo> alimentoInfos = res.getComidasConsumidas();

                        if (alimentoInfos == null) {
                            return res;
                        }

                        alimentoInfos = alimentoInfos.stream()
                                .map(alimentoInfo -> {

                                    List<Comida> comidasRegistradasUsuario = usuario.get().getComidasRegistradas();

                                    Optional<Comida> comidaRegistrada = comidasRegistradasUsuario.stream()
                                            .filter(item -> item.getId().equals(alimentoInfo.getComidaId()))
                                            .findFirst();

                                    return getAlimentoInfo(alimentoInfo, comidaRegistrada);
                                })
                                .toList();

                        res.setComidasConsumidas(alimentoInfos != null ? alimentoInfos : new ArrayList<>());

                        return res;
                    })
                    .toList();
        }

        return result;
    }

    private RequestModificarRutina.AlimentoInfo getAlimentoInfo(RequestModificarRutina.AlimentoInfo alimentoInfo, Optional<Comida> comidaRegistrada) {
        if (comidaRegistrada.isPresent()) {
            alimentoInfo.setNombre(comidaRegistrada.get().getNombre());
            alimentoInfo.setDescripcion(comidaRegistrada.get().getDescripcion());
            alimentoInfo.setCalorias(comidaRegistrada.get().getCalorias());
            alimentoInfo.setGrasas(comidaRegistrada.get().getGrasas());
            alimentoInfo.setCarbohidratos(comidaRegistrada.get().getCarbohidratos());
            alimentoInfo.setVitaminas(comidaRegistrada.get().getVitaminas());
        }
        return alimentoInfo;
    }

    @Override
    public ResponseLogin login(RequestLogin model) {
        ResponseLogin response = ResponseLogin.builder()
                .build();

        Optional<Usuario> usuario = DAOS.findById(model.getEmail());

        if (usuario.isEmpty()) {
            response.setSuccess(false);
            response.setResponseDescription("El usuario no existe.");

            return response;
        }

        boolean verificado = cifrar.matches(model.getPassword(), usuario.get().getContrasena());

        if(verificado) {
            response.setUsername(usuario.get().getNombreUsuario());
            response.setEmail(usuario.get().getEmail());
            response.setName(usuario.get().getNombre());
            response.setFirstSurname(usuario.get().getPrimerApellido());
            response.setSecondSurname(usuario.get().getSegundoApellido());
            response.setResponseDescription("Credenciales válidos.");
            response.setSuccess(true);
            response.setLoggedAt(LocalDateTime.now());
        } else {
            response.setResponseDescription("Credenciales no válidos.");
            response.setSuccess(false);
        }

        return response;
    }
}
