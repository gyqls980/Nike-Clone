package nike.core.controller;

import nike.core.domain.Member;
import nike.core.repository.MemberRepository;
import nike.core.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class MemberController {

    private final MemberService memberService;
    private final MemberRepository memberRepository;

    @Autowired
    public MemberController(MemberService memberService, MemberRepository memberRepository) {
        this.memberService = memberService;
        this.memberRepository = memberRepository;
    }

    //회원 가입
    @PostMapping("/members/new")
    @ResponseBody
    public Long create(@RequestBody Member form){
        Member member = new Member();
        member.setEmail(form.getEmail());
        member.setPassword(form.getPassword());
        member.setName(form.getName());
        member.setPhone(form.getPhone());

        memberService.join(member);

        return member.getId();
    }

    //로그인
    @PostMapping("/login")
    @ResponseBody
    public Optional<Member> login(@RequestBody Member form){
        Optional<Member> member = memberService.login(form.getEmail(), form.getPassword());
        return member;
    }

    //전체 회원 조회
    @GetMapping("/members")
    @ResponseBody
    public List<Member> list(){
        List<Member> members = memberRepository.findAllMember();
        return members;
    }

    // id로 회원 조회
    @GetMapping("/member")
    @ResponseBody
    public Member findMemberById(@RequestParam("id") Long id){
        Member member = memberRepository.findMember(id);
        return member;
    }

    // email로 회원 조회
    @PostMapping("/member")
    @ResponseBody
    public Optional<Member> findMemberByEmail(@RequestBody String email){
        Optional<Member> member = memberRepository.findMemberByEmail(email);
        return member;
    }
}
