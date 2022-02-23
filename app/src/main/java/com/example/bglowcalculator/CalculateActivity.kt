package com.example.bglowcalculator

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.example.bglowcalculator.databinding.ActivityCalculateBinding
import com.example.bglowcalculator.databinding.ActivityMainBinding
import java.text.NumberFormat
import java.util.*

class CalculateActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {
    private lateinit var binding: ActivityCalculateBinding
    private var selectedOption: String = "200spm"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCalculateBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val spinner: Spinner = binding.spinner
        ArrayAdapter.createFromResource(
            this,
            R.array.speed_array,
            android.R.layout.simple_expandable_list_item_1
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_expandable_list_item_1)
            spinner.adapter = adapter
        }
        spinner.setSelection(0)
        spinner.onItemSelectedListener = this
        binding.calculate.setOnClickListener { calculate() }
    }

    @SuppressLint("StringFormatInvalid", "StringFormatMatches", "SetTextI18n")
    private fun calculate() {
        val stitches = binding.stitches.text.toString()
        val speed: Int
        if (stitches.isNotEmpty()) {
            speed = when (selectedOption) {
                "200spm" -> { 200 }
                "250spm" -> { 250}
                "300spm" -> { 300}
                "350spm" -> { 350 }
                "400spm" -> { 400 }
                "450spm" -> { 450 }
                "500spm" -> { 500 }
                "550spm" -> { 550 }
                "600spm" -> { 600 }
                "650spm" -> { 650 }
                "700spm" -> { 700 }
                "750spm" -> { 750 }
                "800spm" -> { 800 }
                "850spm" -> { 850 }
                "900spm" -> { 900 }
                "950spm" -> { 950 }
                else -> { 1000 }
            }

            val cost = stitches.toInt()
            val totalmin = cost / speed
            binding.resultTime.text =getString(R.string.total_time, totalmin) + "min"
        }

        val price = binding.stitches.text.toString()
        val quan = binding.quantity.text.toString()
        val stit = price.toInt()
        val pre = quan.toInt()
        val cond : Int
        if(quan.isNotEmpty()){
            cond = when(pre){
                1 -> {100
                }
                2 -> {
                    100
                }
                3 -> {
                    100
                }
                4 -> 100
                5 -> 100
                6 -> 100
                7 -> 100
                8 -> 100
                9-> 100
                10 -> 100
                11 -> 70
                12 -> 70
                13 -> 70
                14 -> 70
                15 -> 70
                16 -> 70
                17 -> 70
                18 -> 70
                19 -> 70
                20 -> 70
                21 -> 70
                22 -> 70
                23 -> 70
                24 -> 70
                25 -> 70
                else -> 50
            }
            val st = stit.toDouble()
            val con = cond.toDouble()
            val pr = pre.toDouble()
            val total = st / 1000.0* con * pr
            val NGN = Locale("en","NG")
            val formattedTotal = NumberFormat.getCurrencyInstance(NGN).format(total)
            binding.amountResult.text = getString(R.string.money, formattedTotal)
        }
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("Not yet implemented")
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        selectedOption = parent?.getItemAtPosition(position).toString()
    }
}