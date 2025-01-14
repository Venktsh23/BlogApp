package edu.blogapp.entity;


import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;



@Entity
@Table(name = "comments")
@Data
@NoArgsConstructor
public class Comment {
 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private Long id;
 
 @Column(nullable = false, columnDefinition = "TEXT")
 private String content;
 
 @Column(name = "created_at")
 @CreationTimestamp
 private LocalDateTime createdAt;
 
 @ManyToOne(fetch = FetchType.LAZY)
 @JoinColumn(name = "user_id", nullable = false)
 private User user;
 
 @ManyToOne(fetch = FetchType.LAZY)
 @JoinColumn(name = "post_id", nullable = false)
 private Post post;
 
 
}
