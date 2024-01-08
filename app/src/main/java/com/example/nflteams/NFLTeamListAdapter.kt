package com.example.nflteams

import android.content.res.Resources
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.nflteams.databinding.ListItemTeamBinding


class TeamHolder(
    private val binding: ListItemTeamBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(team: NFLTeam, onTeamClicked: (teamId: String) -> Unit) {
        binding.teamName.text = team.teamName
        binding.teamStadium.text = team.stadium
        val img = team.logoFile.substringBefore(".")
        val resource: Resources =  binding.teamImage.context.resources
        val resId: Int = resource.getIdentifier(img,"drawable", binding.teamImage.context.packageName)
        binding.teamImage.setImageResource(resId)
        binding.root.setOnClickListener{
            onTeamClicked(team.teamId)
        }
    }
}
class NFLTeamListAdapter(
    private val teams: List<NFLTeam>,
    private val onTeamClicked: (teamId: String) -> Unit

): RecyclerView.Adapter<TeamHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListItemTeamBinding.inflate(inflater, parent, false)
        return TeamHolder(binding)
    }

    override fun getItemCount() = teams.size

    override fun onBindViewHolder(holder: TeamHolder, position: Int) {
        val team = teams[position]
        holder.apply {
            holder.bind(team, onTeamClicked)
        }
    }
}