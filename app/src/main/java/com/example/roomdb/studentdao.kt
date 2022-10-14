package com.example.roomdb

import androidx.room.*

@Dao
interface studentdao {
    @Query("SELECT * FROM STUDENT_TABLE")
    fun getall () : List<student>

    @Query("SELECT * FROM STUDENT_TABLE WHERE roll_no LIKE :roll LIMIT 1 ")
    suspend fun findbyroll(roll :Int): student

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(student: student)

    @Delete
    suspend fun delete(student: student)

    @Query("Delete FROM STUDENT_TABLE")
    suspend fun deleteall()


}