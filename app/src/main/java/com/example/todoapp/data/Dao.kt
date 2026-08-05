package com.example.todoapp.data

import androidx.room.*
import com.example.todoapp.data.entities.*
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {
    @Query("SELECT * FROM tasks ORDER BY createdAt DESC")
    fun getAll(): Flow<List<Task>>

    @Insert
    suspend fun insert(task: Task)

    @Update
    suspend fun update(task: Task)

    @Delete
    suspend fun delete(task: Task)
}

@Dao
interface SubTaskDao {
    @Query("SELECT * FROM sub_tasks WHERE taskId = :taskId")
    fun getForTask(taskId: Int): Flow<List<SubTask>>

    @Insert
    suspend fun insert(subTask: SubTask)

    @Update
    suspend fun update(subTask: SubTask)

    @Delete
    suspend fun delete(subTask: SubTask)
}

@Dao
interface TagDao {
    @Query("SELECT * FROM tags")
    fun getAll(): Flow<List<Tag>>

    @Insert
    suspend fun insert(tag: Tag)

    @Delete
    suspend fun delete(tag: Tag)
}

@Dao
interface NoteDao {
    @Query("SELECT * FROM notes ORDER BY updatedAt DESC")
    fun getAll(): Flow<List<Note>>

    @Insert
    suspend fun insert(note: Note)

    @Update
    suspend fun update(note: Note)

    @Delete
    suspend fun delete(note: Note)
}

@Dao
interface CalendarNoteDao {
    @Query("SELECT * FROM calendar_notes WHERE date = :date")
    fun getForDate(date: String): Flow<CalendarNote?>

    @Query("SELECT * FROM calendar_notes")
    fun getAll(): Flow<List<CalendarNote>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(note: CalendarNote)
}

@Dao
interface HabitDao {
    @Query("SELECT * FROM habits")
    fun getAll(): Flow<List<Habit>>

    @Insert
    suspend fun insert(habit: Habit)

    @Delete
    suspend fun delete(habit: Habit)

    @Query("SELECT * FROM habit_entries WHERE habitId = :habitId")
    fun getEntries(habitId: Int): Flow<List<HabitEntry>>

    @Insert
    suspend fun insertEntry(entry: HabitEntry)

    @Delete
    suspend fun deleteEntry(entry: HabitEntry)
}
