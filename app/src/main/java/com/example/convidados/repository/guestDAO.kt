package com.example.convidados.repository

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.convidados.Model.GuestModel


@Dao
interface guestDAO {

    @Insert
    fun insert(guest: GuestModel): Long

    @Update
    fun update(guest: GuestModel): Int

    @Delete
    fun delete(guest: GuestModel)

    @Query("SELECT * FROM Guest WHERE id = :id ")
    fun getGuest(id:Int): GuestModel?

    @Query("SELECT * FROM Guest")
    fun getAll(): List<GuestModel>

    @Query("SELECT * FROM Guest WHERE presence = :presence")
    fun getPrecenseOrAbsent(presence: Int): List<GuestModel>

    @Query("UPDATE Guest set presence = 0 WHERE presence = 1")
    fun updatePresentPerAbsent(): Int
}