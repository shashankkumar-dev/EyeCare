package com.emerald.eyecare.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.emerald.eyecare.databinding.FragmentHomeBinding
import com.emerald.eyecare.service.AlarmService
import com.emerald.eyecare.utils.Constant

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var viewModel: HomeViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        setText()
        setButtonClickListener()
        return binding.root
    }

    private fun setButtonClickListener() {
        binding.button.setOnClickListener {
            Log.i(Constant.TAG, "setButtonClickListener: ")
            AlarmService.checkPermission(requireContext())
        }
    }

    private fun setText() {
        // useless function
        val root: View = binding.root
        val textView: TextView = binding.textHome
        viewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
    }
}