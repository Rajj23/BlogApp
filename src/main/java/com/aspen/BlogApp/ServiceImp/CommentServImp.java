package com.aspen.BlogApp.ServiceImp;


import com.aspen.BlogApp.dto.CommentDto;
import com.aspen.BlogApp.model.Comment;
import com.aspen.BlogApp.model.Post;
import com.aspen.BlogApp.repo.CommentRepo;
import com.aspen.BlogApp.repo.PostRepo;
import com.aspen.BlogApp.service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServImp implements CommentService {

    @Autowired
    private CommentRepo commentRepo;

    @Autowired
    private PostRepo postRepo;

    @Autowired
    private ModelMapper model;

    @Override
    public CommentDto addCommentDto(CommentDto commentDto,int postId){
        Post post = this.postRepo.findById(postId).orElseThrow(()-> new RuntimeException("Post not found by id: "+postId));
        Comment comment = model.map(commentDto,Comment.class);
        comment.setPost(post);
        Comment saveComment = commentRepo.save(comment);
        return model.map(saveComment,CommentDto.class);
    }

    @Override
    public void deleteComment(int id){
        Comment comment = this.commentRepo.findById(id).orElseThrow(()-> new RuntimeException("Post not found to delete for id: "+ id));
        this.commentRepo.delete(comment);
    }

}
