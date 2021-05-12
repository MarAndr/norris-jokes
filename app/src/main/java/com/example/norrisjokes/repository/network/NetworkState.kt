package com.example.norrisjokes.repository.network

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat.getSystemService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NetworkState @Inject constructor(private val application: Application) {

    fun hasInternetConnection(): Boolean {
        val cm = application.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val network = cm.activeNetwork
            val networkCapabilities = cm.getNetworkCapabilities(network)
            val isInternet = networkCapabilities?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
            return isInternet == true
        } else {
            var isWifiConn: Boolean = false
            var isMobileConn: Boolean = false

            cm.allNetworks.forEach { network ->
                cm.getNetworkInfo(network)?.apply {
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
}