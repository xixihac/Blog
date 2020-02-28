package com.study.boke.dto;

import lombok.Data;

@Data
public class CommentDTO {
    private Long id;
    private String context;
    private Integer parentId;
    private Integer type;
    private Integer commentator;
    private Long gmtCreate;
    private Long gmtModified;
    private Long likeCount;
}
