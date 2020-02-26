package com.study.boke.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
@Data
public class PaginationDTO {
    private List<QuestionDTO> questionDTOS;
    private boolean showPrevious;
    private boolean showFirstPage;
    private boolean showEndPage;
    private boolean showNext;
    private Integer page;
    private List<Integer> pages = new ArrayList<>();
    private Integer totalPage;

    public void setPagnation(Integer totalCount, Integer page, Integer size) {

        Integer totalPage;
        if(totalCount % size==0 ){
            totalPage = totalCount / size;
        }else{
            totalPage = totalCount / size + 1;
        }
        if(totalPage==0){
            totalPage=1;
        }
        this.totalPage=totalPage;


        pages.add(page);
        for (int i=1 ; i<=3 ; i++){
            if(page-i>0){
                pages.add(0,page-i);
            }
            if (page + i <= totalPage){
                pages.add(page + i);
            }
        }


        //是否展示上一页
        if(page==1){
            showPrevious=false;
        }else {
            showPrevious=true;
        }

        //是否展示下一页
        if(page == totalPage){
            showNext=false;
        }else{
            showNext=true;
        }

        if(pages.contains(1)){
            showFirstPage=false;
        }else {
            showFirstPage=true;
        }
        if(pages.contains(totalPage)){
            showEndPage=false;
        }else {
            showEndPage=true;
        }


    }
}
