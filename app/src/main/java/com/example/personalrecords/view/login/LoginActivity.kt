package com.example.personalrecords.view.login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.personalrecords.databinding.ActivityLoginBinding
import com.example.personalrecords.view.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private lateinit var loginViewModel: LoginViewModel
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

        observe()
        verifyLoggedUser()

        binding.buttonLogin.setOnClickListener {
            handleLogin()
        }
        binding.textRegister.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }

    }

    private fun verifyLoggedUser() {
        loginViewModel.verifyLoggedUser()
    }

    private fun observe() {
        loginViewModel.login.observe(this, Observer {
            if (it.success()) {
                startActivity(Intent(this, MainActivity::class.java))
            } else {
                val message = it.failure()
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
            }
        })
        loginViewModel.loggedUser.observe(this, Observer {
            if (it) {
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }
        })
    }

    private fun handleLogin() {
        val email = binding.editEmail.text.toString()
        val password = binding.editPassword.text.toString()

        loginViewModel.doLogin(email, password)
    }
}

