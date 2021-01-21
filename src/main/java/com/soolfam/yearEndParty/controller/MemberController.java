package com.soolfam.yearEndParty.controller;

import com.soolfam.yearEndParty.domain.Member;
import com.soolfam.yearEndParty.service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@RestController
public class MemberController {

    private MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }


    /*@GetMapping(value="/members/new")
    public String createForm(){
        return "members/createMemberForm";
    }
*/
    static class Response{
        public Response(String status) {
            this.status = status;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        private String status;

    }
    @CrossOrigin()//origins="http://duckbo.site")
    @PostMapping(value="/members")
    public Response register(@RequestBody Map<String, Object> payload){

        Member member = new Member();
        member.setName((String) payload.get("name"));
        member.setEmail((String) payload.get("email"));
        member.setPhone((String) payload.get("phone"));
        member.setComment((String) payload.get("comment"));

        memberService.join(member);

        return new Response("OK");
    }

    @CrossOrigin()//origins="http://duckbo.site")
    @GetMapping("/members/test")
    public ResponseEntity<List<Member>> test(){
        List<Member> members = memberService.findAllMembers();
        return new ResponseEntity<>(members,HttpStatus.OK);

    }

    @CrossOrigin()//origins="http://duckbo.site")
    @GetMapping("/members/emailCheck/{email}")
    public ResponseEntity emailCheck(@PathVariable String email){

        if(memberService.checkDuplicatedEmail(email) == true)
        {
            //return new ResponseEntity(new Response("Duplicate"),HttpStatus.CONFLICT);
            return new ResponseEntity<>("Duplicate",HttpStatus.CONFLICT);
        }
        else
        {
            //return new ResponseEntity(new Response("Not Duplicate"),HttpStatus.OK);
            return new ResponseEntity<>("Not Duplicate",HttpStatus.OK);
        }
    }

    @CrossOrigin()//origins="http://duckbo.site")
    @GetMapping("/members/phoneCheck/{phone}")
    public ResponseEntity phoneCheck(@PathVariable String phone){

        if(memberService.checkDuplicatedPhone(phone) == true)
        {
            return new ResponseEntity(new Response("Duplicate"),HttpStatus.CONFLICT);
        }
        else
        {
            return new ResponseEntity(new Response("Not Duplicate"),HttpStatus.OK);
        }
    }

    @PostMapping("/members/new")
    public String create(MemberForm form){
        Member member = new Member();
        member.setName(form.getName());
        member.setEmail(form.getEmail());
        member.setPhone(form.getPhone());

        memberService.join(member);

        return "redirect:/";
    }

    @CrossOrigin()//origins="http://duckbo.site")
    @GetMapping("/members")
    public List<Member> list(Model model){
        List<Member> members = memberService.findAllMembers();

        for ( int i = 0; i < members.size();i++){
            members.get(i).setEmail("Privacy Information");
            members.get(i).setPhone("Privacy Information");
        }
        model.addAttribute("members",members);

        return members;
    }


}
