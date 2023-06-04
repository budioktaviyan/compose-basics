package id.android.basics.compose.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import id.android.basics.compose.base.Result
import id.android.basics.compose.data.DestinationsRepository
import id.android.basics.compose.data.ExploreModel
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
  private val repository: DestinationsRepository,
  savedStateHandle: SavedStateHandle) : ViewModel() {

  private val cityName = savedStateHandle.get<String>(KEY_ARG_DETAILS_CITY_NAME)

  val cityDetails: Result<ExploreModel>
    get() {
      val destination = cityName?.let { repository.getDestination(it) }

      return if (destination != null) {
        Result.Success(destination)
      } else {
        Result.Error(IllegalArgumentException("City doesn't exist"))
      }
    }
}