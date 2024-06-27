package com.sonia.spinner

import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.sonia.spinner.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var binding: ActivityMainBinding? = null
    var spinnerValue = mutableListOf("First", "Second", "Third")
    lateinit var arrayAdapter: ArrayAdapter<String>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        arrayAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, spinnerValue)
        binding?.spDynamic?.adapter = arrayAdapter
        binding?.spDynamic?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                //var selectedItem=binding?.spDynamic?.selectedItem as String
                //Toast.makeText(this@MainActivity,"Selected item $position $selectedItem",Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }
        binding?.spStatic?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                // var selectedItem=binding?.spStatic?.selectedItem as String
                //Toast.makeText(this@MainActivity,"Selected item $position $selectedItem",Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }
        binding?.btnAdd?.setOnClickListener {
            var dialog = Dialog(this)
            dialog.setContentView(
                R.layout.custom_dialog
            )
            dialog.window?.setLayout(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT,
            )
            var btnconfirm = dialog.findViewById<Button>(R.id.btnconfirm)
            var etName = dialog.findViewById<EditText>(R.id.etName)
            btnconfirm.setOnClickListener {
                if (etName?.text?.toString()?.trim().isNullOrEmpty()) {
                    etName?.error = "Please Enter Name"
                } else {
                    spinnerValue.add(etName.text.toString().trim())
                    arrayAdapter.notifyDataSetChanged()
                    dialog.dismiss()
                }
            }
            dialog.show()
        }
    }
}