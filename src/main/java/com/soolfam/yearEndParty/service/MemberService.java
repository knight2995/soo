package com.soolfam.yearEndParty.service;

import com.soolfam.yearEndParty.domain.Member;
import com.soolfam.yearEndParty.repository.JdbcMemberRepository;
import com.soolfam.yearEndParty.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /*
    *
    * 등록하기
    * */
    public void join(Member member){

        //중복체크 할 필요가 있나 ?

        memberRepository.save(member);

    }

    public boolean checkDuplicatedEmail(String email){

        return memberRepository.checkDuplicatedEmail(email);
    }

    public boolean checkDuplicatedPhone(String phone){

        return memberRepository.checkDuplicatedPhone(phone);
    }



    public List<Member> findAllMembers(){
        return memberRepository.findAll();
    }



}
