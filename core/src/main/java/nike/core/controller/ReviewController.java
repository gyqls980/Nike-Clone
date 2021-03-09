package nike.core.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nike.core.domain.Item;
import nike.core.domain.Member;
import nike.core.domain.Review;
import nike.core.repository.MemberRepository;
import nike.core.service.ItemService;
import nike.core.service.MemberService;
import nike.core.service.ReviewService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/review")
public class ReviewController {

    private final ReviewService reviewService;
    private final ItemService itemService;
    private final MemberRepository memberRepository;

    @PostMapping
    public Long create(@RequestBody ReviewVO reviewVO){
        Member member = memberRepository.findMember(reviewVO.getMemberId());
        Item item = itemService.findOne(reviewVO.getItemId());
        Long reviewId = reviewService.create(member, item, reviewVO.getStar(), reviewVO.getComment());
        return reviewId;
    }

    @GetMapping
    public List<Review> findItems(@RequestParam("memberId") Long memberId){
        return reviewService.findReviews(memberId);
    }

    @GetMapping("/{reviewId}")
    public Review findItem(@PathVariable("reviewId") Long reviewId){
        return reviewService.findOne(reviewId);
    }

    @PutMapping("/{reviewId}")
    public Long update(@PathVariable("reviewId") Long reviewId, @RequestBody ReviewVO reviewVO){
        return reviewService.update(reviewId, reviewVO.getComment(), reviewVO.getStar());
    }

    @DeleteMapping("/{reviewId}")
    public String delete(@PathVariable("reviewId") Long reviewId){
        reviewService.remove(reviewId);
        return "success";
    }
}
