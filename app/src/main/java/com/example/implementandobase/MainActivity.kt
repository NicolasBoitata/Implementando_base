package com.example.implementandobase

import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

//    // Cria um botão que simula uma falha quando pressionado
//    val crashButton = Button(this).apply {
//        text = "Test Crash"
//        setOnClickListener {
//            throw RuntimeException("Test Crash") // Força uma falha
//        }
//    }
//    // Cria os LayoutParams para o botão
//    val layoutParams = LinearLayout.LayoutParams(
//        LinearLayout.LayoutParams.MATCH_PARENT,
//        LinearLayout.LayoutParams.WRAP_CONTENT
//    )
//
//// Adiciona o botão ao conteúdo da atividade com os LayoutParams apropriados

}
