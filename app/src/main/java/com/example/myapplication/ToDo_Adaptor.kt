package com.example.myapplication

import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class ToDo_Adaptor(
    private val todos: MutableList<ToDo>
) : RecyclerView.Adapter<ToDo_Adaptor.ToDoViewHolder>() {

    //class ToDoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    class ToDoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvToDoTitle = itemView.findViewById<TextView>(R.id.tvToDoTitle)
        val cdDone = itemView.findViewById<CheckBox>(R.id.cbDone)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoViewHolder {
        return ToDoViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_todo, parent, false)
        )
    }
    fun addToDo(todo: ToDo){
        todos.add(todo)
        notifyItemInserted(todos.size - 1)
    }

    fun deleteDone(){
        todos.removeAll { todo ->
            todo.isChecked
        }
        notifyDataSetChanged()
    }

    private fun toggleStrikeThrough(tvToDoTitle: TextView, isChecked: Boolean){
        if (isChecked){
            tvToDoTitle.paintFlags = tvToDoTitle.paintFlags or STRIKE_THRU_TEXT_FLAG
        }
        else{
            tvToDoTitle.paintFlags = tvToDoTitle.paintFlags and STRIKE_THRU_TEXT_FLAG.inv()
        }
    }

    override fun onBindViewHolder(holder: ToDoViewHolder, position: Int) {
        val current_todo = todos[position]
        holder.itemView.apply {
            holder.tvToDoTitle.text = current_todo.title
            holder.cdDone.isChecked = current_todo.isChecked
            toggleStrikeThrough(holder.tvToDoTitle, current_todo.isChecked)
            holder.cdDone.setOnCheckedChangeListener{ _, isChecked ->
                toggleStrikeThrough(holder.tvToDoTitle, isChecked)
                current_todo.isChecked = !current_todo.isChecked
            }
        }
    }

    override fun getItemCount(): Int {
        return todos.size
    }
}