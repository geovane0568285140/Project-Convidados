package com.example.convidados.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.convidados.Model.GuestModel
import com.example.convidados.repository.GuestRepository

class GuestFormViewModel(application: Application) : AndroidViewModel(application) {

    

    private val repository = GuestRepository(application)

    private val GuestViewModel = MutableLiveData<GuestModel>()
    val guest: LiveData<GuestModel> = GuestViewModel

    private val _saveViewModel = MutableLiveData<String>()
    val save: LiveData<String> = _saveViewModel


    fun save(guest: GuestModel) {

        if (guest.id == 0)
            if (repository.insert(guest))
                _saveViewModel.value = "Inserção efetuada com sucesso"
            else
                _saveViewModel.value = "Falha na inserção"
        else
            if (repository.update(guest))
                _saveViewModel.value = "alteração efetuada com sucesso"
            else
                _saveViewModel.value = "Falha na alteração"


    }


    fun get(id: Int) {
        GuestViewModel.value = repository.getGuest(id)
    }


}