package com.example.todoapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Todo(
    @ColumnInfo(name="Title")
    var title: String,
    @ColumnInfo(name="Notes")
    var notes: String,
    @ColumnInfo(name="priority")
    var priority:Int,
    @ColumnInfo(name="is_done")
    var is_done:Int = 0,
) {
    @PrimaryKey(autoGenerate = true)
    var uuid:Int = 0
}