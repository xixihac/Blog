package com.study.boke.controller;

import com.study.boke.Exception.AllException;
import com.study.boke.Exception.BokeCustomerException;
import com.study.boke.mapper.QuestionMapper;
import com.study.boke.model.Question;
import com.study.boke.model.QuestionExample;
import com.study.boke.model.User;
import com.study.boke.service.QuestionService;
import com.study.boke.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class PublishController {

    @Autowired
    QuestionMapper questionMapper;

    @Autowired
    QuestionService questionService;

    @Autowired
    UserService userService;

    @GetMapping("/publish")
    public String publish(HttpServletRequest request){
        User githubUser = (User) request.getSession().getAttribute("githubUser");

        if(githubUser == null){
            throw new BokeCustomerException(AllException.HAVE_NO_POWER);
        }
        return "publish";
    }

    @PostMapping("/publish")
    public String doPublish(
            @RequestParam String title,
            @RequestParam String description,
            @RequestParam String tag,
            @RequestParam Integer id,
            HttpServletRequest request,
            Model model){

//        User githubUser = (User) request.getSession().getAttribute("githubUser");
//        if( githubUser != null && !id.equals(githubUser.getId())){
//            throw new BokeCustomerException(AllException.HAVE_NO_POWER);
//        }

        model.addAttribute("id",id);
        model.addAttribute("title",title);
        model.addAttribute("description",description);
        model.addAttribute("tag",tag);

        if(title==null || "".equals(title)){
            model.addAttribute("error","标题不能为空");
            return "publish";
        }
        if(description==null || "".equals(description)){
            model.addAttribute("error","描述不能为空");
            return "publish";
        }
        if(tag==null || "".equals(tag)){
            model.addAttribute("error","标签不能为空");
            return "publish";
        }

        User user = (User) request.getSession().getAttribute("githubUser");

        if (user == null){
            model.addAttribute("error","用户未登录");
            return "publish";
        }

        if ( id!=null ){
            Integer creator = questionMapper.selectByPrimaryKey(id).getCreator();
            if (!creator.equals(user.getId())){
                throw new BokeCustomerException(AllException.HAVE_NO_POWER);
            }

        }

        Question question = new Question();
        question.setId(id);
        question.setTitle(title);
        question.setDescription(description);
        question.setTag(tag);
        question.setCreator(user.getId());
        question.setGmtCreate(System.currentTimeMillis());
        question.setGmtModified(question.getGmtCreate());
        questionService.createOrUpdate(question);

        return "redirect:/";
    }

    @GetMapping("/publish/{questionId}")
    public String edit(@PathVariable("questionId") Integer questionId,
                       Model model){


        Question question = questionMapper.selectByPrimaryKey(questionId);

        model.addAttribute("id",questionId);
        model.addAttribute("title",question.getTitle());
        model.addAttribute("description",question.getDescription());
        model.addAttribute("tag",question.getTag());

        return "publish";
    }
}
