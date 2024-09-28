package com.example.convidados.view.adapter

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.Paint
import android.graphics.pdf.PdfDocument
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.ContentView
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.recyclerview.widget.RecyclerView.inflate
import com.example.convidados.Model.GuestModel
import com.example.convidados.databinding.RowGuestBinding
import com.example.convidados.view.listener.OnGuestListener
import com.example.convidados.view.viewholder.GuestViewHolder
import com.google.android.material.internal.ViewUtils.getContentView

class GuestsAdapter: RecyclerView.Adapter<GuestViewHolder>() {

    private var listGuest: List<GuestModel> = listOf()
    private lateinit var listener: OnGuestListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GuestViewHolder {
        val item = RowGuestBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GuestViewHolder(item, listener)
    }

    override fun onBindViewHolder(holder: GuestViewHolder, position: Int) {
        holder.bind(listGuest[position])
    }

    override fun getItemCount(): Int {
        return listGuest.count()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun adapterUpdate(list: List<GuestModel>){
        listGuest = list
        notifyDataSetChanged()
    }

    fun attachListener (guestListener: OnGuestListener){
        listener = guestListener
    }


}