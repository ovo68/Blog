package com.zy.service;

import com.zy.entity.Blog;
import com.zy.entity.Tag;
import com.zy.vo.BlogQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface BlogService {

    Blog getBlog(Long id);

    Page<Blog> listBlog(Pageable pageable, BlogQuery blog);

    Blog getAndConvert(Long id);

    Page<Blog> listBlog(Pageable pageable);

    Page<Blog> listBlog(String query,Pageable pageable);
    Page<Blog> listBlog(Long tagId, Pageable pageable);

    Map<String,List<Blog>> archiveBlog();

    Blog saveBlog(Blog blog);

    Long countBlog();

    Blog updateBlog(Long id,Blog blog);

    List<Blog> listRecommendBlogTop(Integer size);

    void deleteBlog(Long id);
}
