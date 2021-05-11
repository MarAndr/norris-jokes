package com.example.norrisjokes.ui

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.norrisjokes.R
import com.example.norrisjokes.ViewBindingFragment
import com.example.norrisjokes.data.Joke
import com.example.norrisjokes.databinding.FragmentJokesBinding
import com.example.norrisjokes.model.DownloadingState
import com.example.norrisjokes.model.JokesViewModel
import com.example.norrisjokes.util.createToast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class JokesFragment : ViewBindingFragment<FragmentJokesBinding>(FragmentJokesBinding::inflate) {

    private val jokesViewModel: JokesViewModel by viewModels()
    private var jokesListAdapter: JokesListAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.materialButton.setOnClickListener {
            val jokesAmount = binding.editTextJokesFragment.text.toString().trim().toIntOrNull() ?: 0
            jokesViewModel.getRandomJokesFromServer(requireContext(), jokesAmount)
        }
        initList()
        observeData()
    }

    private fun observeData() {
        jokesViewModel.jokes.observe(viewLifecycleOwner) { jokes ->
            jokesListAdapter?.submitList(jokes)
        }

        lifecycleScope.launch {
            jokesViewModel.downLoadingState.collect { downloadingState ->
                when (downloadingState) {
                    is DownloadingState.LOADING -> isLoading(true)
                    is DownloadingState.SUCCESS -> isLoading(false)
                    is DownloadingState.ERROR -> {
                        isLoading(false)
                        createToast(downloadingState.message)
                    }
                    else -> Unit
                }
            }

        }
    }

    private fun initList() {
        jokesListAdapter = JokesListAdapter()
        val divider = DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL)
        binding.apply {
            recyclerViewJokesFragment.apply {
                adapter = jokesListAdapter
                layoutManager = LinearLayoutManager(requireContext())
                setHasFixedSize(true)
                addItemDecoration(divider)
            }
        }
    }

    private fun isLoading(loadingState: Boolean) {
        binding.progressJokesFragment?.isVisible = loadingState
        binding.materialButton.isEnabled = loadingState.not()
    }

    override fun onDestroy() {
        super.onDestroy()
        jokesListAdapter = null
    }
}