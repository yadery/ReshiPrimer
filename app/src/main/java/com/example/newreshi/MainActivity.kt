package com.example.newreshi

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Message
import android.view.View
import com.example.newreshi.databinding.ActivityMainBinding
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
    fun checkEquation (list: List<String>, PlayerSolution: Int): Boolean {
        var result = 0;
        val dig1 = list[0].toInt()
        val dig2 = list[2].toInt()
        val operand = list[1];
        when (operand){
            "/" -> result = dig1 / dig2;
            "*" -> result = dig1 * dig2;
            "+" -> result = dig1 + dig2;
            "-" -> result = dig1 - dig2;
        }
        return (result == PlayerSolution);
    }

    fun onClickStart(view: View) {
        binding.txtEditSolving.text.clear();
        binding.txtViewEquation.setBackgroundColor(Color.WHITE);
        val operands = arrayOf("*", "/", "-", "+");
        val operand = operands.random();
        var rng1 = (10..99).random();
        var rng2 = (10..99).random();
        if (operand == "/")
        {
            while(rng1 % rng2 != 0)
            {
                rng1 = (10..99).random();
                rng2 = (10..99).random();
            }
        }
        binding.txtViewEquation.text = "${rng1} ${operand} ${rng2}";
        binding.txtEditSolving.isEnabled = true;
        binding.btnCheck.isEnabled = true;
        binding.btnStart.isEnabled = false;
    }
    fun onClickCheck(view: View) {
        try {
            var loses = binding.txtViewWrong.text.toString().toInt();
            var wins = binding.txtViewRight.text.toString().toInt();
            val equation = binding.txtViewEquation.text;
            val equationSplit = equation.split(" ");
            val playerSolution = binding.txtEditSolving.text.toString().toInt();
            if (checkEquation(equationSplit, playerSolution)){
                wins += 1;
                binding.txtViewRight.text = wins.toString();
                binding.txtViewEquation.setBackgroundColor(Color.GREEN);
            }
            else{
                loses += 1;
                binding.txtViewWrong.text = loses.toString();
                binding.txtViewEquation.setBackgroundColor(Color.RED);
            }
            binding.txtEditSolving.isEnabled = false;
            binding.btnStart.isEnabled = true;
            binding.btnCheck.isEnabled = false;
        }
        catch (ex:Exception){

        }
    }
}
