package nike.core.service;

import nike.core.domain.Member;
import nike.core.repository.MemberRepository;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.Assert.fail;


@RunWith(SpringRunner.class)
@SpringBootTest
public class MemberServiceTest {
    @Autowired EntityManager em;
    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;

    @Test
    public void 회원가입(){ // DB에 둘다 들어감
        Member member1 = new Member();
        member1.setEmail("a@naver.com");
        member1.setPassword("1234");
        member1.setName("효빈");
        member1.setPhone("010-1111-1111");

        Member member2 = new Member();
        member2.setEmail("b@naver.com");
        member2.setPassword("5678");
        member2.setName("지연");
        member2.setPhone("010-2222-2222");

        Long memberId = memberService.join(member1);
        memberService.join(member2);

        Assertions.assertEquals(member1, memberRepository.findMember(memberId));
    }

    @Test(expected = IllegalStateException.class)
    public void 중복_회원_확인() throws Exception{ // DB에 member1만 들어감감
        Member member1 = new Member();
        member1.setEmail("a@naver.com");
        member1.setPassword("1234");
        member1.setName("효빈");
        member1.setPhone("010-1111-1111");

        Member member2 = new Member();
        member2.setEmail("a@naver.com");
        member2.setPassword("5678");
        member2.setName("지연");
        member2.setPhone("010-2222-2222");

        memberService.join(member1);
        memberService.join(member2);

        fail("예외가 발생해야 한다.");
    }

}
