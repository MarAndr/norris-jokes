package com.example.norrisjokes.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.norrisjokes.ViewBindingFragment
import com.example.norrisjokes.data.Joke
import com.example.norrisjokes.databinding.FragmentJokesBinding
import com.example.norrisjokes.model.JokesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class JokesFragment : ViewBindingFragment<FragmentJokesBinding>(FragmentJokesBinding::inflate) {

    companion object{
        private const val TAG = "JokesFragment"
    }

    private val jokesViewModel: JokesViewModel by viewModels()
    private var jokesListAdapter: JokesListAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        jokesViewModel.getRandomJokesFromServer(5)
        initList()
        observeLiveData()
    }

    private fun observeLiveData() {
        jokesViewModel.jokes.observe(viewLifecycleOwner){jokes ->
            Log.d(TAG, "observeLiveData: jokes = $jokes")
            jokesListAdapter?.submitList(jokes)
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

    override fun onDestroy() {
        super.onDestroy()
        jokesListAdapter = null
    }
}