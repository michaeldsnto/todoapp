package com.example.todoapp.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapp.R
import com.example.todoapp.model.Todo

class TodoListAdapter(val todoList: ArrayList<Todo>, val adapterOnClick: (Todo) -> Unit
) : RecyclerView.Adapter<TodoListAdapter.TodoListViewHolder>() {
    class TodoListViewHolder(var view: View): RecyclerView.ViewHolder(view) {
        val cbTask: CheckBox
        val imgEdit: ImageButton
        init {
            cbTask = view.findViewById(R.id.cbTask)
            imgEdit = view.findViewById(R.id.imgEdit)
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.todo_item_layout, parent, false)
        return TodoListViewHolder(view)
    }
    override fun onBindViewHolder(holder: TodoListViewHolder, position: Int) {
        holder.cbTask.text = todoList[position].title
        holder.imgEdit.setOnClickListener {
            val action = TodoListFragmentDirections.actionEditTodo(todoList[position].uuid)
            Navigation.findNavController(it).navigate(action)
        }
        holder.cbTask.setOnCheckedChangeListener { compoundButton, isChecked ->
            if (isChecked == true) {
                todoList[position].is_done = 1
                adapterOnClick(todoList[position])
                notifyItemChanged(position)
            }
        }

    }
    override fun getItemCount(): Int {
        return todoList.size
    }
    fun updateTodoList(newTodoList: List<Todo>) {
        todoList.clear()
        todoList.addAll(newTodoList)
        notifyDataSetChanged()
    }
}
