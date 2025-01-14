package edu.blogapp.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import edu.blogapp.entity.Comment;
import edu.blogapp.entity.Post;
import edu.blogapp.entity.User;
import edu.blogapp.helper.EmailHelper;
import edu.blogapp.service.CommentService;
import edu.blogapp.service.PostService;
import edu.blogapp.service.UserService;

@Controller
public class CommentController {

  @Autowired
  PostService postService;

  @Autowired
  UserService userService;

  @Autowired
  CommentService commentService;

  @GetMapping("/user/comments/{postid}")
  public String comments(@PathVariable Long postid, Model model, Authentication authentication) {
    List<Comment> com = commentService.getAllComments(postid);
    Optional<Post> post = postService.getPostById(postid);
    System.out.println(post.get());

    String username = EmailHelper.getEmailOfLoggedInUser(authentication);

    System.out.println(username);

    Optional<User> user = userService.getUserByEmail(username);
    model.addAttribute("comments", com);
    model.addAttribute("postid", postid);
    model.addAttribute("user", username);
    System.out.println(postid + " this is post is");

    return "user/comment";

  }

  @PostMapping("/user/addnew/{postid}")
  public String createNewComment(@PathVariable Long postid, @ModelAttribute Comment comment, Model model,
      Authentication authentication) {

    Optional<Post> post = postService.getPostById(postid);
    System.out.println(post.get());

    String username = EmailHelper.getEmailOfLoggedInUser(authentication);

    System.out.println(username);

    Optional<User> user = userService.getUserByEmail(username);
    Post p = new Post();
    // p.setId(id++);
    User usr = user.get();

    Comment c = new Comment();
    c.setContent(comment.getContent());
    c.setId(comment.getId());
    c.setPost(post.get());
    c.setUser(usr);

    commentService.save(c);

    System.out.println(usr + " comments");
    System.out.println(post.get() + " commet s");

    return "user/comment";

  }

}
