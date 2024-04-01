package com.strategic.grid.app

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.TextView
import androidx.core.content.ContextCompat

class IntroActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)

        window.apply {
            addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            statusBarColor = ContextCompat.getColor(context, R.color.background)
            navigationBarColor = ContextCompat.getColor(context, R.color.background)
        }

        val nextBtn = findViewById<TextView>(R.id.tv_next)
        nextBtn.setOnClickListener {
            val preferences = getSharedPreferences("MyPreferences", Context.MODE_PRIVATE)
            val editor = preferences.edit()
            editor.putBoolean("isFirstRun", false)
            editor.apply()

            var intent = Intent(this, AddPlayersActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }
    }
}