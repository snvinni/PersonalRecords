package com.example.personalrecords.view.settings

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.personalrecords.databinding.ActivitySettingsBinding
import com.example.personalrecords.service.repository.local.SecurityPreferences
import com.example.personalrecords.view.addexercise.AddExerciseViewModel
import com.example.personalrecords.view.login.LoginActivity
import com.example.personalrecords.view.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SettingsActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySettingsBinding
    private val settingsViewModel: SettingsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.radioLbs.isChecked = true
        binding.radioKgs.isChecked = false

        binding.buttonLogout.setOnClickListener {
            settingsViewModel.logOut()
            val intent = Intent(this, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            this.startActivity(intent)
        }
        binding.buttonCancel.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
        observe()
        convertUnit()

    }

    private fun observe() {
        settingsViewModel.logOut.observe(this, Observer {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        })

    }

    private fun convertUnit() {
        binding.buttonSave.setOnClickListener {
            val recordKgs = binding.radioKgs.isChecked

            settingsViewModel.convertLbsToKgs(recordKgs)
            startActivity(Intent(this, MainActivity::class.java))
        }
    }

}