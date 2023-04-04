package com.example.aid.data.mapper

import com.example.aid.data.data_source.local.entity.SmokePlaceEntity
import com.example.aid.data.data_source.remote.dto.hospital.GwangDtoItem
import com.example.aid.domain.model.SmokePlace

fun GwangDtoItem.toEntity() = SmokePlaceEntity(
    id = id!!,
    areaName = areaName!!,
    roadNameAddress = roadNameAddress!!,
    landNumberAddress = landNumberAddress!!,
    category = category!!,
    latitude = latitude,
    longitude = longitude
)

fun SmokePlaceEntity.toModel() = SmokePlace(
    id = id,
    areaName = areaName,
    roadNameAddress = roadNameAddress,
    landNumberAddress = landNumberAddress,
    category = category,
    latitude = latitude,
    longitude = longitude
)