package com.evotwin.memory

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class MemoryDB(context: Context) :
    SQLiteOpenHelper(context, "memory.db", null, 1) {

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("""
            CREATE TABLE memory (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                user TEXT,
                ai TEXT
            )
        """)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {}

    fun save(u: String, a: String) {
        writableDatabase.execSQL(
            "INSERT INTO memory(user, ai) VALUES (?,?)",
            arrayOf(u, a)
        )
    }

    fun getRecent(): String {
        val cursor = readableDatabase.rawQuery(
            "SELECT user, ai FROM memory ORDER BY id DESC LIMIT 5",
            null
        )

        val sb = StringBuilder()
        try {
            while (cursor.moveToNext()) {
                sb.append(cursor.getString(0)).append(" -> ").append(cursor.getString(1)).append("\n")
            }
        } finally {
            cursor.close()
        }

        return sb.toString()
    }
}
