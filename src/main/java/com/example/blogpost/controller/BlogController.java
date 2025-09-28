package com.example.blogpost.controller;

import com.example.blogpost.model.BlogModel;
import com.example.blogpost.service.BlogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/blogs")
public class BlogController {

    private static final Logger logger = LoggerFactory.getLogger(BlogController.class);

    @Autowired
    private BlogService blogService;

    @GetMapping
    public ResponseEntity<List<BlogModel>> getAllBlogs() {
        logger.info("Request to get all blogs");
        List<BlogModel> blogs = blogService.getAllBlogs();
        logger.info("Retrieved {} blogs", blogs.size());
        return ResponseEntity.ok(blogs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BlogModel> getBlogById(@PathVariable Long id) {
        logger.info("Request to get blog by id: {}", id);
        BlogModel blog = blogService.getBlogById(id);
        logger.info("Retrieved blog: {}", blog);
        return ResponseEntity.ok(blog);
    }

    @PostMapping
    public ResponseEntity<ApiResponse> addBlog(@RequestBody BlogModel blogModel) {
        logger.info("Request to add blog: {}", blogModel);
        blogService.addBlog(blogModel);
        logger.info("Added blog with id: {}", blogModel.getId());
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse("Blog added successfully"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> updateBlog(@PathVariable Long id, @RequestBody BlogModel blogModel) {
        logger.info("Request to update blog id {} with: {}", id, blogModel);
        blogService.updateBlog(id, blogModel);
        logger.info("Updated blog id: {}", id);
        return ResponseEntity.ok(new ApiResponse("Blog updated successfully"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteBlog(@PathVariable Long id) {
        logger.info("Request to delete blog id: {}", id);
        blogService.deleteBlog(id);
        logger.info("Deleted blog id: {}", id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse("Blog deleted successfully"));
    }

    // Inner class for standardized API response
    private static class ApiResponse {
        private String message;

        public ApiResponse(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}