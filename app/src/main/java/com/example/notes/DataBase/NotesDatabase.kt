package com.example.notes.DataBase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.notes.DAO.NoteDao
import com.example.notes.Entity.Notes

@Database(entities = [Notes::class], version = 1, exportSchema = false)
abstract class NotesDatabase : RoomDatabase() {

    companion object {
        var notesDatabase: NotesDatabase? = null

        @Synchronized
        fun getDatabase(context: Context): NotesDatabase {
            if (notesDatabase == null) {
                notesDatabase = Room.databaseBuilder(
                    context
                    , NotesDatabase::class.java
                    , "Notes.db"
                ).build()
            }
            return notesDatabase!!
        }
    }

    abstract fun noteDao(): NoteDao
}