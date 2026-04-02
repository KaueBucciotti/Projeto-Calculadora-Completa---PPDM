package com.example.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityMainBinding
import kotlin.math.pow

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var valor1: Double = 0.0
    private var operador: String = ""
    private var novoNumero: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Configurar números
        val botoesNumeros = listOf(binding.btn0, binding.btn1, binding.btn2, binding.btn3,
            binding.btn4, binding.btn5, binding.btn6, binding.btn7,
            binding.btn8, binding.btn9)

        botoesNumeros.forEach { botao ->
            botao.setOnClickListener {
                if (novoNumero) {
                    binding.tvRes.text = botao.text
                    novoNumero = false
                } else {
                    binding.tvRes.append(botao.text)
                }
            }
        }

        // Operações
        val configurarOperador = { op: String ->
            valor1 = binding.tvRes.text.toString().toDouble()
            operador = op
            novoNumero = true
        }

        binding.btnSoma.setOnClickListener { configurarOperador("+") }
        binding.btnSub.setOnClickListener { configurarOperador("-") }
        binding.btnMult.setOnClickListener { configurarOperador("*") }
        binding.btnDiv.setOnClickListener { configurarOperador("/") }
        binding.btnPot.setOnClickListener { configurarOperador("^") }

        // Limpar (C)
        binding.btnC.setOnClickListener {
            binding.tvRes.text = "0"
            valor1 = 0.0
            operador = ""
            novoNumero = true
        }

        // Resultado (=)
        binding.btnIgual.setOnClickListener {
            val valor2 = binding.tvRes.text.toString().toDouble()
            val resultado = when (operador) {
                "+" -> valor1 + valor2
                "-" -> valor1 - valor2
                "*" -> valor1 * valor2
                "/" -> if (valor2 != 0.0) valor1 / valor2 else "Erro"
                "^" -> valor1.pow(valor2)
                else -> valor2
            }
            binding.tvRes.text = resultado.toString()
            novoNumero = true
        }
    }
}