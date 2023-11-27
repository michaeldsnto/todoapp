package com.example.todoapp.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.todoapp.R
import com.example.todoapp.model.Todo
import com.example.todoapp.viewmodel.DetailTodoViewModel
import com.google.android.material.textfield.TextInputEditText

class EditTodoFragment : Fragment() {
    private lateinit var viewModel: DetailTodoViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create_todo, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(DetailTodoViewModel::class.java)
        val txtTitle = view.findViewById<TextInputEditText>(R.id.txtTitle)
        val txtNotes = view.findViewById<TextInputEditText>(R.id.txtNotes)
        val radioGroupPriority = view.findViewById<RadioGroup>(R.id.radioGroupPriority)
        var radio = view.findViewById<RadioButton>(radioGroupPriority.checkedRadioButtonId)

        val txtJudulTodo = view.findViewById<TextView>(R.id.txtJudulTodo)
        val btnAdd = view.findViewById<Button>(R.id.btnAdd)
        txtJudulTodo.text = "Edit Todo"
        btnAdd.text = "Save Changes"
        val uuid = EditTodoFragmentArgs.fromBundle(requireArguments()).uuid
        Log.d("EditTodoFragment", "UUID: $uuid")
        viewModel.fetch(uuid)
        observeViewModel()
        btnAdd.setOnClickListener {
            val radio =
                view.findViewById<RadioButton>(radioGroupPriority.checkedRadioButtonId)
            viewModel.update(txtTitle.text.toString(), txtNotes.text.toString(),
                radio.tag.toString().toInt(), uuid)
            Toast.makeText(view.context, "Todo updated", Toast.LENGTH_SHORT).show()
            Navigation.findNavController(it).popBackStack()
        }


    }
    fun observeViewModel() {
        viewModel.todoLD.observe(viewLifecycleOwner, Observer {
            val txtTitle = view?.findViewById<TextInputEditText>(R.id.txtTitle)
            val txtNotes = view?.findViewById<TextInputEditText>(R.id.txtNotes)
            val rdoLow = view?.findViewById<RadioButton>(R.id.rdoLow)
            val rdoMedium = view?.findViewById<RadioButton>(R.id.rdoMedium)
            val rdoHigh = view?.findViewById<RadioButton>(R.id.rdoHigh)

            txtTitle?.setText(it.title)
            txtNotes?.setText(it.notes)
            when (it.priority) {
                1 -> rdoLow?.isChecked = true
                2 -> rdoMedium?.isChecked = true
                else -> rdoHigh?.isChecked = true
            }


        })
    }


}