package com.example.choosey
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
/**
 * Seeding with raw SQL so we can hardcode IDs (1 = Takeaway, 2 = Movie Genre).
 * This runs only on first DB create.
 */
object SeedCallback : RoomDatabase.Callback() {
    override fun onCreate(db: SupportSQLiteDatabase) {
        super.onCreate(db)
        // Categories
        db.execSQL("INSERT INTO categories (id, name) VALUES (1,'Yes / No')")
        db.execSQL("INSERT INTO categories (id, name) VALUES (2,'Movie Genre')")
        db.execSQL("INSERT INTO categories (id, name) VALUES (3,'Date Night')")
        db.execSQL("INSERT INTO categories (id, name) VALUES (4,'Takeaway')")
        // Takeaway selections (categoryId = 4)

        val yesno = listOf(
            "YES","NO"
        )
        yesno.forEach { label ->
            db.execSQL("INSERT INTO selections (categoryId,label,isSelected) VALUES (1,'$label',0)")
        }

        val takeaway = listOf(
            "Chinese","Indian","Pizza","Fish & Chips","Burger","Kebab","Thai","Sushi", "Mexican","Fried Chicken","Greek","Turkish","Korean","Vietnamese", "Italian","Lebanese",
        )
        takeaway.forEach { label ->
            db.execSQL("INSERT INTO selections (categoryId,label,isSelected) VALUES (4,'$label',0)")
        }
        // Movie genre selections (categoryId = 2)
        val genres = listOf(
            "Action","Comedy","Drama","Horror","Sci-Fi","Fantasy","Romance","Thriller",
            "Mystery","Animation","Documentary","Adventure","Crime","Family",
            "Western","Musical"
        )
        genres.forEach { label ->
            db.execSQL("INSERT INTO selections (categoryId,label,isSelected) VALUES (2,'$label',0)")
        }
        val datenight = listOf(
            "Dinner", "Movie", "Bowling", "Drinks", "Board Game", "Karaoke",
            "Mini Golf", "Night Out", "Desert Crawl", "Escape Room"
        )
        datenight.forEach { label ->
            db.execSQL("INSERT INTO selections (categoryId,label,isSelected) VALUES (3,'$label',0)")
        }

    }
}