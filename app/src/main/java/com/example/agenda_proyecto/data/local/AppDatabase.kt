package com.example.agenda_proyecto.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [TareaEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun tareaDao(): TareaDao
}

