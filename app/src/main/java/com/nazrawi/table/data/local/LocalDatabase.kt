package com.nazrawi.table.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.nazrawi.table.data.local.dao.TeamDao
import com.nazrawi.table.data.local.entity.TeamEntity

@Database(
    entities = [TeamEntity::class],
    version = 1
)
abstract class LocalDatabase : RoomDatabase() {
    abstract fun teamDao(): TeamDao
}