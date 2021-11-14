package web.member.dao;

import java.util.List;

import web.member.entity.Member;

public interface MemberDAO {
	
	public void insert(Member member);
	public void update(Member member);
	public Member findByMemberId(Integer memberId);
	public List<Member> getAll();
	
}
