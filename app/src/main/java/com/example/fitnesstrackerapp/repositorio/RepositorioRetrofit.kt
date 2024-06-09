package com.example.fitnesstrackerapp.repositorio

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.example.fitnesstrackerapp.apiservicio.ApiServicio
import com.example.fitnesstrackerapp.apiservicio.getRetrofitClient
import com.example.fitnesstrackerapp.objetos.login.UsuarioVerificar
import com.example.fitnesstrackerapp.objetos.request.RegisterRequest
import com.example.fitnesstrackerapp.objetos.request.RutinaRequest
import com.example.fitnesstrackerapp.objetos.request.UsuarioRequest
import com.example.fitnesstrackerapp.objetos.response.AlimentosResponse
import com.example.fitnesstrackerapp.objetos.response.ResponseInsertar
import com.example.fitnesstrackerapp.objetos.response.ResponseLogin
import com.example.fitnesstrackerapp.objetos.response.RutinasResponse
import com.example.fitnesstrackerapp.objetos.response.responseAlimentos.ResponseAlimentos
import com.example.fitnesstrackerapp.objetos.usuario.DatosUsuario
import retrofit2.Retrofit
import retrofit2.create

/**
 * Clase que actúa como repositorio para realizar llamadas a la API utilizando Retrofit.
 *
 * @param apiServicio Instancia de Retrofit que proporciona las operaciones de la API.
 */
@RequiresApi(Build.VERSION_CODES.O)
class RepositorioRetrofit(
    private val apiServicio: Retrofit = getRetrofitClient()
) {
    /**
     * Realiza la verificación de usuario mediante la API.
     *
     * @param user El objeto de usuario para la verificación.
     * @return La respuesta de la API para la verificación de usuario.
     */
    suspend fun verificar(user: UsuarioVerificar): ResponseLogin {
        return apiServicio.create(ApiServicio::class.java).hacerLogin(user)
    }

    /**
     * Obtiene los datos del usuario utilizando el token de autorización.
     *
     * @param token El token de autorización para la solicitud.
     * @return Los datos del usuario obtenidos de la API.
     */
    suspend fun getDatosUsuario(token: String): DatosUsuario {
        return apiServicio.create(ApiServicio::class.java).getDatosUsuario("Bearer $token")
    }

    /**
     * Actualiza los datos del usuario utilizando el token de autorización.
     *
     * @param token El token de autorización para la solicitud.
     * @param usuarioRequest El objeto de solicitud para actualizar los datos del usuario.
     */
    suspend fun actualizarUsuario(token: String, usuarioRequest: UsuarioRequest) {
        return apiServicio.create(ApiServicio::class.java).actualizarUsuario("Bearer $token", usuarioRequest)
    }

    /**
     * Obtiene las rutinas del usuario para un rango de fechas utilizando el token de autorización.
     *
     * @param token El token de autorización para la solicitud.
     * @param fechaInicio La fecha de inicio del rango de fechas.
     * @param fechaFinal La fecha final del rango de fechas.
     * @return Las rutinas del usuario para el rango de fechas especificado.
     */
    suspend fun getRutinasUsuario(token: String, fechaInicio: String, fechaFinal: String): AlimentosResponse {
        return apiServicio.create(ApiServicio::class.java).getRutinasUsuario("Bearer $token", false, fechaInicio, fechaFinal)
    }

    /**
     * Busca alimentos según una cadena de búsqueda utilizando el token de autorización.
     *
     * @param token El token de autorización para la solicitud.
     * @param cadena La cadena de búsqueda para buscar alimentos.
     * @return La respuesta de la API que contiene los alimentos encontrados.
     */
    suspend fun buscarAlimentos(token: String, cadena: String): ResponseAlimentos {
        return apiServicio.create(ApiServicio::class.java).buscarAlimentos("Bearer $token", cadena)
    }

    /**
     * Inserta una nueva rutina utilizando el token de autorización.
     *
     * @param token El token de autorización para la solicitud.
     * @param rutina El objeto de rutina a insertar.
     * @return La respuesta de la API después de insertar la rutina.
     */
    suspend fun insertarRutina(token: String, rutina: RutinaRequest): ResponseInsertar {
        return apiServicio.create(ApiServicio::class.java).insertarRutina("Bearer $token", rutina)
    }

    /**
     * Actualiza una rutina existente utilizando el token de autorización.
     *
     * @param token El token de autorización para la solicitud.
     * @param rutina El objeto de rutina actualizado.
     */
    suspend fun actualizarRutina(token: String, rutina: RutinasResponse) {
        return apiServicio.create(ApiServicio::class.java).modificarDieta("Bearer $token", rutina)
    }

    /**
     * Registra un nuevo usuario utilizando los detalles de registro proporcionados.
     *
     * @param user El objeto de solicitud para registrar un nuevo usuario.
     */
    suspend fun register(user: RegisterRequest) {
        return apiServicio.create(ApiServicio::class.java).register(user)
    }
}