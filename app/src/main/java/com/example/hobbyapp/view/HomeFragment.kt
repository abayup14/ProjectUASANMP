package com.example.hobbyapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hobbyapp.R
import com.example.hobbyapp.databinding.FragmentHomeBinding
import com.example.hobbyapp.viewmodel.ListViewModel

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var viewModel: ListViewModel
    private val newsListAdapter  = HomeListAdapter(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater,container,false)
        return  binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(ListViewModel::class.java)
        viewModel.refresh()
        binding.recView.layoutManager = LinearLayoutManager(context)
        binding.recView.adapter = newsListAdapter

        observeViewModel()
    }

    fun observeViewModel() {
        viewModel.newsLD.observe(viewLifecycleOwner, Observer {
            newsListAdapter.updateNewsList(it)
            if(it.isEmpty()) {
                binding.recView?.visibility = View.GONE
                binding.txtError2.setText("No News Available")
            } else {
                binding.recView?.visibility = View.VISIBLE
            }
        })

        viewModel.loadingLD.observe(viewLifecycleOwner, Observer {
            if(it == false) {
                binding.progressBar2?.visibility = View.GONE
            } else {
                binding.progressBar2?.visibility = View.VISIBLE
            }
        })

        viewModel.newsLoadErrorLD.observe(viewLifecycleOwner, Observer {
            if(it == false) {
                binding.txtError2?.visibility = View.GONE
            } else {
                binding.txtError2?.visibility = View.VISIBLE
            }
        })


    }
}