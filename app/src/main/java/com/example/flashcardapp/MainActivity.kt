package com.example.flashcardapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.flashcardapp.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.submitButton.setOnClickListener {
            if(binding.usernameEditText.text.toString() == "cs501" && binding.passwordEditText.text.toString() == "123") {
                println("hello")
                val intent = Intent(this@MainActivity, FlashCardActivity::class.java)
                startActivity(intent)
            }
            else{
                Snackbar.make(findViewById(android.R.id.content), "Invalid Credentials!", Snackbar.LENGTH_SHORT).show()
            }
        }
    }
}