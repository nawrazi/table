package com.nazrawi.table.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.nazrawi.table.common.resource.Resource
import com.nazrawi.table.domain.model.League
import com.nazrawi.table.domain.model.Team
import com.nazrawi.table.domain.repository.TableRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TableViewModel @Inject constructor(
    private val tableRepo: TableRepository
): ViewModel() {

    suspend fun getPremierLeague(): LiveData<Resource<List<Team>>> {
        return tableRepo.getTable(League.PREMIER_LEAGUE).asLiveData()
    }

}