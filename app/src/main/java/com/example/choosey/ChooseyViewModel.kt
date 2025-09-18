package com.example.choosey
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.choosey.data.repo.ChooseyRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import androidx.compose.runtime.State

class ChooseyViewModel(
    private val repo: ChooseyRepository = AppGraph.repo
) : ViewModel() {

    // --- Current category state (shared across screens) ---
    private val _currentCategoryId = mutableStateOf(1L) // default Takeaway
    val currentCategoryId: State<Long> get() = _currentCategoryId
    fun setCategory(id: Long) { _currentCategoryId.value = id }

    // --- Repository access ---
    fun selections(categoryId: Long) = repo.observeSelectionsByCategory(categoryId)
    fun selectedAll() = repo.observeAllSelected()

    fun toggleSelection(id: Long) {
        viewModelScope.launch { repo.toggleSelection(id) }
    }

    suspend fun pickRandomLabel(categoryId: Long): String? {
        val list = repo.observeSelectionsByCategory(categoryId).first()
            .filter { it.isSelected }
        return if (list.isEmpty()) null else list.random().label
    }
}