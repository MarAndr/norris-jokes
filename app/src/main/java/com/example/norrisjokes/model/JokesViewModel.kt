package com.example.norrisjokes.model

import android.content.Context
import android.net.ConnectivityManager
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.norrisjokes.data.Joke
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class JokesViewModel @ViewModelInject constructor(
    private val repository: JokesRepository
) : ViewModel() {

    private val _jokes = MutableLiveData<List<Joke>>()
    val jokes: LiveData<List<Joke>> = _jokes
    private val _downLoadingState = MutableStateFlow<DownloadingState>(DownloadingState.EMPTY)
    val downLoadingState: StateFlow<DownloadingState> = _downLoadingState

    fun addJoke(joke: Joke) {
        viewModelScope.launch {
            repository.addJoke(joke)
        }
    }

    fun getJokes() {
        viewModelScope.launch {
            _jokes.postValue(repository.getJokes())
        }
    }

    fun getRandomJokesFromServer(context: Context, jokesAmount: Int) {
        viewModelScope.launch {
            _downLoadingState.value = DownloadingState.LOADING
            try {
                if (hasInternetConnection(context)) {
                    if (jokesAmount != 0) {
                        _downLoadingState.value = DownloadingState.SUCCESS
                        _jokes.postValue(repository.getRandomJokes(jokesAmount))
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

    fun hasInternetConnection(context: Context): Boolean {
        val connMgr = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        var isWifiConn: Boolean = false
        var isMobileConn: Boolean = false

        connMgr.allNetworks.forEach { network ->
            connMgr.getNetworkInfo(network)?.apply {
                if (type == ConnectivityManager.TYPE_WIFI) {
                    isWifiConn = isWifiConn or isConnected
                }
                if (type == ConnectivityManager.TYPE_MOBILE) {
                    isMobileConn = isMobileConn or isConnected
                }
            }
        }
        return isMobileConn || isWifiConn
    }
}