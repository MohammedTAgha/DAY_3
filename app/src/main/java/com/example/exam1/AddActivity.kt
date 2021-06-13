package com.example.exam1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_add.*

class AddActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)
        add.setOnClickListener {
            if(ed_addriss.text.isNotEmpty() && ed_name.text.isNotEmpty() && ed_phone.text.isNotEmpty() && ed_students.text.isNotEmpty()) {
                var datas = SchoolModle(
                    0,
                    ed_name.text.toString(),
                    ed_addriss.text.toString(),
                    ed_phone.text.toString(),
                    ed_students.text.toString().toInt()
                )
                val Helper =DatabaseHelper(this)
                val status = Helper.add(datas)
                if(status > -1){
                    Toast.makeText(applicationContext,"record added", Toast.LENGTH_LONG).show()
                }
            }else{
                Toast.makeText(applicationContext,"title or description cannot be blank", Toast.LENGTH_LONG).show()
            }
            }
        back.setOnClickListener {
            val Helper =DatabaseHelper(this)
            val posts: List<SchoolModle> = Helper.view()
            Log.wtf("TAG", "onCreate:${posts[0]} ", )
        }

        }
    }
