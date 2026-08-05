package com.example.todoapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.todoapp.data.entities.*

@Database(
    entities = [Task::class, SubTask::class, Tag::class, Note::class, CalendarNote::class, Habit::class, HabitEntry::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao
    abstract fun subTaskDao(): SubTaskDao
    abstract fun tagDao(): TagDao
    abstract fun noteDao(): NoteDao
    abstract fun calendarNoteDao(): CalendarNoteDao
    abstract fun habitDao(): HabitDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "todo_database"
                ).build().also { INSTANCE = it }
            }
        }
    }
}
