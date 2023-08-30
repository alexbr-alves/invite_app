package com.example.drawer.viewHolder

import android.content.DialogInterface
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.example.drawer.R
import com.example.drawer.databinding.RowGuestsBinding
import com.example.drawer.model.GuestModel
import com.example.drawer.view.listener.OnGuestListener

class GuestsViewHolder(
    private val bind: RowGuestsBinding,
    private val listener: OnGuestListener
) : RecyclerView.ViewHolder(bind.root) {

    fun bind(guest: GuestModel) {
        bind.textName.text = guest.name

        bind.textName.setOnClickListener {
            listener.onClick(guest.id)
        }
        showDialog(guest)
    }
    private fun showDialog(guest: GuestModel){
        bind.textName.setOnLongClickListener {
            AlertDialog.Builder(itemView.context)
                .setTitle("Remoção de convidado")
                .setMessage("Tem certeza que deseja remover ${guest.name} da lista de convidados?")
                .setPositiveButton("Sim") { dialog, whitc ->
                    listener.onDelete(guest.id)
                }
                .setNegativeButton("Não", null)
                .create()
                .show()
            true
        }
    }

}