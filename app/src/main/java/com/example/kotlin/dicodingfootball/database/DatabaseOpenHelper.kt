package com.example.kotlin.dicodingfootball.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.util.Log
import com.example.kotlin.dicodingfootball.table.Favorite
import org.jetbrains.anko.db.*

class DatabaseOpenHelper(mContext: Context): ManagedSQLiteOpenHelper(mContext, KEY_DATABASE_NAME, null, KEY_DATABASE_VERSION) {

    companion object {

        const val KEY_DATABASE_VERSION = 1
        const val KEY_DATABASE_NAME = "FootballTeam"

        private var instance: DatabaseOpenHelper? = null

        @Synchronized
        fun getInstance(ctx: Context): DatabaseOpenHelper?{
            if (instance == null){
                instance = DatabaseOpenHelper(ctx.applicationContext)
            }
            return instance
        }
    }

    override fun onCreate(database: SQLiteDatabase?) {
        database?.createTable(Favorite.TABLE_FAVORITE, true,
                Favorite.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
                Favorite.EVENT_ID to TEXT,
                Favorite.TEAM_AWAY_NAME to TEXT,
                Favorite.TEAM_HOME_NAME to TEXT,
                Favorite.TEAM_AWAY_SCORE to INTEGER,
                Favorite.TEAM_HOME_SCORE to INTEGER,
                Favorite.EVENT_DATE to TEXT)
        showLog("Create Table DONE ")
    }

    private fun showLog(message: String){
        Log.i(DatabaseOpenHelper@this::class.java.simpleName, message)
    }

    override fun onUpgrade(database: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        database?.dropTable(Favorite.TABLE_FAVORITE, true)
    }
}

val Context.database: DatabaseOpenHelper?
    get() = DatabaseOpenHelper.getInstance(applicationContext)