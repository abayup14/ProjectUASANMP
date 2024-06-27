package com.example.hobbyapp.view

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.hobbyapp.R
import com.example.hobbyapp.databinding.FragmentProfileBinding
import com.example.hobbyapp.model.User
import com.example.hobbyapp.viewmodel.UserViewModel

class ProfileFragment : Fragment(), UpdateUserClick {
    private lateinit var binding:FragmentProfileBinding
    private lateinit var viewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_profile, container, false)
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (MainActivity.getSharedPref(requireActivity()) != 0) {
            viewModel = ViewModelProvider(this).get(UserViewModel::class.java)
            val shared = activity?.packageName
            val sharedPref: SharedPreferences = requireActivity().getSharedPreferences(shared, Context.MODE_PRIVATE)
            val id = sharedPref.getInt("KEY_ID", 0)
            viewModel.selectUser(id)
            binding.updateListener = this
            observeViewModel()
        }

    }

    fun observeViewModel() {
        viewModel.userLD.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                binding.user = it
//                binding.txtCurrUser.text = "${it.nama_depan} ${it.nama_belakang}"
//                binding.txtUserEmail.text = it.email
//                binding.txtChangeFirstName.setText(it.nama_depan)
//                binding.txtChangeLastName.setText(it.nama_belakang)
//                binding.txtChangePassword.setText(it.password)
            }
        })
    }

    override fun updateUser(v: View, obj: User) {
        viewModel.update(
            obj.nama_depan,
            obj.nama_belakang,
            obj.password,
            obj.uuid)
        Toast.makeText(v.context, "User updated", Toast.LENGTH_SHORT).show()
    }

}