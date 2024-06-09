package com.example.fitnesstrackerapp.basedatos.repositorio

import com.example.fitnesstrackerapp.basedatos.dao.ComidaDao
import com.example.fitnesstrackerapp.basedatos.entidades.Comida
import javax.inject.Inject

/**
 * Clase repositorio para gestionar operaciones relacionadas con la entidad de Comida.
 *
 * @property comidaDao Objeto de acceso a datos (DAO) para la entidad de Comida.
 */
class ComidaRepositorio @Inject constructor(private val comidaDao: ComidaDao) {

    /**
     * Inserta una nueva comida en la base de datos.
     *
     * @param comida La comida a insertar.
     */
    fun insertar(comida: Comida) = comidaDao.insertar(comida)

    /**
     * Obtiene todas las comidas para una fecha específica.
     *
     * @param fecha La fecha en formato de cadena (yyyy-MM-dd) para la cual se desean obtener las comidas.
     * @return Una lista de comidas para la fecha especificada.
     */
    fun getComidasDia(fecha: String) = comidaDao.getComidasDia(fecha)

    /**
     * Verifica si una comida específica existe para una fecha dada.
     *
     * @param id El ID de la comida a verificar.
     * @param fecha La fecha en formato de cadena (yyyy-MM-dd) para la cual se desea verificar la existencia de la comida.
     * @return La comida correspondiente al ID y fecha especificados, si existe; de lo contrario, null.
     */
    fun verificarComida(id: String, fecha: String) = comidaDao.verificarComidas(id, fecha)
}





