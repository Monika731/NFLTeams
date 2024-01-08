package com.example.nflteams

data class NFLTeam(val teamId: String, val teamName: String, val logoFile: String, val conference: String,
                    val division: String, val stadium: String, val latitude: Double, val longitude: Double)