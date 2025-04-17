package com.aspen.BlogApp.repo;

import com.aspen.BlogApp.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepo extends JpaRepository<Comment,Integer> {
}
