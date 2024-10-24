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
import com.fitness.aplicacion.utilidades.UtilidadesFechas;
import com.fitness.aplicacion.utilidades.UtilidadesUsuario;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.fitness.aplicacion.mapeo.ObjectMapperUtils;
import com.fitness.aplicacion.repositorio.IUsuarioRepositorio;
import org.springframework.transaction.annotation.Transactional;
import static com.fitness.aplicacion.dto.RequestRegistrarDieta.ComidaSugeridaData;

import static com.fitness.aplicacion.dto.ResponseGetDatosUsuario.ResponseGetDatosUsuarioData;
import static com.fitness.aplicacion.dto.ResponseLogin.ResponseLoginData;


/**
 * Implementación de la interfaz del servicio de usuarios
 *
 * @version 1.0
 * */
@Service
public class UsuarioServicioImpl implements IUsuarioServicio {

    // Inyección del repositorio de usuarios
    @Autowired
    IUsuarioRepositorio usuarioRepositorio;

    // Instancia el encoder para cifrar contraseñas
    BCryptPasswordEncoder cifrar = new BCryptPasswordEncoder();
    
    @Override
    public Boolean insertarUsuario(RequestRegistrarUsuario user) {
        boolean introducido = false;
        Optional<Usuario> userDB = usuarioRepositorio.findById(user.getEmail());
        boolean contrasenaEsValida = UtilidadesUsuario.passwordCheck(user.getContrasena());

        if (!contrasenaEsValida) {
            throw new RuntimeException("La contraseña es débil. Debe tener al menos 8 caracteres, un dígito y uno no alfanumérico.");
        }

        if(userDB.isEmpty()) {
            Usuario userInsertar = ObjectMapperUtils.map(user, Usuario.class);

            userInsertar.setSexo(Sexo.fromStr(user.getSexo()));
            userInsertar.setFechaRegistro(LocalDateTime.now());
            userInsertar.setFechaUltimaModificacion(LocalDateTime.now());

            String contrasenaCifrada = cifrar.encode(userInsertar.getContrasena());
            userInsertar.setContrasena(contrasenaCifrada);

            usuarioRepositorio.save(userInsertar);
            introducido = true;
        }
        
        return introducido;
    }

    @Override
    public Optional<Usuario> insertarDebug(Usuario user) {
        return Optional.of(usuarioRepositorio.insert(user));
    }

    @Override
    public Optional<UsuarioInfo> verificarUsuario(UsuarioVerificar user) {
        // Buscar el usuario en la base de datos por su correo electrónico
        Optional<Usuario> userDB = usuarioRepositorio.findById(user.getEmail());
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
        Optional<Usuario> userDB = usuarioRepositorio.findById(email);
        Optional<UsuarioInfo> retornar = Optional.empty();
        
        // Si se encuentra el usuario en la base de datos, mapear sus datos a UsuarioInfo y devolverlo
        if(userDB.isPresent()) {
            retornar = Optional.of(ObjectMapperUtils.map(userDB.get(), UsuarioInfo.class));
        }
        
        return retornar;
    }

