package com.example.drawer.view

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.drawer.R
import com.example.drawer.constants.DataBaseConstants
import com.example.drawer.databinding.ActivityGuestFormBinding
import com.example.drawer.model.GuestModel
import com.example.drawer.veiwModel.GuestFormViewModel

class GuestFormActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityGuestFormBinding
    private lateinit var viewModel: GuestFormViewModel
    private var guestId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityGuestFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(GuestFormViewModel::class.java)

        binding.bottonSave.setOnClickListener(this)
        binding.radioPresent.isChecked = true

        observe()
        loadData()

    }

    @SuppressLint("SuspiciousIndentation")
    override fun onClick(v: View) {
        if (v.id == R.id.botton_save) {
            val name = binding.editTextName.text.toString()
            val presence = binding.radioPresent.isChecked

            val model = GuestModel(guestId, name, presence)
            viewModel.save(model)

        }
    }

    private fun observe() {
        viewModel.guests.observe(this, Observer {
            binding.editTextName.setText(it.name)

            if (it.presence) {
                binding.radioPresent.isChecked = true
            } else {
                binding.radioAbsent.isChecked = true
            }
        })
        viewModel.saveGuest.observe(this, Observer {
            if (it.isNotEmpty()) {
                Toast.makeText(applicationContext, it, Toast.LENGTH_SHORT).show()
                finish()
            }
        })
    }

    private fun loadData() {
        val bundle = intent.extras
        if (bundle != null) {
            guestId = bundle.getInt(DataBaseConstants.GUEST.ID)
            viewModel.get(guestId)
        }
    }
}