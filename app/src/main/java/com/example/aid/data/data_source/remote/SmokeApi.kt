package com.example.aid.data.data_source.remote

import com.example.aid.data.data_source.remote.dto.hospital.GwangDtoX
import retrofit2.Call
import retrofit2.http.GET

private const val apiKey: String = "iqsfQNfPIs8JsmMOXxBztm%2B4GmUT%2BqCgf7kQkc0xabgp6dnCOm4Z0o1YISAU%2Bg%2BlApxUu4KznQ8msFhW6jAEJQ%3D%3D"

interface SmokeApi {
    @GET("getSmkAreaList?type=json&serviceKey=$apiKey&pageNo=1&numOfRows=500&id=")
    fun getAllSmokePlaces(): Call<GwangDtoX>
}