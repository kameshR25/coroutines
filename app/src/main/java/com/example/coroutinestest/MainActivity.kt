package com.example.coroutinestest

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private lateinit var toast:Button
    private lateinit var longTask:Button
    private lateinit var Count:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        toast = findViewById(R.id.distoast)
        longTask =findViewById(R.id.longtask)
        Count = findViewById(R.id.numbercount)

        toast.setOnClickListener {
            Toast.makeText(this, "Hello, Butoon is clicked", Toast.LENGTH_SHORT).show()
        }

        longTask.setOnClickListener {
            lifecycleScope.launch(Dispatchers.IO) {
                for (i in 0 .. 10000000000){
                   // Log.i("buttoncount",i.toString())
                    withContext(Dispatchers.Main){
                        Count.text = i.toString()
                    }

                }
            }

        }
    }
}