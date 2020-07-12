package com.vmodev.mvvm.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.vmodev.mvvm.data.local.dao.SongDao
import com.vmodev.mvvm.data.model.ItemSong

@Database(entities = arrayOf(ItemSong::class), version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun songDao(): SongDao
}