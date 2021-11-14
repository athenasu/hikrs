package web.member.entity;

import java.io.Serializable;
import java.sql.Clob;
import java.sql.Date;

//import java.sql.Blob;
//import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table; // is it this one?
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table(name = "member")
public class Member implements Serializable {

	private static final long serialVersionUID = 1L;

	// don't use BLOB, need to read it as a byte[]

	private Integer memberId;
	private String memberName;
	private String memberEmail;
	private String memberPassword;
	private String memberUsername;
	private String memberPhoneNum;
	private Date memberRegisterDate;
	private byte[] memberProfilePic;
	private String memberIntro;
	private Integer memberStatus;
	private Integer memberSumPoints;
	private Integer memberSubscription;
	private String picStr;
	
	
	@Transient
	public String getPicStr() {
		return picStr;
	}

	public void setPicStr(String picStr) {
		this.picStr = picStr;
	}

	@Id // telling hibernate which one is the primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY) // telling Hibernate that this PK is auto incremented
	@Column(name = "member_id") // the db column name
	public Integer getMemberId() {
		return memberId;
	}

	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}

	@Column(name = "member_name")
	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	@Column(name = "member_email", updatable = false)
	public String getMemberEmail() {
		return memberEmail;
	}

	public void setMemberEmail(String memberEmail) {
		this.memberEmail = memberEmail;
	}

	@Column(name = "member_password")
	public String getMemberPassword() {
		return memberPassword;
	}

	public void setMemberPassword(String memberPassword) {
		this.memberPassword = memberPassword;
	}

	@Column(name = "member_username")
	public String getMemberUsername() {
		return memberUsername;
	}

	public void setMemberUsername(String memberUsername) {
		this.memberUsername = memberUsername;
	}

	@Column(name = "member_phone_num")
	public String getMemberPhoneNum() {
		return memberPhoneNum;
	}

	public void setMemberPhoneNum(String memberPhoneNum) {
		this.memberPhoneNum = memberPhoneNum;
	}

	@Column(name = "member_register_date")
	public Date getMemberRegisterDate() {
		return memberRegisterDate;
	}

	public void setMemberRegisterDate(Date memberRegisterDate) {
		this.memberRegisterDate = memberRegisterDate;
	}

	@Column(name = "member_profile_pic", columnDefinition = "LONGBLOB")
	public byte[] getMemberProfilePic() {
		return memberProfilePic;
	}

	public void setMemberProfilePic(byte[] memberProfilePic) {
		this.memberProfilePic = memberProfilePic;
	}

	@Column(name = "member_intro", columnDefinition = "TEXT")
	public String getMemberIntro() {
		return memberIntro;
	}

	public void setMemberIntro(String memberIntro) {
		this.memberIntro = memberIntro;
	}

	@Column(name = "member_status", insertable = false)
	public Integer getMemberStatus() {
		return memberStatus;
	}

	public void setMemberStatus(Integer memberStatus) {
		this.memberStatus = memberStatus;
	}

	@Column(name = "member_sum_points")
	public Integer getMemberSumPoints() {
		return memberSumPoints;
	}

	public void setMemberSumPoints(Integer memberSumPoints) {
		this.memberSumPoints = memberSumPoints;
	}

	@Column(name = "member_subscription", columnDefinition = "int default 0")
	public Integer getMemberSubscription() {
		return memberSubscription;
	}

	public void setMemberSubscription(Integer memberSubscription) {
		this.memberSubscription = memberSubscription;
	}

}
