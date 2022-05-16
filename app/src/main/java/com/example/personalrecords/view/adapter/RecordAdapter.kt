package com.example.personalrecords.view.adapter

import android.app.AlertDialog
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.personalrecords.databinding.ActivitySettingsBinding
import com.example.personalrecords.databinding.RowRecordsListBinding
import com.example.personalrecords.model.RecordModel
import com.example.personalrecords.view.settings.SettingsActivity

class RecordAdapter : ListAdapter<RecordModel, RecordAdapter.ViewHolder>(diffCallBack) {

    var onItemClickedListener: OnItemClickedListener? = null
    var onDeleteItemListener: OnDeleteItemListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       val binding: RowRecordsListBinding = RowRecordsListBinding.inflate(
           LayoutInflater.from(parent.context), parent, false
       )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       holder.bind(getItem(position))
    }
    inner class ViewHolder(private val binding: RowRecordsListBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(record: RecordModel) = with(binding) {

            textExerciseName.text = record.exerciseName
            textDate.text = record.date
            textRecord.text = record.exerciseRecord.toString()
            textRepetitions.text = record.haveRepetitions
            textMeasurement.text = record.measurement
            textRepetition.text = record.repetition

            textExerciseName.setOnClickListener {
                onItemClickedListener?.onCLicked(record.id)
            }

            textExerciseName.setOnLongClickListener {
                AlertDialog.Builder(itemView.context)
                    .setTitle("Remoção de record")
                    .setMessage("Deseja remover seu record ?")
                    .setPositiveButton("Remover") { dialog, witch ->
                        onDeleteItemListener?.onDelete(record.id)
                    }
                    .setNeutralButton("Cancelar", null)
                    .show()
                true
            }

        }

    }

    fun interface OnItemClickedListener {
        fun onCLicked(id: Int)
    }
    fun interface OnDeleteItemListener {
        fun onDelete(id: Int)
    }

    companion object {
        private val diffCallBack = object : DiffUtil.ItemCallback<RecordModel>() {
            override fun areItemsTheSame(oldItem: RecordModel, newItem: RecordModel): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: RecordModel, newItem: RecordModel): Boolean {
               return oldItem == newItem
            }

        }
    }

}