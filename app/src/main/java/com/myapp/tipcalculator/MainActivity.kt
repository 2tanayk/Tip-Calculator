package com.myapp.tipcalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.myapp.tipcalculator.databinding.ActivityMainBinding
import java.text.NumberFormat
import java.util.logging.Logger
import kotlin.math.ceil


class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.calculateButton.setOnClickListener { calculateTip() }

    }

    fun calculateTip() {
//        val Log = Logger.getLogger(MainActivity::class.java.name)
//        Log.warning(binding.costOfService.text.toString().length.toString())
//        val c: TextInputEditText =binding.costOfServiceEditText
        val stringInTextField = binding.costOfServiceEditText.text.toString()

        if (!stringInTextField.isEmpty()) {
            val cost = stringInTextField.toDoubleOrNull()
            val selectedId = binding.tipOptions.checkedRadioButtonId

            val tipPercentage = when (selectedId) {
                R.id.option_twenty_percent -> 0.20
                R.id.option_eighteen_percent -> 0.18
                else -> 0.15
            }
            if (cost != null) {
                var tip = tipPercentage * cost


                val roundUp = binding.roundUpSwitch.isChecked

                if (roundUp) {
                    tip = ceil(tip)
                }

                val formattedTip = NumberFormat.getCurrencyInstance().format(tip)
                binding.tipResult.text = formattedTip
            } else {
                Toast.makeText(this, "Enter a valid no. !", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(this, "Cost field empty!", Toast.LENGTH_SHORT).show()
        }

    }

}