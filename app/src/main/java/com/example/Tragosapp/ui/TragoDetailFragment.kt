package com.example.Tragosapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import com.example.Tragosapp.AppDatabase
import com.example.Tragosapp.R
import com.example.Tragosapp.data.DataSourceImp
import com.example.Tragosapp.data.model.Drink
import com.example.Tragosapp.data.model.DrinkEntity
import com.example.Tragosapp.domain.RepoImp
import com.example.Tragosapp.ui.viewmodel.MainViewModel
import com.example.Tragosapp.ui.viewmodel.VMFactory
import kotlinx.android.synthetic.main.fragment_trago_detail.*

class TragoDetailFragment : Fragment() {

    private val viewModel by activityViewModels<MainViewModel> { VMFactory(RepoImp(DataSourceImp(
        AppDatabase.getDataBase(requireActivity().applicationContext)))) }
    lateinit var drink: Drink

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireArguments().let {
            drink = it.getParcelable<Drink>("drink")!!
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_trago_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Glide.with(requireContext()).load(drink.image).centerCrop().into(img_drink)
        txt_title.text = drink.name
        txt_description.text = drink.description
        if(drink.hasAlcohol == "Non_Alcoholic"){
            txt_has_alcohol.text = "Bebida sin Alcohol"
        }else{
            txt_has_alcohol.text = "Bebida con Alcohol"
        }
        btn_save_drink.setOnClickListener{
            viewModel.saveDrink(DrinkEntity(drink.drinkId, drink.image, drink.name, drink.description, drink.hasAlcohol))
            Toast.makeText(requireContext(), "Se guardo el trago a favoritos", Toast.LENGTH_SHORT).show()
        }
    }
}