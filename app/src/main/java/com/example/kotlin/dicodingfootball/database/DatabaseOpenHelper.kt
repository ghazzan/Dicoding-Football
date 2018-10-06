package com.example.kotlin.dicodingfootball.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.provider.SyncStateContract.Helpers.insert
import com.example.kotlin.dicodingfootball.table.Favorite
import org.jetbrains.anko.db.*

class DatabaseOpenHelper(private val mContext: Context): ManagedSQLiteOpenHelper(mContext, KEY_DATABASE_NAME, null, KEY_DATABASE_VERSION) {

    val Context.database: DatabaseOpenHelper?
        get() = DatabaseOpenHelper.getInstance(applicationContext)

    override fun onCreate(database: SQLiteDatabase?) {
        database?.createTable(Favorite.TABLE_FAVORITE, true,
                Favorite.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
                Favorite.TEAM_ID to TEXT + UNIQUE,
                Favorite.TEAM_NAME to TEXT,
                Favorite.TEAM_BADGE to TEXT)
    }

    override fun onUpgrade(database: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        database?.dropTable(Favorite.TABLE_FAVORITE, true)
    }

    fun addFavorite(entity: Favorite){
        DatabaseOpenHelper.getInstance(mContext)?.use {
            this.insert(Favorite.TABLE_FAVORITE,
                    Favorite.TEAM_ID to entity.id,
                    Favorite.TEAM_NAME to entity.teamName,
                    Favorite.TEAM_BADGE to entity.teamBadge)
        }
    }

    fun getListFavorite(): List<Favorite>?{
        var result: List<Favorite>? = null
        DatabaseOpenHelper.getInstance(mContext)?.use {
            result = this.select(Favorite.TABLE_FAVORITE).parseList(classParser<Favorite>())
        }
        return result
    }

    fun removeFavorite(id: String){
        DatabaseOpenHelper.getInstance(mContext)?.use {
            this.delete(Favorite.TABLE_FAVORITE, "(TEAM_ID = {id})",
                    "id" to id)
        }
    }

    fun checkFavoriteState(id: String): Boolean{
        var result = false
        DatabaseOpenHelper.getInstance(mContext)?.use {
            val item = this.select(Favorite.TABLE_FAVORITE)
                    .whereArgs("(TEAM_ID = {id})",
                            "id" to id)
            result = item.parseList(classParser<Favorite>()).isNotEmpty()
        }
        return result
    }

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
}