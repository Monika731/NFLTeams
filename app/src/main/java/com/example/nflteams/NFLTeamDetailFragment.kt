package com.example.nflteams


import android.content.res.Resources
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import com.example.nflteams.databinding.FragmentTeamDetailBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.coroutines.launch

class NFLTeamDetailFragment : Fragment(), OnMapReadyCallback {

    private var _binding: FragmentTeamDetailBinding? = null
    private val binding
        get() = checkNotNull(_binding) {
            "Cannot access binding because it is null. Is the view visible?"
        }
    private val args: NFLTeamDetailFragmentArgs by navArgs()
    private val nflTeamDetailViewModel: NFLTeamDetailViewModel by viewModels {
        NFLTeamDetailViewModelFactory(args.teamId)
    }
    private lateinit var map: GoogleMap
    private var latitude: Double = 0.0
    private var longitude: Double = 0.0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding =
            FragmentTeamDetailBinding.inflate(inflater, container, false)
        binding.mapView.onCreate(savedInstanceState)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                nflTeamDetailViewModel.NFLteam.collect{nflTeam ->
                    nflTeam?.let { updateUI(it) }
                }
            }
        }
        binding.mapView.getMapAsync(this)
    }

    private fun updateUI(team: NFLTeam) {
        binding.textView.setText(team.teamName).toString()
        binding.textView2.setText(team.division).toString()
        binding.textView3.setText(team.stadium).toString()

        latitude = team.latitude
        longitude = team.longitude
        val img = team.logoFile.substringBefore(".")
        val resource: Resources =  binding.imageView.context.resources
        val resId: Int = resource.getIdentifier(img,"drawable", binding.imageView.context.packageName)
        binding.imageView.setImageResource(resId)
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onMapReady(p0: GoogleMap) {
        map = p0
        map.uiSettings.isZoomControlsEnabled = false
        map.uiSettings.isMyLocationButtonEnabled = false
        val lat = latitude
        val lon = longitude
        val location = LatLng(lat, lon)
        map.addMarker(
            MarkerOptions()
                .position(location)
                .visible(true)
        )
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 15f))
        binding.mapView.onResume()
    }
}