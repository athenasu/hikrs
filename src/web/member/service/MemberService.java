package web.member.service;

import web.member.entity.Member;

public interface MemberService {
	
	public Member findById(Integer id); // read
	public int registerMember(Member member); // create
	public Member updateMember(Member member); // update
	public int insertPic(byte[] file,  Member member);
	
}
