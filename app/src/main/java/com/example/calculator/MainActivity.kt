package com.example.calculator

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.calculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var getInput:String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.button0.setOnClickListener {
            getInput = binding.textProcesses.text.toString()
            binding.textProcesses.text = "${getInput}0"
        }
        binding.button1.setOnClickListener {
            getInput = binding.textProcesses.text.toString()
            binding.textProcesses.text = "${getInput}1"
        }
        binding.button2.setOnClickListener {
            getInput = binding.textProcesses.text.toString()
            binding.textProcesses.text = "${getInput}2"
        }
        binding.button3.setOnClickListener {
            getInput = binding.textProcesses.text.toString()
            binding.textProcesses.text = "${getInput}3"
        }
        binding.button4.setOnClickListener {
            getInput = binding.textProcesses.text.toString()
            binding.textProcesses.text = "${getInput}4"
        }
        binding.button5.setOnClickListener {
            getInput = binding.textProcesses.text.toString()
            binding.textProcesses.text = "${getInput}5"
        }
        binding.button6.setOnClickListener {
            getInput = binding.textProcesses.text.toString()
            binding.textProcesses.text = "${getInput}6"
        }
        binding.button7.setOnClickListener {
            getInput = binding.textProcesses.text.toString()
            binding.textProcesses.text = "${getInput}7"
        }
        binding.button8.setOnClickListener {
            getInput = binding.textProcesses.text.toString()
            binding.textProcesses.text = "${getInput}8"
        }
        binding.button9.setOnClickListener {
            getInput = binding.textProcesses.text.toString()
            binding.textProcesses.text = "${getInput}9"
        }
        binding.buttonPoint.setOnClickListener {
            getInput = binding.textProcesses.text.toString()
            if(getInput.isNotEmpty()){
                if(getInput.last().isDigit()){
                    val separateInput = getInput.split(" ").toMutableList()
                    if(!separateInput[separateInput.size-1].contains(".")){
                        getInput = binding.textProcesses.text.toString()
                        binding.textProcesses.text = "${getInput}."
                    }
                }
            }
        }

        binding.buttonDelete.setOnClickListener {
            getInput = binding.textProcesses.text.toString()
            if(getInput.isNotEmpty()){
                val separateInput = getInput.split(" ").toMutableList()
                val lastNum = separateInput[separateInput.size-1]
                //-99
                if(getInput.last() == ' '){
                    val stringWithoutLastChar = getInput.substring(0, getInput.length - 3)
                    binding.textProcesses.text = stringWithoutLastChar
                }else if(lastNum[lastNum.length-2] == '-'){
                    val stringWithoutLastTwoChar = getInput.substring(0, getInput.length - 2)
                    binding.textProcesses.text = stringWithoutLastTwoChar
                }
                else{
                    val stringWithoutLastChar = getInput.substring(0, getInput.length - 1)
                    binding.textProcesses.text = stringWithoutLastChar
                }
            }
        }
        binding.buttonDivide.setOnClickListener {
            getInput = binding.textProcesses.text.toString()
            if(getInput.isNotEmpty()){
                if(binding.textProcesses.text.toString().last().isDigit()){
                    getInput = binding.textProcesses.text.toString()
                    binding.textProcesses.text = "${getInput} ÷ "
                }
            }
        }
        binding.buttonMultiple.setOnClickListener {
            getInput = binding.textProcesses.text.toString()
            if(getInput.isNotEmpty()){
                if(binding.textProcesses.text.toString().last().isDigit()){
                    getInput = binding.textProcesses.text.toString()
                    binding.textProcesses.text = "${getInput} x "
                }
            }
        }
        binding.buttonPlus.setOnClickListener {
            getInput = binding.textProcesses.text.toString()
            if(getInput.isNotEmpty()){
                if(binding.textProcesses.text.toString().last().isDigit()){
                    getInput = binding.textProcesses.text.toString()
                    binding.textProcesses.text = "${getInput} + "
                }
            }
        }
        binding.buttonMinus.setOnClickListener {
            getInput = binding.textProcesses.text.toString()
            if(getInput.isNotEmpty()){
                if(binding.textProcesses.text.toString().last().isDigit()){
                    getInput = binding.textProcesses.text.toString()
                    binding.textProcesses.text = "${getInput} - "
                }
            }
        }
        binding.buttonMultiplyWithMinus.setOnClickListener {
            getInput = binding.textProcesses.text.toString()
            if(getInput.isNotEmpty()){
                if(binding.textProcesses.text.toString().last().isDigit()){
                    getInput = binding.textProcesses.text.toString()
                    //"3 + 5 + 8 - 999"
                    val separateInput = getInput.split(" ").toMutableList()
                    var lastNum = (separateInput[separateInput.size-1].toDouble()*(-1)).toString()
                    if(lastNum.takeLast(2)==".0"){
                        lastNum = lastNum.dropLast(2)
                    }

                    separateInput[separateInput.size-1] = lastNum
                    val modifiedInput = separateInput.joinToString(" ")
                    binding.textProcesses.text = modifiedInput
                }
            }
        }
        binding.buttonAllClear.setOnClickListener {
            binding.textProcesses.text = ""
            binding.textResult.text = "0"
            getInput = ""
        }

        binding.buttonEqual.setOnClickListener {
            getInput = binding.textProcesses.text.toString()
            val separateInput = getInput.split(" ").toMutableList()
            // "3 + 4 x 8 / 7 x 9"
            if(getInput.isNotEmpty() && getInput[getInput.length-1].isDigit() && separateInput.size>2){
                var i = 0
                while (i < separateInput.size) {
                    if (separateInput[i] == "x") {
                        val result = separateInput[i - 1].toDouble() * separateInput[i + 1].toDouble()
                        separateInput[i - 1] = result.toString()
                        separateInput.removeAt(i)
                        separateInput.removeAt(i)
                    } else if (separateInput[i] == "÷") {
                        val result = separateInput[i - 1].toDouble() / separateInput[i + 1].toDouble()
                        separateInput[i - 1] = result.toString()
                        separateInput.removeAt(i)
                        separateInput.removeAt(i)
                    } else {
                        i++
                    }
                }

                var result = separateInput[0].toDouble()
                for (j in 1 until separateInput.size step 2) {
                    val operator = separateInput[j]
                    val num = separateInput[j + 1].toDouble()
                    when (operator) {
                        "+" -> result += num
                        "-" -> result -= num
                    }
                }
                var formattedResult = "%.2f".format(result)
                if(formattedResult.takeLast(3)==",00"){
                    formattedResult = formattedResult.dropLast(3)
                }
                binding.textResult.text = formattedResult
            }
        }

    }
}