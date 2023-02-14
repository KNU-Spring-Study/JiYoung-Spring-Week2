package com.example.membership.controller;

import com.example.membership.dto.MemberDto;
import com.example.membership.entity.Member;
import com.example.membership.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@Controller
public class MemberController {

    static String userID;

    //스프링 부트가 미리 생성해놓은 객체를 가져다가 자동 연결!
    @Autowired
    private MemberRepository memberRepository;

    @GetMapping("/")
    public String intro(){
        return "intro";//templates/intro.mustache -> 브라우저로 전송
    }


    //로그인 페이지
    @GetMapping("/login")
    public String login(){

        return "login";
    }

    //로그인
    @RequestMapping(value = "/login/run", method = {RequestMethod.POST})
    public String login(MemberDto memberDto, Model model){

        boolean isMember = memberRepository.existsById(memberDto.getUserID());

        if(isMember){
            Optional<Member> user = memberRepository.findById(memberDto.getUserID());
            if(user.get().getUserPW().equals(memberDto.getUserPW())){
                model.addAttribute("userID",memberDto.getUserID());

                userID = memberDto.getUserID();

                return "home";
            }
            else{
                model.addAttribute("errorMsg","비밀번호 틀림 ");
                return "error";
            }
        }

        else{
            model.addAttribute("errorMsg","회원정보 없음");
            return "error";
        }
    }

    //회원가입
    @GetMapping("/join")
    public String join(){
        return "join";
    }

    //회원가입
    @RequestMapping(value = "/join/run", method = {RequestMethod.POST})
    public String join(MemberDto memberDto, Model model){

//        System.out.println(memberDto.toString());

        boolean isMember = memberRepository.existsById(memberDto.getUserID());

        if(!isMember){ //입력한 아이디가 기존에 존재하지 않는 아이디이다.
            Member member = memberDto.toEntity(); //dto -> entity
//        System.out.println(member.toString());

            Member saved = memberRepository.save(member); //repository에게 entity를 db에 저장하도록 함
//        System.out.println(saved.toString());

            return "intro";
        }

        else{
            model.addAttribute("errorMsg","이미 존재하는 아이디입니다.");
            return "error";
        }


    }

    //로그아웃
    @GetMapping("/logout")
    public String logout(){

        return "intro";
    }

    //회원정보 수정

    @GetMapping("/edit")
    public String edit(){

        return "edit";
    }

    @RequestMapping(value = "/edit/run", method = {RequestMethod.POST})
    public String edit(MemberDto memberDto){

        if(memberRepository.existsById(memberDto.getUserID())){
            memberRepository.save(memberDto.toEntity());
            return "home";
        }
        else{
            return "error2";
        }

    }


    //회원 탈퇴
    @DeleteMapping("delete")
    public String delete(MemberDto memberDto){

        memberRepository.deleteById(userID);

        return "intro";
    }



    //모든 회원 검색
    @GetMapping("/allMember")
    public String findAllMember(){
        System.out.println(memberRepository.findAll());
        return "";
    }

}
