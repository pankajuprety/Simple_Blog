package com.example.blog_java.blog.controller;

import com.example.blog_java.blog.model.Blog;
import com.example.blog_java.blog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/api/blogs")
public class BlogController {
    @Autowired
    private BlogService blogService;

    @GetMapping("/new")
    public String newBlogForm(Model model) {
        model.addAttribute("blog", new Blog());
        return "new_blog"; // Renders new_blog.html
    }

    @GetMapping
    public String listAllBlogs(Model model) {
        model.addAttribute("blogs", blogService.getAllBlogs());
        return "blog_list"; // Renders blog_list.html
    }

    @GetMapping("/{id}")
    public String viewBlogDetails(@PathVariable Long id, Model model) {
        Blog blog = blogService.getBlogById(id);
        model.addAttribute("blog", blog);
        return "blog_details"; // Renders blog_details.html
    }



    @PostMapping
    public String createBlog(
            @RequestParam("title") String title,
            @RequestParam("content") String content,
            @RequestParam(value ="image", required = false) MultipartFile imageFile
            ) throws IOException{
        Blog blog = new Blog();
        blog.setTitle(title);
        blog.setContent(content);
        blogService.saveBlog(blog, imageFile);
        return "redirect:/api/blogs";
    }

    @GetMapping("/{id}/image")
    public ResponseEntity<byte[]> getBlogImage(@PathVariable Long id){
        Blog blog= blogService.getBlogById(id);
        if(blog !=null && blog.getImage()!=null){
            return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, "image/jpeg")
                    .body(blog.getImage());
        }
        return ResponseEntity.notFound().build();
    }
    @DeleteMapping("/{id}")
    public void deleteBlog(@PathVariable Long id){
        blogService.getBlogById(id);
    }
}
