package com.example.personalrecords.view.viewholder

import android.app.AlertDialog
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.personalrecords.R
import com.example.personalrecords.databinding.RowRecordsListBinding
import com.example.personalrecords.model.RecordModel
import com.example.personalrecords.view.listener.RecordListener

class RecordViewHolder(private val binding: RowRecordsListBinding, private val listener: RecordListener) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(record: RecordModel) = with(binding) {

        textExerciseName.text = record.exerciseName
        textDate.text = record.date
        textRecord.text = record.exerciseRecord.toString()
        textRepetitions.text = record.haveRepetitions
        textMeasurement.text = record.measurement
        textRepetition.text = record.repetition

        textExerciseName.setOnClickListener {
            listener.onClick(record.id)
        }

        textExerciseName.setOnLongClickListener {
            AlertDialog.Builder(itemView.context)
                .setTitle("Remoção de record")
                .setMessage("Deseja remover seu record ?")
                .setPositiveButton("Remover") { dialog, witch ->
                    listener.onDelete(record.id)
                }
                .setNeutralButton("Cancelar", null)
                .show()
            true
        }

    }

}