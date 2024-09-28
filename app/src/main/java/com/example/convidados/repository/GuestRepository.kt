package com.example.convidados.repository

import android.content.Context
import com.example.convidados.Model.GuestModel

class GuestRepository(context: Context) {

    private val guestDataBase = GuestDataBase.getDataBase(context).guestDAO()

//    companion object {
//        //Singleton used to avoid multiple instances
//        private lateinit var repository: GuestRepository
//        fun getRepository(context: Context): GuestRepository {
//            if (!::repository.isInitialized)
//                repository = GuestRepository(context)
//            return repository
//        }
//    }

    fun insert(guest: GuestModel): Boolean {
        return guestDataBase.insert(guest) > 0
//        // guestaDataBase.readableDatabase - read only
//
//        return try {
//            val db = guestaDataBase.writableDatabase
//
//            val presence =
//                if (guest.presence) DataBaseConstants.GUEST.PRESENCE.PRESENT else DataBaseConstants.GUEST.PRESENCE.ABSENT
//
//            val values = ContentValues()
//            values.put(DataBaseConstants.GUEST.COLUMNS.NAME, guest.name)
//            values.put(DataBaseConstants.GUEST.COLUMNS.PRESENCE, presence)
//
//            db.insert(DataBaseConstants.GUEST.TABLE_NAME, null, values)
//            true
//        } catch (e: Exception) {
//            false
//        }
    }

    fun update(guest: GuestModel): Boolean {
        return guestDataBase.update(guest) > 0
//        return try {
//            val db = guestaDataBase.writableDatabase
//            val presence =
//                if (guest.presence) DataBaseConstants.GUEST.PRESENCE.PRESENT else DataBaseConstants.GUEST.PRESENCE.ABSENT
//
//            val values = ContentValues()
//            values.put(DataBaseConstants.GUEST.COLUMNS.NAME, guest.name)
//            values.put(DataBaseConstants.GUEST.COLUMNS.PRESENCE, presence)
//
//            val selection = DataBaseConstants.GUEST.COLUMNS.ID + " = ?"
//            val arg = arrayOf(guest.id.toString())
//
//            db.update(DataBaseConstants.GUEST.TABLE_NAME, values, selection, arg)
//            true
//        } catch (e: Exception) {
//            false
//        }
    }

    fun delete(id: Int) {
        val guest = guestDataBase.getGuest(id)
        guestDataBase.delete(guest!!)
//        return try {
//            val db = guestaDataBase.writableDatabase
//
//            val selection = DataBaseConstants.GUEST.COLUMNS.ID + " = ?"
//            val arg = arrayOf(id.toString())
//
//            db.delete(DataBaseConstants.GUEST.TABLE_NAME, selection, arg)
//            true
//        } catch (e: Exception) {
//            false
//        }
    }

    fun getAll(): List<GuestModel> {
        return guestDataBase.getAll()
//        val list = mutableListOf<GuestModel>()
//        try {
//            val db = guestaDataBase.readableDatabase
//
//            val selection = arrayOf(
//                DataBaseConstants.GUEST.COLUMNS.ID,
//                DataBaseConstants.GUEST.COLUMNS.NAME,
//                DataBaseConstants.GUEST.COLUMNS.PRESENCE
//            )
//
//            val cursor = db.query(
//                DataBaseConstants.GUEST.TABLE_NAME,
//                selection,
//                null,
//                null,
//                null,
//                null,
//                null
//            )
//
//            if (cursor != null && cursor.count > 0) {
//                while (cursor.moveToNext()) {
//
//                    val id =
//                        cursor.getInt(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.ID))
//                    val name =
//                        cursor.getString(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.NAME))
//                    val presence =
//                        cursor.getInt(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.PRESENCE))
//
//                    list.add(GuestModel(id, name.toString(), presence == 1))
//                }
//
//                cursor.close()
//            }
//        } catch (e: Exception) {
//            return list
//        }
//
//        return list
    }

    fun getGuest(id:Int): GuestModel?{

        return guestDataBase.getGuest(id)

//        var guest: GuestModel? = null
//
//        try {
//
//            val db = guestaDataBase.readableDatabase
//
//
//            val columns: Array<String> = arrayOf(
//                DataBaseConstants.GUEST.COLUMNS.NAME,
//                DataBaseConstants.GUEST.COLUMNS.PRESENCE
//            )
//
//            val selection: String = DataBaseConstants.GUEST.COLUMNS.ID + " = ?"
//            val args: Array<String> = arrayOf(id.toString())
//
//            val cursor = db.query(DataBaseConstants.GUEST.TABLE_NAME, columns, selection, args, null, null, null)
//
//
//            if ( cursor != null && cursor.count > 0){
//                while (cursor.moveToNext()){
//
//                    val name = cursor.getString(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.NAME))
//                    val presence = cursor.getInt(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.PRESENCE))
//
//                    guest = GuestModel(id, name, presence == 1)
//                }
//            }
//        } catch (e: Exception){
//            return guest
//        }
//        return guest
    }

    fun getPrecenseOrAbsent(presenceId: Int): List<GuestModel> {

        return guestDataBase.getPrecenseOrAbsent(presenceId)

//        val list = mutableListOf<GuestModel>()
//        try {
//            val db = guestaDataBase.readableDatabase
//
//            val arg = arrayOf(presence.toString())
//
//            // rawQuery to commands SQLs in Strings, Different of the function Query used through parameters
//            val cursor = db.rawQuery(
//                "SELECT " +
//                        DataBaseConstants.GUEST.COLUMNS.ID + ", " +
//                        DataBaseConstants.GUEST.COLUMNS.NAME + ", " +
//                        DataBaseConstants.GUEST.COLUMNS.PRESENCE +
//                        " FROM " +
//                DataBaseConstants.GUEST.TABLE_NAME + " WHERE " + DataBaseConstants.GUEST.COLUMNS.PRESENCE + " = ?"
//                , arg
//            )
//
//            if (cursor != null && cursor.count > 0) {
//                while (cursor.moveToNext()) {
//
//                    val id =
//                        cursor.getInt(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.ID))
//                    val name =
//                        cursor.getString(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.NAME))
//                    val presence =
//                        cursor.getInt(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.PRESENCE))
//
//                    list.add(GuestModel(id, name, presence == 1))
//                }
//
//                cursor.close()
//            }
//        } catch (e: Exception) {
//            return list
//        }
//
//
//        return list
    }

    fun setPresentPerAbsent(): Boolean{
        return guestDataBase.updatePresentPerAbsent() > 0
//        return try {
//            val db = guestaDataBase.writableDatabase
//
//            val values = ContentValues()
//            values.put(DataBaseConstants.GUEST.COLUMNS.PRESENCE, DataBaseConstants.GUEST.PRESENCE.ABSENT)
//
//            val selection = DataBaseConstants.GUEST.PRESENCE.PRESENT
//
//            db.update(DataBaseConstants.GUEST.TABLE_NAME, values, selection.toString(), null)
//            true
//        } catch (e: Exception) {
//            false
//        }
    }

}