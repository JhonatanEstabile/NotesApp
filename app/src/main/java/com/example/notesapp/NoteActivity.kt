package com.example.notesapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.notesapp.db.Note
import com.example.notesapp.db.NoteRepository
import kotlinx.android.synthetic.main.activity_note.*
import java.util.*

class NoteActivity : AppCompatActivity() {
    private var note: Note? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note)

        btnSave.setOnClickListener {
            note?.title = txtTitle.text?.toString()
            note?.content = txtContent.text?.toString()

            if (note?.id == 0L) {
                NoteRepository(this).create(note!!)
            } else {
                NoteRepository(this).update(note!!)
            }

            finish()
        }
    }

    override fun onResume() {
        super.onResume()
        note = Note()
    }
}