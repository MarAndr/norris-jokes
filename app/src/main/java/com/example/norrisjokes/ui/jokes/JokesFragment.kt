package com.example.norrisjokes.ui.jokes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.norrisjokes.R
import com.example.norrisjokes.ViewBindingFragment
import com.example.norrisjokes.databinding.FragmentJokesBinding

class JokesFragment : ViewBindingFragment<FragmentJokesBinding>(FragmentJokesBinding::inflate) {

    private val jokesViewModel: JokesViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}