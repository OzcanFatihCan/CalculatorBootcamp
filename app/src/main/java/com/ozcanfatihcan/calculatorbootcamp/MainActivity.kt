package com.ozcanfatihcan.calculatorbootcamp

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.ozcanfatihcan.calculatorbootcamp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var currentText = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val numberButtons = listOf(
            binding.button0, binding.button1, binding.button2, binding.button3,
            binding.button4, binding.button5, binding.button6, binding.button7,
            binding.button8, binding.button9
        )

        binding.buttonTemizle.setOnClickListener {
            clearText()
        }
        binding.buttonEsittir.setOnClickListener {
            calculateResult()
        }
        binding.buttonToplam.setOnClickListener {
            if (currentText.isNotEmpty() && currentText.last().isDigit()) {
                currentText += "+"
            }
            binding.editTextNumber.setText(currentText)
        }
        numberButtons.forEachIndexed { index, button ->
            button.setOnClickListener {
                addNumber(index)
            }
        }
    }
    private fun addNumber(number: Int) {
        currentText += number.toString()
        binding.editTextNumber.setText(currentText)
    }
    private fun clearText(){
        currentText=""
        binding.editTextNumber.setText(currentText)
        binding.textViewResult.text=""
    }
    private fun calculateResult(){
        if (currentText.isNotEmpty()){
            val numbers=currentText.split("+")
            val result= numbers.sumOf {
                it.toInt()
            }
            binding.textViewResult.text=result.toString()
            currentText=""
            binding.editTextNumber.setText(currentText)
        }else{
            Toast.makeText(this, "Lütfen değer giriniz", Toast.LENGTH_SHORT).show()
        }
    }
}
