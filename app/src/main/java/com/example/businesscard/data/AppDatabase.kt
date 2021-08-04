package com.example.businesscard.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.businesscard.data.BusinessCard
import com.example.businesscard.data.BusinessCardDAO

@Database(entities = [BusinessCard::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun businessDao(): BusinessCardDAO

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "businesscard_db"
                ).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }

}