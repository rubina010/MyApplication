package com.example.myapplication.ui.dashboard

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.myapplication.entity.WeatherData
import com.example.myapplication.utils.LATITUDE
import com.example.myapplication.utils.LONGITUDE
import com.example.myapplication.utils.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import timber.log.Timber
import javax.inject.Inject

class DashboardViewModel @Inject constructor(val app: Application, val repository: DashboardRepository,
                                             val compositeDisposable: CompositeDisposable, val schedulerProvider: SchedulerProvider) : AndroidViewModel(app) {
    val weatherData = MutableLiveData<List<WeatherData>>()
    fun getWeather() {
        Timber.i("this is called")
        compositeDisposable.add(repository.getWeatherData()
                .observeOn(schedulerProvider.ui())
                .subscribeOn(schedulerProvider.io())
                .subscribeBy(
                        onNext = {
                            weatherData.value = it.weather
                            /*repository.sharedPreferences.edit().apply {
                                putString(LATITUDE.toString(), it.coord.lat.toString())
                                putString(LONGITUDE.toString(), it.coord.lon.toString())
                            }.apply()*/
                        },
                        onError = {
                            Timber.i("weatherData error ${it.message}")
                        }
                ))
    }

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }
}