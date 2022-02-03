package companyname.bulidresult.service;

import companyname.bulidresult.domain.Member;
import companyname.bulidresult.repository.MemberRepository;
import companyname.bulidresult.repository.MemoryMemberRepository;
import org.apache.juli.logging.Log;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {
    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach
    public void beforeEach(){
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);


    }

    @AfterEach
    public void afterEach(){
        memberRepository.clearStore();
    }

    @Test
    void join() {
        //given
        Member member = new Member();
        member.setName("Hello");

        //when
        Long saveId =  memberService.join(member);
        //thenR
        Member one = memberService.findOne(saveId).get();
        Assertions.assertThat(member.getName()).isEqualTo(one.getName());

    }
    @Test
    public void 중복_회원_예외(){
        //given
        Member member = new Member();
        member.setName("Hello");

        Member member2 = new Member();
        member2.setName("Hello");

        //when
        memberService.join(member);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));

        Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");




//
//        try {
//            memberService.join(member2);
//            fail();
//
//        }catch (IllegalStateException e){
//            Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
//
//        }

        //then

    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}