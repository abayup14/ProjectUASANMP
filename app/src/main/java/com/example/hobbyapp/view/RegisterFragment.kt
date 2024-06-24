package com.example.hobbyapp.view

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.hobbyapp.R
import com.example.hobbyapp.databinding.FragmentRegisterBinding
import com.example.hobbyapp.model.User
import com.example.hobbyapp.viewmodel.UserViewModel

class RegisterFragment : Fragment() {
    private lateinit var binding:FragmentRegisterBinding
    private lateinit var viewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_register, container, false)
        binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        binding.btnRegister.setOnClickListener {
            val username = binding.txtUsername.text.toString()
            val nama_depan = binding.txtNamaDepan.text.toString()
            val nama_belakang = binding.txtNamaBelakang.text.toString()
            val email = binding.txtEmail.text.toString()
            val password = binding.txtPassw.text.toString()
            val konfPassw = binding.txtKonfirmPassword.text.toString()

            val dialog = AlertDialog.Builder(activity)
            if (password == konfPassw) {
                dialog.setTitle("Konfirmasi")
                dialog.setMessage("Apakah anda ingin melakukan registrasi?")
                dialog.setPositiveButton("Register", DialogInterface.OnClickListener { dialog, which ->
                    var user = User(
                        username,
                        nama_depan,
                        nama_belakang,
                        password
                    )
                    val list = listOf(user)
                    viewModel.register(list)
                    val alert = AlertDialog.Builder(activity)
                    alert.setTitle("Informasi")
                    alert.setMessage("Berhasil mendaftarkan user.\nLogin dengan username dan password yang sudah didaftarkan.")
                    alert.setPositiveButton("OK", DialogInterface.OnClickListener { dialog, which ->
                        dialog.dismiss()
                    })
                    alert.create().show()
                })
                dialog.setNegativeButton("Batal", DialogInterface.OnClickListener { dialog, which ->
                    dialog.dismiss()
                })
                dialog.create().show()
            } else {
                dialog.setTitle("Informasi")
                dialog.setMessage("Gagal mendaftarkan user.\nCek apakah password dengan konfirmasinya sama")
                dialog.setPositiveButton("OK", DialogInterface.OnClickListener { dialog, which ->
                    dialog.dismiss()
                })
                dialog.create().show()
            }
        }
    }

}