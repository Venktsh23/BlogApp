package edu.blogapp.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "posts")
@Data
@NoArgsConstructor
public class Post {
 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private Long id;
 
 @Column(nullable = false)
 private String title;
 
 @Column(nullable = false, columnDefinition = "TEXT")
 private String content;
 
 @Column(name = "created_at")
 @CreationTimestamp
 private LocalDateTime createdAt;
 
 @ManyToOne(fetch = FetchType.LAZY)
 @JoinColumn(name = "user_id")
 private User author;

 @Column(columnDefinition = "TEXT")
 private String authorName;


 
 @OneToMany(mappedBy = "post", cascade = CascadeType.ALL,  orphanRemoval = true)
 private List<Comment> comments = new ArrayList<>();
 
//  @ManyToMany
//  @JoinTable(
//      name = "post_tags",
//      joinColumns = @JoinColumn(name = "post_id"),
//      inverseJoinColumns = @JoinColumn(name = "tag_id")
//  )
//  private Set<Tag> tags = new HashSet<>();

@Override
public String toString() {
    return "Post{" +
            "id=" + id +
            ", title='" + title + '\'' +
            ", createdAt=" + createdAt +
            ", userId=" + (author != null ? author.getId() : null) +  // Only print the user ID
            '}';
}
 
 
 
}