    @Override
    public Boolean actualizarUsuario(RequestRegistrarUsuario user) {
        // Buscar el usuario en la base de datos por su correo electrónico
        Optional<Usuario> userDB = usuarioRepositorio.findById(user.getEmail());
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
                usuarioRepositorio.save(userAct);
                exito = true;
            }
        }
        
        return exito;
    }

    @Override
    public Boolean borrarUsuario(UsuarioVerificar user) {
        // Buscar el usuario en la base de datos por su correo electrónico
        Optional<Usuario> userDB = usuarioRepositorio.findById(user.getEmail());
        Boolean exito = false;
        
        // Verificar si el usuario existe en la base de datos
        if(userDB.isPresent()) {
            // Verificar si la contraseña proporcionada coincide con la contraseña almacenada
            if(cifrar.matches(user.getContrasena(), userDB.get().getContrasena())) {
                // Eliminar el usuario de la base de datos
                usuarioRepositorio.deleteById(userDB.get().getEmail());
                exito = true;
            }
        }
        
        return exito;
    }

    @Override
    public boolean cambiarPassword(RequestCambiarPassword model) {
        boolean result = true;

        Optional<Usuario> info = usuarioRepositorio.findById(model.getEmail());

        if (info.isPresent()) {
            boolean match = cifrar.matches(model.getOldPassword(), info.get().getContrasena());
            boolean oldMatchesNew = cifrar.matches(model.getNewPassword(), info.get().getContrasena());

            if (match && oldMatchesNew) {
                throw new RuntimeException("La contraseña nueva debe ser diferente a la antigua");
            }

            if (match) {
                info.get().setContrasena(cifrar.encode(model.getNewPassword()));
                usuarioRepositorio.save(info.get());
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
        Optional<Usuario> usuario = usuarioRepositorio.findById(model.getEmail());
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
        Optional<Usuario> usuario = usuarioRepositorio.findById(model.getEmail());

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
        usuario.get().setImagen(model.getImagen());
        usuario.get().setObjetivoPeso(model.getObjetivoPeso());

        usuario.get().setFechaUltimaModificacion(LocalDateTime.now());

        usuarioRepositorio.save(usuario.get());

        return true;
    }

    @Override
    public ResponseRegistrarDieta registrarDieta(RequestRegistrarDieta model) {
        Optional<Usuario> usuario = usuarioRepositorio.findById(model.getEmail());

        if (usuario.isEmpty()) {
            return ResponseRegistrarDieta.builder()
                    .createdAt(null)
                    .responseDescription("El usuario no existe")
                    .success(false)
                    .build();
        }

        Dieta nueva = ObjectMapperUtils.map(model, Dieta.class);

        nueva.setId(new ObjectId().toString());
        nueva.setFechaRegistro(LocalDateTime.now());
        nueva.setFechaUltimaModificacion(LocalDateTime.now());

        // no hay otra dieta activa en el rango de fechas
        dietasActivasSolapan(usuario, model.getFechaInicio(), model.getFechaFin(), model.isActiva());

        List<ComidaSugerida> comidasSugeridasDietaNueva = new ArrayList<>();
        List<Dieta> dietasDelUsuario = usuario.get().getDietas() != null ? usuario.get().getDietas() : new ArrayList<>();
        List<Comida> comidasRegistradasDelUsuario = usuario.get().getComidasRegistradas() != null ? usuario.get().getComidasRegistradas() : new ArrayList<>();

        for (ComidaSugeridaData comidaNueva : model.getComidasSugeridas()) {
            // Comida a insertar en caso de que no exista en la lista de registros del usuario
            Comida aInsertar = ObjectMapperUtils.map(comidaNueva, Comida.class);

            Optional<Comida> comidaRegistrada = comidasRegistradasDelUsuario.stream()
                    .filter(comida -> comida.equals(aInsertar))
                    .findFirst();

            // La comida no está registrada en la lista de comidas del usuario
            if (comidaRegistrada.isEmpty()) {
                aInsertar.setId(comidaNueva.getId());
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

        usuarioRepositorio.save(usuario.get());

        return ResponseRegistrarDieta.builder()
                .id(nueva.getId())
                .createdAt(LocalDateTime.now())
                .responseDescription("Dieta registrada con éxito")
                .success(true)
                .build();
    }

    /**
     * Asume que el usuario no está vació
     * */
    private static void dietasActivasSolapan(Optional<Usuario> usuario, LocalDateTime fechaInicio, LocalDateTime fechaFin, boolean activa) {
        // TODO: hotfix
        if (usuario.get().getDietas() == null) {
            usuario.get().setDietas(new ArrayList<>());
        }

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
        Optional<Usuario> usuario = usuarioRepositorio.findById(model.getEmail());

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

        aModificar.get().setActiva(model.isActiva());
        aModificar.get().setFechaFin(model.getFechaFin());
        aModificar.get().setFechaInicio(model.getFechaInicio());
        aModificar.get().setFechaUltimaModificacion(LocalDateTime.now());

        usuarioRepositorio.save(usuario.get());

        return true;
    }

    @Override
    public Optional<ResponseGetDietaUsuario.ResponseGetDietaUsuarioData> getDieta(RequestGetDietaUsuario model) {
        Optional<Usuario> usuario = usuarioRepositorio.findById(model.getEmail());

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
        Optional<Usuario> usuario = usuarioRepositorio.findById(model.getEmail());

        if (usuario.isEmpty()) {
            throw new RuntimeException("El usuario no existe");
        }
        List<ResponseGetDietaUsuario.ResponseGetDietaUsuarioData> result = new ArrayList<>();

        if (usuario.get().getDietas() != null) {
             result =
                    usuario.get().getDietas().stream()
                            .map(dietaRegistradaUsuario -> {
                                ResponseGetDietaUsuario.ResponseGetDietaUsuarioData singleDietaData =
                                        ObjectMapperUtils.map(dietaRegistradaUsuario, ResponseGetDietaUsuario.ResponseGetDietaUsuarioData.class);

                                List<ResponseGetDietaUsuario.ResponseGetDietaUsuarioDataComida> comidasSugeridasResult = new ArrayList<>();

                                for (ComidaSugerida comidaSugerida : dietaRegistradaUsuario.getComidasSugeridas()) {
                                    Optional<Comida> comidaResult = Optional.empty();

                                    if (usuario.get().getComidasRegistradas() != null) {
                                        comidaResult = usuario.get().getComidasRegistradas().stream()
                                                .filter(c -> c.getId().equals(comidaSugerida.getId()))
                                                .findFirst();
                                    }

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
        }


        return result;
    }

    @Override
    public ResponseRegistrarRutina registrarRutina(RequestRegistrarRutina model) {
        Optional<Usuario> usuario = usuarioRepositorio.findById(model.getEmail());

        if (usuario.isEmpty()) {
            return ResponseRegistrarRutina.builder()
                    .responseDescription("El usuario no existe")
                    .success(false)
                    .build();
        }

        // Buscamos si ya existe una rutina para hoy
        Optional<Rutina> rutinaHoy = usuario.get().getRutinas().stream()
                .filter(rutinaItem -> rutinaItem.getFechaSeguimiento().equals(LocalDate.now()))
                .findFirst();

        if (rutinaHoy.isPresent()) {
            return ResponseRegistrarRutina.builder()
                    .id(rutinaHoy.get().getId())
                    .responseDescription(String.format("Existe rutina para el día de hoy con ID: '%s'", rutinaHoy.get().getId()))
                    .success(false)
                    .build();
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

        usuarioRepositorio.save(usuario.get());

        return ResponseRegistrarRutina.builder()
                .id(nueva.getId())
                .responseDescription("Rutina registrada con éxito")
                .createdAt(LocalDateTime.now())
                .success(true)
                .build();
    }

    @Override
    @Transactional
    public Boolean modificarRutina(RequestModificarRutina model) {
        Optional<Usuario> usuario = usuarioRepositorio.findById(model.getEmail());

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

        // cargar las comidas registradas del usuario, en ellas se buscan las comidas
        // que se estén intentando añadir a la rutina a modificar
        List<Comida> comidasRegistradasUsuario = usuario.get().getComidasRegistradas() == null ?
                new ArrayList<>() : usuario.get().getComidasRegistradas();

        // cargamos las comidas consumidas para actualizarlas en el usuario
        List<Alimento> alimentos = rutina.get().getComidasConsumidas() == null ?
                new ArrayList<>() : rutina.get().getComidasConsumidas();

        for (RequestModificarRutina.AlimentoInfo alimentoInfo : model.getAlimentoInfos()) {
            // Buscamos la comida con el id de alimentoInfo en el repertorio de comidas que tiene registrado el usuario
            Optional<Comida> comida = comidasRegistradasUsuario.stream()
                            .filter(c -> alimentoInfo.getComidaId().equals(c.getId()))
                            .findFirst();



            // Si la comida que se intenta añadir no existe se da de alta
            if (comida.isEmpty()) {
                comida=  Optional.of(
                        Comida.builder()
                                .id(alimentoInfo.getComidaId())
                                .calorias(alimentoInfo.getCalorias())
                                .carbohidratos(alimentoInfo.getCarbohidratos())
                                .proteinas(alimentoInfo.getProteinas())
                                .grasas(alimentoInfo.getGrasas())
                                .nombre(alimentoInfo.getNombre())
                                .fechaRegistro(LocalDateTime.now())
                                .fechaUltimaModificacion(LocalDateTime.now())
                                .descripcion(alimentoInfo.getDescripcion())
                                .vitaminas(alimentoInfo.getVitaminas())
                                .build());

                comidasRegistradasUsuario.add(comida.get());
            }

            final String alimentoId = comida.get().getId();
            boolean existeEnRutina = alimentos.stream().anyMatch(a -> alimentoId.equals(a.getId()));

            if (!existeEnRutina) {
                alimentos.add(
                        Alimento
                                .builder()
                                .id(alimentoId)
                                .comida(comida.get())
                                .tipo(Tipo.fromStr(alimentoInfo.getTipo()))
                                .orden(Orden.fromStr(alimentoInfo.getOrden()))
                                .fechaRegistro(LocalDateTime.now())
                                .fechaUltimaModificacion(LocalDateTime.now())
                                .horaConsumo(alimentoInfo.getHoraConsumo())
                                .build());
            } else {
                var result = alimentos.stream().filter(a -> a.getId().equals(alimentoId)).findFirst();

                if (result.isPresent()) {

                    result.get().setComida(comida.get());
                    result.get().setTipo(Tipo.fromStr(alimentoInfo.getTipo()));
                    result.get().setOrden(Orden.fromStr(alimentoInfo.getOrden()));
                    result.get().setFechaUltimaModificacion(LocalDateTime.now());
                    result.get().setHoraConsumo(alimentoInfo.getHoraConsumo());
                }
            }
        }

        rutina.get().setComidasConsumidas(alimentos);

        usuario.get().setComidasRegistradas(comidasRegistradasUsuario);
        usuarioRepositorio.save(usuario.get());

        return true;
    }

    @Override
    public Optional<ResponseGetRutina.ResponseGetRutinaData> getRutina(RequestGetRutina model) {
        Optional<Usuario> usuario = usuarioRepositorio.findById(model.getEmail());

        if (usuario.isEmpty()) {
            return Optional.empty();
        }

        Optional<Rutina> rutina = usuario.get().getRutinas().stream()
                .filter(rutina1 -> rutina1.getId().equalsIgnoreCase(model.getRutinaId()))
                .findFirst();

        if (rutina.isEmpty()) {
            return Optional.empty();
        }

        List<RequestModificarRutina.AlimentoInfo> alimentoInfos = rutina.get().getComidasConsumidas() != null ?
                rutina.get().getComidasConsumidas().stream()
                        .map(comida -> {
                            RequestModificarRutina.AlimentoInfo res = ObjectMapperUtils.map(comida, RequestModificarRutina.AlimentoInfo.class);

                            List<Comida> comidasRegistradasUsuario = usuario.get().getComidasRegistradas();

                            Optional<Comida> comidaRegistrada = comidasRegistradasUsuario.stream()
                                    .filter(item -> item.getId().equals(comida.getId()))
                                    .findFirst();

                            return getAlimentoInfo(res, comidaRegistrada);
                        })
                        .toList()
                : new ArrayList<>();

        ResponseGetRutina.ResponseGetRutinaData result =
                ObjectMapperUtils.map(rutina.get(), ResponseGetRutina.ResponseGetRutinaData.class);
        result.setComidasConsumidas(alimentoInfos);

        return Optional.of(result);
    }

    @Override
    public List<ResponseGetRutina.ResponseGetRutinaData> getListRutinas(RequestGetListRutinas model) {
        Optional<Usuario> usuario = usuarioRepositorio.findById(model.getEmail());

        if (usuario.isEmpty()) {
            throw new RuntimeException("El usuario con el email especificado no existe.");
        }

        List<ResponseGetRutina.ResponseGetRutinaData> result;

        if (!model.isFetchAll() && (model.getFechaInicio() == null || model.getFechaFin() == null)) {
            throw new RuntimeException("Si se piden rutinas en un rango de fechas, ambos extremos son obligatorios.");
        }

        if (!model.isFetchAll()) {
            // Se quiere rutinas en un rango
            result = usuario.get().getRutinas().stream()
                    .filter(rutina -> UtilidadesFechas
                        .isBetween(rutina.getFechaSeguimiento(), model.getFechaInicio(), model.getFechaFin()))
                    .map(rutina -> {
                        ResponseGetRutina.ResponseGetRutinaData res = ObjectMapperUtils.map(rutina, ResponseGetRutina.ResponseGetRutinaData.class);

                        List<RequestModificarRutina.AlimentoInfo> alimentoInfos = res.getComidasConsumidas();

                        if (alimentoInfos == null) {
                            // Mandamos una lista vacía indicando que no hay
                            // alimentos registrados para la fecha de esta rutina
                            alimentoInfos = new ArrayList<>();
                            return res;
                        }

                        alimentoInfos = alimentoInfos.stream()
                                        .map(alimentoInfo -> {

                                            List<Comida> comidasRegistradasUsuario = usuario.get().getComidasRegistradas();

                                            if (comidasRegistradasUsuario == null) {
                                                // TODO: revisar, temporal para evitar nullptr excep si el usuario no tiene comidas registradas
                                                comidasRegistradasUsuario = new ArrayList<>();
                                            }

                                            Optional<Comida> comidaRegistrada = comidasRegistradasUsuario.stream()
                                                    .filter(item -> item.getId().equals(alimentoInfo.getComidaId()))
                                                    .findFirst();

                                            return getAlimentoInfo(alimentoInfo, comidaRegistrada);
                                        })
                                .toList();

                        res.setComidasConsumidas(alimentoInfos);

                        return res;
                    })
                    .toList();
        } else {
            // Se piden todas las rutinas
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

                                    if (comidasRegistradasUsuario == null) {
                                        // TODO: revisar, temporal para evitar nullptr excep si el usuario no tiene comidas registradas
                                        comidasRegistradasUsuario = new ArrayList<>();
                                    }

                                    Optional<Comida> comidaRegistrada = comidasRegistradasUsuario.stream()
                                            .filter(item -> item.getId().equals(alimentoInfo.getComidaId()))
                                            .findFirst();

                                    return getAlimentoInfo(alimentoInfo, comidaRegistrada);
                                })
                                .toList();

                        res.setComidasConsumidas(alimentoInfos);

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
    public ResponseLoginData login(RequestLogin model) {
        ResponseLoginData response;
        Optional<Usuario> usuario = usuarioRepositorio.findById(model.getEmail());

        if (usuario.isEmpty()) {
            throw new RuntimeException("El usuario no existe.");
        }

        boolean verificado = cifrar.matches(model.getPassword(), usuario.get().getContrasena());

        if(verificado) {
            response = ObjectMapperUtils.map(usuario, ResponseLoginData.class);
            response.setLoggedAt(LocalDateTime.now());
        } else {
            throw new RuntimeException("Credenciales inválidos.");
        }

        return response;
    }

    @Override
    public ResponseGetAlimentos getListAlimentos(RequestGetAlimentos model) {
        Optional<Usuario> usuario = usuarioRepositorio.findById(model.getEmail());

        if (usuario.isEmpty()) {
            return ResponseGetAlimentos.builder()
                    .success(false)
                    .responseDescription("El usuario no existe")
                    .data(new ArrayList<>())
                    .build();
        }

        // TODO: hotfix
        if (usuario.get().getComidasRegistradas() == null) {
            usuario.get().setComidasRegistradas(new ArrayList<>());
        }

        List<ResponseGetAlimentos.GetAlimentoListItem> alimentos =
                usuario.get().getComidasRegistradas().stream()
                        .map(comida -> ObjectMapperUtils.map(comida, ResponseGetAlimentos.GetAlimentoListItem.class))
                        .toList();

        for (ResponseGetAlimentos.GetAlimentoListItem alimento : alimentos) {
            if (alimento.getVitaminas() == null) {
                alimento.setVitaminas(new ArrayList<>());
            }
        }

        return ResponseGetAlimentos.builder()
                .success(true)
                .responseDescription("Alimentos localizados con éxito")
                .data(alimentos)
                .build();
    }
}
