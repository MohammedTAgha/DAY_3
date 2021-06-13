package com.example.exam1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_shaw.*

class ShawActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shaw)
        val Helper =DatabaseHelper(this)
        val data: List<SchoolModle> = Helper.view()
        list_view.adapter=SchoolAdapter(this, data as ArrayList<SchoolModle>)
    }
}