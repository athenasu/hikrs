package web.member.controller;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.member.entity.Member;

// we have to go to member/login first to set the member id, and then everyone else can get the information they need

@WebServlet("/test")
public class TestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		Member member = (Member) request.getSession().getAttribute("member");
		System.out.println(member.getMemberId());
		System.out.println(member.getMemberUsername());
	}
}
