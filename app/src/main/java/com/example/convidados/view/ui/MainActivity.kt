package com.example.convidados.view.ui

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.example.convidados.R
import com.example.convidados.databinding.ActivityMainBinding
import com.example.convidados.viewModel.GuestsViewModel


class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: GuestsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = ActivityMainBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this)[GuestsViewModel::class.java]

        setContentView(binding.root)

        setSupportActionBar(binding.appBarMain.toolbar)

        binding.appBarMain.iconsMain.fab.setOnClickListener { startActivityForm() }
        binding.appBarMain.iconsMain.pdf.setOnClickListener { savePDF() }
        binding.appBarMain.iconsMain.resetPresent.setOnClickListener { resetPresent() }
        binding.appBarMain.contentMain.editSearch.addTextChangedListener(createTextWatcher())
        binding.appBarMain.contentMain.editSearch.setOnKeyListener { v, keyCode, event ->
            hideKeyBoard(
                v,
                keyCode,
                event
            )
        }

        setUpNavigation()

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
            != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                1
            )
        }

    }


    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    private fun hideKeyBoard(v: View, keyCode: Int, event: KeyEvent): Boolean {
        return when (keyCode) {
            KeyEvent.KEYCODE_ENTER -> {
                Toast.makeText(this, "enter", Toast.LENGTH_SHORT).show()
                    val imm = getSystemService(InputMethodManager::class.java)
                    imm.hideSoftInputFromWindow(
                        binding.appBarMain.contentMain.editSearch.windowToken,
                        InputMethodManager.HIDE_NOT_ALWAYS
                    )
                true
            }
            else -> super.onKeyUp(keyCode, event)
        }

    }

    private fun createTextWatcher(): TextWatcher {
        return object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // Antes do texto mudar
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                createfilter(s.toString())
            }

            override fun afterTextChanged(s: Editable?) {
                // Após o texto ter mudado
            }
        }
    }

    private fun createfilter(str: String) {
        viewModel.applyFilter(str)
    }

    private fun setUpNavigation() {

        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_main)

        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_all_guests, R.id.nav_preset, R.id.nav_absent
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    private fun startActivityForm() {
        startActivity(Intent(applicationContext, GuestFormActivity::class.java))
    }

    private fun savePDF() {
        AlertDialog.Builder(this, R.style.CustomAlertDialogTheme)
            .setTitle("Deseja criar um PDF?")
            .setMessage("Aperte 'Sim' para criar um PDF desta lista de nomes")
            .setPositiveButton("sim") { dialog, which ->
                if (viewModel.pdf())
                    Toast.makeText(this, "PDF Salvo com sucesso no dispositivo", Toast.LENGTH_LONG)
                        .show()
                else
                    Toast.makeText(this, "PDF não foi salvo no dispositivo", Toast.LENGTH_LONG)
                        .show()
            }
            .setNeutralButton("não", null)
            .create()
            .show()
    }

    private fun resetPresent() {

        AlertDialog.Builder(this, R.style.CustomAlertDialogTheme)
            .setTitle("ATENÇÂO")
            .setMessage("Deseja resetar todos os convidados para ausentes")
            .setPositiveButton("Sim") { dialog, which ->
                viewModel.resetPresentPerAbsent()
            }
            .setNegativeButton("Não", null)
            .create()
            .show()


    }


}