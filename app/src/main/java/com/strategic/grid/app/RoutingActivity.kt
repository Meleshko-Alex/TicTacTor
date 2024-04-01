package com.strategic.grid.app

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class RoutingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val intent = if (isFirstRun()) Intent(this, IntroActivity::class.java)
        else Intent(this, AddPlayersActivity::class.java)

        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }

    private fun isFirstRun(): Boolean {
        val preferences = getSharedPreferences("MyPreferences", Context.MODE_PRIVATE)
        return preferences.getBoolean("isFirstRun", true)
    }
}