package com.example.convidados.viewModel

import android.annotation.SuppressLint
import android.app.Application
import android.content.ContentValues.TAG
import android.content.Context
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.convidados.Model.GuestModel
import com.example.convidados.constants.DataBaseConstants
import com.example.convidados.generatePdf.GeneratePdf
import com.example.convidados.repository.GuestRepository

class GuestsViewModel(application: Application) : AndroidViewModel(application) {

    val repository: GuestRepository = GuestRepository(application.applicationContext)

    @SuppressLint("StaticFieldLeak")
    private val context: Context = application.applicationContext

    private val listALLGuest = MutableLiveData<List<GuestModel>>()
    val listObeserve: LiveData<List<GuestModel>> = listALLGuest

    private val fragmentId = MutableLiveData<Int>()

    // used in applyFilter to avoid Multiple calls from the function getGuest
    private val listCopyGuest = MutableLiveData<List<GuestModel>>()

    fun getALL() {
        listALLGuest.value = repository.getAll()
        listCopyGuest.value = listALLGuest.value
    }

    fun getpresence(presence: Int) {
        listALLGuest.value = repository.getPrecenseOrAbsent(presence)
        listCopyGuest.value = listALLGuest.value

    }

    fun delete(id: Int) {
        repository.delete(id)
    }

    fun setFragmnetId(id: Int){
        fragmentId.value = id
    }

    fun pdf(): Boolean {
        return GeneratePdf().createPdf(listALLGuest.value)
    }

    fun resetPresentPerAbsent() {
        repository.setPresentPerAbsent()
        if (DataBaseConstants.FRAGMENT_ID.PRESENT_FRAGMENT == fragmentId.value) {
            getpresence(DataBaseConstants.GUEST.PRESENCE.PRESENT)
        } else {
            getpresence(DataBaseConstants.GUEST.PRESENCE.ABSENT)
        }
    }

    fun applyFilter(str: String) {
        if (str != ""){
            listALLGuest.value = listCopyGuest.value
            val listguest = listALLGuest.value
            Log.i(TAG, "listguest: $listguest")
            listALLGuest.value = listguest?.filter { it.name.contains(str) }
        } else{
            listALLGuest.value = listCopyGuest.value
        }
    }


}