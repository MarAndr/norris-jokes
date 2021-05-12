package com.example.norrisjokes.viewmodel

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.norrisjokes.data.Joke
import com.example.norrisjokes.repository.JokesRepository
import com.example.norrisjokes.repository.network.NetworkState
import com.example.norrisjokes.state.DownloadingState
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class JokesViewModel @ViewModelInject constructor(
    private val repository: JokesRepository,
    private val networkState: NetworkState
) : ViewModel() {

    private val _jokes = MutableLiveData<List<Joke>>()
    val jokes: LiveData<List<Joke>> = _jokes
    private val _downLoadingState = MutableStateFlow<DownloadingState>(DownloadingState.EMPTY)
    val downLoadingState: StateFlow<DownloadingState> = _downLoadingState

    fun getRandomJokesFromServer(jokesAmount: Int) {
        viewModelScope.launch {
            _downLoadingState.value = DownloadingState.LOADING
            try {
                if (networkState.hasInternetConnection()) {
                    if (jokesAmount != 0) {
                        val result = async {
                        _jokes.postValue(repository.getRandomJokes(jokesAmount))
                        }
                        result.await()
                        _downLoadingState.value = DownloadingState.SUCCESS
                    } else {
                        _downLoadingState.value =
                            DownloadingState.ERROR("You should enter the amount of jokes before!")
                    }
                } else {
                    _downLoadingState.value =
                        DownloadingState.ERROR("You need to connect to the internet")
                }

            } catch (e: Exception) {
                _downLoadingState.value = DownloadingState.ERROR("Unknown error. Try it later")
            }
        }
    }
}