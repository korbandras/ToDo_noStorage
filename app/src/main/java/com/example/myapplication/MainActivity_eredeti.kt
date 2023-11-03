package com.example.myapplication

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.ComponentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity_eredeti : ComponentActivity() {

    private lateinit var todoAdaptor: ToDo_Adaptor
    private lateinit var rvToDo: RecyclerView
    private lateinit var btnToDoAdd: Button
    private lateinit var etToDo: EditText
    private lateinit var btnToDoDelete: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        todoAdaptor = ToDo_Adaptor(mutableListOf())

        rvToDo = findViewById(R.id.rvToDo)
        btnToDoAdd = findViewById(R.id.btnToDoAdd)
        etToDo = findViewById(R.id.etToDo)
        btnToDoDelete = findViewById(R.id.btnToDoDelete)

        rvToDo.adapter = todoAdaptor
        rvToDo.layoutManager = LinearLayoutManager(this)

        btnToDoAdd.setOnClickListener{
            val todoTitle = etToDo.text.toString()
            if (todoTitle.isNotEmpty()){
                val todo = ToDo(todoTitle)
                todoAdaptor.addToDo(todo)
                etToDo.text.clear()
            }
        }

        btnToDoDelete.setOnClickListener{
            todoAdaptor.deleteDone()
        }
        //anyad
    }
}