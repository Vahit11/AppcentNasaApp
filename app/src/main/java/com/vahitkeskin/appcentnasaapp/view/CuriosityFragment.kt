package com.vahitkeskin.appcentnasaapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.vahitkeskin.appcentnasaapp.adapter.NasaAdapter
import com.vahitkeskin.appcentnasaapp.databinding.FragmentCuriosityBinding
import com.vahitkeskin.appcentnasaapp.viewmodel.CuriosityViewModel

class CuriosityFragment : Fragment() {

    private lateinit var binding: FragmentCuriosityBinding
    private lateinit var viewModel: CuriosityViewModel
    private val nasaAdapter = NasaAdapter(arrayListOf())


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCuriosityBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Process of importing data of Curiosity with ViewModel
        viewModel = ViewModelProviders.of(this).get(CuriosityViewModel::class.java)
        viewModel.getDataCuriosityAPI()

        //It has been reported that the tracks will be listed one under the other in the RecyclerView.
        binding.rvCuriosity.layoutManager = LinearLayoutManager(context)
        binding.rvCuriosity.adapter = nasaAdapter

        observeLiveData()

    }

    //The data coming to CuriosityFragment with LiveData is transmitted to the adapter.
    private fun observeLiveData() {
        viewModel.photos.observe(viewLifecycleOwner, { photoList ->
            photoList?.let {
                binding.rvCuriosity.isVisible = true
                //With the updatePhotoList, the Photo class has been sent as a List in the Adapter.
                nasaAdapter.updatePhotoList(it)
            }

        })
    }
}