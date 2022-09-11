package com.nazrawi.table.ui.bundesliga

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.nazrawi.table.R
import com.nazrawi.table.common.resource.Resource
import com.nazrawi.table.databinding.FragmentBundesligaBinding
import com.nazrawi.table.ui.MainActivity
import com.nazrawi.table.ui.TableAdapter
import com.nazrawi.table.ui.TableViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class BundesligaFragment : Fragment() {
    private var _binding: FragmentBundesligaBinding? = null
    private val binding get() = _binding!!
    private val tableViewModel by viewModels<TableViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBundesligaBinding.inflate(inflater, container, false)

        val tableAdapter = TableAdapter(requireContext())
        val layoutManager = LinearLayoutManager(context)

        binding.teamList.let {
            it.layoutManager = layoutManager
            it.addItemDecoration(DividerItemDecoration(context, layoutManager.orientation))
            it.adapter = tableAdapter
        }

        tableViewModel.viewModelScope.launch {
            tableViewModel.getBundesliga().observe(viewLifecycleOwner) {
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
                        tableAdapter.setTable(it.value!!)
                        val activity = requireContext() as MainActivity
                        Snackbar
                            .make(
                                activity.findViewById(android.R.id.content),
                                it.errMsg,
                                Snackbar.LENGTH_LONG
                            )
                            .setAnchorView(activity.findViewById(R.id.main_activity_bottom_nav))
                            .show()
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