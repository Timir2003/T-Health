package com.example.t_health

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DataBHelper (val context: Context, val factory: SQLiteDatabase.CursorFactory?)
    : SQLiteOpenHelper (context,"appUser", factory, 1) {

    override fun onCreate(db: SQLiteDatabase?) {
        val query = "create table users (id int primary key, login text, password text, email text)"
        db!!.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("drop table if exists users")
        onCreate (db)
    }

    fun addUser (user: User) {
        val values = ContentValues()
        values.put("login", user.login)
        values.put("password", user.password)
        values.put("email", user.email)

        val db = this.writableDatabase
        db.insert("users", null, values)
        db.close()
    }

    fun getUser(login: String, password: String): Boolean {

        val db = this.readableDatabase

        val result = db.rawQuery("select * from users where login = '$login' and password = '$password'", null)
        return result.moveToFirst()

    }


}