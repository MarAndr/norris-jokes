package com.example.norrisjokes.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.norrisjokes.R
import com.example.norrisjokes.ViewBindingFragment
import com.example.norrisjokes.databinding.FragmentApiinfoBinding
import com.example.norrisjokes.databinding.FragmentJokesBinding
import com.example.norrisjokes.model.ApiInfoViewModel

class ApiInfoFragment : ViewBindingFragment<FragmentApiinfoBinding>(FragmentApiinfoBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.webViewApiInfoFragment.settings.javaScriptEnabled
        if (savedInstanceState == null){
        binding.apply {
        webViewApiInfoFragment.loadUrl("https://www.icndb.com/api/")
        }
        }

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        binding.webViewApiInfoFragment.saveState(outState)
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        if (savedInstanceState != null) {
            binding.webViewApiInfoFragment.restoreState(savedInstanceState)
        }
    }
}