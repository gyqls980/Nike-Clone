package nike.core.service;

import lombok.RequiredArgsConstructor;
import nike.core.domain.Member;
import nike.core.repository.MemberRepository;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    // 회원등록(회원가입)
    @Transactional
    public Long join(Member member){
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    // 중복 확인
    private void validateDuplicateMember(Member member) {
        List<Member> findMembers = memberRepository.findMemberByEmail(member.getEmail());
        if (!findMembers.isEmpty()){
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    // TODO : security 활용으로 수정
    // 로그인
    public Member login(String email, String password){
        try{
            Member member = memberRepository.isMember(email, password);
            return member;
        }
        catch(Exception e) {
            throw new IllegalStateException("존재하지 않는 회원이거나 비밀번호가 틀렸습니다.");
        }
    }
}
