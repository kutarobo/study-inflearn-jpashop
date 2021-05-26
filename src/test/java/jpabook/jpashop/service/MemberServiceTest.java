package jpabook.jpashop.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class MemberServiceTest {

	@Autowired MemberService memberService;
	@Autowired MemberRepository memberRepository;

	@Test
	public void 회원가입() throws Exception {
		// given
		Member member = new Member();
		member.setName("kim");

		// when
		Long saveId = memberService.join(member);

		// then
		Assertions.assertEquals(member, memberRepository.findOne(saveId));
	}

	@Test
	public void 중복_회원_예외() throws Exception {
		// given
		Member member1 = new Member();
		member1.setName("kim");

		Member member2 = new Member();
		member2.setName("kim");
		// when
		memberService.join(member1);
		try {
			memberService.join(member2);
		} catch (IllegalStateException e) {
			return;
		}
		// then
		fail("예외가 발생해야 한다.");
	}

	@Test(expected = IllegalStateException.class)
	public void 중복_회원_예외2() throws Exception {
		// given
		Member member1 = new Member();
		member1.setName("kim");

		Member member2 = new Member();
		member2.setName("kim");
		// when
		memberService.join(member1);
		memberService.join(member2); // expected에 정의 된 exception은 try catch를 해주지않아도된다.

		// then
		fail("예외가 발생해야 한다.");
	}

}
