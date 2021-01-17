package com.example.notesapp.db

import java.io.Serializable

data class Note(
    var id: Long = 0,
    var title: String? = null,
    var content: String? = null,
) : Serializable {
    override fun toString(): String {
        return title.toString()
    }
}