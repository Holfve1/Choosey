package com.example.choosey.data.db
import androidx.room.Database
import androidx.room.RoomDatabase
@Database(
    entities = [Category::class, Selection::class],
    version = 1,
    exportSchema = true
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun categoryDao(): CategoryDao
    abstract fun selectionDao(): SelectionDao
}
