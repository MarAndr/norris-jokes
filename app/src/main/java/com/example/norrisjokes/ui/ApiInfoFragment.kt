package com.example.norrisjokes.ui

import android.os.Bundle
import android.view.View
import com.example.norrisjokes.ViewBindingFragment
import com.example.norrisjokes.databinding.FragmentApiinfoBinding

class ApiInfoFragment :
    ViewBindingFragment<FragmentApiinfoBinding>(FragmentApiinfoBinding::inflate) {

    companion object {
        private const val API_URL = "https://www.icndb.com/api/"
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (savedInstanceState == null) {
            binding.apply {
                webViewApiInfoFragment.loadUrl(API_URL)
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