package jpabook.jpashop.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.service.MemberService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MemberController {

	private final MemberService memberService;

	@GetMapping("/members/new")
	public String createForm(Model model) {
		model.addAttribute("memberForm", new MemberForm());
		return "members/createMemberForm";
	}

	@PostMapping("/members/new")
	public String create(@Valid MemberForm form,
		BindingResult result) { // @Vaild 어노테이션이 멤버폼에 있는 vaild를 체크해준다. notempty가 하나걸려있다.

		if (result.hasErrors()) {
			return "members/createMemberForm";
		}

		Address address = new Address(form.getCity(), form.getStreet(), form.getZipcode());

		Member member = new Member();
		member.setName(form.getName());
		member.setAddress(address);

		memberService.join(member);
		return "redirect:/";
	}

	@GetMapping("/members")
	public String list(Model model) {
		// 실무에서는 멤버객체(List<Member>)를 DTO로 변환해서 화면에 필요한 데이터만 출력하는 것을 권장한다.
		// entity는 api에 노출하지마라.
		List<Member> members = memberService.findMembers();
		model.addAttribute("members",members);
		return "members/memberList";
	}

}
