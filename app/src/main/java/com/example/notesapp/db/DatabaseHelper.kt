package com.example.notesapp.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.*

class DatabaseHelper(context: Context) : ManagedSQLiteOpenHelper(ctx = context , name = "agenda.db",  version = 3) {
    private val scriptSQLCreate = arrayOf(
        "INSERT INTO notes VALUES(1,'First note','First notes content');",
        "INSERT INTO notes VALUES(2,'Second note','Second notes content');",
        "INSERT INTO notes VALUES(3,'Third note','Third notes content');",
        "INSERT INTO notes VALUES(4,'Fourth note','Fourth notes content');",
        "INSERT INTO notes VALUES(5,'Fifth note','Fifth notes content');",

        )

    //singleton da classe
    companion object {
        private var instance: DatabaseHelper? = null

        @Synchronized
        fun getInstance(ctx: Context): DatabaseHelper {
            if (instance == null) {
                instance = DatabaseHelper(ctx.applicationContext)
            }
            return instance!!
        }
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.createTable("notes", true,
            "id" to INTEGER + PRIMARY_KEY + UNIQUE,
            "title" to TEXT + NOT_NULL,
            "content" to TEXT + NOT_NULL
        )

        // insere dados iniciais na tabela
        scriptSQLCreate.forEach {sql ->
            db.execSQL(sql)
        }
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.dropTable("notes", true)
        onCreate(db)
    }
}

val Context.database: DatabaseHelper get() = DatabaseHelper.getInstance(applicationContext)