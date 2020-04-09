package com.example.cse438.cse438_assignment2.Database
//
//import android.content.Context
//import androidx.room.Database
//import androidx.room.Room
//import androidx.room.RoomDatabase
//
//// Annotates class to be a Room Database with a table (entity) of the Word class
//@Database(entities = arrayOf(TrackList::class), version = 2)
//
//public abstract class TrackListRoomDatabase : RoomDatabase() {
//
//    abstract fun tracklistDao(): TrackListDao
//
//    companion object {
//
//        @Volatile
//        private var INSTANCE: TrackListRoomDatabase? = null
//
//        fun getDatabase(context: Context): TrackListRoomDatabase {
//            val tempInstance = INSTANCE
//            if (tempInstance != null) {
//                return tempInstance
//            }
//            synchronized(this) {
//                val instance = Room.databaseBuilder(
//                    context.applicationContext,
//                    TrackListRoomDatabase::class.java,
//                    "tracklist_database8"
//                ).build()
//                INSTANCE = instance
//                return instance
//            }
//        }
//    }
//}