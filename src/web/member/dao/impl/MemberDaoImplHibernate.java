package web.member.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import web.member.dao.MemberDAOHibernate;
import web.member.entity.Member;

@Repository // will tell Spring to make this a bean
public class MemberDaoImplHibernate implements MemberDAOHibernate {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Member selectById(Integer id) {
		Session session = sessionFactory.getCurrentSession();
		return session.get(Member.class, id);
	}

	@Override
	public boolean checkEmail(String email) {
		Session session = sessionFactory.getCurrentSession();
		// :email is like ?, Member.class is the return type
		Query<Member> query = session.createQuery("FROM Member WHERE memberEmail = :email", Member.class)
				.setParameter("email", email);
//		Member member = query.uniqueResult();

		return query.uniqueResult() != null; // if it returns true, this means this account has already been registered
	}

	@Override
	public int register(Member member) {
		Session session = sessionFactory.getCurrentSession(); 
		session.save(member);

		System.out.println("committed");
		// if information has successfully been added to the db, then it will return 1,
		return 1;
	}

	@Override
	public Member update(Member member) {
		Session session = sessionFactory.getCurrentSession();

		// i can get this from the member package that is thrown back i think
		
		Member tempMember = session.get(Member.class, member.getMemberId());
		// do we need to use .trim() here?
		
		if (tempMember.getMemberName() != null && tempMember.getMemberName() != "") {
			tempMember.setMemberName(member.getMemberName());
		}
		
		if (tempMember.getMemberUsername() != null) {
			tempMember.setMemberUsername(member.getMemberUsername());
		}
		
		if (tempMember.getMemberPhoneNum() != null) {
			tempMember.setMemberPhoneNum(member.getMemberPhoneNum());
		}
		
		// really have no idea how to set profile pics here... 
		
		if (tempMember.getMemberIntro() != null) {
			tempMember.setMemberIntro(member.getMemberIntro());
		}
		
		// what about the other updates? like the points (this needs to come from a lot of different places)
		// maybe i need to get these from other people?
		
		return tempMember;
	}

	public Member updateMemberPic(byte[] file, Member member) {
		Session session = sessionFactory.getCurrentSession();
		Member temp = session.get(Member.class, member.getMemberId());
		temp.setMemberProfilePic(file);
		return member;
	}

	public int defaultPic() {
		Session session = sessionFactory.getCurrentSession();
		// need to get picture from local space
		return 0;
	}

}
