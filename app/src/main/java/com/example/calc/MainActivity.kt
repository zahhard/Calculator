package com.example.calc

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.example.calc.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Exception
import kotlin.properties.Delegates

class MainActivity : AppCompatActivity() {



    private lateinit var binding: ActivityMainBinding
    private var result = ""
    private var firstRes = 0F
    private var secondRes = 0F
    private val calculator = Calculator()
    private var caUseOperator = false
    private var caUseDot = false
    private var boardList = ArrayList<Button>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        buttonClicked()
    }

    private fun buttonClicked() {

        // Numbers
        binding.one.setOnClickListener(::numberClicked)
        binding.tow.setOnClickListener (::numberClicked)
        binding.three.setOnClickListener (::numberClicked)
        binding.four.setOnClickListener (::numberClicked)
        binding.five.setOnClickListener (::numberClicked)
        binding.six.setOnClickListener (::numberClicked)
        binding.seven.setOnClickListener (::numberClicked)
        binding.eight.setOnClickListener (::numberClicked)
        binding.nine.setOnClickListener (::numberClicked)
        binding.zero.setOnClickListener (::numberClicked)
        binding.dot.setOnClickListener (::numberClicked)

        // Operators
        binding.division.setOnClickListener (::operatorClicked)
        binding.x.setOnClickListener (::operatorClicked)
        binding.plus.setOnClickListener (::operatorClicked)
        binding.minus.setOnClickListener (::operatorClicked)
        binding.btnRadical.setOnClickListener(::del)
        binding.equals.setOnClickListener(::equals)
    }

    private fun numberClicked(view: View){

        if (view is Button){

            if (view.text == "."){
                if ( caUseDot ){
                    binding.textOne.append(view.text)
                    caUseDot = false
                }
            }
            else{
                binding.textOne.append(view.text)
                caUseDot = true
            }
            caUseOperator = true
        }
    }

    private fun operatorClicked(view: View){

        if (view is Button && caUseOperator){
            binding.textOne.append(view.text)
            caUseOperator = false
        }
    }

    private fun del(view: View) {
        binding.textOne.text = ""
        binding.textTow.text = ""
    }

    private fun equals(view: View){
        binding.textTow.text = calcRes()
    }

    private fun calcRes(): String{
        var digits = calcDigits()
        if (digits.isEmpty())
            return ""

        var turn1 = turn(digits)
        if (turn1.isEmpty())
            return ""

        val result = addPlusAndMinuce(turn1)
        return result.toString()
    }

    private fun calcDigits(): MutableList<Any>{
        val listOfAllThings = mutableListOf<Any>()
        var num = ""
        for (char in textOne.text){
            if (char.isDigit() || char == '.'){
                num += char
            }
            else{
                listOfAllThings.add(num.toFloat())
                num = ""
                listOfAllThings.add(char)
            }
        }
        if (num != ""){
            listOfAllThings.add(num.toFloat())
        }

        return listOfAllThings

    }


    private fun addPlusAndMinuce(list: MutableList<Any>): Float {
        var result = list[0] as Float
        for (o in list.indices){
            if (list[o] is Char && o != list.lastIndex){
                val operator = list[o]
                var second = list[o+1] as Float
                if (operator == '+'){
                    result += second
                }
                if (operator == '-'){
                   result -= second
                }
            }
        }
        return result
    }

    private fun turn(digits: MutableList<Any>): MutableList<Any>{
        var list = digits
        while (list.contains('x') || list.contains('/')){
            list = calcTurn(list)
        }
        return list
    }

    private fun calcTurn(list: MutableList<Any>): MutableList<Any>{
        var newList = mutableListOf<Any>()
        var restartIndex = newList.size

        for (i in list.indices){
            if (list[i] is Char && i != list.lastIndex && i < restartIndex) {
                var operator = list[i]
                var first = list[i - 1] as Float
                var second = list[i + 1] as Float

                when (operator) {
                    'x' -> {
                        newList.add(first * second)
                        restartIndex = i + 1
                    }
                    '/' -> {
                        newList.add(first / second)
                        restartIndex = i + 1
                    }
                    else -> {
                        newList.add(list[i])
                    }
                }
            }
        }


    return newList
    }


}


//initBoard()
/*for (button in boardList){
    button.setOnClickListener(::numberClicked)
    button.setOnClickListener(::operatorClicked)
}

 */

/*

private fun firstCalculate(){
    var temp = result
    if (result.contains("+")) {
        temp.split("+")
        secondRes = calculator.plus(temp[0].toFloat(), temp[1].toFloat())
    }
    if (result.contains("-")) {
        temp.split("-")
        secondRes = calculator.plus(temp[0].toFloat(), temp[1].toFloat())
    }
    if (result.contains("*")) {
        temp.split("*")
        secondRes = calculator.plus(temp[0].toFloat(), temp[1].toFloat())
    }
    if (result.contains("/")) {
        temp.split("/")
        secondRes = calculator.plus(temp[0].toFloat(), temp[1].toFloat())
    }
}

private fun clicked(view: View, s: String) {
    Toast.makeText(this, s, Toast.LENGTH_SHORT).show()
    result += s
    binding.textOne.text = result
}

private fun equalsClicked(view: View) {
    firstCalculate()
    binding.textOne.text = ""
    binding.textTow.text = secondRes.toString()
    try {
    }
    catch (e:Exception){
        Toast.makeText(this, "${e.message}", Toast.LENGTH_SHORT)
    }
}

private fun operatorClicked(view: View, s: String) {
    firstRes = result.toFloat()
    result = ""

    if (s == "x") {
        secondRes = calculator.multi(firstRes)
        binding.textTow.text = secondRes.toString()
    }
    else if (s == "/") {
        firstRes = calculator.division(firstRes)
        binding.textTow.text = secondRes.toString()
    }
    else if (s == "+") {
        secondRes = calculator.plus(firstRes)
        binding.textTow.text = secondRes.toString()
    }
    else if (s == "-") {
        firstRes = calculator.mines(firstRes)
        binding.textTow.text = secondRes.toString()
    }
}


}

private fun cc (view: View, s: String, canClear: Boolean){
    if (canClear){
        binding.textTow.text = ""
        binding.textOne.append(s)
    }
    else{
        var temp = binding.textTow.text.toString()
        binding.textOne.append(temp)
        binding.textOne.append(s)
        binding.textTow.text = ""
    }
}
*/
/*
    private fun initBoard() {
        boardList.add(binding.one)
        boardList.add(binding.three)
        boardList.add(binding.tow)
        boardList.add(binding.four)
        boardList.add(binding.five)
        boardList.add(binding.six)
        boardList.add(binding.seven)
        boardList.add(binding.eight)
        boardList.add(binding.nine)
        boardList.add(binding.zero)
        boardList.add(binding.division)
        boardList.add(binding.dot)
        boardList.add(binding.x)
        boardList.add(binding.plus)
        boardList.add(binding.minus)
    }*/
