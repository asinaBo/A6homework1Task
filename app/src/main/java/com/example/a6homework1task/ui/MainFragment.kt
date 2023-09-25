package com.example.a6homework1task.ui

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.a6homework1task.MainViewModel
import com.example.a6homework1task.R
import com.example.a6homework1task.databinding.FragmentMainBinding
import com.example.a6homework1task.model.TaskModel
import com.example.a6homework1task.ui.adapter.MainFragmentAdapter


class MainFragment : Fragment(), MainFragmentAdapter.Listener {
    private lateinit var binding: FragmentMainBinding
    private lateinit var viewModel: MainViewModel
    private var adapter = MainFragmentAdapter(this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initModelView()
        initRecyclerView()
        initListener()

        if (arguments != null) {
            var data = arguments?.getString("key")
            viewModel.addTask(data.toString())
        }
    }

    private fun initModelView() {
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
    }

    private fun initRecyclerView() {
        viewModel.taskList.observe(viewLifecycleOwner) { list ->
            adapter.addTask(list)
        }
        binding.rcViewMain.adapter = adapter
    }

    private fun initListener() {
        binding.btnAdd.setOnClickListener {
            findNavController().navigate(R.id.secondFragment)
        }
    }

    override fun onClickItem(position: Int) {
        viewModel.checkTask(position)
    }

    override fun onLongClickItem(task: TaskModel) {
        showDeleteDialog(task)
    }

    private fun showDeleteDialog(task: TaskModel) {
        val alertBuilder = AlertDialog.Builder(requireContext())
        alertBuilder.setTitle(getString(R.string.delete))
        alertBuilder.setMessage(getString(R.string.delete_message))
        alertBuilder.setPositiveButton(getString(R.string.yes)) { _, _ ->
            viewModel.removeTask(task)
            adapter.notifyDataSetChanged()
        }
        alertBuilder.setNeutralButton(getString(R.string.cancel))
        { _, _ ->
        }
        alertBuilder.show()
    }
}