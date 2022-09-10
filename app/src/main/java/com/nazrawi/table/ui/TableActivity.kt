package com.nazrawi.table.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.nazrawi.table.ui.adapter.TableAdapter
import com.nazrawi.table.databinding.ActivityTableBinding
import com.nazrawi.table.data.repository.APIRepository
import com.nazrawi.table.ui.viewmodel.TableViewModel

class TableActivity : AppCompatActivity() {
    private val tableViewModel by viewModels<TableViewModel>()
    private lateinit var binding: ActivityTableBinding
    private lateinit var tableAdapter: TableAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityTableBinding.inflate(layoutInflater)
        setContentView(binding.root)

        tableViewModel.repo = APIRepository()

        binding.teamList.let {
            val layoutManager = LinearLayoutManager(this)
            it.layoutManager = layoutManager
            val separator = DividerItemDecoration(this, layoutManager.orientation)
            it.addItemDecoration(separator)
        }

        tableAdapter = TableAdapter(this)
        binding.teamList.adapter = tableAdapter
        tableViewModel.liveTable.observe(this) {
            tableAdapter.setTable(it)
        }

        tableViewModel.updateTable()
    }
}