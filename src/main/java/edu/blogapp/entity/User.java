package edu.blogapp.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
public class User implements UserDetails {
 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private Long id;
 
 @Column(nullable = false, unique = true)
 private String username;
 
 @Column(nullable = false, unique = true)
 private String email;
 
 @Column(nullable = false)
 private String password;
 
 @Column(name = "created_at")
 @CreationTimestamp
 private LocalDateTime createdAt;

 @Column
private String phone;
 
 @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
 @ToString.Exclude
 private List<Post> posts = new ArrayList<>();

 
 @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
 private List<Comment> comments = new ArrayList<>();

 @Override
 public String getUsername(){
    return email;
 }



@Override
public Collection<? extends GrantedAuthority> getAuthorities() {
    // For basic implementation, return a simple user role
    return List.of(new SimpleGrantedAuthority("ROLE_USER"));
}

@Override
public boolean isAccountNonExpired() {
    return true;
}

@Override
public boolean isAccountNonLocked() {
    return true;
}

@Override
public boolean isCredentialsNonExpired() {
    return true;
}

@Override
public boolean isEnabled() {
    return true;
}


public String getName(){
    return username;
}


@Override
public String toString() {
    return "User{" +
            "id=" + id +
            ", username='" + username + '\'' +
            ", posts=" + posts.size() + " posts" +  // Only print the number of posts
            '}';
}
 
 
 
}







