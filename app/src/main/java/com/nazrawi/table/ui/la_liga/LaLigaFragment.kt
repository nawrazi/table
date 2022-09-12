package com.nazrawi.table.ui.la_liga

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
import com.nazrawi.table.databinding.FragmentLaLigaBinding
import com.nazrawi.table.ui.TableAdapter
import com.nazrawi.table.ui.TableViewModel
import com.nazrawi.table.utils.hide
import com.nazrawi.table.utils.show
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LaLigaFragment : Fragment() {
    private var _binding: FragmentLaLigaBinding? = null
    private val binding get() = _binding!!
    private val tableViewModel by viewModels<TableViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLaLigaBinding.inflate(inflater, container, false)

        val tableAdapter = TableAdapter(requireContext())
        val layoutManager = LinearLayoutManager(context)

        binding.teamList.let {
            it.layoutManager = layoutManager
            it.addItemDecoration(DividerItemDecoration(context, layoutManager.orientation))
            it.adapter = tableAdapter
        }

        tableViewModel.viewModelScope.launch {
            tableViewModel.getLaLiga().observe(viewLifecycleOwner) {
                when (it) {
                    is Resource.Loading -> {
                        binding.progress.progressBar.show()
                        binding.tableTitle.root.hide()
                        binding.progress.errorImage.hide()
                    }
                    is Resource.Success -> {
                        binding.progress.progressBar.hide()
                        binding.tableTitle.root.show()
                        binding.progress.errorImage.hide()
                        tableAdapter.setTable(it.value!!)
                    }
                    is Resource.Error -> {
                        binding.progress.progressBar.hide()
                        it.value?.let { teams ->
                            tableAdapter.setTable(teams)
                            if (teams.isEmpty()) {
                                binding.tableTitle.root.hide()
                                binding.progress.errorImage.show()
                            }
                        }
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