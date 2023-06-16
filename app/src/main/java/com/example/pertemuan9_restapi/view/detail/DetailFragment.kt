package com.example.pertemuan9_restapi.view.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.pertemuan9_restapi.databinding.FragmentDetailBinding
import com.example.pertemuan9_restapi.viewmodel.ViewModelMahasiswa

class DetailFragment : Fragment() {
    lateinit var viewModel: ViewModelMahasiswa
    lateinit var binding: FragmentDetailBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDetailBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val nim = arguments?.getString("nim")

        viewModel = ViewModelProvider(requireActivity()).get(ViewModelMahasiswa::class.java)
        viewModel.getDetailDataMahasiswa().observe(viewLifecycleOwner){
            if(it != null){
                binding.txtNIM.text = it.data?.nIM
                binding.txtNama.text = it.data?.nama
                binding.txtTelp.text = it.data?.telepon
            } else{
                Toast.makeText(context, "Data not found", Toast.LENGTH_SHORT).show()
            }
        }
        viewModel.getDetailData(nim!!)
    }
}