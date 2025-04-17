package com.aspen.BlogApp.repo;

import com.aspen.BlogApp.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepo extends JpaRepository<Post,Integer> {
}
