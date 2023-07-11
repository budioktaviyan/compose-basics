package id.android.basics.compose.data.posts

import id.android.basics.compose.model.Post

/**
 * Simplified implementation of PostsRepository that returns a hardcoded list of
 * posts with resources synchronously
 */
class PostsRepository {
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
}