package com.vahitkeskin.appcentnasaapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.vahitkeskin.appcentnasaapp.adapter.NasaAdapter
import com.vahitkeskin.appcentnasaapp.databinding.FragmentOpportunityBinding
import com.vahitkeskin.appcentnasaapp.viewmodel.OpportunityViewModel

class OpportunityFragment : Fragment() {

    private lateinit var binding: FragmentOpportunityBinding
    private lateinit var viewModel: OpportunityViewModel
    private val nasaAdapter = NasaAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOpportunityBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Process of importing data of Opportunity with ViewModel
        viewModel = ViewModelProviders.of(this).get(OpportunityViewModel::class.java)
        viewModel.getDataOpportunityAPI()

        //It has been reported that the tracks will be listed one under the other in the RecyclerView.
        binding.rvOpportunity.layoutManager = LinearLayoutManager(context)
        binding.rvOpportunity.adapter = nasaAdapter

        observeLiveData()

    }

    //The data coming to OpportunityViewModel with LiveData is transmitted to the adapter.
    private fun observeLiveData() {
        viewModel.photos.observe(viewLifecycleOwner, { photoList ->
            photoList?.let {
                binding.rvOpportunity.isVisible = true
                //With the updatePhotoList, the Photo class has been sent as a List in the Adapter.
                nasaAdapter.updatePhotoList(it)
            }
        })

    }
}