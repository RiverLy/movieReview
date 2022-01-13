package com.river.movieReview.repository;

import com.river.movieReview.entity.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

import java.util.stream.IntStream;

@SpringBootTest
public class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @Test
    public void insertMember(){

        IntStream.rangeClosed(1,100).forEach(i -> {

            Member member = Member.builder()
                    .email("r" + i + "gram.org")
                    .pw("password**")
                    .nickname("reviewer..." + i)
                    .build();

            memberRepository.save(member);

        });

        Assertions.assertThat(memberRepository.findById(100L).isPresent());
        System.out.println(memberRepository.findById(2L));

    }

}
