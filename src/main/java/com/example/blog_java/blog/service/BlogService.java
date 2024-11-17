package com.example.blog_java.blog.service;

import com.example.blog_java.blog.model.Blog;
import com.example.blog_java.blog.repo.BlogReprository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class BlogService {
    @Autowired
    private BlogReprository blogReprository;
    public List<Blog> getAllBlogs(){
        return blogReprository.findAll();
    }
    public Blog getBlogById(Long id){
        return blogReprository.findById(id).orElse(null);
    }
    public Blog saveBlog(Blog blog, MultipartFile imageFile) throws IOException{
        if (imageFile != null && !imageFile.isEmpty()){
            blog.setImage(imageFile.getBytes());
            }
        return blogReprository.save(blog);
    }
    public void deleteBlog(Long id){
        blogReprository.deleteById(id);
    }
}
