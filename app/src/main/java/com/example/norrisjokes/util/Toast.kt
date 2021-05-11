package com.example.norrisjokes.util

import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar

fun <T:Fragment>T.createToast(message: String){
    Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
}