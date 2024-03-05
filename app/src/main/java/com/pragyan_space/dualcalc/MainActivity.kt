package com.pragyan_space.dualcalc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStore
import com.pragyan_space.dualcalc.databinding.ActivityMainBinding
import com.pragyan_space.dualcalc.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        viewModel.cal1Editor.postValue("")
        binding.setUpButtons()
        binding.setupObservers()
    }

    private fun ActivityMainBinding.setUpButtons() {
        calc1.apply {
            allClear.setOnClickListener { viewModel.cal1Editor.postValue("") }
            //setting numbers
            one.setOnClickListener { viewModel.cal1Editor.postValue(viewModel.cal1Editor.value + "1") }
            two.setOnClickListener { viewModel.cal1Editor.postValue(viewModel.cal1Editor.value + "2") }
            three.setOnClickListener { viewModel.cal1Editor.postValue(viewModel.cal1Editor.value + "3") }
            four.setOnClickListener { viewModel.cal1Editor.postValue(viewModel.cal1Editor.value + "4") }
            five.setOnClickListener { viewModel.cal1Editor.postValue(viewModel.cal1Editor.value + "5") }
            six.setOnClickListener { viewModel.cal1Editor.postValue(viewModel.cal1Editor.value + "6") }
            seven.setOnClickListener { viewModel.cal1Editor.postValue(viewModel.cal1Editor.value + "7") }
            eight.setOnClickListener { viewModel.cal1Editor.postValue(viewModel.cal1Editor.value + "8") }
            nine.setOnClickListener { viewModel.cal1Editor.postValue(viewModel.cal1Editor.value + "9") }
            zero.setOnClickListener { viewModel.cal1Editor.postValue(viewModel.cal1Editor.value + "0") }
            dZero.setOnClickListener { viewModel.cal1Editor.postValue(viewModel.cal1Editor.value + "00") }
            plus.setOnClickListener { viewModel.cal1Editor.postValue(viewModel.cal1Editor.value + "+") }
            minus.setOnClickListener { viewModel.cal1Editor.postValue(viewModel.cal1Editor.value + "-") }
            dot.setOnClickListener { viewModel.cal1Editor.postValue(viewModel.cal1Editor.value + ".") }
            delete.setOnClickListener {
                val value=viewModel.cal1Editor.value
                viewModel.cal1Editor.postValue(value?.substring(0,value.length-1)) }
        }
    }

    private fun ActivityMainBinding.setupObservers() {
        viewModel.cal1Editor.observe(this@MainActivity)
        {
            this.calc1.editorValue=it
            viewModel.calculate(it)
        }

        viewModel.cal1Total.observe(this@MainActivity)
        {
            this.calc1.totalValue=it
        }
    }
}
