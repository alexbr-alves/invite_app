package com.example.drawer.repository

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.drawer.constants.DataBaseConstants

class GuestDataBase(
    context: Context
) : SQLiteOpenHelper(context, NAME, null, VERSION) {



    override fun onCreate(db: SQLiteDatabase) {
      db.execSQL(CREATE_TABLE_GUEST)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }

    companion object {
        private const val NAME = "guestdb"
        private const val VERSION = 1

        private const val CREATE_TABLE_GUEST =
            ("create table " + DataBaseConstants.GUEST.TABLE_NAME + " ("
                    + DataBaseConstants.GUEST.COLUMNS.ID + " integer primary key autoincrement, "
                    + DataBaseConstants.GUEST.COLUMNS.NAME + " text, "
                    + DataBaseConstants.GUEST.COLUMNS.PRESENCE + " integer);")
    }

}