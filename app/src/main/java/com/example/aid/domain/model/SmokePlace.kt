package com.example.aid.domain.model

/**
 * 흡연장소 모델 클래스
 * @param id 아이디
 * @param areaName 흡연구역 이름
 * @param roadNameAddress 도로명주소
 * @param landNumberAddress 지번주소
 * @param category 흡연장소 분류
 * @param latitude 위도
 * @param longitude 경도
 */
data class SmokePlace(
    val id: String,
    val areaName: String,
    val roadNameAddress: String,
    val landNumberAddress: String,
    val category: String,
    val latitude: Double,
    val longitude: Double,
)
