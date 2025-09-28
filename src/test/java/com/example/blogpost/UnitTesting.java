package com.example.blogpost;

import com.example.blogpost.controller.BlogController;
import com.example.blogpost.controller.LoginController;
import com.example.blogpost.model.BlogModel;
import com.example.blogpost.model.User;
import com.example.blogpost.repository.BlogRepository;
import com.example.blogpost.repository.UserRepository;
import com.example.blogpost.security.CustomUserDetails;
import com.example.blogpost.service.BlogService;
import com.example.blogpost.service.CustomUserDetailsService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class UnitTesting {

    // BlogService
    @Mock
    private BlogRepository blogRepository;

    @InjectMocks
    private BlogService blogService;

    // CustomUserDetailsService
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private CustomUserDetailsService customUserDetailsService;

    // BlogController
    @Mock
    private BlogService blogServiceMockForController;

    @InjectMocks
    private BlogController blogController;

    private MockMvc blogMockMvc;

    // LoginController
    @InjectMocks
    private LoginController loginController;

    private MockMvc loginMockMvc;

    @BeforeEach
    void setUp() {
        blogMockMvc = MockMvcBuilders.standaloneSetup(blogController).build();
        loginMockMvc = MockMvcBuilders.standaloneSetup(loginController).build();
    }

    // ---------------- BlogService Tests ----------------
    @Test
    void getAllBlogs() {
        List<BlogModel> blogs = Arrays.asList(new BlogModel(1L, "Title1", "Content1"));
        when(blogRepository.findAll()).thenReturn(blogs);

        List<BlogModel> result = blogService.getAllBlogs();
        assertEquals(1, result.size());
    }

    @Test
    void getBlogById_found() {
        BlogModel blog = new BlogModel(1L, "Title", "Content");
        when(blogRepository.findById(1L)).thenReturn(Optional.of(blog));

        BlogModel result = blogService.getBlogById(1L);
        assertEquals("Title", result.getTitle());
    }

    @Test
    void getBlogById_notFound() {
        when(blogRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(ResponseStatusException.class, () -> blogService.getBlogById(1L));
    }

    @Test
    void addBlog_success() {
        BlogModel blog = new BlogModel(1L, "Title", "Content");
        when(blogRepository.save(any())).thenReturn(blog);

        blogService.addBlog(blog);
        verify(blogRepository).save(blog);
    }

    @Test
    void addBlog_noId() {
        BlogModel blog = new BlogModel(null, "Title", "Content");
        assertThrows(ResponseStatusException.class, () -> blogService.addBlog(blog));
    }

    @Test
    void updateBlog_success() {
        BlogModel existing = new BlogModel(1L, "Old", "Old");
        BlogModel updated = new BlogModel(1L, "New", "New");
        when(blogRepository.findById(1L)).thenReturn(Optional.of(existing));
        when(blogRepository.save(any())).thenReturn(updated);

        blogService.updateBlog(1L, updated);
        assertEquals("New", existing.getTitle());
    }

    @Test
    void updateBlog_notFound() {
        when(blogRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(ResponseStatusException.class, () -> blogService.updateBlog(1L, new BlogModel()));
    }

    @Test
    void deleteBlog_success() {
        BlogModel blog = new BlogModel(1L, "Title", "Content");
        when(blogRepository.findById(1L)).thenReturn(Optional.of(blog));

        blogService.deleteBlog(1L);
        verify(blogRepository).deleteById(1L);
    }

    @Test
    void deleteBlog_notFound() {
        when(blogRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(ResponseStatusException.class, () -> blogService.deleteBlog(1L));
    }

    

    // ---------------- BlogController Tests ----------------
    @Test
    void getAllBlogs_controller() throws Exception {
        when(blogServiceMockForController.getAllBlogs()).thenReturn(Arrays.asList(new BlogModel(1L, "Title", "Content")));

        blogMockMvc.perform(get("/api/blogs"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1));
    }

    @Test
    void getBlogById_controller() throws Exception {
        when(blogServiceMockForController.getBlogById(1L)).thenReturn(new BlogModel(1L, "Title", "Content"));

        blogMockMvc.perform(get("/api/blogs/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Title"));
    }

    @Test
    void addBlog_controller() throws Exception {
        doNothing().when(blogServiceMockForController).addBlog(any(BlogModel.class));

        blogMockMvc.perform(post("/api/blogs")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":1,\"title\":\"Title\",\"content\":\"Content\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.message").value("Blog added successfully"));
    }

    @Test
    void updateBlog_controller() throws Exception {
        doNothing().when(blogServiceMockForController).updateBlog(eq(1L), any(BlogModel.class));

        blogMockMvc.perform(put("/api/blogs/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\":\"Updated Title\",\"content\":\"Updated Content\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Blog updated successfully"));
    }

    @Test
    void deleteBlog_controller() throws Exception {
        doNothing().when(blogServiceMockForController).deleteBlog(1L);

        blogMockMvc.perform(delete("/api/blogs/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Blog deleted successfully"));
    }


}
