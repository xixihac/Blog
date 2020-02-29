package com.study.boke.mapper;

public interface ReplyMapperExt {

    long countNoReadByReId(Integer id);

    void emptyRead(Integer id );
}