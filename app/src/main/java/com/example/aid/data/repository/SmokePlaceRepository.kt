package com.example.aid.data.repository

import android.util.Log
import androidx.annotation.WorkerThread
import com.example.aid.data.data_source.local.dao.SmokePlaceDao
import com.example.aid.data.data_source.local.entity.SmokePlaceEntity
import com.example.aid.data.data_source.remote.SmokeApi
import com.example.aid.data.data_source.remote.SmokeClient
import com.example.aid.data.data_source.remote.dto.hospital.GwangDtoX
import com.example.aid.data.mapper.toEntity
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SmokePlaceRepository(private val smokePlaceDao: SmokePlaceDao) {
    var smokePlaces: Flow<List<SmokePlaceEntity>> = smokePlaceDao.getAllSmokePlaceEntity()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun getAllSmokePlaceEntity() {
        val dtos: ArrayList<SmokePlaceEntity> = ArrayList()
        val retrofit = SmokeClient.getInstance()
        val service = retrofit.create(SmokeApi::class.java)
        withContext(CoroutineScope(Dispatchers.IO).coroutineContext) {
            service.getAllSmokePlaces()
                .enqueue(object : Callback<GwangDtoX> {
                    override fun onResponse(
                        call: Call<GwangDtoX>,
                        response: Response<GwangDtoX>
                    ) {
                        Log.d("SmokePlaceRepository", "onResponse 성공: ${response.body()?.size}")
                        if (response.body() == null) {
                            Log.d("SmokePlaceRepository", "null body")
                        } else {
                            for (dto in response.body()!!) {
                                if (dto.id != null) {
                                    //Log.d("HospitalClient", dto.areaName!!)
                                    dtos.add(dto.toEntity())
                                    Log.d(
                                        "SmokePlaceRepository",
                                        "getAllSmokePlaceEntity size = " + dtos.size.toString()
                                    )

                                }
                            }
                            CoroutineScope(Dispatchers.IO).launch {
                                smokePlaceDao.insertSmokePlaceEntities(dtos)
                            }
                            Log.d(
                                "SmokePlaceRepository",
                                "getAllSmokePlaceEntity total size = " + dtos.size.toString()
                            )
                        }
                    }

                    override fun onFailure(
                        call: Call<GwangDtoX>,
                        t: Throwable
                    ) {
                        Log.d("SmokePlaceRepository", "onFailure 에러: " + t.message.toString())
                    }
                })
        }
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insertSmokePlaceEntities(places: List<SmokePlaceEntity>) {
        smokePlaceDao.insertSmokePlaceEntities(places)
    }
}