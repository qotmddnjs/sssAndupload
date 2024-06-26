package com.koreait.exam.sssandupload.app.member.controller;

import com.koreait.exam.sssandupload.app.member.entity.Member;
import com.koreait.exam.sssandupload.app.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.Principal;

@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {
  private final MemberService memberService;
  private final PasswordEncoder passwordEncoder;

  @GetMapping("/join")
  public String showJoin() {
    return "member/join";
  }

  @PostMapping("/join")
  public String join(HttpServletRequest req, String username, String password, String email, MultipartFile profileImg) {
    Member oldMember = memberService.getMemberByUsername(username);

    String passwordClearText = password;
    password = passwordEncoder.encode(password);

    if (oldMember != null) {
      return "redirect:/?errorMsg=AlreadyExists";
    }

    Member member = memberService.join(username, password, email, profileImg);

    try {
      req.login(username, passwordClearText);
    } catch (ServletException e) {
      throw new RuntimeException(e);
    }

    return "redirect:/member/profile";
  }

  @PreAuthorize("isAuthenticated()")
  @GetMapping("/profile")
  public String showProfile(Principal principal, Model model) {

    Member loginedMember = memberService.getMemberByUsername(principal.getName());

    model.addAttribute("loginedMember", loginedMember);

    return "member/profile";
  }
}