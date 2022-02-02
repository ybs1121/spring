package companyname.bulidresult.service;


import companyname.bulidresult.domain.Member;
import companyname.bulidresult.repository.MemoryMemberRepository;
import companyname.bulidresult.repository.MemberRepository;

import java.util.List;
import java.util.Optional;

public class MemberService {

    private final MemberRepository MemberRespository = new MemoryMemberRepository();
    /** 회원가입

     */

    public long join(Member member){
        //같은 이름이 있는 중복 회원 X
        validateDuplicateMember(member);

        MemberRespository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        MemberRespository.findByName(member.getName())
        .ifPresent(m->{
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        });
    }

    /**
     *
     * 전체 회원 조회
     */
    public List<Member> findMembers(){
        return MemberRespository.findAll();
    }

    public Optional<Member> findOne(Long memberId){
        return MemberRespository.findById(memberId);
    }
}
