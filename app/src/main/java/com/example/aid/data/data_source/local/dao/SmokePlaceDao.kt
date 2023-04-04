package com.example.aid.data.data_source.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.aid.data.data_source.local.entity.SmokePlaceEntity

private const val tableName: String = "smoke_place_table"

@Dao
interface SmokePlaceDao {
    @Query("SELECT * FROM $tableName")
    fun getAllSmokePlaceEntity(): List<SmokePlaceEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertSmokePlaceEntities(places: List<SmokePlaceEntity>)

    @Query("DELETE FROM $tableName")
    suspend fun deleteAllSmokePlaceEntity(): Int
}