package com.soolfam.yearEndParty;

import com.soolfam.yearEndParty.domain.Guestbook;
import com.soolfam.yearEndParty.repository.GuestbookRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
public class IntegralTest {

    @Autowired GuestbookRepository guestbookRepository;

    @Test
    public void save() {

        Guestbook guestbook = new Guestbook();
        guestbook.setNickname("테스터");
        guestbook.setComment("테스트중입니다.");


        Guestbook guestbook1 = guestbookRepository.save(guestbook);

        Assertions.assertThat(guestbook).isEqualTo(guestbook1);


    }
}
