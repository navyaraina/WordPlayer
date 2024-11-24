package com.example.wordplayer

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val playButton: Button = findViewById(R.id.playButton)
        val quitButton: Button = findViewById(R.id.quitButton)

        playButton.setOnClickListener {
            val intent = Intent(this, GameMode::class.java)
            startActivity(intent)
        }
        quitButton.setOnClickListener {
            finish()
        }
    }
}
