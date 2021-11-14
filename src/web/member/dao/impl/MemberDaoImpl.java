package web.member.dao.impl;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Repository;

import web.member.dao.MemberDAO;
import web.member.entity.Member;

@Repository // the same as @Componnet, just telling it what it is
//@ComponentScan("member") // scan for this package name// we already configured this in 
public class MemberDaoImpl implements MemberDAO {

	@Autowired 
	private DataSource dataSource; 

	final String INSERT_MEMBER = "INSERT INTO member "
			+ "(member_name, member_email, member_password, member_username, member_phone_num, member_register_date, member_profile_pic, member_intro, member_status, member_sum_points, member_subscription, member_id)"
			+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

	final String QUERY_MEMBER_BY_ID = "SELECT * FROM member WHERE member_id = ?";
	
	final String UPDATE_MEMBER = "UPDATE member SET `member_username` = 'JiaTing123' WHERE (`member_id` = '1')";

	@Override
	public void insert(Member member) {

		Connection conn = null;
		PreparedStatement ps = null;

		try {
			conn = dataSource.getConnection();
			ps = conn.prepareStatement(INSERT_MEMBER);

//			ps.setString(1, member.getMemberName());
//			ps.setString(2, member.getMemberEmail());
//			ps.setString(3, member.getMemberPassword());
//			ps.setString(4, member.getMemberUsername());
//			ps.setString(5, member.getMemberPhoneNum());
			// setting the date
			java.util.Date du = new java.util.Date();
			Date date = new Date(du.getTime());
			ps.setDate(6, date);

//			// setting the picture--> will need to set the picture to a default picture
//			System.out.println(member.getMemberId()); 
//			InputStream is = getPictureStream("filename"); // Need to fix this to be able to insert this picture
//			ps.setBlob(7, is);
			
//			ps.setString(8, member.getMemberIntro());
			ps.setInt(9, 1);
			ps.setInt(10, 0);
			ps.setInt(11, 0);

//		} catch (IOException io) {
//			System.out.print("In insert, ioexception");
//			io.printStackTrace();
		} catch (SQLException se) {
			System.out.println("In insert, sqlexception");
			se.printStackTrace();
		} finally {
			close(conn, ps, null);
		}

	}

	@Override
	public void update(Member member) {
		// TODO Auto-generated method stub

	}

	@Override
	public Member findByMemberId(Integer memberId) {
		Member member = null;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			conn = dataSource.getConnection();
			ps = conn.prepareStatement(QUERY_MEMBER_BY_ID);

			ps.setInt(1, memberId);
			rs = ps.executeQuery();

			// i forgot if this has to be exactly the same
			while (rs.next()) {
				member = new Member();
				member.setMemberId(memberId);
				member.setMemberName(rs.getString("member_name"));
//				member.setMemberEmail(rs.getString("member_email"));
//				member.setMemberPhoneNum(rs.getString("member_phone_num"));
//				member.setMemberUsername(rs.getString("member_username"));
//				member.setMemberRegisterDate(rs.getDate("member_register_date"));
//				member.setMemberIntro(rs.getString("member_intro"));
////				member.setMemberProfilePic(rs.getBlob("member_profile_pic")); // how do i make this work?
//				member.setMemberStatus(rs.getInt("member_status"));
//				member.setMemberSumPoints(rs.getInt("member_sum_points"));
//				member.setMemberSubscription(rs.getInt("member_subscription"));
			}

		} catch (SQLException se) {
			System.out.println("In findByMemberId: SQLException");
			se.printStackTrace();
			return null;
		} catch (NullPointerException npe) {
			System.out.println("In findByMemberId: NullPointerException");
			npe.getStackTrace();
			return null;
		} finally {
			close(conn, ps, rs);
		}

		return member;
	}

	@Override
	public List<Member> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	public static InputStream getPictureStream(String path) throws IOException {
		FileInputStream fis = new FileInputStream(path);
		return fis;
	}

	private void close(Connection conn, Statement stmt, ResultSet rs) {
		try {
			if (rs != null) {
				rs.close();
			}
			if (stmt != null) {
				stmt.close();
			}
			if (conn != null) {
				// doesn't really close the connection, it just returns the connection back to
				// the connection pool,
				// making it available for the next user to see
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
