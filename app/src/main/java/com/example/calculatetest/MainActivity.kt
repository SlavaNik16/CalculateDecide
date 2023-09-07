package com.example.calculatetest

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.calculatetest.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.butStart.isEnabled = true
        binding.butCheck.isEnabled = false
        binding.userEntered.isEnabled = false
    }

    //При нажатие на кнопку Старт запускаем программу, присваивая начальные значения
    fun butStart(view: View) {

        binding.txtVarOne.text = VarValue().toString()
        binding.txtOperating.text = OperatValue()
        binding.txtVarTwo.text = VarValue().toString()

        Division()

        binding.butStart.isEnabled = false
        binding.butCheck.isEnabled = true
        binding.userEntered.setText("")
        binding.userEntered.isEnabled = true
        binding.linearLayoutExample.setBackgroundColor(Color.argb(255,100,100,100))


    }
    private fun Division(){
        var VarOne: Int = binding.txtVarOne.text.toString().toInt()
        var Operating: String = binding.txtOperating.text.toString()
        var VarTwo: Int = binding.txtVarTwo.text.toString().toInt()
        var num: Int = 0

        var Validate = false;
        if(Operating == "/"){
            while(!Validate){
                num = HOD(VarOne,VarTwo)
                if(num == 1) {
                    Validate = false
                    VarOne = VarValue()
                    VarTwo = VarValue()
                } else {
                    Validate = true
                }
            }
            binding.txtVarOne.text = if(VarOne > VarTwo) VarOne.toString() else VarTwo.toString()
            binding.txtVarTwo.text = num.toString()
        }
    }
    //Создаем функции,которые
    private fun VarValue():Int = (10..99).random() //Возвращает рандомное двухзначное число
    private fun OperatValue():String = arrayOf("+","-","*","/").random()//Возвращает рандомный операцию

    fun butCheck(view: View){
        binding.txtAllCount.text = (binding.txtAllCount.text.toString().toInt() + 1).toString()
        binding.userEntered.isEnabled = false
        binding.butCheck.isEnabled = false
        binding.butStart.isEnabled = true
        Checking()
    }

    private fun Checking(){

        try {

            var VarOne: Int = binding.txtVarOne.text.toString().toInt()
            var Operating: String = binding.txtOperating.text.toString()
            var VarTwo: Int = binding.txtVarTwo.text.toString().toInt()
            var UserEntered: Int = binding.userEntered.text.toString().toInt()
            var result: Int = 0
            when(Operating){
                "+" -> result = VarOne + VarTwo
                "-" -> result = VarOne - VarTwo
                "*" -> result = VarOne * VarTwo
                "/" -> result = VarOne / VarTwo
            }
            if(UserEntered == result){
                binding.linearLayoutExample.setBackgroundColor(Color.argb(255,0,255,0))
                binding.txtCountTrue.text = (binding.txtCountTrue.text.toString().toInt() + 1).toString()
            }else{
                binding.linearLayoutExample.setBackgroundColor(Color.argb(255,255,0,0))
                binding.txtCountFalse.text = (binding.txtCountFalse.text.toString().toInt() + 1).toString()
            }
        }catch (e:java.lang.Exception){
            binding.linearLayoutExample.setBackgroundColor(Color.argb(255,255,0,0))
            binding.txtCountFalse.text = (binding.txtCountFalse.text.toString().toInt() + 1).toString()
        }
        var AllCount: Int = binding.txtAllCount.text.toString().toInt()
        var CountTrue:Int = binding.txtCountTrue.text.toString().toInt()
        binding.txtProcentResult.text = "${String.format("%.2f", (CountTrue * 100.0 / AllCount))}%"
    }

    private fun HOD(x: Int, y: Int): Int{
        return if(y==0) x else HOD(y,x % y)
    }

}