package com.soolfam.yearEndParty.repository;

import com.soolfam.yearEndParty.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository {

    Member save(Member member);
    List<Member> findAll();

    boolean checkDuplicatedEmail(String email);
    boolean checkDuplicatedPhone(String phone);
}
