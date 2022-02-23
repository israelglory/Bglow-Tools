package com.example.bglowcalculator

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.bglowcalculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(){
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.ImageButton.setOnClickListener {
            calculate()
        }
        binding.ImageButton2.setOnClickListener {
            invoice()
        }
    }


     private fun calculate() {
        val intent = Intent(this, CalculateActivity::class.java)
         startActivity(intent)
    }
    private fun invoice() {
        val intent = Intent(this, SaveActivity::class.java)
        startActivity(intent)
    }

}







