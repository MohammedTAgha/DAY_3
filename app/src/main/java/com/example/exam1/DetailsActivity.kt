package com.example.exam1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_details.*

class DetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        val position = intent.getIntExtra("id", Int.MAX_VALUE)
        val Helper =DatabaseHelper(this)
        val schools: List<SchoolModle> = Helper.view()
        var school=schools.get(position)
        name.text=school.Name
        addriss.text=school.Address
        phone.text=school.Phone
        students.text=school.Number.toString()

    }
}