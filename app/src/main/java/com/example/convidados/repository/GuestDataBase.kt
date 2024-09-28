package com.example.convidados.repository

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.convidados.Model.GuestModel
import com.example.convidados.constants.DataBaseConstants

// class GuestDataBase(context: Context): SQLiteOpenHelper(context, NAME, null, VERSION) {
@Database(entities = [GuestModel::class], version = 1)
abstract class GuestDataBase: RoomDatabase() {


    abstract fun guestDAO(): guestDAO

    companion object{

        private lateinit var INSTANCE: GuestDataBase

        fun getDataBase(context: Context): GuestDataBase{
            if (!::INSTANCE.isInitialized){
                synchronized(GuestModel::class.java){
                    INSTANCE = Room.databaseBuilder(context, GuestDataBase::class.java, "guestdb")
                        .addMigrations(MIGRATION_1_2)
                        .allowMainThreadQueries()
                        .build()
                }
            }
            return INSTANCE
        }


        private var MIGRATION_1_2: Migration = object : Migration(1, 2) {
            override fun migrate(db: SupportSQLiteDatabase) {
                db.query("")
            }
        }

    }


    /*companion object{
        private const val NAME = "guestdb"
        private const val VERSION = 1
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("CREATE TABLE " + DataBaseConstants.GUEST.TABLE_NAME + " ("
        + DataBaseConstants.GUEST.COLUMNS.ID+ " integer primary key autoincrement, " +
        DataBaseConstants.GUEST.COLUMNS.NAME + " text, " +
        DataBaseConstants.GUEST.COLUMNS.PRESENCE + " integer);")
    }

    /* "CREATE TABLE "  +DataBaseConstants.GUEST.TABLE_NAME+  " ("+
    DataBaseConstants.GUEST.COLUMNS.ID + " interger primary key autoincrement, "+
    DataBaseConstants.GUEST.COLUMNS.NAME +" text, " +
    DataBaseConstants.GUEST.COLUMNS.PRESENCE +" interger);" */

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    } */
}