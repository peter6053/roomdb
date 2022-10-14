package com.example.roomdb

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "student_table")
data class student(
    @PrimaryKey(autoGenerate = true) val id: Int?,
    @ColumnInfo(name = "first_name") val firstname: String?,
    @ColumnInfo(name = "second_name") val secondname: String?,
    @ColumnInfo(name = "roll_no") val rollno: String,



    )
