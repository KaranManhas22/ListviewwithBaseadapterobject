package com.karan.listviewwithbaseadapterobject

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class List_Adapter1(var List:ArrayList<NameData>): BaseAdapter() {
    override fun getCount(): Int {
        return List.size
    }

    override fun getItem(position: Int): Any {
        return List[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    @SuppressLint("MissingInflatedId")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var view=LayoutInflater.from(parent?.context).inflate(R.layout.activity_list_adapter1,parent,false)
        val tvname=view?.findViewById<TextView>(R.id.name)
        tvname?.setText(List[position].name)
        return view
    }
}