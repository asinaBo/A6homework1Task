package com.example.a6homework1task.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.a6homework1task.MainViewModel
import com.example.a6homework1task.R
import com.example.a6homework1task.databinding.FragmentMainBinding
import com.example.a6homework1task.databinding.FragmentSecondBinding
import com.example.a6homework1task.model.TaskModel


class SecondFragment : Fragment() {
    private lateinit var binding: FragmentSecondBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        initListener()
    }

    private fun initListener() {
        binding.btnSend.setOnClickListener {
            val data = binding.edTitle.text.toString()
            findNavController().navigate(R.id.mainFragment, bundleOf("key" to data))
        }
    }
}