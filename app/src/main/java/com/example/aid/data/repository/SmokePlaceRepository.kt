package com.example.aid.data.repository

import android.util.Log
import androidx.annotation.WorkerThread
import com.example.aid.data.data_source.local.dao.SmokePlaceDao
import com.example.aid.data.data_source.local.entity.SmokePlaceEntity
import com.example.aid.data.mapper.toEntity
import com.example.aid.data.data_source.remote.SmokeApi
import com.example.aid.data.data_source.remote.SmokeClient
import com.example.aid.data.data_source.remote.dto.hospital.GwangDtoX
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SmokePlaceRepository(private val smokePlaceDao: SmokePlaceDao) {
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun getAllSmokePlaceEntity(): List<SmokePlaceEntity> {
        val entities: List<SmokePlaceEntity> = smokePlaceDao.getAllSmokePlaceEntity()

        // 캐시에 데이터가 존재한다면
        if (entities.isNotEmpty()) {
            return entities
        }

        val dtos: ArrayList<SmokePlaceEntity> = ArrayList()
        val retrofit = SmokeClient.getInstance()
        val service = retrofit.create(SmokeApi::class.java)
        service.getAllHospitals()
            .enqueue(object : Callback<GwangDtoX> {
                override fun onResponse(
                    call: Call<GwangDtoX>,
                    response: Response<GwangDtoX>
                ) {
                    Log.d("HospitalClient", "onResponse 성공: ${response.body()?.size}")
                    if (response.body() == null) {
                        Log.d("HospitalClient", "null body")
                    } else {
                        for (dto in response.body()!!) {
                            if (dto.id != null) {
                                dtos.add(dto.toEntity())
                            }
                        }
                    }
                }

                override fun onFailure(
                    call: Call<GwangDtoX>,
                    t: Throwable
                ) {
                    Log.d("HospitalClient", "onFailure 에러: " + t.message.toString())
                }

            })

        return dtos
    }
}