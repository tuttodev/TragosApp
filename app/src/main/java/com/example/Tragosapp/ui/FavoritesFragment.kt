package com.example.Tragosapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.Tragosapp.AppDatabase
import com.example.Tragosapp.R
import com.example.Tragosapp.data.DataSourceImp
import com.example.Tragosapp.data.model.Drink
import com.example.Tragosapp.data.model.DrinkEntity
import com.example.Tragosapp.domain.RepoImp
import com.example.Tragosapp.ui.adapter.MainAdapter
import com.example.Tragosapp.ui.viewmodel.MainViewModel
import com.example.Tragosapp.ui.viewmodel.VMFactory
import com.example.Tragosapp.vo.Resource
import kotlinx.android.synthetic.main.fragment_favorites.*

class FavoritesFragment : Fragment(), MainAdapter.MainAdapterListener {
    private val viewModel by activityViewModels<MainViewModel> { VMFactory(
        RepoImp(
            DataSourceImp(
        AppDatabase.getDataBase(requireActivity().applicationContext))
        )
    ) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_favorites, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupObservers()
    }

    private fun setupObservers() {
        viewModel.getFavoriteDrinks().observe(viewLifecycleOwner, Observer {result ->
            when(result){
                is Resource.Loading -> {}
                is Resource.Success -> {
                    val list = result.data.map {
                        Drink(it.drinkId, it.image, it.name, it.description, it.hasAlcohol)
                    }
                    rv_favorite_drinks.adapter = MainAdapter(requireContext(), list, this)
                }
                is Resource.Failure -> {}
            }
        })
    }

    private fun setupRecyclerView() {
        rv_favorite_drinks.layoutManager = LinearLayoutManager(requireContext())
        rv_favorite_drinks.addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL))
    }

    override fun onItemClick(item: Drink, position: Int) {
        viewModel.deleteDrink(DrinkEntity(item.drinkId, item.image, item.name, item.description, item.hasAlcohol))
        //rv_favorite_drinks.adapter?.notifyItemRemoved(position)
        rv_favorite_drinks.adapter?.notifyItemRangeRemoved(position, rv_favorite_drinks.adapter?.itemCount!!)
    }
}