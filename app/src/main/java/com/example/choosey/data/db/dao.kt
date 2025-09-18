package com.example.choosey.data.db
import androidx.room.*
import kotlinx.coroutines.flow.Flow
@Dao
interface CategoryDao {
    @Query("SELECT * FROM categories ORDER BY name")
    fun observeAll(): Flow<List<Category>>
    @Transaction
    @Query("SELECT * FROM categories WHERE id = :id")
    fun observeWithSelections(id: Long): Flow<CategoryWithSelections>
    @Insert
    suspend fun insertAll(categories: List<Category>)
    @Query("DELETE FROM categories")
    suspend fun clear()
}
@Dao
interface SelectionDao {
    @Query("SELECT * FROM selections WHERE categoryId = :categoryId ORDER BY label")
    fun observeByCategory(categoryId: Long): Flow<List<Selection>>
    @Query("SELECT * FROM selections WHERE isSelected = 1 ORDER BY label")
    fun observeSelected(): Flow<List<Selection>>
    @Insert
    suspend fun insertAll(selections: List<Selection>)
    @Update
    suspend fun update(selection: Selection)
    @Query("UPDATE selections SET isSelected = NOT isSelected WHERE id = :id")
    suspend fun toggle(id: Long)
    @Query("DELETE FROM selections")
    suspend fun clear()
}