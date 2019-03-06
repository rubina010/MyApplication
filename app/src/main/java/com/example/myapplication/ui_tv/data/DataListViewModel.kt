package com.example.myapplication.ui_tv.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.myapplication.ui_tv.entity.SongEntity
import com.example.myapplication.utils.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class DataListViewModel @Inject constructor(val app: Application, val repository: DashboardRepository,
                                            private val compositeDisposable: CompositeDisposable,
                                            val schedulerProvider: SchedulerProvider) : AndroidViewModel(app) {
    var listOfSongs = arrayListOf(SongData("Colors", "Halsey"), SongData("Sorry", "Halsey")
            , SongData("Closer", "Halsey"), SongData("Pretty Girl", "Halsey"))
    var listOfVideos = arrayListOf(VideoData("One More Light", "Song"), VideoData("Heavy", "Song")
            , VideoData("Numb", "Song"), VideoData("Youth", "Song"))
    var listOfMovies = arrayListOf(MovieData("Everest", "Adventure"), MovieData("Friends", "Comedy")
            , MovieData("Taking 5", "Love Story"), MovieData("Annabella", "Horrer"))
    var songLiveData = MutableLiveData<List<SongEntity>>()
    var videoLiveData = MutableLiveData<List<VideoData>>()
    var movieLiveData = MutableLiveData<List<MovieData>>()

    fun initialize() {
        // songLiveData.postValue(listOfSongs)
        videoLiveData.postValue(listOfVideos)
        movieLiveData.postValue(listOfMovies)
    }

    fun addSongsToDb(songName: String, singer: String) {
        repository.addSongs(songName, singer)
        /* compositeDisposable.add(repository.addSongs(songName, singer)
                 .subscribeOn(schedulerProvider.io()).observeOn(schedulerProvider.ui()))*/

    }

    fun getSongs() = repository.getSongs()

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }

}