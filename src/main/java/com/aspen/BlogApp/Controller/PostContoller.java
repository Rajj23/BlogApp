package com.aspen.BlogApp.Controller;

import com.aspen.BlogApp.dto.PostDto;
import com.aspen.BlogApp.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping("/home/api")
public class PostContoller {

    @Autowired
    private PostService postService;

    @PostMapping("user/{userId}/category/{categoryId}/post")
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto,
                                              @PathVariable int userId,
                                              @PathVariable int categoryId){
        PostDto postDto1 = this.postService.createPost(postDto,userId,categoryId);
        return new ResponseEntity<PostDto>(postDto1, HttpStatus.CREATED);
    }

    @PutMapping("post/{postId}")
    public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto,@PathVariable("postId") int id){
        PostDto postDto1 = this.postService.updatePost(postDto,id);
        return new ResponseEntity<PostDto>(postDto1,HttpStatus.OK);
    }

    @GetMapping("post/{postId}")
    public ResponseEntity<PostDto> getPostById(@PathVariable("postId") int id){
        PostDto postDto = this.postService.getPostById(id);
        return new ResponseEntity<PostDto>(postDto,HttpStatus.OK);
    }

    @GetMapping("post")
    public ResponseEntity<List<PostDto>> getAllPost(){
        List<PostDto> postDtos = postService.getAllPost();
        return new ResponseEntity<>(postDtos,HttpStatus.OK);
    }

    @DeleteMapping("post/{postId}")
    public ResponseEntity<String> deletePost(@PathVariable("postId") int id) {
        this.postService.delete(id);
        return new ResponseEntity<>("Post deleted successfully", HttpStatus.OK);
    }

    @GetMapping("category/{categoryId}/post")
    public ResponseEntity<List<PostDto>> getPostByCategeory(@PathVariable("categoryId") int id){
        List<PostDto>  postDtoList = this.postService.getPostByCategory(id);
        return new ResponseEntity<List<PostDto>>(postDtoList,HttpStatus.OK);
    }

    @GetMapping("user/{userId}/post")
    public ResponseEntity<List<PostDto>> getPostByUser(@PathVariable("userId") int id){
        List<PostDto> postDtoList = this.postService.getPostByUser(id);
        return new ResponseEntity<List<PostDto>>(postDtoList,HttpStatus.OK);
    }

}
