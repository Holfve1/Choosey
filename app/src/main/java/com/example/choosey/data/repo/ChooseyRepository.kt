package com.example.choosey.data.repo
import com.example.choosey.data.db.Category
import com.example.choosey.data.db.CategoryDao
import com.example.choosey.data.db.Selection
import com.example.choosey.data.db.SelectionDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlin.random.Random
class ChooseyRepository(
    private val categoryDao: CategoryDao,
    private val selectionDao: SelectionDao
) {

    // Observe all categories
    fun observeCategories(): Flow<List<Category>> = categoryDao.observeAll()

    // Observe selections in a specific category
    fun observeSelectionsByCategory(categoryId: Long): Flow<List<Selection>> =
        selectionDao.observeByCategory(categoryId)

    // Observe all selected items (across categories)
    fun observeAllSelected(): Flow<List<Selection>> = selectionDao.observeSelected()

    // Toggle selection state for a single item
    suspend fun toggleSelection(selectionId: Long) = selectionDao.toggle(selectionId)

    // Pick a random selection from a specific category
    suspend fun randomPickFromSelected(categoryId: Long): Selection? {
        val list = selectionDao.observeByCategory(categoryId)
            .first()
            .filter { it.isSelected }
        return if (list.isEmpty()) null else list[Random.nextInt(list.size)]
    }
}