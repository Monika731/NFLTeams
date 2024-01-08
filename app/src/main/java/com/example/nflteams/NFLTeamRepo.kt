package com.example.nflteams

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import java.lang.IllegalStateException

class NFLTeamRepo {
    private var teams = mutableListOf<NFLTeam>()
    init {
        teams = mutableListOf(
            NFLTeam ("bears", "Chicago Bears", "bears.png", "NFC", "NFC North", "Soldier Field", 41.8625,-87.616667),
            NFLTeam ("bengals", "Cincinnati Bengals", "bengals.png", "AFC", "AFC North", "Paul Brown Stadium", 39.095444,-84.516039),
            NFLTeam ("bills", "Buffalo Bills", "bills.png", "AFC", "AFC East", "Bills Stadium", 42.773611,-78.786944),
            NFLTeam ("broncos", "Denver Broncos", "broncos.png", "AFC", "AFC West", "Empower Field at Mile High", 39.743889,-105.02),
            NFLTeam ("browns", "Cleveland Browns", "browns.png", "AFC", "AFC North", "FirstEnergy Stadium", 41.506111,-81.699444),
            NFLTeam ("buccaneers", "Tampa Bay Buccaneers", "buccaneers.png", "NFC", "NFC South", "Raymond James Stadium", 27.975833,-82.503333),
            NFLTeam ("cardinals", "Arizona Cardinals", "cardinals.png", "NFC", "NFC West", "State Farm Stadium", 33.5275,-112.2625),
            NFLTeam ("chargers", "Los Angeles Chargers", "chargers.png", "AFC", "AFC West", "SoFi Stadium", 33.9533849,-118.3409007),
            NFLTeam ("chiefs", "Kansas City Chiefs", "chiefs.png", "AFC", "AFC West", "Arrowhead Stadium", 39.0489391,-94.4861044),
            NFLTeam ("colts", "Indianapolis Colts", "colts.png", "AFC", "AFC South", "Lucas Oil Stadium", 39.760056,-86.163806),
            NFLTeam ("cowboys", "Dallas Cowboys", "cowboys.png", "NFC", "NFC East", "AT&T Stadium", 32.747778,-97.092778),
            NFLTeam ("dolphins", "Miami Dolphins", "dolphins.png", "AFC", "AFC East", "Hard Rock Stadium", 25.958056,-80.238889),
            NFLTeam ("eagles", "Philadelphia Eagles", "eagles.png", "NFC", "NFC East", "Lincoln Financial Field", 39.900833,-75.1675),
            NFLTeam ("falcons", "Atlanta Falcons", "falcons.png", "NFC", "NFC South", "Mercedes-Benz Stadium", 33.755556,-84.400833),
            NFLTeam ("giants", "New York Giants", "giants.png", "NFC", "NFC East", "Met Life Stadium", 40.813611,-74.074444),
            NFLTeam ("jaguars", "Jacksonville Jaguars", "jaguars.png", "AFC", "AFC South", "TIAA Bank Field", 30.323889,-81.6375),
            NFLTeam ("jets", "New York Jets", "jets.png", "AFC", "AFC East", "Met Life Stadium", 40.813611,-74.074444),
            NFLTeam ("lions", "Detroit Lions", "lions.png", "NFC", "NFC North", "Ford Field", 42.34,-83.045556),
            NFLTeam ("packers", "Green Bay Packers", "packers.png", "NFC", "NFC North", "Lambeau Field", 44.501389,-88.062222),
            NFLTeam ("panthers", "Carolina Panthers", "panthers.png", "NFC", "NFC South", "Bank of America Stadium", 35.225833,-80.852778),
            NFLTeam ("patriots", "New England Patriots", "patriots.png", "AFC", "AFC East", "Gillette Stadium", 42.090944,-71.264344),
            NFLTeam ("raiders", "Las Vegas Raiders", "raiders.png", "AFC", "AFC West", "Allegiant Stadium", 36.090833,-115.183611),
            NFLTeam ("rams", "Los Angeles Rams", "rams.png", "NFC", "NFC West", "SoFi Stadium", 33.95345,-118.3392),
            NFLTeam ("ravens", "Baltimore Ravens", "ravens.png", "AFC", "AFC North", "M&T Bank Stadium", 39.2779876,-76.6248931),
            NFLTeam ("saints", "New Orleans Saints", "saints.png", "NFC", "NFC South", "Mercedes-Benz Superdome", 29.951061,-90.0834329),
            NFLTeam ("seahawks", "Seattle Seahawks", "seahawks.png", "NFC", "NFC West", "CenturyLink Field", 47.5951518,-122.3338281),
            NFLTeam ("steelers", "Pittsburgh Steelers", "steelers.png", "AFC", "AFC North", "Heinz Field", 40.446667,-80.015833),
            NFLTeam ("texans", "Houston Texans", "texans.png", "AFC", "AFC South", "NRG Stadium", 29.684722,-95.410833),
            NFLTeam ("the49ers", "San Francisco 49ers", "the49ers.png", "NFC", "NFC West", "Levi's Stadium", 37.403,121.97),
            NFLTeam ("titans", "Tennessee Titans", "titans.png", "AFC", "AFC South", "Nissan Stadium", 36.166251,-86.771422),
            NFLTeam ("vikings", "Minnesota Vikings", "vikings.png", "NFC", "NFC North", "Mall of America Field", 44.973889,-93.258056),
            NFLTeam ("washington", "Washington Football Team", "washington.png", "NFC", "NFC East", "FedExField", 38.907778,-76.864444)
        )
    }
    suspend fun fetchNFLTeams():Flow<List<NFLTeam>> {
        delay(5000)
        return flowOf(teams)
    }

    fun fetchById(teamId: String): NFLTeam? =
        teams.find { team -> team.teamId == teamId }

    companion object {
        private var INSTANCE: NFLTeamRepo? = null

        fun initialize() {
            if (INSTANCE == null) {
                INSTANCE = NFLTeamRepo()
            }
        }

        fun get(): NFLTeamRepo {
            return INSTANCE ?: throw IllegalStateException("NFLTeamRepo is not initialized")
        }
    }
}