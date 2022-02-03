package companyname.bulidresult;

import companyname.bulidresult.repository.MemberRepository;
import companyname.bulidresult.repository.MemoryMemberRepository;
import companyname.bulidresult.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository(){
        return new MemoryMemberRepository();
    }
}
