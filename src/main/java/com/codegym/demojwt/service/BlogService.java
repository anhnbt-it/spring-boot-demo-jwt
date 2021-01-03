package com.codegym.demojwt.service;

import com.codegym.demojwt.model.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface BlogService {
    Page<Blog> findAll(Pageable pageable);

    Page<Blog> findAllByTitleContains(String title, Pageable pageable);

    Page<Blog> findAllByCategoryId(Long id, Pageable pageable);

    Optional<Blog> findById(Long id);
}