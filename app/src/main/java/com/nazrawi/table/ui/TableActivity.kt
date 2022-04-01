package com.nazrawi.table.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.nazrawi.table.databinding.ActivityTableBinding
import com.nazrawi.table.repository.APIRepository
import com.nazrawi.table.viewmodel.TableViewModel

class TableActivity : AppCompatActivity() {
    private val tableViewModel by viewModels<TableViewModel>()
    private lateinit var binding: ActivityTableBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityTableBinding.inflate(layoutInflater)
        setContentView(binding.root)

        tableViewModel.repo = APIRepository(applicationContext)

        tableViewModel.liveTable.observe(this) {
            binding.centerText.text = it.size.toString()
        }

        tableViewModel.updateTable()

    }
}