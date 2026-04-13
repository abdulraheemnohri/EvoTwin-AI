package com.evotwin.memory

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.content.ContentValues

class MemoryDB(context: Context) :
    SQLiteOpenHelper(context, "memory.db", null, 2) {

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("""
            CREATE TABLE memory (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                user TEXT,
                ai TEXT,
                importance INTEGER DEFAULT 0
            )
        """)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        if (oldVersion < 2) {
            db.execSQL("ALTER TABLE memory ADD COLUMN importance INTEGER DEFAULT 0")
        }
    }

    fun save(u: String, a: String, importance: Int = 0) {
        val values = ContentValues().apply {
            put("user", u)
            put("ai", a)
            put("importance", importance)
        }
        writableDatabase.insert("memory", null, values)
    }

    fun getRecent(): String {
        val cursor = readableDatabase.rawQuery(
            "SELECT user, ai FROM memory ORDER BY importance DESC, id DESC LIMIT 5",
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
