package edu.blogapp.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.blogapp.entity.Post;
import edu.blogapp.entity.User;
import edu.blogapp.repository.PostRepo;
import edu.blogapp.service.PostService;

@Service
@Transactional
public class PostServiceImpl implements PostService {

    @Autowired
    PostRepo prepo;

    @Override
    public List<Post> getAllPost(Optional<User> user) {

        return prepo.findByAuthor(user);
    }

    @Override
    public Page<Post> getByAuthor(Optional<User> user, int page, int size, String sortBy, String sortDirection) {

        Sort sort = sortDirection.equals("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();

        var pageable = PageRequest.of(page, size, sort);

        return prepo.findByAuthor(user, pageable);

    }

    @Override
    public void save(Post post) {
        prepo.save(post);
    }

    @Override
    public List<Post> getAll() {
        return prepo.findAll();
    }

    @Override
    public Page<Post> getAllPages(Pageable pageable) {
        return prepo.findAll(pageable);

    }

    @Override
    public Page<Post> getAllPages(Pageable pageable, Optional<User> user) {
        return prepo.findByOptionalAuthor(user, pageable);

    }

    @Override
    public Optional<Post> getPostById(Long pid) {
        return prepo.findById(pid);
    }

    @Override
    public void remove(Long pid) {

        prepo.deleteCommentsByPostId(pid);
        prepo.deletePostById(pid);
    }

}
