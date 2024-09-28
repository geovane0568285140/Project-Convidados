package com.example.convidados.view.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.convidados.constants.DataBaseConstants
import com.example.convidados.databinding.FragmentPresetBinding
import com.example.convidados.view.adapter.GuestsAdapter
import com.example.convidados.view.listener.OnGuestListener
import com.example.convidados.viewModel.GuestsViewModel

class PresetFragment : Fragment() {

    private var _binding: FragmentPresetBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: GuestsViewModel
    private val adapter = GuestsAdapter()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, b: Bundle?): View {
        viewModel = ViewModelProvider(requireActivity())[GuestsViewModel::class.java]
        _binding = FragmentPresetBinding.inflate(inflater, container, false)

        binding.recyclerViewGuest.layoutManager = LinearLayoutManager(context)
        binding.recyclerViewGuest.adapter = adapter

        val listener = object : OnGuestListener {

            override fun onClick(id: Int) {
                val intent  = Intent(context, GuestFormActivity::class.java)

                val bundle: Bundle = Bundle()
                bundle.putInt(DataBaseConstants.GUEST.BUNDLE.GUESTID, id)
                intent.putExtras(bundle)
                startActivity(intent)

            }

            override fun onDelete(id: Int) {
                viewModel.delete(id)
                viewModel.getpresence(DataBaseConstants.GUEST.PRESENCE.PRESENT)
            }

        }

        adapter.attachListener(listener)
        observe()

        viewModel.setFragmnetId(DataBaseConstants.FRAGMENT_ID.PRESENT_FRAGMENT)

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        viewModel.getpresence(DataBaseConstants.GUEST.PRESENCE.PRESENT)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun observe(){
        viewModel.listObeserve.observe(viewLifecycleOwner) {
            adapter.adapterUpdate(it)
        }
    }

}