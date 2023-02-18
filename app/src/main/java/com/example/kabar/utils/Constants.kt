package com.example.kabar.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment

class Constants {

    companion object {
        const val VIEW_PAGER_ITEMS = 3
        const val MILLIS_IN_FUTURE = 2_000L
        const val COUNT_DOWN_INTERVAL = 1_000L
        const val PAGER_SCREEN_COUNT = 7
        const val API_KEY1 = "f7474a66c6054ab7bdf44915b6852732"
        const val BASE_URL = "https://newsapi.org/"
        const val API_KEY2 = "69de666bd53b4f348c2e87a5bad0383f"
    }
}

@RequiresApi(Build.VERSION_CODES.M)
fun Fragment.checkOnline(context: Context):Boolean{
    val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    if (connectivityManager != null) {
        val capabilities =
            connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
        if (capabilities != null) {
            if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                return true
            } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                return true
            } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
                return true
            }
        }
    }
    return false
}

//fun isOnline(context: Context): Boolean {
//
//}