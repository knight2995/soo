package com.soolfam.yearEndParty.controller;

import com.soolfam.yearEndParty.domain.Guestbook;
import com.soolfam.yearEndParty.domain.Member;
import com.soolfam.yearEndParty.service.GuestbookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class GuestbookController {

    private GuestbookService guestbookService;

    @Autowired
    public GuestbookController(GuestbookService guestbookService) {
        this.guestbookService = guestbookService;
    }

    @CrossOrigin()//origins="http://duckbo.site")
    @PostMapping(value = "/guestbooks")
    public ResponseEntity register(@RequestBody Map<String, Object> payload) {

        Guestbook guestbook = new Guestbook();
        guestbook.setNickname((String) payload.get("nickname"));
        guestbook.setComment((String) payload.get("comment"));

        guestbookService.register(guestbook);

        return new ResponseEntity("{'status':'OK'}", HttpStatus.OK);
    }

    @CrossOrigin()//origins="http://duckbo.site")
    @GetMapping("/guestbooks")
    public List<Guestbook> list(Model model) {
        List<Guestbook> guestbooks = guestbookService.findAllGuestbooks();

        return guestbooks;

    }
}
