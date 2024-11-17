package com.example.blog_java.blog.repo;

import com.example.blog_java.blog.model.Blog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogReprository extends JpaRepository<Blog, Long> {
}
