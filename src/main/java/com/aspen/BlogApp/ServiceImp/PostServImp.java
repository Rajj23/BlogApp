package com.aspen.BlogApp.ServiceImp;

import com.aspen.BlogApp.dto.PostDto;
import com.aspen.BlogApp.model.Category;
import com.aspen.BlogApp.model.Post;
import com.aspen.BlogApp.model.User;
import com.aspen.BlogApp.repo.CategoryRepo;
import com.aspen.BlogApp.repo.PostRepo;
import com.aspen.BlogApp.repo.UserRepo;
import com.aspen.BlogApp.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServImp implements PostService {

    @Autowired
    private PostRepo postRepo;

    @Autowired
    private ModelMapper model;

    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private UserRepo userRepo;

    @Override
    public PostDto createPost(PostDto postDto,int userId,int categoryId){
        User user = userRepo.findById(userId).orElseThrow(()->new RuntimeException("Can't find User with this id: "+userId));
        Category category = categoryRepo.findById(categoryId).orElseThrow(()->new RuntimeException("Can't find Category with this id: "+categoryId));

        Post post = DtoToPost(postDto);
        post.setImageName("default.png");
        post.setUploadDate(new Date());
        post.setCategory(category);
        post.setUser(user);
        Post savedPost = postRepo.save(post);
        return this.PostToDto(savedPost);
    }

    @Override
    public PostDto updatePost(PostDto postDto,int id){
        Post post = postRepo.findById(id).orElseThrow(()-> new RuntimeException("Can't find Post with this id: "+id));
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        Post post1 = postRepo.save(post);
        return this.PostToDto(post1);
    }

    @Override
    public PostDto getPostById(int id){
        Post post = postRepo.findById(id).orElseThrow(()->new RuntimeException("Can't find Post with this id: "+id));
        return this.PostToDto(post);
    }

    @Override
    public List<PostDto> getAllPost(){
        List<Post> allPost = postRepo.findAll();
        List<PostDto> allDtoPost = allPost.stream().map(post -> (PostToDto(post))).collect(Collectors.toList());
        return allDtoPost;
    }

    @Override
    public void delete(int id){
        Post post = postRepo.findById(id).orElseThrow(()->new RuntimeException("Can't find Post to delete with this id: "+id));
        this.postRepo.delete(post);
    }

    @Override
    public List<PostDto> getPostByCategory(int id){
           Category category = categoryRepo.findById(id).orElseThrow(()->new RuntimeException("Can't find Category with this id: "+id));
           List<Post> posts = postRepo.findByCategory(category);
           List<PostDto>  postDtos = posts.stream().map(post -> (PostToDto(post))).collect(Collectors.toList());
           return postDtos;
    }

    @Override
    public List<PostDto> getPostByUser(int id){
        User user = userRepo.findById(id).orElseThrow(()->new RuntimeException("Can't find User with id: "+id));
        List<Post> posts = postRepo.findByUser(user);
        List<PostDto> userDtos = posts.stream().map(usp->(PostToDto(usp))).collect(Collectors.toList());
        return userDtos;
    }


    public Post DtoToPost(PostDto postDto){
        Post post = this.model.map(postDto,Post.class);
        return post;
    }

    public PostDto PostToDto(Post post){
        PostDto postDto = model.map(post,PostDto.class);
        return postDto;
    }

}
