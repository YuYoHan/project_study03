package com.example.core.member;

import com.example.core.discount.DiscountPolicy;

public class MemberServiceImpl implements MemberService{

    private  final  MemberRepository memberRepository;


    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(long memberId) {
        return memberRepository.findById(memberId);
    }
}
