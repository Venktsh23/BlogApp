package edu.blogapp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.blogapp.entity.Comment;
import edu.blogapp.repository.CommentRepo;
import edu.blogapp.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    CommentRepo commentRepo;

    @Override
    public List<Comment> getAllComments(Long postid) {
        return commentRepo.findByPostId(postid);
    }

    @Override
    public void save(Comment comment) {
        commentRepo.save(comment);
    }

}
