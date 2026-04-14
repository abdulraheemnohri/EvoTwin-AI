package com.evotwin.memory

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.content.ContentValues
import com.evotwin.utils.SecurityManager

class MemoryDB(context: Context) :
    SQLiteOpenHelper(context, "memory.db", null, 2) {

    private val securityManager = SecurityManager()

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
            put("user", securityManager.encrypt(u))
            put("ai", securityManager.encrypt(a))
            put("importance", importance)
        }
        writableDatabase.insert("memory", null, values)
    }

    fun getRecent(): String {
        val cursor = readableDatabase.rawQuery(
            "SELECT user, ai FROM memory ORDER BY id DESC LIMIT 10",
            null
        )

        val sb = StringBuilder()
        try {
            while (cursor.moveToNext()) {
                val u = securityManager.decrypt(cursor.getString(0))
                val a = securityManager.decrypt(cursor.getString(1))
                sb.append(u).append(" -> ").append(a).append("\n")
            }
        } finally {
            cursor.close()
        }

        return sb.toString()
    }
}
