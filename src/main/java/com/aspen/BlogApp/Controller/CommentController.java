package com.aspen.BlogApp.Controller;

import com.aspen.BlogApp.dto.CommentDto;
import com.aspen.BlogApp.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("home/api/post")
public class CommentController {

    @Autowired
    private CommentService service;

    @PostMapping("/{id}/comments")
    public ResponseEntity<CommentDto> addComment(@RequestBody CommentDto commentDto, @PathVariable("id") int id){
        CommentDto commentDto1 = this.service.addCommentDto(commentDto,id);
        return new ResponseEntity<>(commentDto1, HttpStatus.CREATED);
    }

    public void deleteComment(@PathVariable("id") int id){
        this.service.deleteComment(id);
    }


}