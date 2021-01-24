package nike.core.repository;

import lombok.RequiredArgsConstructor;
import nike.core.domain.Member;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

    private final EntityManager em;

    // DB에 회원 저장
    public void save(Member member){
        em.persist(member);
    }

    // DB에서 이메일로 회원 검색
    public List<Member> findMemberByEmail(String email){
        return em.createQuery("select m from Member m where m.email = :email", Member.class)
                .setParameter("email", email)
                .getResultList();
    }

    // DB에서 ID로 회원 검색
    public Member findMember(Long id){
        return em.find(Member.class, id);
    }

    // DB에서 모든 회원 검색
    public List<Member> findAllMember(Long id){
        return em.createQuery("select  m from Member m", Member.class)
                .getResultList();
    }


}