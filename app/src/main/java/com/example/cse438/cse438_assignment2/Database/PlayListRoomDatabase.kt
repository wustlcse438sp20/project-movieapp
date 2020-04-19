package com.example.cse438.cse438_assignment2.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


// Annotates class to be a Room Database with a table (entity) of the Word class
@Database(entities = arrayOf(PlayList::class), version = 2)

public abstract class PlayListRoomDatabase : RoomDatabase() {

    abstract fun playlistDao(): PlayListDao

    companion object {

        @Volatile
        private var INSTANCE: PlayListRoomDatabase? = null

        fun getDatabase(context: Context): PlayListRoomDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PlayListRoomDatabase::class.java,
                    "watchlist_data"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}