package web.member.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import web.member.dao.MemberDAOHibernate;
import web.member.entity.Member;
import web.member.service.MemberService;

@Service
@Transactional
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberDAOHibernate memberDAOHibernate;
	
	@Override
	public int registerMember(Member member) { 
		
		// check if email has been registered already, send to the DAO and return an int 
		boolean added = memberDAOHibernate.checkEmail(member.getMemberEmail());
		if (added) {
			System.out.println("This account already exists");
			return 0;
		}
		memberDAOHibernate.defaultPic();
		// if it has not been registered, then return the following
		return memberDAOHibernate.register(member);
	}
	
	@Override
	public Member findById(Integer id) {
		return memberDAOHibernate.selectById(id);
	}

	@Override
	public Member updateMember(Member member) {
		return memberDAOHibernate.update(member);
	}
	
	@Override
	public int insertPic(byte[] file, Member member) {
		memberDAOHibernate.updateMemberPic(file, member);
		return 1;
	}

}
