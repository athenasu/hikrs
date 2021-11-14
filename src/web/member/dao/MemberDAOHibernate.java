package web.member.dao;

import web.member.entity.Member;

public interface MemberDAOHibernate {

	public int register(Member member); // create
	public Member selectById(Integer id); // read
	public Member update(Member member); // update
	public boolean checkEmail(String email);
	public Member updateMemberPic(byte[] file, Member member);
	public int defaultPic();

}