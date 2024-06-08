package com.example.fitnesstrackerapp.uiViewModel

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitnesstrackerapp.basedatos.entidades.Comida
import com.example.fitnesstrackerapp.basedatos.entidades.FechaDia
import com.example.fitnesstrackerapp.basedatos.entidades.Rutina
import com.example.fitnesstrackerapp.basedatos.entidades.UsuarioInfo
import com.example.fitnesstrackerapp.basedatos.repositorio.ComidaRepositorio
import com.example.fitnesstrackerapp.basedatos.repositorio.FechaRepositorio
import com.example.fitnesstrackerapp.basedatos.repositorio.RutinaRepositorio
import com.example.fitnesstrackerapp.basedatos.repositorio.UsuarioRepositorio
import com.example.fitnesstrackerapp.objetos.response.RutinasResponse
import com.example.fitnesstrackerapp.objetos.response.Sexo
import com.example.fitnesstrackerapp.repositorio.RepositorioRetrofit
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.Period
import javax.inject.Inject

@RequiresApi(Build.VERSION_CODES.O)
@HiltViewModel
class AlimentosViewModel @Inject constructor(
    private val dao: UsuarioRepositorio,
    private val fDao: FechaRepositorio,
    private val cDao: ComidaRepositorio,
    private val rDao: RutinaRepositorio,
    private val eventosViewModel: EventosViewModel = EventosViewModel()
): ViewModel(){

    @RequiresApi(Build.VERSION_CODES.O)
    private val repositorio: RepositorioRetrofit = RepositorioRetrofit()

    private val _usuario = MutableStateFlow(UsuarioInfo())

    private val _fecha = MutableStateFlow(LocalDate.now())
    val fecha:StateFlow<LocalDate> get() = _fecha.asStateFlow()

    private val _comidas = MutableStateFlow<List<Comida>>(listOf())
    val comidas:StateFlow<List<Comida>> get() = _comidas.asStateFlow()

    private val _kcal  = MutableStateFlow(0f)
    val kcal:StateFlow<Float> get() = _kcal.asStateFlow()

    private val _proteinas  = MutableStateFlow(0f)
    val proteinas:StateFlow<Float> get() = _proteinas.asStateFlow()

    private val _grasas  = MutableStateFlow(0f)
    val grasas:StateFlow<Float> get() = _grasas.asStateFlow()

    private val _carbohidratos  = MutableStateFlow(0f)
    val carbohidratos:StateFlow<Float> get() = _carbohidratos.asStateFlow()

    private val _agua = MutableStateFlow(0)
    val agua :StateFlow<Int> get() = _agua.asStateFlow()

    private val _aguaReq = MutableStateFlow(0f)
    val aguaReq :StateFlow<Float> get() = _aguaReq.asStateFlow()

    init {
        try {
            calcularKCal()
            requerimientoAgua()
            cogerRutinas()
        }catch (e:Exception){
            Log.d("ERROR",e.message.toString())
        }
    }

    fun cogerRutinas(){
        viewModelScope.launch (Dispatchers.IO){
            eventosViewModel.setState(EventosUIState.Cargando)
            val fecha = LocalDate.parse(fDao.getFecha())
            _fecha.value = fecha
            val  token = dao.getToken()?:""

            val entrega = repositorio.getListasUsuario(token,fecha.minusDays(1).toString(),fecha.plusDays(1).toString())
            if(entrega.rutinas.isNotEmpty()){
                pasarABBDD(entrega.rutinas.get(0))
                fDao.actualizar(FechaDia(1,fecha.toString(),true))
            }else{
                fDao.actualizar(FechaDia(1,fecha.toString(),false))
                _comidas.value = cDao.getComidasDia(fecha.toString()) ?: listOf()
            }
            eventosViewModel.setState(EventosUIState.Done)
        }
    }

    fun pasarABBDD(rutinaResponse: RutinasResponse){
        var rutina:Rutina = Rutina()

        rutina.id = rutinaResponse.id
        rutina.tiempoSueno = rutinaResponse.tiempoSueno
        rutina.pasosRealizados = rutinaResponse.pasosRealizados
        rutina.fechaSeguimiendo = rutinaResponse.fechaSeguimiendo
        rutina.caloriasQuemadas = rutinaResponse.caloriasQuemadas
        rutina.fechaUltimaModificacion = rutinaResponse.fechaUltimaModificacion
        rutina.frecuenciaCardiaca = rutinaResponse.frecuenciaCardiaca
        rutina.nivelOxigenoSangre = rutinaResponse.nivelOxigenoSangre

        viewModelScope.launch (Dispatchers.IO){
            rDao.insertar(rutina)
            if(rutinaResponse.comidas != null){
                rutinaResponse.comidas.forEach {
                    if(cDao.verificarComida(it.comidaId,it.horaConsumo) == null)
                        cDao.insertar(it)
                }
                _comidas.value = cDao.getComidasDia(rutina.fechaSeguimiendo)
            }else{
                _comidas.value = listOf()
            }
        }
    }

    fun diaMenos(){
        viewModelScope.launch (Dispatchers.IO){
            var fecha = LocalDate.parse(fDao.getFecha())
            fecha = fecha.minusDays(1)
            fDao.actualizar(FechaDia(1,fecha.toString()))
            requerimientoAgua()
            cogerRutinas()
        }
    }

    fun diaMas(){
        viewModelScope.launch (Dispatchers.IO){
            var fecha = LocalDate.parse(fDao.getFecha())
            fecha = fecha.plusDays(1)
            fDao.actualizar(FechaDia(1,fecha.toString()))
            requerimientoAgua()
            cogerRutinas()
        }
    }


    fun calcularKCal(){
        /*
        Para hombres: RMB = (10 x peso en kg) + (6.25 x altura en cm) – (5 x edad en años) + 5.
        Para mujeres: RMB = (10 x peso en kg) + (6.25 x altura en cm) – (5 x edad en años) – 161.
         */
        viewModelScope.launch (Dispatchers.IO){
            eventosViewModel.setState(EventosUIState.Cargando)
            _usuario.value = dao.getUsuario()
            val hombreMujer = if(_usuario.value.sexo == Sexo.Mujer) -161 else 5
            val anios = Period.between(LocalDate.parse(_usuario.value.fechaNacimiento), LocalDate.now()).years

            val rmb = (10 * _usuario.value.peso) + (6.25 * _usuario.value.altura) - (5 * anios)  + hombreMujer

            _kcal.value = rmb.toFloat()
            _proteinas.value = _kcal.value * 0.4f / 4
            _grasas.value = _kcal.value * 0.3f / 9
            _carbohidratos.value = _kcal.value * 0.3f / 4
        }
    }

    fun actualizarAgua(aumento:Boolean){
        viewModelScope.launch (Dispatchers.IO){
            try{
                eventosViewModel.setState(EventosUIState.Cargando)
                val valorAgua = if(aumento) 250 else -250
                if(_agua.value + valorAgua >= 0){
                    val fecha = fDao.getFecha()
                    val rutina = rDao.getRutina(fecha)

                    if(rutina != null){
                        _agua.value += valorAgua
                        rDao.actualizarAgua(_agua.value,fecha)
                    }else{
                        val rutinaCrear = Rutina()
                        _agua.value += valorAgua
                        rutinaCrear.fechaSeguimiendo = fecha
                        rutinaCrear.aguaconsumida = _agua.value
                        rDao.insertar(rutinaCrear)
                    }

                }
                eventosViewModel.setState(EventosUIState.Done)
            }catch (e:Exception){
                Log.d("ERROR",e.message.toString())
            }
        }

    }

    fun requerimientoAgua(){
        viewModelScope.launch (Dispatchers.IO){
            val fecha = fDao.getFecha()

            _agua.value = rDao.cogerAgua(fecha)
            _aguaReq.value = (0.033f * _usuario.value.peso) * 1000f
        }
    }
}