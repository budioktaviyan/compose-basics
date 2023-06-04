package id.android.basics.compose.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import id.android.basics.compose.data.DestinationsRepository
import id.android.basics.compose.data.ExploreModel
import id.android.basics.compose.di.DefaultDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.random.Random

const val MAX_PEOPLE = 4

@HiltViewModel
class MainViewModel @Inject constructor(
  private val repository: DestinationsRepository,
  @DefaultDispatcher private val defaultDispatcher: CoroutineDispatcher) : ViewModel() {

  val hotels: List<ExploreModel> = repository.hotels
  val restaurants: List<ExploreModel> = repository.restaurants

  private val _suggestedDestinations = MutableStateFlow<List<ExploreModel>>(emptyList())
  val suggestedDestinations: StateFlow<List<ExploreModel>>
    get() = _suggestedDestinations

  init {
    _suggestedDestinations.value = repository.destinations
  }

  fun updatePeople(people: Int) {
    viewModelScope.launch {
      if (people > MAX_PEOPLE) {
        _suggestedDestinations.value = emptyList()
      } else {
        val newDestinations = withContext(defaultDispatcher) {
          repository.destinations.shuffled(Random(people * (1..100).shuffled().first()))
        }

        _suggestedDestinations.value = newDestinations
      }
    }
  }

  fun toDestinationChanged(newDestination: String) {
    viewModelScope.launch {
      val newDestinations = withContext(defaultDispatcher) {
        repository.destinations.filter { it.city.nameToDisplay.contains(newDestination) }
      }

      _suggestedDestinations.value = newDestinations
    }
  }
}