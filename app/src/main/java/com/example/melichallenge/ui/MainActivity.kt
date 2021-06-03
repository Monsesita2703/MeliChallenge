package com.example.melichallenge.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Toast
import com.example.melichallenge.R
import com.example.melichallenge.utils.NetworkUtil
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val networkUtil = NetworkUtil(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))

        networkUtil.result = { isAvailable ->
            runOnUiThread {
                if (isAvailable) {
                    isInternetAvailable = true
                } else {
                    isInternetAvailable = false
                    Toast.makeText(this, getString(R.string.str_title_connectivity_error),
                        Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        networkUtil.register()
    }

    override fun onStop() {
        super.onStop()
        networkUtil.unregister()
    }

    companion object {
        var isInternetAvailable: Boolean = true
    }
}