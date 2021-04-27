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
import com.vahitkeskin.appcentnasaapp.databinding.FragmentSpiritBinding
import com.vahitkeskin.appcentnasaapp.viewmodel.SpiritViewModel

class SpiritFragment : Fragment() {

    private lateinit var binding: FragmentSpiritBinding
    private lateinit var viewModel: SpiritViewModel
    private val nasaAdapter = NasaAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSpiritBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Process of importing data of Spirit with ViewModel
        viewModel = ViewModelProviders.of(this).get(SpiritViewModel::class.java)
        viewModel.getDataSpiritAPI()

        //It has been reported that the tracks will be listed one under the other in the RecyclerView.
        binding.rvSpirit.layoutManager = LinearLayoutManager(context)
        binding.rvSpirit.adapter = nasaAdapter

        observeLiveData()

    }

    //The data coming to SpiritFragment with LiveData is transmitted to the adapter.
    private fun observeLiveData() {
        viewModel.photos.observe(viewLifecycleOwner, { photoList ->
            photoList?.let {
                binding.rvSpirit.isVisible = true
                //With the updatePhotoList, the Photo class has been sent as a List in the Adapter.
                nasaAdapter.updatePhotoList(it)
            }

        })
    }
}