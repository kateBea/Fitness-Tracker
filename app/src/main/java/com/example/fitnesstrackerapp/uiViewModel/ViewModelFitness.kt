package com.example.fitnesstrackerapp.uiViewModel

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import com.example.fitnesstrackerapp.repositorio.RepositorioRetrofit

@RequiresApi(Build.VERSION_CODES.O)
class ViewModelFitness(
    private val repositorio: RepositorioRetrofit = RepositorioRetrofit()
):ViewModel()
{

}