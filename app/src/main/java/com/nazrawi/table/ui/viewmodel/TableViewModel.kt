package com.nazrawi.table.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nazrawi.table.data.remote.model.Standing
import com.nazrawi.table.data.repository.TableRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TableViewModel @Inject constructor(
    private val tableRepo: TableRepository
): ViewModel() {
    val liveTable = MutableLiveData<List<Standing>>()

    fun updateTable() {
        viewModelScope.launch {
            liveTable.value = tableRepo.getTable()
        }
    }

}