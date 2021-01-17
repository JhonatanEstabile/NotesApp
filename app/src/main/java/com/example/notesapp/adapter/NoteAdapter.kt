package com.example.notesapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.notesapp.R
import com.example.notesapp.db.Note

class NoteAdapter(context: Context, list: ArrayList<Note>) : BaseAdapter() {

    private var notesList: ArrayList<Note>
    private var inflator: LayoutInflater

    init {
        this.notesList = list
        this.inflator = LayoutInflater.from(context)
    }

    override fun getCount(): Int {
        return this.notesList.size
    }

    override fun getItem(position: Int): Any? {
        return this.notesList.get(position)
    }

    override fun getItemId(position: Int): Long {
        return this.notesList.get(position).id;
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View? {
        val note = this.notesList.get(position)
        val view = this.inflator.inflate(R.layout.custom_list, parent, false)
        (view.findViewById<TextView>(R.id.title)).text = note.title
        (view.findViewById<TextView>(R.id.description)).text = note.content
        (view.findViewById<ImageView>(R.id.icon)).setImageResource(R.drawable.note)
        return view
    }
}

