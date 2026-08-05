package com.example.todoapp.data

import com.example.todoapp.data.entities.*
import kotlinx.coroutines.flow.Flow

class Repository(
    private val taskDao: TaskDao,
    private val noteDao: NoteDao,
    private val calendarNoteDao: CalendarNoteDao,
    private val tagDao: TagDao,
    private val subTaskDao: SubTaskDao,
    private val habitDao: HabitDao
) {
    fun getAllTasks(): Flow<List<Task>> = taskDao.getAll()
    suspend fun insertTask(task: Task) = taskDao.insert(task)
    suspend fun updateTask(task: Task) = taskDao.update(task)
    suspend fun deleteTask(task: Task) = taskDao.delete(task)

    fun getSubTasks(taskId: Int): Flow<List<SubTask>> = subTaskDao.getForTask(taskId)
    suspend fun insertSubTask(subTask: SubTask) = subTaskDao.insert(subTask)
    suspend fun updateSubTask(subTask: SubTask) = subTaskDao.update(subTask)
    suspend fun deleteSubTask(subTask: SubTask) = subTaskDao.delete(subTask)

    fun getAllTags(): Flow<List<Tag>> = tagDao.getAll()
    suspend fun insertTag(tag: Tag) = tagDao.insert(tag)
    suspend fun deleteTag(tag: Tag) = tagDao.delete(tag)

    fun getAllNotes(): Flow<List<Note>> = noteDao.getAll()
    suspend fun insertNote(note: Note) = noteDao.insert(note)
    suspend fun updateNote(note: Note) = noteDao.update(note)
    suspend fun deleteNote(note: Note) = noteDao.delete(note)

    fun getNoteForDate(date: String): Flow<CalendarNote?> = calendarNoteDao.getForDate(date)
    fun getAllCalendarNotes(): Flow<List<CalendarNote>> = calendarNoteDao.getAll()
    suspend fun saveCalendarNote(note: CalendarNote) = calendarNoteDao.insert(note)

    fun getAllHabits(): Flow<List<Habit>> = habitDao.getAll()
    suspend fun insertHabit(habit: Habit) = habitDao.insert(habit)
    suspend fun deleteHabit(habit: Habit) = habitDao.delete(habit)
    fun getEntriesForHabit(habitId: Int): Flow<List<HabitEntry>> = habitDao.getEntries(habitId)
    suspend fun insertHabitEntry(entry: HabitEntry) = habitDao.insertEntry(entry)
    suspend fun deleteHabitEntry(entry: HabitEntry) = habitDao.deleteEntry(entry)
}
