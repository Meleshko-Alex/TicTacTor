package com.strategic.grid.app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.strategic.grid.app.databinding.ActivityAddPlayersBinding

class AddPlayersActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddPlayersBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddPlayersBinding.inflate(layoutInflater).also { setContentView(it.root) }

        window.apply {
            addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            statusBarColor = ContextCompat.getColor(context, R.color.background)
            navigationBarColor = ContextCompat.getColor(context, R.color.background)
            setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
        }

        binding.tvStart.setOnClickListener {
            if (binding.etPlayerOne.text.toString().isBlank() || binding.etPlayerTwo.text.toString().isBlank()) {
                Toast.makeText(this, "Please enter player names", Toast.LENGTH_SHORT).show()
            } else {
                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra("playerOne", binding.etPlayerOne.text.toString())
                intent.putExtra("playerTwo", binding.etPlayerTwo.text.toString())
                startActivity(intent)
            }
        }
    }
}