package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity(),
    View.OnClickListener{

    private val field : EditText by lazy { findViewById(R.id.input) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bindListeners()
    }

    private fun bindListeners() {
        val buttons = listOf<Button>(
            findViewById(R.id.btn0),
            findViewById(R.id.btn1),
            findViewById(R.id.btn2),
            findViewById(R.id.btn3),
            findViewById(R.id.btn4),
            findViewById(R.id.btn5),
            findViewById(R.id.btn6),
            findViewById(R.id.btn7),
            findViewById(R.id.btn8),
            findViewById(R.id.btn9),
            findViewById(R.id.btnEquals),
            findViewById(R.id.btnClear),
            findViewById(R.id.btnMinus),
            findViewById(R.id.btnMultiplie),
            findViewById(R.id.btnSubstract),
            findViewById(R.id.btnPlus),
        )
        buttons.forEach { it.setOnClickListener(this) }
    }

    override fun onClick(v: View?) {
        with(v is Button){
            val button = v as Button
            when(v.id){
                R.id.btnEquals -> summary()
                R.id.btnClear -> clearField()
                else -> addTextToField(button.text)
            }
        }
    }

    private fun addTextToField(text: CharSequence){
        field.text.append(text)
    }

    private fun clearField(){
        field.text.clear()
    }

    private fun summary(){
        val nums = getNumberFromString(field.text.toString())
        getOperator(field.text.toString()) {
            field.text.clear()
            when(it) {
                '+' -> {
                    field.text.append("${nums.first + nums.second}")
                }
                '-' -> {
                    field.text.append("${nums.first - nums.second}")
                }
                '/' -> {
                    field.text.append("${nums.first / nums.second}")
                }
                '*' -> {
                    field.text.append("${nums.first * nums.second}")
                }
            }
        }
    }

    private fun getOperator(equality: String, action: (Char) -> Unit) {
        equality.findAnyOf(listOf("+", "-", "/", "*"))?.second?.firstOrNull()?.let {
            action(it)
        }
    }

    private fun getNumberFromString(text: String) : Pair<Int, Int>{
        val nums = text.split('-', '+', '*', '/')
        return Pair(nums[0].toInt(), nums[1].toInt())
    }
}