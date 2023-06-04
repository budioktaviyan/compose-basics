package id.android.basics.compose.data

import javax.inject.Inject

class DestinationsRepository @Inject constructor(private val datasource: DestinationsLocalDataSource) {

  val destinations: List<ExploreModel> = datasource.composerDestinations
  val hotels: List<ExploreModel> = datasource.composerHotels
  val restaurants: List<ExploreModel> = datasource.composerRestaurants

  fun getDestination(cityName: String): ExploreModel? {
    return datasource.composerDestinations.firstOrNull {
      it.city.name == cityName
    }
  }
}