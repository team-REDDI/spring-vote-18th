package com.example.springvote18th.controller;

import com.example.springvote18th.common.ApiResponse;
import com.example.springvote18th.dto.member.response.InfoResponseDto;
import com.example.springvote18th.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/member")
@RequiredArgsConstructor
@Slf4j
public class MemberController {
	private final MemberService memberService;


	@GetMapping("/info")
	public ApiResponse<InfoResponseDto> getMemberInfo() {

		// security context를 통해 member 정보를 가져온다.
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		if (authentication != null) {
			InfoResponseDto infoResponseDto = memberService.getInfoByUsername(authentication.getName());
			return ApiResponse.successResponse(infoResponseDto);
		}

		return null;
	}

}
