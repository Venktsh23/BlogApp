package edu.blogapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import edu.blogapp.entity.Post;
import edu.blogapp.entity.User;

public interface PostService {

    List<Post> getAllPost(Optional<User> user);

    Page<Post> getByAuthor(Optional<User> user, int page, int size, String sortField, String sortDirection);

    void save(Post post);

    List<Post> getAll();

    Page<Post> getAllPages(Pageable pageable);

    Page<Post> getAllPages(Pageable pageable, Optional<User> user);

    Optional<Post> getPostById(Long pid);

    void remove(Long pid);

}
