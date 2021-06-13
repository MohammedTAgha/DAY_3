package com.example.exam1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        bt_add.setOnClickListener {
            var intent1= Intent(this,AddActivity::class.java)
            startActivity(intent1)
        }
        bt_shaw.setOnClickListener {
            var intent1= Intent(this,ShawActivity::class.java)
            startActivity(intent1)
        }
    }
}