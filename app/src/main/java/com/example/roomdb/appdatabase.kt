package com.example.roomdb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [student::class], version = 1)
abstract class appdatabase : RoomDatabase() {
    abstract fun Studentdao(): studentdao

    companion object {
        @Volatile
        private var INSTANCE: appdatabase? = null
        fun getdatabase(context: Context): appdatabase {

            val tempinstance = INSTANCE


            if (tempinstance != null) {

                return tempinstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    appdatabase::class.java,
                    "appdatabase"
                ).build()
                INSTANCE = instance
                return instance

            }


        }


    }


}