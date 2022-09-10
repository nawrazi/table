package com.nazrawi.table.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nazrawi.table.domain.model.Team
import com.nazrawi.table.domain.repository.TableRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TableViewModel @Inject constructor(
    private val tableRepo: TableRepository
): ViewModel() {
    val liveTable = MutableLiveData<List<Team>>()

    fun updateTable() {
        viewModelScope.launch {
            liveTable.value = tableRepo.getTable()
        }
    }

}