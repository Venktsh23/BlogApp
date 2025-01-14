package edu.blogapp.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import edu.blogapp.entity.Post;
import edu.blogapp.entity.User;
import edu.blogapp.helper.EmailHelper;
import edu.blogapp.service.PostService;
import edu.blogapp.service.UserService;

@Controller
@RequestMapping()
public class PostController {

    static long id = 1;

    @Autowired
    PostService postService;

    @Autowired
    UserService userService;

    @PostMapping("/user/profile")
    public String profileView(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size,
            Model model, Authentication authentication) {

        Page<Post> postPage = postService.getAllPages(PageRequest.of(page, size, Sort.by("createdAt").descending()));

        String username = EmailHelper.getEmailOfLoggedInUser(authentication);

        System.out.println(username);

        Optional<User> user = userService.getUserByEmail(username);

        // User user = userService.getUserByEmail(username);
        System.out.println(user);

        model.addAttribute("loggedInUser", user);

        List<Post> posts = postService.getAll();
        model.addAttribute("posts", posts);

        model.addAttribute("posts", postPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", postPage.getTotalPages());
        model.addAttribute("totalItems", postPage.getTotalElements());

        return "user/profile";
    }

    @GetMapping("/user/profile")
    public String profileView3(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size,
            Model model, Authentication authentication) {

        Page<Post> postPage = postService.getAllPages(PageRequest.of(page, size, Sort.by("createdAt").descending()));

        String username = EmailHelper.getEmailOfLoggedInUser(authentication);

        System.out.println(username);

        Optional<User> user = userService.getUserByEmail(username);

        // User user = userService.getUserByEmail(username);
        System.out.println(user);

        model.addAttribute("loggedInUser", user);

        List<Post> posts = postService.getAll();
        model.addAttribute("posts", posts);

        model.addAttribute("posts", postPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", postPage.getTotalPages());
        model.addAttribute("totalItems", postPage.getTotalElements());

        return "user/profile";
    }

    @GetMapping("/user/newpost")
    public String postForm(Model model) {
        model.addAttribute("post", new Post());
        return "user/newpost";
    }

    @PostMapping("/create-post")
    public String savePost(@ModelAttribute Post post, Authentication authentication) {
        // Get the current user
        String username = EmailHelper.getEmailOfLoggedInUser(authentication);

        System.out.println(username);

        Optional<User> user = userService.getUserByEmail(username);
        Post p = new Post();
        // p.setId(id++);
        User id = user.get();

        String name = id.getName();
        if (post.getId() != null) {

            p.setId(post.getId());
        }
        p.setAuthor(user.get());
        p.setAuthorName(name);
        p.setTitle(post.getTitle());
        p.setContent(post.getContent());
        // p.setCreatedAt(LocalDateTime.now());

        // Save the post
        postService.save(p);

        return "redirect:/user/newpost";
    }

    @GetMapping("/user/myposts")
    public String getAllPosts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            Model model, Authentication authentication) {

        String username = EmailHelper.getEmailOfLoggedInUser(authentication);

        System.out.println(username);

        Optional<User> user = userService.getUserByEmail(username);

        // User user = userService.getUserByEmail(username);
        System.out.println(user);
        Page<Post> postPage = postService.getAllPages(PageRequest.of(page, size, Sort.by("createdAt").descending()),
                user);

        model.addAttribute("loggedInUser", user);

        List<Post> posts = postService.getAllPost(user);
        model.addAttribute("posts", posts);

        model.addAttribute("posts", postPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", postPage.getTotalPages());
        model.addAttribute("totalItems", postPage.getTotalElements());

        return "user/userprofile";
    }

    @GetMapping("/user/post/edit/{id}")
    public String editPost(@PathVariable Long id, Model model) {
        Optional<Post> post = postService.getPostById(id);

        model.addAttribute("post", post.get());
        return "user/newpost";
    }

    @PostMapping("/user/post/delete/{id}")
    public String deletePost(@PathVariable Long id, Model model) {
        Optional<Post> post = postService.getPostById(id);
        postService.remove(id);
        return "redirect:/user/profile";
    }

}
