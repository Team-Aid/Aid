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

        val button: Button = findViewById(R.id.testButton)
        val textView: TextView = findViewById(R.id.textvvv)
        mapViewModel.getSmokePlaces()

        // 앱의 상태는 시간에 따라 변할 수 있는 값을 모두 포함한 것을 이야기한다.
        // (ex: Room 데이터베이스부터 클래스 변수까지 모든 변할 수 있는값을 뜻함)

        // LiveData의 value가 바뀌면 아래 observe 함수가 실행됨
        // 왜 이런 짓을 하느냐?
        // 예를 들면 세로모드에서 가로모드로 변하는 경우 UI의 상태가 바뀐다
        // 그러면 DB 값이나 다른 상태들을 다시 가져와야 하는 이슈가 생긴다
        // 성능에도 안 좋을 수 있고, 상태가 바뀔 수 있는 케이스를 고려하여 코딩해야 하는 번거로움도 생긴다
        // LiveData와 ViewModel을 사용하면 비동기적으로 데이터를 받아오면서 상태관리를 할 수 있다
        // 액티비티가 종료되기 전까지 다른 액티비티나 Fragment에서도 쓸 수 있다!
        mapViewModel.smokePlaces.observe(this){
            var names = ""
            for(entity in it){
                Log.d("MainActivity", "smokePlaces observe " + entity.areaName)
                names = names.plus(entity.areaName)
            }
            textView.text = names
        }

        button.setOnClickListener {
            mapViewModel.getSmokePlaces()
        }
    }
}