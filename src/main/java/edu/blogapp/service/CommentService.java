package edu.blogapp.service;

import java.util.List;

import edu.blogapp.entity.Comment;
import edu.blogapp.entity.Post;

public interface CommentService {

    List<Comment> getAllComments(Long postid);

    void save(Comment comment);

}
