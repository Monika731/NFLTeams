package com.example.nflteams

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

private const val TAG = "NFLTeamListViewModel"
class NFLTeamListViewModel : ViewModel() {
    private val nflTeamRepo: NFLTeamRepo = NFLTeamRepo.get()
    private val _nflTeamsFlow: MutableStateFlow<List<NFLTeam>> = MutableStateFlow(emptyList())
    val teamsFlow: StateFlow<List<NFLTeam>>
        get() = _nflTeamsFlow.asStateFlow()
    init {
        Log.d(TAG, "init starting")
        viewModelScope.launch {
            Log.d(TAG, "coroutine launched")
            nflTeamRepo.fetchNFLTeams().collect{teams ->
                _nflTeamsFlow.value = teams

            }
            Log.d(TAG, "Loading teams finished")
        }
    }
}