package com.example.choosey
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.choosey.data.repo.ChooseyRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import androidx.compose.runtime.State

class ChooseyViewModel(
    private val repo: ChooseyRepository = AppGraph.repo
) : ViewModel() {

    // --- Current category state (shared across screens) ---
    private val _currentCategoryId = mutableStateOf(1L)
    val currentCategoryId: State<Long> get() = _currentCategoryId

    fun setCategory(id: Long) {
        _currentCategoryId.value = id
    }

    val categories = repo.observeCategories()

    // --- Repository access ---
    fun selections(categoryId: Long) = repo.observeSelectionsByCategory(categoryId)
    fun selectedAll() = repo.observeAllSelected()

    fun toggleSelection(id: Long) {
        viewModelScope.launch { repo.toggleSelection(id) }
    }

    fun setAllSelected(categoryId: Long, select: Boolean) = viewModelScope.launch {
        repo.setAllSelected(categoryId, select)
    }

    suspend fun pickRandomLabel(categoryId: Long): String? {
        val list = repo.observeSelectionsByCategory(categoryId).first()
            .filter { it.isSelected }
        return if (list.isEmpty()) null else list.random().label
    }

    fun addOption(label: String) {
        val categoryId = currentCategoryId.value
        viewModelScope.launch {
            repo.addSelection(categoryId, label)
        }
    }

    fun deleteById(id: Long) = viewModelScope.launch {
        repo.deleteById(id)
    }

        fun addCategory(name: String, onAdded: (Long) -> Unit) {
            viewModelScope.launch {
                val result = repo.addCategory(name)
                result.onSuccess { id -> onAdded(id) }
                // Optionally handle failure with result.onFailure { ... }
            }
        }


        suspend fun getCategoryName(id: Long): String {
            val categories = repo.observeCategories().first()
            return categories.find { it.id == id }?.name ?: "Unknown"
        }

        fun deleteCategory(id: Long) {
            viewModelScope.launch {
                repo.deleteCategory(id)
            }
        }
    }
