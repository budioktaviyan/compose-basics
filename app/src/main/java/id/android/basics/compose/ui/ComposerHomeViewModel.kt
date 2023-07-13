package id.android.basics.compose.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.android.basics.compose.data.Email
import id.android.basics.compose.data.EmailsRepository
import id.android.basics.compose.data.EmailsRepositoryImpl
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

data class ComposerHomeUIState(
  val emails: List<Email> = emptyList(),
  val loading: Boolean = false,
  val error: String? = null
)

class ComposerHomeViewModel(private val repository: EmailsRepository = EmailsRepositoryImpl()) : ViewModel() {
  /* UI state exposed to the UI */
  private val _uiState = MutableStateFlow(ComposerHomeUIState(loading = true))
  val uiState: StateFlow<ComposerHomeUIState> = _uiState

  init {
    observeEmails()
  }

  private fun observeEmails() {
    viewModelScope.launch {
      repository.getAllEmails()
        .catch { exception ->
        _uiState.value = ComposerHomeUIState(error = exception.message)
        }
        .collect { emails ->
        _uiState.value = ComposerHomeUIState(emails = emails)
      }
    }
  }
}