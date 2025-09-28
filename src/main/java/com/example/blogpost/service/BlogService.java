package com.example.blogpost.service;

import com.example.blogpost.model.BlogModel;
import com.example.blogpost.repository.BlogRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class BlogService {

    private static final Logger logger = LoggerFactory.getLogger(BlogService.class);

    @Autowired
    private BlogRepository blogRepository;

    public List<BlogModel> getAllBlogs() {
        logger.info("Fetching all blogs");
        return blogRepository.findAll();
    }

    public BlogModel getBlogById(Long id) {
        logger.info("Fetching blog by id: {}", id);
        return blogRepository.findById(id).orElseThrow(() -> {
            logger.warn("Blog not found for id: {}", id);
            return new ResponseStatusException(HttpStatus.NOT_FOUND, "Blog not found");
        });
    }

    public void addBlog(BlogModel blogModel) {
        if (blogModel.getId() == null) {
            logger.error("ID must be provided for new blog");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID required");
        }
        logger.info("Adding blog: {}", blogModel);
        blogRepository.save(blogModel);
    }

    public void updateBlog(Long id, BlogModel updatedBlog) {
        BlogModel blog = getBlogById(id);
        blog.setTitle(updatedBlog.getTitle());
        blog.setContent(updatedBlog.getContent());
        logger.info("Updating blog id: {}", id);
        blogRepository.save(blog);
    }

    public void deleteBlog(Long id) {
        getBlogById(id);
        logger.info("Deleting blog id: {}", id);
        blogRepository.deleteById(id);
    }
}