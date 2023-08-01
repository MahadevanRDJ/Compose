package com.example.compose.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.compose.data.dao.RegisterDao
import com.example.compose.data.entity.RegisterEntity

@Database(
    entities = [RegisterEntity::class],
    version = 2
)
abstract class CountryDatabase : RoomDatabase() {
    abstract fun registerDao(): RegisterDao

    companion object {
        @Volatile
        private var INSTANCE: CountryDatabase? = null

        fun getInstance(context: Context): CountryDatabase {
            synchronized(this) {

                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        CountryDatabase::class.java,
                        "country_database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()

                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}