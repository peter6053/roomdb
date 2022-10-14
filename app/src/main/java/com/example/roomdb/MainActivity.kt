package com.example.roomdb

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.roomdb.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var appdb: appdatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        appdb = appdatabase.getdatabase(this)
        binding.btnWriteData.setOnClickListener {
            writedate()
        }
        binding.btnReadData.setOnClickListener {
            readdata()
        }
    }

    private fun writedate() {
        val firstname = binding.etFirstName.toString()
        val lastname = binding.etLastName.toString()
        val rollnumber = binding.etRollNo.toString()
        if (firstname.isNotEmpty() && lastname.isNotEmpty() && rollnumber.isNotEmpty()) {

            val Student = student(
                null, firstname, lastname,  rollnumber

            )
            GlobalScope.launch {
                appdb.Studentdao().insert(Student)
            }
            binding.etFirstName.text.clear()
            binding.etLastName.text.clear()
            binding.etRollNo.text.clear()

            Toast.makeText(this@MainActivity, "Succefuly written", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this@MainActivity, "Please enter data", Toast.LENGTH_SHORT).show()
        }


    }

    private suspend fun dispaydate(student: student) {
        withContext(Dispatchers.Main) {
            binding.tvFirstName.text = student.firstname
            binding.tvLastName.text = student.secondname
            binding.tvRollNo.text = student.rollno.toString()
        }


    }

    private fun readdata() {
        val rollnu = binding.etRollNo.toString()
        if (rollnu.isNotEmpty()) {
            lateinit var student: student
            GlobalScope.launch {
                student = appdb.Studentdao().findbyroll(roll = rollnu.toInt())
                dispaydate(student)
            }
        }

    }
}

