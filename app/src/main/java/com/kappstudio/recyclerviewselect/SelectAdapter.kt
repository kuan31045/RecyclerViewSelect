package com.kappstudio.recyclerviewselect

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.kappstudio.recyclerviewselect.databinding.ItemSelectBinding

class SelectAdapter(private val viewModel: SelectViewModel) :
    ListAdapter<Int, SelectAdapter.SelectViewHolder>(DiffCallback) {

    inner class SelectViewHolder(private val binding: ItemSelectBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(num: Int, viewModel: SelectViewModel, position: Int) {
            binding.apply {
                radioButton.text = num.toString()
                checkBox.text = num.toString()

                radioButton.isChecked = viewModel.singleSelection.value == position
                checkBox.isChecked = viewModel.multipleSelection.value?.contains(position) ?: false

                radioButton.setOnClickListener {
                    viewModel.selectSingle(position)
                }

                checkBox.setOnClickListener {
                    viewModel.selectMultiple(position)
                }
            }
        }
    }

    object DiffCallback : DiffUtil.ItemCallback<Int>() {
        override fun areItemsTheSame(oldItem: Int, newItem: Int): Boolean =
            oldItem === newItem

        override fun areContentsTheSame(oldItem: Int, newItem: Int): Boolean =
            oldItem == newItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SelectViewHolder {
        return SelectViewHolder(
            ItemSelectBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: SelectViewHolder, position: Int) {
        holder.bind(getItem(position), viewModel, position)
    }
}