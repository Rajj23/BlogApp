package com.aspen.BlogApp.repo;

import com.aspen.BlogApp.model.Category;
import com.aspen.BlogApp.model.Post;
import com.aspen.BlogApp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepo extends JpaRepository<Post,Integer> {

    public List<Post> findByUser(User user);
    public List<Post> findByCategory(Category category);


}
