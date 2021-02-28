package nike.core.repository;

import lombok.RequiredArgsConstructor;
import nike.core.domain.Member;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
@Transactional
public class MemberRepository {

    private final EntityManager em;

    // DB에 회원 저장
    public void save(Member member){
        em.persist(member);
    }

    // DB에서 이메일로 회원 검색
    public Optional<Member> findMemberByEmail(String email){
        return em.createQuery("select m from Member m where m.email = :email", Member.class)
                .setParameter("email", email)
                .getResultStream().findAny();
    }

    // DB에서 ID로 회원 검색
    public Member findMember(Long id){
        return em.find(Member.class, id);
    }

    // DB에서 모든 회원 검색
    public List<Member> findAllMember(){
        return em.createQuery("select  m from Member m", Member.class)
                .getResultList();
    }

    // TODO(HYOBIN) : security 활용으로 수정
    // 로그인
    public Optional<Member> isMember(String email, String password){
        return em.createQuery("select m from Member m where m.email = :email and m.password = :password", Member.class)
                .setParameter("email", email)
                .setParameter("password", password)
                .getResultStream().findAny();
    }
}
