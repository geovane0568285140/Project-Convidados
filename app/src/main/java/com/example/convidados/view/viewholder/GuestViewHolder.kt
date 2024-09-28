package com.example.convidados.view.viewholder

import android.graphics.pdf.PdfDocument
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.example.convidados.Model.GuestModel
import com.example.convidados.databinding.RowGuestBinding
import com.example.convidados.generatePdf.GeneratePdf
import com.example.convidados.view.listener.OnGuestListener

class GuestViewHolder(private val bind: RowGuestBinding, private val listener: OnGuestListener) : RecyclerView.ViewHolder(bind.root) {

    fun bind(guest: GuestModel){
        bind.textName.text = guest.name

        bind.textName.setOnClickListener{
            listener.onClick(guest.id)
        }


        bind.textName.setOnLongClickListener {
            AlertDialog.Builder(itemView.context)
                .setTitle("Remoção de convidado")
                .setMessage("Tem certeza que deseja remover?")
                .setPositiveButton("sim") { dialog, which ->
                    listener.onDelete(guest.id)
                }
                .setNeutralButton("não", null)
                .create()
                .show()
            true
        }

    }

}