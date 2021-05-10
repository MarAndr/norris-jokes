package com.example.norrisjokes.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.norrisjokes.data.Joke
import com.example.norrisjokes.databinding.ItemJokeBinding

class JokesListAdapter: ListAdapter<Joke, JokesListAdapter.JokesViewHolder>(JokesListDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JokesViewHolder {
        val binding = ItemJokeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return JokesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: JokesViewHolder, position: Int) {
        val joke = getItem(position)
        holder.bind(joke)

    }

    class JokesViewHolder(private val binding: ItemJokeBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(joke: Joke){
            binding.textViewItemJokeJokeText.text = joke.text
        }
    }
    class JokesListDiffUtil: DiffUtil.ItemCallback<Joke>(){
        override fun areItemsTheSame(oldItem: Joke, newItem: Joke): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Joke, newItem: Joke): Boolean {
            return oldItem == newItem
        }
    }
}