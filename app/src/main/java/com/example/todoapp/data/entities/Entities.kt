package com.example.todoapp.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

enum class Priority { NONE, LOW, MEDIUM, HIGH }
enum class RecurrenceType { NONE, DAILY, WEEKLY, MONTHLY }

@Entity(tableName = "tasks")
data class Task(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val description: String = "",
    val category: String = "General",
    val priority: Priority = Priority.NONE,
    val expiryDate: Long? = null,
    val recurrenceType: RecurrenceType = RecurrenceType.NONE,
    val tagIds: String = "",
    val isCompleted: Boolean = false,
    val createdAt: Long = System.currentTimeMillis()
)

@Entity(tableName = "sub_tasks")
data class SubTask(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val taskId: Int,
    val title: String,
    val isCompleted: Boolean = false
)

@Entity(tableName = "tags")
data class Tag(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val color: Long
)

@Entity(tableName = "notes")
data class Note(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val content: String = "",
    val tagIds: String = "",
    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long = System.currentTimeMillis()
)

@Entity(tableName = "calendar_notes")
data class CalendarNote(
    @PrimaryKey val date: String,
    val content: String = ""
)

@Entity(tableName = "habits")
data class Habit(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val description: String = "",
    val color: Long,
    val targetDaysPerWeek: Int = 7
)

@Entity(tableName = "habit_entries", primaryKeys = ["habitId", "date"])
data class HabitEntry(
    val habitId: Int,
    val date: String
)
