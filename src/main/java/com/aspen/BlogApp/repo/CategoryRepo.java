package com.aspen.BlogApp.repo;

import com.aspen.BlogApp.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category,Integer> {
}
