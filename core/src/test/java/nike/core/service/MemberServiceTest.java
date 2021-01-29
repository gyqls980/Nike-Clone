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
import javax.swing.text.html.Option;

import java.util.Optional;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;


@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
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

    @Test
    public void 로그인_성공(){
        Member member1 = new Member();
        member1.setEmail("c@naver.com");
        member1.setPassword("5555");
        member1.setName("민지");
        member1.setPhone("010-4434-6331");
        memberService.join(member1);

        Optional<Member> is_member = memberService.login("c@naver.com","5555");
        Assertions.assertEquals(member1, is_member.get());
    }

    @Test
    public void 로그인_실패(){
        Member member1 = new Member();
        member1.setEmail("a@naver.com");
        member1.setPassword("1234");
        member1.setName("효빈");
        member1.setPhone("010-1111-1111");
        memberService.join(member1);

        Optional<Member> is_member = memberService.login("a@naver.com","12");
        Assertions.assertEquals(Optional.empty(), is_member);
    }
}
