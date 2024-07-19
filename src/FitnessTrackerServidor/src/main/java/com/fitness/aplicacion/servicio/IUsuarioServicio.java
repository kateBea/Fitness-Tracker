package com.fitness.aplicacion.servicio;

import java.util.List;
import java.util.Optional;

import com.fitness.aplicacion.documentos.Usuario;
import com.fitness.aplicacion.dto.*;
import org.springframework.stereotype.Service;
import static com.fitness.aplicacion.dto.ResponseGetDatosUsuario.ResponseGetDatosUsuarioData;

@Service
public interface IUsuarioServicio {
	/**
	 * Inserta un nuevo usuario.
	 *
	 * @param user El modelo que contiene la información necesaria para insertar un nuevo usuario.
	 * @return true si la inserción fue exitosa, false en caso contrario.
	 */
	Boolean insertarUsuario(RequestRegistrarUsuario user);

	/**
	 * Servicio para depuración. Inserta usuarios directamente en la base de datos.
	 * Este método es utilizado para cargar datos y tener algo en la base de datos.
	 * Normalmente se debe usar insertarUsuario() que utiliza los DTOs.
	 *
	 * @param user El usuario a insertar.
	 * @return Un Optional que contiene el usuario insertado si la operación fue exitosa, de lo contrario un Optional vacío.
	 */
	Optional<Usuario> insertarDebug(Usuario user);

	/**
	 * Verifica la información de un usuario.
	 *
	 * @param user El modelo que contiene la información necesaria para verificar al usuario.
	 * @return Un Optional que contiene la información del usuario si la verificación fue exitosa, de lo contrario un Optional vacío.
	 */
	Optional<UsuarioInfo> verificarUsuario(UsuarioVerificar user);

	/**
	 * Obtiene la información de un usuario basado en su email.
	 *
	 * @param email El email del usuario.
	 * @return Un Optional que contiene la información del usuario si se encuentra, de lo contrario un Optional vacío.
	 */
	Optional<UsuarioInfo> informacionUsuario(String email);

	/**
	 * Actualiza la información de un usuario.
	 *
	 * @param user El modelo que contiene la información necesaria para actualizar al usuario.
	 * @return true si la actualización fue exitosa, false en caso contrario.
	 */
	Boolean actualizarUsuario(RequestRegistrarUsuario user);

	/**
	 * Borra un usuario de la base de datos.
	 *
	 * @param user El modelo que contiene la información necesaria para borrar al usuario.
	 * @return true si el borrado fue exitoso, false en caso contrario.
	 */
	Boolean borrarUsuario(UsuarioVerificar user);



	/**
	 * Cambia la contraseña del usuario.
	 *
	 * @param model El modelo que contiene la información necesaria para cambiar la contraseña.
	 * @return true si el cambio de contraseña fue exitoso, false en caso contrario.
	 */
	boolean cambiarPassword(RequestCambiarPassword model);

	/**
	 * Consulta los datos del usuario.
	 *
	 * @param model El modelo que contiene la información necesaria para consultar los datos del usuario.
	 * @return Un Optional que contiene los datos del usuario si se encuentran, de lo contrario un Optional vacío.
	 */
	Optional<ResponseGetDatosUsuarioData> consultar(RequestGetDatosUsuario model);

	/**
	 * Modifica los datos del usuario.
	 *
	 * @param model El modelo que contiene la información necesaria para modificar los datos del usuario.
	 * @return true si la modificación fue exitosa, false en caso contrario.
	 */
	Boolean modificar(RequestModificarDatosUsuario model);

	/**
	 * Registra una nueva dieta.
	 *
	 * @param model El modelo que contiene la información necesaria para registrar una dieta.
	 * @return true si el registro de la dieta fue exitoso, false en caso contrario.
	 */
	ResponseRegistrarDieta registrarDieta(RequestRegistrarDieta model);

	/**
	 * Modifica una dieta existente.
	 *
	 * @param model El modelo que contiene la información necesaria para modificar la dieta.
	 * @return true si la modificación de la dieta fue exitosa, false en caso contrario.
	 */
	Boolean modificar(RequestModificarDieta model);

	/**
	 * Obtiene la dieta de un usuario específico.
	 *
	 * @param model El modelo que contiene la información necesaria para obtener la dieta del usuario.
	 * @return Un Optional que contiene los datos de la dieta del usuario si se encuentran, de lo contrario un Optional vacío.
	 */
	Optional<ResponseGetDietaUsuario.ResponseGetDietaUsuarioData> getDieta(RequestGetDietaUsuario model);

	/**
	 * Obtiene una lista de las dietas del usuario.
	 *
	 * @param model El modelo que contiene la información necesaria para obtener la lista de dietas del usuario.
	 * @return Una lista de datos de las dietas del usuario.
	 */
	List<ResponseGetDietaUsuario.ResponseGetDietaUsuarioData> getListDietas(RequestGetListDietas model);

	/**
	 * Registra una nueva rutina.
	 *
	 * @param model El modelo que contiene la información necesaria para registrar una rutina.
	 * @return true si el registro de la rutina fue exitoso, false en caso contrario.
	 */
	ResponseRegistrarRutina registrarRutina(RequestRegistrarRutina model);

	/**
	 * Modifica una rutina existente.
	 *
	 * @param model El modelo que contiene la información necesaria para modificar la rutina.
	 * @return true si la modificación de la rutina fue exitosa, false en caso contrario.
	 */
	Boolean modificarRutina(RequestModificarRutina model);

	/**
	 * Obtiene una rutina específica del usuario.
	 *
	 * @param model El modelo que contiene la información necesaria para obtener la rutina del usuario.
	 * @return Un Optional que contiene los datos de la rutina del usuario si se encuentran, de lo contrario un Optional vacío.
	 */
	Optional<ResponseGetRutina.ResponseGetRutinaData> getRutina(RequestGetRutina model);

	/**
	 * Obtiene una lista de las rutinas del usuario.
	 *
	 * @param model El modelo que contiene la información necesaria para obtener la lista de rutinas del usuario.
	 * @return Una lista de datos de las rutinas del usuario.
	 */
	List<ResponseGetRutina.ResponseGetRutinaData> getListRutinas(RequestGetListRutinas model);

	/**
	 * Maneja la solicitud de inicio de sesión de un usuario.
	 *
	 * @param model La solicitud de inicio de sesión que contiene las credenciales del usuario.
	 * @return Una respuesta que contiene la información de inicio de sesión del usuario.
	 */
	ResponseLogin login(RequestLogin model);

	/**
	 * Devuelve el listado de alimentos registrado para el usuario.
	 *
	 * @param model El modelo de solicitud con la identificación del usuario.
	 * @return Una respuesta que contiene los alimentos registrados de este usuario.
	 */
	ResponseGetAlimentos getListAlimentos(RequestGetAlimentos model);
}