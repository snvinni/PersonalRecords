package com.example.personalrecords.view.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.personalrecords.databinding.ActivityMainBinding
import com.example.personalrecords.service.constants.RecordConstants
import com.example.personalrecords.service.repository.local.SecurityPreferences
import com.example.personalrecords.view.addexercise.AddExerciseActivity
import com.example.personalrecords.view.adapter.RecordAdapter
import com.example.personalrecords.view.listener.RecordListener
import com.example.personalrecords.view.login.LoginActivity
import com.example.personalrecords.view.settings.SettingsActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var listener: RecordListener
    private val mainViewModel: MainViewModel by viewModels()
    private val adapter: RecordAdapter = RecordAdapter()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.addButton.setOnClickListener {
            startActivity(Intent(this, AddExerciseActivity::class.java))
        }

        binding.settingsButton.setOnClickListener {
            startActivity(Intent(this, SettingsActivity::class.java ))
        }

        binding.recylerRecords.layoutManager = LinearLayoutManager(applicationContext)

        binding.recylerRecords.adapter = adapter

        listener = object : RecordListener {
            override fun onClick(id: Int) {
                val intent = Intent(applicationContext, AddExerciseActivity::class.java)

                val bundle = Bundle()
                bundle.putInt(RecordConstants.ID.RECORDID, id)

                intent.putExtras(bundle)
                startActivity(intent)
            }
            override fun onDelete(id: Int) {
                mainViewModel.delete(id)
                mainViewModel.load()
            }
        }
        observe()
    }

    override fun onResume() {
        super.onResume()
        mainViewModel.load()
    }

    private fun observe() {
        mainViewModel.recordList.observe(this, Observer {
            adapter.submitList(it)
        })
    }
}

