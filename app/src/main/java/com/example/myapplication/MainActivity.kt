package com.example.myapplication

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.ComponentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity(private val context: Context) : ComponentActivity() {

    private lateinit var todoAdaptor: ToDo_Adaptor
    private lateinit var rvToDo: RecyclerView
    private lateinit var btnToDoAdd: Button
    private lateinit var etToDo: EditText
    private lateinit var btnToDoDelete: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //todoAdaptor = ToDo_Adaptor(mutableListOf())

        val savedTodoList = loadToDoList()
        todoAdaptor = ToDo_Adaptor(savedTodoList, this)

        //todoAdaptor = loadToDoList()

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
    }

    private val sharedPreferences: SharedPreferences = context.getSharedPreferences("todo_prefs", Context.MODE_PRIVATE)
    private val todoListKey = "todo_list"

    private fun loadToDoList(): MutableList<ToDo> {
        val jsonList = sharedPreferences.getString(todoListKey, null)
        return if (jsonList != null) {
            val todoList = mutableListOf<ToDo>()
            val todoStrings = jsonList.split(",")
            for (todoString in todoStrings) {
                val todo = ToDo(todoString)
                todoList.add(todo)
            }
            todoList
        } else {
            mutableListOf()
        }
    }
}