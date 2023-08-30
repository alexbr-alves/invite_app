package com.example.drawer.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.drawer.databinding.RowGuestsBinding
import com.example.drawer.model.GuestModel
import com.example.drawer.view.listener.OnGuestListener
import com.example.drawer.viewHolder.GuestsViewHolder

class GuestsAdapter : RecyclerView.Adapter<GuestsViewHolder>() {

    private var guestList: List<GuestModel> = listOf()
    private lateinit var listener: OnGuestListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GuestsViewHolder {
        val item = RowGuestsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GuestsViewHolder(item, listener)
    }

    override fun onBindViewHolder(holder: GuestsViewHolder, position: Int) {
        holder.bind(guestList[position])
    }

    override fun getItemCount(): Int {
        return guestList.count()
    }

    fun updateGuests(list: List<GuestModel>) {
        guestList = list
        notifyDataSetChanged()
    }

    fun attachListener(guestListener: OnGuestListener) {
        listener = guestListener
    }
}