package com.hlk.practice.controller;

import com.hlk.practice.mapper.PeopleMapper;
import com.hlk.practice.pojo.People;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class PeopleController {
    @Autowired
    PeopleMapper peopleMapper;

    @RequestMapping({"/login.html","/","/Login.html"})
    public String login(Model model){
        model.addAttribute("msg","hello");
        return "/login";
    }
    @RequestMapping("/main.html")
    public String main(HttpSession session,String name, Model model, String pwd){
        Subject subject= SecurityUtils.getSubject();
        UsernamePasswordToken token=new UsernamePasswordToken(name,pwd);
        try {
            subject.login(token);
            return "main";
        } catch (UnknownAccountException e) {
            e.printStackTrace();
            model.addAttribute("msg","用户名不存在");
            return "login";
        }catch (IncorrectCredentialsException e) {
            e.printStackTrace();
            model.addAttribute("msg","密码错误");
            return "login";
        }
    }

    @RequestMapping("/toregist.html")
    public String toregist(){
        return "regist";
    }

    @RequestMapping("/regist.html")
    public String regist(Model model,String role,String name,String pwd){
        model.addAttribute("user",name+"hello!");
        People people = new People();
        people.setRole(role);
        people.setName(name);
        people.setPwd(pwd);
        peopleMapper.addPeople(people);
        return "main";
    }
    @RequestMapping("/logout.html")
    public String logout(HttpSession session){
        Subject subject= SecurityUtils.getSubject();
        subject.logout();
        return "login";
    }
    @RequestMapping("/allList.html")
    public String allList(Model model){
        List<People> allPeople = peopleMapper.getAllPeople();
        model.addAttribute("list",allPeople);
        return "allList";
    }
    @RequestMapping("/allPeople.html")
    public String allPeople(){
        return "allPeople";
    }
    @RequestMapping("/modify.html")
    public String modify(){
        return "modify";
    }
    @RequestMapping("/unAuth.html")
    public String unAuth() {
        return "unAuth";
    }
}
