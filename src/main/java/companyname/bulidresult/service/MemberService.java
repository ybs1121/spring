package companyname.bulidresult.service;


import companyname.bulidresult.domain.Member;
import companyname.bulidresult.repository.MemoryMemberRepository;
import companyname.bulidresult.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public class MemberService {



    private final MemberRepository memberRespository;


    public MemberService(MemberRepository memberRespository) {
        this.memberRespository = memberRespository;
    }

    /** 회원가입

     */

    public long join(Member member){
        //같은 이름이 있는 중복 회원 X
        validateDuplicateMember(member);

        memberRespository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRespository.findByName(member.getName())
        .ifPresent(m->{
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        });
    }

    /**
     *
     * 전체 회원 조회
     */
    public List<Member> findMembers(){
        return memberRespository.findAll();
    }

    public Optional<Member> findOne(Long memberId){
        return memberRespository.findById(memberId);
    }
}
