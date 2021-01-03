package com.codegym.demojwt.service;

import com.codegym.demojwt.model.Category;

public interface CategoryService {
    Iterable<Category> findAll();
}
