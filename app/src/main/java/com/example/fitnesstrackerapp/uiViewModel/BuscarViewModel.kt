package com.example.fitnesstrackerapp.uiViewModel

import android.icu.text.IDNA.Info
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitnesstrackerapp.basedatos.entidades.Comida
import com.example.fitnesstrackerapp.basedatos.entidades.Orden
import com.example.fitnesstrackerapp.basedatos.entidades.Rutina
import com.example.fitnesstrackerapp.basedatos.entidades.TipoComida
import com.example.fitnesstrackerapp.basedatos.repositorio.ComidaRepositorio
import com.example.fitnesstrackerapp.basedatos.repositorio.FechaRepositorio
import com.example.fitnesstrackerapp.basedatos.repositorio.RutinaRepositorio
import com.example.fitnesstrackerapp.basedatos.repositorio.UsuarioRepositorio
import com.example.fitnesstrackerapp.objetos.request.RutinaRequest
import com.example.fitnesstrackerapp.objetos.response.RutinasResponse
import com.example.fitnesstrackerapp.objetos.response.responseAlimentos.InformacionComida
import com.example.fitnesstrackerapp.repositorio.RepositorioRetrofit
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class BuscarViewModel @Inject constructor(
    private val dao: UsuarioRepositorio,
    private val fDao: FechaRepositorio,
    private val cDao: ComidaRepositorio,
    private val rDao: RutinaRepositorio,
    private val eventosViewModel: EventosViewModel = EventosViewModel()
): ViewModel() {

    @RequiresApi(Build.VERSION_CODES.O)
    private val repositorio: RepositorioRetrofit = RepositorioRetrofit()

    //necesito informacionComida
    private val _alimentos = MutableStateFlow<List<InformacionComida>>(listOf())
    val alimentos: StateFlow<List<InformacionComida>> get() = _alimentos.asStateFlow()

    private val _done = MutableStateFlow<Boolean>(false)
    val done:StateFlow<Boolean> get() = _done.asStateFlow()

    @RequiresApi(Build.VERSION_CODES.O)
    fun buscarAlimentos(cadena:String){
        viewModelScope.launch (Dispatchers.IO){
            try {
                eventosViewModel.setState(EventosUIState.Cargando)
                val token = dao.getToken()?:""
                val response = repositorio.buscarAlimentos(token,cadena)
                _alimentos.value = response.pistas.alimentos
                eventosViewModel.setState(EventosUIState.Done)
            }catch (e:Exception){
                eventosViewModel.setState(EventosUIState.Error("No se han podido cargar los alimentos"))
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun actualizar(comida:InformacionComida, numero:Int){
        viewModelScope.launch (Dispatchers.IO){
            try {
                val fecha = fDao.getFecha()
                eventosViewModel.setState(EventosUIState.Cargando)
                val comidaBBDD = mapearInformacion(comida,numero,fecha)
                val token = dao.getToken()?:""

                cDao.insertar(comidaBBDD)

                val existeRutina = fDao.existeRutina()

                if(existeRutina){
                    val rutinas = rDao.getRutina(fecha)
                    val rutinaResponse = mapearRutina(rutinas)

                    rutinaResponse.comidas = cDao.getComidasDia(fecha)

                    repositorio.actualizarRutina(token,rutinaResponse)
                }else{
                    val rutinaResponse = RutinasResponse()
                    rutinaResponse.comidas = cDao.getComidasDia(fecha)

                    val request = RutinaRequest()
                    rutinaResponse.id = repositorio.insertarRutina(token,request).id
                    rutinaResponse.fechaSeguimiendo = fecha

                    repositorio.actualizarRutina(token, rutinaResponse)
                }

                _done.value = true
                eventosViewModel.setState(EventosUIState.Done)
            }
            catch (e:Exception){
                Log.e("ERROR",e.message.toString())
                eventosViewModel.setState(EventosUIState.Error("No se ha podido insertar el alimento"))
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun mapearRutina(rutina: Rutina):RutinasResponse{
       val rutinaR = RutinasResponse()
        rutinaR.id = rutina.id
        rutinaR.tiempoSueno = rutina.tiempoSueno
        rutinaR.tiempoSueno = rutina.tiempoSueno
        rutinaR.pasosRealizados = rutina.pasosRealizados
        rutinaR.fechaSeguimiendo = rutina.fechaSeguimiendo
        rutinaR.fechaUltimaModificacion = rutina.fechaUltimaModificacion
        rutinaR.caloriasQuemadas = rutina.caloriasQuemadas
        rutinaR.frecuenciaCardiaca = rutina.frecuenciaCardiaca
        rutinaR.nivelOxigenoSangre = rutina.nivelOxigenoSangre

        return rutinaR
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun mapearInformacion(comidaInfo:InformacionComida, numero:Int,fecha:String): Comida {
        var comida = Comida()
        comida.nombre = comidaInfo.comida.nombre
        comida.comidaId = comidaInfo.comida.id
        comida.descripcion = comidaInfo.comida.descripcion
        comida.tipo = getTipoComida(numero)
        comida.grasas = comidaInfo.comida.nutrientes.grasas
        comida.proteinas = comidaInfo.comida.nutrientes.proteinas
        comida.carbohidratos = comidaInfo.comida.nutrientes.carbohidratos
        comida.calorias = comidaInfo.comida.nutrientes.calorias
        comida.horaConsumo = fecha
        comida.orden = Orden.PRIMER_PLATO
        return comida
    }

    fun getTipoComida(numero:Int):TipoComida{
        val tipo:TipoComida
        when(numero){
            0 -> {tipo = TipoComida.DESAYUNO}
            1 -> {tipo = TipoComida.MERIENDA_MATUTINA}
            2 -> {tipo = TipoComida.ALMUERZO}
            3 -> {tipo = TipoComida.MERIENDA_VESPERTINA}
            else -> {tipo = TipoComida.CENA}
        }
        return tipo
    }

}