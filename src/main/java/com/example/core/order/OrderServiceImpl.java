package com.example.core.order;

import com.example.core.discount.DiscountPolicy;
import com.example.core.discount.FixDiscountPolicy;
import com.example.core.member.Member;
import com.example.core.member.MemberRepository;
import com.example.core.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService{

    private final MemberRepository memberRepository;

    // 여기서 문제점은 클라이언트인 OrderServiceImpl이 DiscountPolicy 인터페이스뿐만아니라  FixDiscountPolicy인
    // 구체 클래스도 함께 의존하고 있다. 이것은 DIP 위반이다.


//    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();

    // 그렇다고 FixDiscountPolicy를 RateDiscountPolicy로 변경하는 순간 OrderServiceImpl의 소스 코드도 변경해야한다.
    // 이것은 OCP 위반이다.
//    private final DiscountPolicy discountPolicy = new RateDiscountPolicy();

    private DiscountPolicy discountPolicy;

    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order creatOrder(long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
