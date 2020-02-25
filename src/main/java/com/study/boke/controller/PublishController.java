package com.study.boke.controller;

import com.study.boke.mapper.QuestionMapper;
import com.study.boke.mapper.UserMapper;
import com.study.boke.model.Question;
import com.study.boke.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class PublishController {
    @Autowired
    UserMapper userMapper;

    @Autowired
    QuestionMapper questionMapper;



    @GetMapping("/publish")
    public String publish(){
        return "publish";
    }

    @PostMapping("/publish")
    public String doPublish(
            @RequestParam String title,
            @RequestParam String description,
            @RequestParam String tag,
            HttpServletRequest request,
            Model model
    ){
        model.addAttribute("title",title);
        model.addAttribute("description",description);
        model.addAttribute("tag",tag);
        System.out.println("model+"+title+":"+description+":"+tag);
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
        Cookie[] cookies = request.getCookies();
        User user =null;
        if (cookies!=null){
            for(Cookie token:cookies){
                if ("token".equals(token.getName())){
                    user = userMapper.findByCookie(token.getValue());
                    request.getSession().setAttribute("githubUser",user);
                }
            }
        }

        if (user == null){
            model.addAttribute("error","用户未登录");
            System.out.println("user为空");
            return "publish";
        }
        Question question = new Question();
        question.setTitle(title);
        question.setDescription(description);
        question.setTag(tag);
        question.setCreator(user.getId());
        question.setGmtCreate(System.currentTimeMillis());
        question.setGmtModified(question.getGmtCreate());



        questionMapper.create(question);

        return "redirect:/";
    }
}
