package com.example.aid

import android.app.Application
import com.example.aid.data.data_source.local.SmokePlaceDatabase
import com.example.aid.data.repository.SmokePlaceRepository

class SmokePlaceApplication : Application() {
    val database by lazy { SmokePlaceDatabase.getDatabase(this) }
    val repository by lazy { SmokePlaceRepository(database.smokePlaceDao()) }
}