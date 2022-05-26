//package com.example.personalrecords.view.addexercise
//
//import android.content.Intent
//import android.os.Bundle
//import android.widget.Toast
//import androidx.activity.viewModels
//import androidx.appcompat.app.AppCompatActivity
//import androidx.lifecycle.Observer
//import com.example.personalrecords.databinding.ActivityAddExerciseBinding
//import com.example.personalrecords.service.constants.RecordConstants
//import com.example.personalrecords.view.main.MainActivity
//import dagger.hilt.android.AndroidEntryPoint
//
//@AndroidEntryPoint
//class AddExerciseActivity : AppCompatActivity() {
//
//    private val addExerciseViewModel: AddExerciseViewModel by viewModels()
//    private lateinit var binding: ActivityAddExerciseBinding
//    var recordId: Int = 0
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
//        binding = ActivityAddExerciseBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//
//        binding.radioWeight.isChecked = true
//
//        setListeners()
//        observe()
//        loadData()
//
//    } private fun loadData() {
////        val bundle = intent.extras
////        if (bundle != null) {
////            recordId = bundle.getInt(RecordConstants.ID.RECORDID)
////            addExerciseViewModel.load(recordId)
////        }
////    }
//
//
//
//    private fun observe() {
//        addExerciseViewModel.saveRecord.observe(this, Observer {
//            if (it) {
//                Toast.makeText(
//                    applicationContext,
//                    "Record Adicionado com sucesso!",
//                    Toast.LENGTH_SHORT
//                ).show()
//            } else {
//                Toast.makeText(applicationContext, "Preencha todos os campos!", Toast.LENGTH_SHORT)
//                    .show()
//            }
//            finish()
//        })
//
//        addExerciseViewModel.record.observe(this, Observer {
//            binding.editExerciseName.setText(it.exerciseName)
//            binding.editExerciseRecord.setText(String.format("%.2f", it.exerciseRecord))
//            binding.editDate.setText(it.date)
//            binding.editNumberRepetitions.setText(it.haveRepetitions)
//        })
//    }
//
//    private fun setListeners() {
//        binding.buttonSave.setOnClickListener {
//            val exerciseName = binding.editExerciseName.text.toString()
//            val stringExerciseRecord = binding.editExerciseRecord.text.toString()
//            val exerciseRecord = stringExerciseRecord.toDouble()
//            val date = binding.editDate.text.toString()
//            val exerciseRecordKgs = exerciseRecord / 2
//
//            addExerciseViewModel.save(
//                recordId,
//                exerciseName,
//                exerciseRecord,
//                haveRepetitions(),
//                date,
//                setMeasurement(),
//                repetition(),
//                exerciseRecordKgs,
//                )
//        }
//
//        binding.buttonCancel.setOnClickListener {
//            startActivity(Intent(this, MainActivity::class.java))
//        }
//    }
//
//    private fun setMeasurement(): String {
//        var measurement = ""
//        measurement = if (binding.radioTime.isChecked) {
//            "minutos"
//        } else {
//            "lbs"
//        }
//        return measurement
//    }
//
//    private fun haveRepetitions(): String {
//        var numberOfRepetitions = binding.editNumberRepetitions.text.toString()
//        if (binding.checkBoxNotRepetitions.isChecked) {
//            numberOfRepetitions = ""
//        }
//        return numberOfRepetitions
//    }
//
//
//    private fun repetition(): String {
//        var repetition = binding.editNumberRepetitions.text.toString()
//        repetition = when (repetition) {
//            "1" -> "repetição"
//            "" -> ""
//            "0" -> ""
//            else -> {
//                "repetições"
//            }
//        }
//        return repetition
//    }
//}