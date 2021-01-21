package com.soolfam.yearEndParty.service;

import com.soolfam.yearEndParty.domain.Guestbook;
import com.soolfam.yearEndParty.domain.Member;
import com.soolfam.yearEndParty.repository.GuestbookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GuestbookService {

    private final GuestbookRepository guestbookRepository;

    @Autowired
    public GuestbookService(GuestbookRepository guestbookRepository) {
        this.guestbookRepository = guestbookRepository;
    }

    public void register(Guestbook guestbook){

        //중복체크 할 필요가 있나 ?

        guestbookRepository.save(guestbook);

    }



    public List<Guestbook> findAllGuestbooks(){
        return guestbookRepository.findAll();
    }
}
