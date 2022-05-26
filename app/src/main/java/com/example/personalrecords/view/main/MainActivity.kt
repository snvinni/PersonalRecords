package com.example.personalrecords.view.main

import android.app.ProgressDialog.show
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.personalrecords.R
import com.example.personalrecords.databinding.ActivityMainBinding
import com.example.personalrecords.service.constants.RecordConstants
import com.example.personalrecords.service.repository.local.SecurityPreferences
import com.example.personalrecords.view.adapter.RecordAdapter
import com.example.personalrecords.view.addexercise.AddExerciseBottomSheet
import com.example.personalrecords.view.listener.RecordListener
import com.example.personalrecords.view.login.LoginActivity
import com.example.personalrecords.view.settings.SettingsActivity
import com.google.android.material.bottomsheet.BottomSheetDialog
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

        refreshApp()

        binding.addButton.setOnClickListener {

            AddExerciseBottomSheet.apply {
                val bottomSheet = AddExerciseBottomSheet()
                bottomSheet.show(supportFragmentManager, TAG)
            }
        }

        // binding.addButton.setOnClickListener {
        //startActivity(Intent(this, AddExerciseActivity::class.java))
        //}

        binding.settingsButton.setOnClickListener {
            val intent = Intent(this, SettingsActivity::class.java)
            intent.putExtra("test", 33)
            startActivity(intent)
        }

        binding.recylerRecords.layoutManager = LinearLayoutManager(applicationContext)

        binding.recylerRecords.adapter = adapter

        listener = object : RecordListener {
            override fun onClick(id: Int) {
                val intent = Intent(applicationContext, AddExerciseBottomSheet::class.java)

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

    private fun observe() {
        mainViewModel.recordList.observe(this, Observer {
            adapter.submitList(it)
        })
    }

    private fun refreshApp() {
        binding.swipeToRefresh.setOnRefreshListener {
            mainViewModel.load()
            Toast.makeText(applicationContext, "Records Atualizados!", Toast.LENGTH_SHORT).show()
            binding.swipeToRefresh.isRefreshing = false
        }
    }
}

