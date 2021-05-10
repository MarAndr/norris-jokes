package com.example.norrisjokes.model

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.norrisjokes.data.Joke
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


class JokesViewModel @ViewModelInject constructor(
    private val repository: JokesRepository
) : ViewModel() {

    private val _jokes = MutableLiveData<List<Joke>>()
    val jokes: LiveData<List<Joke>> = _jokes

    fun addJoke(joke: Joke){
        viewModelScope.launch {
            repository.addJoke(joke)
        }
    }

    fun getJokes(){
        viewModelScope.launch {
            _jokes.postValue(repository.getJokes())
        }
    }
}