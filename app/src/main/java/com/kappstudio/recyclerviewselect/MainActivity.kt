package com.kappstudio.recyclerviewselect

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.kappstudio.recyclerviewselect.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val viewModel: SelectViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = SelectAdapter(viewModel)
        binding.rvSelect.adapter = adapter

        viewModel.list.observe(this) {
            adapter.submitList(it)
        }

        viewModel.singleSelection.observe(this) {
            adapter.notifyDataSetChanged()
        }

        viewModel.multipleSelection.observe(this) {
            adapter.notifyDataSetChanged()
        }
    }
}