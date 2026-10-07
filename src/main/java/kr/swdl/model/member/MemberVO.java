package kr.swdl.model.member;

import java.sql.Date;

public class MemberVO {
	private String memberId;
	private String pw;
	private String name;
	private Date birth;
	private String phone;
	private String email;
	private String memberFile;
	private String companyFile;
	private String isCompany;
	private String memberName;
	private String zipCode;
	private String address;
	private String detailAddress;
	private String status;
	private Date requestDate;
	private Date memberDate;
	
	// 기본 생성자
	public MemberVO() {}

	
	
	public MemberVO(String memberId, String name, String memberName, String status, Date requestDate, String isCompany) {
		setMemberId(memberId);
		setName(name);
		setMemberName(memberName);
		setStatus(status);
		setRequestDate(requestDate);
		setIsCompany(isCompany);
	}

	

	public MemberVO(String name, Date birth, String phone, String email, String memberFile, String address,
			String detailAddress, Date requestDate) {
		setName(name);
		setBirth(birth);
		setPhone(phone);
		setEmail(email);
		setMemberFile(memberFile);
		setAddress(detailAddress);
		setRequestDate(requestDate);
		setEmail(email);
		setMemberFile(memberFile);
		setAddress(detailAddress);
		setDetailAddress(detailAddress);
		setRequestDate(requestDate);
	}

	

	public MemberVO(String name, Date birth, String phone, String email, String memberFile, String companyFile,
			String memberName, String address, String detailAddress, Date requestDate) {
		setName(name);
		setBirth(birth);
		setPhone(phone);
		setEmail(email);
		setMemberFile(memberFile);
		setCompanyFile(companyFile);
		setMemberName(memberName);
		setAddress(address);
		setDetailAddress(detailAddress);
		setRequestDate(requestDate);
	}



	// getter and setter
	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getBirth() {
		return birth;
	}

	public void setBirth(Date birth) {
		this.birth = birth;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMemberFile() {
		return memberFile;
	}

	public void setMemberFile(String memberFile) {
		this.memberFile = memberFile;
	}

	public String getCompanyFile() {
		return companyFile;
	}

	public void setCompanyFile(String companyFile) {
		this.companyFile = companyFile;
	}

	public String getIsCompany() {
		return isCompany;
	}

	public void setIsCompany(String IsCompany) {
		this.isCompany = IsCompany;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDetailAddress() {
		return detailAddress;
	}

	public void setDetailAddress(String detailAddress) {
		this.detailAddress = detailAddress;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getRequestDate() {
		return requestDate;
	}

	public void setRequestDate(Date requestDate) {
		this.requestDate = requestDate;
	}

	public Date getMemberDate() {
		return memberDate;
	}

	public void setMemberDate(Date memberDate) {
		this.memberDate = memberDate;
	}
	
	
}
