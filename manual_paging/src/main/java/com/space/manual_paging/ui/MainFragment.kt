package com.space.manual_paging.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.space.manaul_paging.databinding.FragmentMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment() {


    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MainViewModel by viewModels()

    private val movieAdapter by lazy { MoviesAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setAdapter()
        getMovies()
        setPageLister()
    }

    /**
     * This method is used to get the movies from the view model
     */
    private fun getMovies() {
        viewModel.movies.observe(viewLifecycleOwner) {
            movieAdapter.setData(it.data!!.results)
            binding.page.text = it.data.page.toString()
        }
    }

    /**
     * Change the page number when the user clicks on the next or previous button
     */
    private fun setPageLister() {

        with(binding) {
            buttonNext.setOnClickListener {
                viewModel.getNextPage()
            }
            buttonPrev.setOnClickListener {
                viewModel.getPrevPage()
            }
        }
    }

    private fun setAdapter() {
        binding.recycler.adapter = movieAdapter
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}