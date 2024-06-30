package com.example.hobbyapp.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.hobbyapp.R
import com.example.hobbyapp.databinding.FragmentDetailBinding
import com.example.hobbyapp.viewmodel.DetailNewsViewModel
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

class DetailFragment : Fragment() {
    private lateinit var viewModel: DetailNewsViewModel
    private lateinit var dataBinding:FragmentDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding = DataBindingUtil.inflate<FragmentDetailBinding>(inflater, R.layout.fragment_detail, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(DetailNewsViewModel::class.java)
        val uuid = DetailFragmentArgs.fromBundle(requireArguments()).id
        viewModel.fetch(uuid)
        observeViewModel()

    }

    fun observeViewModel() {
        viewModel.newsLD.observe(viewLifecycleOwner, Observer {
            dataBinding.news = it
            load_picture(requireView(), it.url, dataBinding.imageView )
        })
    }

    fun load_picture(view: View, photo: String, imageView: ImageView) {
        val picasso = Picasso.Builder(view.context)
        picasso.listener { picasso, uri, exception ->
            exception.printStackTrace()
        }
        picasso.build().load(photo)
            .into(imageView, object : Callback {
                override fun onSuccess() {
                    imageView.visibility = View.VISIBLE
                }

                override fun onError(e: Exception?) {
                    Log.d("picasso error", e.toString())
                }
            })
    }

}