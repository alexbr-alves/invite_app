package com.example.drawer.veiwModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.drawer.model.GuestModel
import com.example.drawer.repository.GuestRepository

class GuestFormViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = GuestRepository.getInstance(application.applicationContext)

    private val guestModel = MutableLiveData<GuestModel>()
    val guests: LiveData<GuestModel> = guestModel

    private val _saveGuest = MutableLiveData<String>()
    val saveGuest: LiveData<String> = _saveGuest


    fun get(id: Int) {
        guestModel.value = repository.get(id)
    }

    fun save(guest: GuestModel) {
        if (guest.id == 0) {
                if (repository.insert(guest)) {
                    _saveGuest.value = "Inserção realizada"
                } else {
                    _saveGuest.value = "Falha"
                }
        } else {
            if (repository.update(guest)) {
                _saveGuest.value = "Atualização realizada"
            } else {
                _saveGuest.value = "Falha"
            }
        }
    }
}