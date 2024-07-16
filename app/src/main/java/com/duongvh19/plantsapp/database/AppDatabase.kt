package com.duongvh19.plantsapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.duongvh19.plantsapp.data.dao.PlantDao
import com.duongvh19.plantsapp.data.model.Plant

@Database(entities = [Plant::class], version = 1, exportSchema = true)
abstract class AppDatabase : RoomDatabase() {

    abstract fun PlantDao() : PlantDao

    companion object {
        private var db : AppDatabase?= null
        const val DATABASE_NAME = "plants-db"

        fun getInstance(context: Context) : AppDatabase {
            if (db == null) {
                db = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    DATABASE_NAME
                ).build()
            }
            return db!!

        }
    }

}