package com.example.bglowcalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.bglowcalculator.databinding.ActivityEditBinding
import com.example.bglowcalculator.databinding.ActivitySaveBinding

class EditActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEditBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}