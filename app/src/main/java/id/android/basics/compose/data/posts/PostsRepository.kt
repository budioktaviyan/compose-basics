package id.android.basics.compose.data.posts

import id.android.basics.compose.model.Post
import id.android.basics.compose.utils.addOrRemove
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

/**
 * Simplified implementation of PostsRepository that returns a hardcoded list of
 * posts with resources synchronously
 */
class PostsRepository {
  /**
   * Keep the favorites in memory
   */
  private val favorites = MutableStateFlow<Set<String>>(setOf())

  /**
   * Get a specific News post
   */
  fun getPost(postId: String?): Post? {
    return posts.find { it.id == postId }
  }

  /**
   * Get News posts
   */
  fun getPosts(): List<Post> {
    return posts
  }

  /**
   * Observe the current favorites
   */
  fun observeFavorites(): Flow<Set<String>> = favorites

  /**
   * Toggle a postId to be a favorite or not
   */
  fun toggleFavorite(postId: String) {
    val set = favorites.value.toMutableSet()
    set.addOrRemove(postId)
    favorites.value = set
  }
}