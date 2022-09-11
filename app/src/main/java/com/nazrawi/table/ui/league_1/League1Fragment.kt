package com.nazrawi.table.ui.league_1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.nazrawi.table.common.resource.Resource
import com.nazrawi.table.databinding.FragmentLeague1Binding
import com.nazrawi.table.ui.TableAdapter
import com.nazrawi.table.ui.TableViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class League1Fragment : Fragment() {
    private var _binding: FragmentLeague1Binding? = null
    private val binding get() = _binding!!
    private val tableViewModel by viewModels<TableViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLeague1Binding.inflate(inflater, container, false)

        val tableAdapter = TableAdapter(requireContext())
        val layoutManager = LinearLayoutManager(context)

        binding.teamList.let {
            it.layoutManager = layoutManager
            it.addItemDecoration(DividerItemDecoration(context, layoutManager.orientation))
            it.adapter = tableAdapter
        }

        tableViewModel.viewModelScope.launch {
            tableViewModel.getLeague1().observe(viewLifecycleOwner) {
                when (it) {
                    is Resource.Loading -> {
                        binding.progressBar.visibility = View.VISIBLE
                    }
                    is Resource.Success -> {
                        binding.progressBar.visibility = View.GONE
                        tableAdapter.setTable(it.value!!)
                    }
                    is Resource.Error -> {
                        binding.progressBar.visibility = View.GONE
                        it.value?.let { teams -> tableAdapter.setTable(teams) }
                        Toast.makeText(context, it.errMsg, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}