package com.study.boke.dto;

import com.study.boke.model.User;
import lombok.Data;

@Data
public class CommentDTO {
    private Integer id;
    private String context;
    private Integer parentId;
    private Integer type;
    private Integer commentator;
    private Long gmtCreate;
    private Long gmtModified;
    private Long likeCount;
    private User user;
}
