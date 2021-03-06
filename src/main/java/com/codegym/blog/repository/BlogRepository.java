package com.codegym.blog.repository;

import com.codegym.blog.model.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogRepository extends PagingAndSortingRepository<Blog, Long> {

    Page<Blog> findAllByCategoryId(Long id, Pageable pageable);

    Page<Blog> findAllByTitleContains(String title, Pageable pageable);

}
