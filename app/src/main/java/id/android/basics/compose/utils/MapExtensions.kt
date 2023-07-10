package id.android.basics.compose.utils

internal fun <E> MutableSet<E>.addOrRemove(element: E) {
  if (!add(element)) {
    remove(element)
  }
}