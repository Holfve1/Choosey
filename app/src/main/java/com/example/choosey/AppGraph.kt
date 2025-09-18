package com.example.choosey
import android.content.Context
import androidx.room.Room
import com.example.choosey.data.db.AppDatabase
import com.example.choosey.data.repo.ChooseyRepository
object AppGraph {
    lateinit var db: AppDatabase
        private set
    lateinit var repo: ChooseyRepository
        private set
    fun provide(ctx: Context) {
        db = Room.databaseBuilder(ctx, AppDatabase::class.java, "choosey.db")
            .fallbackToDestructiveMigration()
            .addCallback(SeedCallback) // seeds on first create
            .build()
        repo = ChooseyRepository(db.categoryDao(), db.selectionDao())
    }
}