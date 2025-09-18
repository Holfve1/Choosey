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
        db.execSQL("INSERT INTO categories (id, name) VALUES (1,'Takeaway')")
        db.execSQL("INSERT INTO categories (id, name) VALUES (2,'Movie Genre')")
        db.execSQL("INSERT INTO categories (id, name) VALUES (3,'Date Night')")
        // Takeaway selections (categoryId = 1)
        val takeaway = listOf(
            "Chinese","Indian","Pizza","Fish & Chips","Burger","Kebab","Thai","Sushi",
            "Japanese","Mexican","Fried Chicken","Greek","Turkish","Korean","Vietnamese",
            "Malaysian","BBQ","Italian","Falafel","Biryani"
        )
        takeaway.forEach { label ->
            db.execSQL("INSERT INTO selections (categoryId,label,isSelected) VALUES (1,'$label',0)")
        }
        // Movie genre selections (categoryId = 2)
        val genres = listOf(
            "Action","Comedy","Drama","Horror","Sci-Fi","Fantasy","Romance","Thriller",
            "Mystery","Animation","Documentary","Adventure","Crime","Family","War",
            "Western","Musical","Biography","History","Sport"
        )
        genres.forEach { label ->
            db.execSQL("INSERT INTO selections (categoryId,label,isSelected) VALUES (2,'$label',0)")
        }
        val datenight = listOf(
            "Dinner", "Movie", "Bowling", "Drinks", "Board Game", "Karaoke",
            "Mini Golf", "Nightout", "Dessert Crawl", "Escape Room"
        )
        datenight.forEach { label ->
            db.execSQL("INSERT INTO selections (categoryId,label,isSelected) VALUES (3,'$label',0)")
        }

    }
}