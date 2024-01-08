package com.example.nflteams

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class NFLTeamDetailViewModel(teamId: String): ViewModel() {
    private val nflTeamRepo = NFLTeamRepo.get()

    private val _NFLteam: MutableStateFlow<NFLTeam?> = MutableStateFlow(null)
    val NFLteam: StateFlow<NFLTeam?> = _NFLteam.asStateFlow()

    init {
        viewModelScope.launch {
            _NFLteam.value = nflTeamRepo.fetchById(teamId)
        }
    }
}

class NFLTeamDetailViewModelFactory(
    private val teamId: String
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return NFLTeamDetailViewModel(teamId) as T
    }
}