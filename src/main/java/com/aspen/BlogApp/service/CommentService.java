package com.aspen.BlogApp.service;

import com.aspen.BlogApp.dto.CommentDto;

public interface CommentService {

    public CommentDto addCommentDto(CommentDto commentDto,int postId);
    public void deleteComment(int id);

}
