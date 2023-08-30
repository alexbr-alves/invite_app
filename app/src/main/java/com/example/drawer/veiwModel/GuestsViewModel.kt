package com.example.drawer.veiwModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.drawer.model.GuestModel
import com.example.drawer.repository.GuestRepository

class GuestsViewModel(application: Application) : AndroidViewModel(application) {
    private var repository: GuestRepository =
        GuestRepository.getInstance(application.applicationContext)

    private val listAllGuests = MutableLiveData<List<GuestModel>>()
    val guests: LiveData<List<GuestModel>> = listAllGuests

    fun getAll() {
        listAllGuests.value = repository.getAll()
    }

    fun getAbsent() {
        listAllGuests.value = repository.getAbsent()
    }

    fun getPresent() {
        listAllGuests.value = repository.getPresent()
    }

    fun delete(id: Int) {
        repository.delete(id)
    }

}