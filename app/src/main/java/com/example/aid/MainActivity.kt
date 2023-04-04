package com.example.aid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.activity.viewModels
import com.example.aid.presentation.MapViewModel
import com.example.aid.presentation.MapViewModelFactory

class MainActivity : AppCompatActivity() {
    private val mapViewModel: MapViewModel by viewModels {
        MapViewModelFactory((application as SmokePlaceApplication).repository)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //mapViewModel.getSmokePlaces()
        val button: Button = findViewById(R.id.testButton)
        val textView: TextView = findViewById(R.id.textvvv)
        button.setOnClickListener {
            mapViewModel.getSmokePlaces()
            var names = ""
            for(entity in mapViewModel.smokePlaces){
                Log.d("Tag", entity.areaName)
                names = names.plus(entity.areaName)
            }
            Log.d("Tag", names)
            textView.text = names
        }
    }
}