package edu.blogapp.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import edu.blogapp.entity.Post;
import edu.blogapp.entity.User;

@Repository
public interface PostRepo extends JpaRepository<Post, Long> {

  List<Post> findByAuthor(Optional<User> user);

  Page<Post> findByAuthor(Optional<User> user, Pageable page);

  default Page<Post> findByOptionalAuthor(Optional<User> author, Pageable pageable) {
    if (author.isPresent()) {
      return findByAuthor(author, pageable);
    }
    return findAll(pageable);
  }

  @Modifying
  @Query("DELETE FROM Comment c WHERE c.post.id = :postId")
  void deleteCommentsByPostId(@Param("postId") Long postId);

  @Modifying
  @Query("DELETE FROM Post p WHERE p.id = :postId")
  void deletePostById(@Param("postId") Long postId);

}
