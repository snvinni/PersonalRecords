package com.example.personalrecords.view.login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.personalrecords.databinding.ActivityRegisterBinding
import com.example.personalrecords.view.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterActivity : AppCompatActivity() {

    private lateinit var registerViewModel: RegisterViewModel
    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        registerViewModel = ViewModelProvider(this).get(RegisterViewModel::class.java)

        observe()

        binding.buttonSave.setOnClickListener {
            handleRegister()
        }
    }

    private fun observe() {
        registerViewModel.create.observe(this, Observer {
            if (it.success()) {
                startActivity(Intent(this, MainActivity::class.java))
            } else {
                val message = it.failure()
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun handleRegister() {
        val name = binding.editName.text.toString()
        val email = binding.editEmail.text.toString()
        val password = binding.editPassword.text.toString()

        registerViewModel.doRegister(name, email, password)
    }
}
