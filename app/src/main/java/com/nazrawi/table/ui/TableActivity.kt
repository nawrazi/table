package com.nazrawi.table.ui

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.nazrawi.table.common.resource.Resource
import com.nazrawi.table.databinding.ActivityTableBinding
import com.nazrawi.table.ui.adapter.TableAdapter
import com.nazrawi.table.ui.viewmodel.TableViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class TableActivity : AppCompatActivity() {
    private val tableViewModel by viewModels<TableViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityTableBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val tableAdapter = TableAdapter(this)
        binding.teamList.adapter = tableAdapter

        binding.teamList.let {
            val layoutManager = LinearLayoutManager(this)
            it.layoutManager = layoutManager
            val separator = DividerItemDecoration(this, layoutManager.orientation)
            it.addItemDecoration(separator)
        }

        tableViewModel.viewModelScope.launch {
            tableViewModel.getTable().observe(this@TableActivity) {
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
                        Snackbar.make(
                            findViewById(android.R.id.content),
                            it.message ?: "Error occurred",
                            Snackbar.LENGTH_LONG
                        ).show()
                    }
                }
            }
        }
    }
}