package com.example.drawer.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.drawer.adapter.GuestsAdapter
import com.example.drawer.constants.DataBaseConstants
import com.example.drawer.databinding.FragmentPresentBinding
import com.example.drawer.veiwModel.GuestsViewModel
import com.example.drawer.view.listener.OnGuestListener

class PresentFragment : Fragment() {

private var _binding: FragmentPresentBinding? = null
  private val binding get() = _binding!!
    private lateinit var viewModel: GuestsViewModel
    private val adapter = GuestsAdapter()

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
     viewModel = ViewModelProvider(this).get(GuestsViewModel::class.java)
    _binding = FragmentPresentBinding.inflate(inflater, container, false)

      binding.recyclerGuests.layoutManager = LinearLayoutManager(context)
      binding.recyclerGuests.adapter = adapter

      val listener = object : OnGuestListener {
          override fun onClick(id: Int) {
              val intent = Intent(context, GuestFormActivity::class.java)
              val bundle = Bundle()

              bundle.putInt(DataBaseConstants.GUEST.ID, id)
              intent.putExtras(bundle)
              startActivity(intent)
          }

          override fun onDelete(id: Int) {
              viewModel.delete(id)
              Toast.makeText(context, "Item deletado com sucesso", Toast.LENGTH_SHORT).show()
              viewModel.getPresent()
          }

      }

      adapter.attachListener(listener)

      viewModel.getPresent()
      observe()


    return binding.root
  }

    override fun onResume() {
        super.onResume()
        viewModel.getPresent()
    }

override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun observe() {
        viewModel.guests.observe(viewLifecycleOwner) {
            adapter.updateGuests(it)
        }
    }
}