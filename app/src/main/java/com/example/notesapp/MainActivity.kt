package com.example.notesapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.notesapp.adapter.NoteAdapter
import com.example.notesapp.db.Note
import com.example.notesapp.db.NoteRepository
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var notes: ArrayList<Note>? = null
    private var longClick = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listView.setOnItemLongClickListener { _, _, _, id ->
            AlertDialog.Builder(this@MainActivity)
                    .setIcon(android.R.drawable.ic_dialog_info)
                    .setTitle("Deletar")
                    .setMessage("Deseja apagar nota ?")
                    .setPositiveButton("Sim"
                    ) { _, _ ->
                        NoteRepository(this).delete(id)
                        loadNotes()
                    }.setNegativeButton("Nao", null).show()

            true
        }

        listView.setOnItemClickListener() { adapterView, view, position, id ->
            val intent = Intent(this, NoteActivity::class.java)
            intent.putExtra("note", notes?.get(position))
            startActivity(intent)
        }

        floatAddNote.setOnClickListener {
            val intent = Intent(this, NoteActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        loadNotes()
        registerForContextMenu(listView)
    }

    private fun loadNotes() {
        notes = NoteRepository(this).findAll()
        val adapter = NoteAdapter(this, notes!!)
        listView?.adapter = adapter
        adapter.notifyDataSetChanged()
    }
}