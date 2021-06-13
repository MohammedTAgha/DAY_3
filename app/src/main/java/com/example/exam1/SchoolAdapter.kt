package com.example.exam1

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.costom_view_layout.view.*


class SchoolAdapter(
    context: Context,
    var data: ArrayList<SchoolModle>
) :
    ArrayAdapter<SchoolModle>(context, R.layout.costom_view_layout, data) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val l: LayoutInflater = LayoutInflater.from(context)
        val customView = l.inflate(R.layout.costom_view_layout, parent, false)
        customView.school_name.text = data[position].Name
        customView.bt_delete.setOnClickListener {
            val databaseHandler = DatabaseHelper(context)
            databaseHandler.deleteSchool(data[position].Id)
            data.removeAt(position)
//            SchoolAdapter?.notifyDataSetChanged()
            this.notifyDataSetChanged()
            Toast.makeText(getContext(), "record deleted", Toast.LENGTH_LONG).show()
        }
        customView.bt_details.setOnClickListener {
            var x = position
            var intent1= Intent(context,DetailsActivity::class.java).apply {
                putExtra("id",x)
            }
            context.startActivity(intent1)
            Toast.makeText(getContext(), "record ${data[position].Id}", Toast.LENGTH_LONG).show()

        }


        return customView
    }
}

