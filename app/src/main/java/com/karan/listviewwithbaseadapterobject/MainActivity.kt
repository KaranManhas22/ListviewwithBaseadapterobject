package com.karan.listviewwithbaseadapterobject

import android.app.Dialog
import android.os.Bundle
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.ListAdapter
import com.karan.listviewwithbaseadapterobject.databinding.ActivityMainBinding
import com.karan.listviewwithbaseadapterobject.databinding.CustomDialogboxBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    var array = arrayListOf<NameData>()
    var List_Adapter1=List_Adapter1(array)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding.list.adapter = List_Adapter1
        binding.btnfloating.setOnClickListener {

            val dialogBinding = CustomDialogboxBinding.inflate(layoutInflater)
            val dialog = Dialog(this).apply {
                setContentView(dialogBinding.root)
                window?.setLayout(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )

                dialogBinding.btnadd.setOnClickListener {
                    array.add(NameData(dialogBinding.ettext.text.toString()))
                    startActivity(intent)
                    dismiss()
                    List_Adapter1.notifyDataSetChanged()
                }
                show()
            }

            binding.list.setOnItemLongClickListener { parent, View, position, id ->
                AlertDialog.Builder(this).apply {
                    setTitle("Are you want to delete your text from list")
                    setPositiveButton("Yes")
                    { _, _ ->
                        array.removeAt(position)
                        List_Adapter1.notifyDataSetChanged()
                    }
                    setNegativeButton("NO")
                    { _, _ ->
                    }
                    show()
                }

                return@setOnItemLongClickListener true
            }
            binding.list.setOnItemClickListener { parent, view, position, id ->
                val dialogBinding = CustomDialogboxBinding.inflate(layoutInflater)
                val update_dialog = Dialog(this).apply {
                    setContentView(dialogBinding.root)
                    window?.setLayout(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                    )
                    show()
                }
                val oldName: String = array[position].name
                dialogBinding.ettext.setText(oldName)
                val update = "update"
                dialogBinding.btnadd.text = update
                dialogBinding.btnadd.setOnClickListener {
                    if (dialogBinding.ettext.text.toString().isNullOrEmpty()){
                        dialogBinding.ettext.error="Enter Name"
                    }else {
                        array[position] = NameData(
                            dialogBinding.ettext.text.toString()
                        )
                        List_Adapter1.notifyDataSetChanged()
                        update_dialog.dismiss()
                    }
                }
            }

        }

    }
}