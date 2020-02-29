package com.study.boke.dto;

import com.study.boke.model.Question;
import com.study.boke.model.User;
import lombok.Data;

@Data
public class ReplyDTO {
    private Integer id;
    private Integer sender;
    private Integer receiver;
    private Integer type;
    private Long gmtCreate;
    private User senderUser;
    private Question question;
    private Integer isRead;
}
