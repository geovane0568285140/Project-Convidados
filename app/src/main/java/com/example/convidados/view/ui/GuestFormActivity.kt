package com.example.convidados.view.ui

import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.convidados.Model.GuestModel
import com.example.convidados.R
import com.example.convidados.constants.DataBaseConstants
import com.example.convidados.databinding.ActivityGuestFormBinding
import com.example.convidados.viewModel.GuestFormViewModel

class GuestFormActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityGuestFormBinding
    private lateinit var viewModel: GuestFormViewModel
    private var guestId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()

        binding = ActivityGuestFormBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this)[GuestFormViewModel::class.java]

        setContentView(binding.root)
        /*ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }*/

        binding.buttonSave.setOnClickListener(this)
        binding.radioButtonPresent.isChecked = true
        binding.editName.setOnKeyListener { v, keyCode, event -> hideKeyBoar(v, keyCode, event) }

        observe()
        loadData()
    }

    override fun onClick(view: View) {
        if (view.id == R.id.button_save) {
            val name = binding.editName.text.toString()
            val presence = binding.radioButtonPresent.isChecked
            val guest = GuestModel().apply {
                this.id = guestId
                this.name = name
                this.presence = presence
            }

            viewModel.save(guest)

            // TODO temp
            finish()
        }
    }

    private fun hideKeyBoar(view: View, keyCode: Int, event: KeyEvent): Boolean {

        return when (keyCode) {
            KeyEvent.KEYCODE_ENTER -> {
                Toast.makeText(this, "enter", Toast.LENGTH_SHORT).show()
                val imm = getSystemService(InputMethodManager::class.java)
                imm.hideSoftInputFromWindow(
                    binding.editName.windowToken,
                    InputMethodManager.HIDE_NOT_ALWAYS
                )
                true
            }

            else -> super.onKeyUp(keyCode, event)
        }

    }

    fun observe() {

        viewModel.guest.observe(this, Observer {
            binding.editName.setText(it.name)
            if (it.presence) {
                binding.radioButtonPresent.isChecked = true
            } else {
                binding.rfdsaadioButtonAbsent.isChecked = true
            }
        })

        viewModel.save.observe(this, Observer {
            Toast.makeText(applicationContext, it, Toast.LENGTH_SHORT).show()
        })

    }


    fun loadData() {
        val bundle = intent.extras
        if (bundle != null) {
            guestId = bundle.getInt(DataBaseConstants.GUEST.BUNDLE.GUESTID)
            viewModel.get(guestId)
        }

    }


}