package com.example.nflteams

import android.app.Application

class NFLTeamApp: Application() {
    override fun onCreate() {
        super.onCreate()
        NFLTeamRepo.initialize()
    }
}