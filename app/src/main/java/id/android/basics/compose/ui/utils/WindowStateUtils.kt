package id.android.basics.compose.ui.utils

import android.graphics.Rect
import androidx.window.layout.FoldingFeature
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.contract

/**
 * Different type of navigation supported by app depending on size and state
 */
enum class ComposerNavigationType {
  BOTTOM_NAVIGATION,
  NAVIGATION_RAIL,
  PERMANENT_NAVIGATION_DRAWER
}

/**
 * Content shown depending on size and state of device
 */
enum class ComposerContentType {
  LIST_ONLY,
  LIST_AND_DETAIL
}

/**
 * Information about the posture of the device
 */
sealed interface DevicePosture {

  object NormalPosture : DevicePosture

  data class BookPosture(val hingePosition: Rect) : DevicePosture

  data class Separating(
    val hingePosition: Rect,
    var orientation: FoldingFeature.Orientation
  ) : DevicePosture
}

@OptIn(ExperimentalContracts::class)
fun isBookPosture(foldFeature: FoldingFeature?): Boolean {
  contract { returns(true) implies (foldFeature != null) }
  return foldFeature?.state == FoldingFeature.State.HALF_OPENED && foldFeature.orientation == FoldingFeature.Orientation.VERTICAL
}

@OptIn(ExperimentalContracts::class)
fun isSeparating(foldFeature: FoldingFeature?): Boolean {
  contract { returns(true) implies (foldFeature != null) }
  return foldFeature?.state == FoldingFeature.State.FLAT && foldFeature.isSeparating
}