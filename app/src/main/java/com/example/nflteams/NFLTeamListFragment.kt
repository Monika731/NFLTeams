package com.example.nflteams

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nflteams.databinding.FragmentTeamListBinding
import kotlinx.coroutines.launch

class NFLTeamListFragment : Fragment() {
    private var _binding: FragmentTeamListBinding? = null
    private val binding
        get() = checkNotNull(_binding) {
            "Cannot access binding because is is null. is the view visible?"
        }
    private val nflTeamListViewModel: NFLTeamListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTeamListBinding.inflate(inflater, container, false)
        binding.teamRecyclerView.layoutManager = LinearLayoutManager(context)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                nflTeamListViewModel.teamsFlow.collect{teams ->
                    binding.teamRecyclerView.adapter = NFLTeamListAdapter(teams) {teamId ->
                        findNavController().navigate(NFLTeamListFragmentDirections.showTeamDetail(teamId))
                    }
                }
            }
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}