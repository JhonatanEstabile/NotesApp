package com.example.notesapp.db

import android.content.Context
import org.jetbrains.anko.db.MapRowParser
import org.jetbrains.anko.db.*

class NoteRepository(val context: Context) {
    fun findAll() : ArrayList<Note> = context.database.use {
        val notes = ArrayList<Note>()

        select("notes",
            "id",
            "title",
            "content",
        ).parseList(object: MapRowParser<List<Note>> {
            override fun parseRow(columns: Map<String, Any?>): List<Note> {
                notes.add(
                    Note(
                        id = columns.getValue("id").toString()?.toLong(),
                        title = columns.getValue("title")?.toString(),
                        content = columns.getValue("content")?.toString()
                    )
                )

                return notes
            }
        })

        notes
    }

    fun create(note: Note) = context.database.use {
        insert("notes",
            "title" to note.title,
            "content" to note.content,
        )
    }

    fun update(note: Note) = context.database.use {
        update("notes",
            "title" to note.title,
            "content" to note.content,
        )
            .whereArgs("id = {id}", "id" to note.id)
            .exec()
    }

    fun delete(id: Long) = context.database.use {
        delete("notes", "id = {noteId}", args = *arrayOf("noteId" to id))
    }
}