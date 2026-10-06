package kr.swdl.model;

public class MemberVO {
	private String memberId;
	private String pw;
	private String name;
	private String birth;
	private String phone;
	private String email;
	private String memeberFile;
	private String companyFile;
	private String is_Company;
	private String memberName;
	private String zipCode;
	private String address;
	private String detailAddress;
	private String status;
	private String requestDate;
	private String memberDate;

	public MemberVO() {
	}

	public MemberVO(String memberId) {
		setMemberId(memberId);
	}

	public MemberVO(String name, String memberName, String status, String requestDate) {
		setName(name);
		setMemberName(memberName);
		setStatus(status);
		setRequestDate(requestDate);
	}

	public MemberVO(String name, String birth, String phone, String email, String memeberFile, String address,
			String detailAddress, String requestDate) {
		setName(name);
		setBirth(birth);
		setPhone(phone);
		setEmail(email);
		setMemeberFile(memeberFile);
		setAddress(detailAddress);
		setDetailAddress(detailAddress);
		setRequestDate(requestDate);
	}

	public MemberVO(String name, String birth, String phone, String email, String memeberFile, String companyFile,
			String memberName, String address, String detailAddress, String requestDate) {
		setName(name);
		setBirth(birth);
		setPhone(phone);
		setEmail(email);
		setMemeberFile(memeberFile);
		setCompanyFile(companyFile);
		setMemberName(memberName);
		setAddress(address);
		setDetailAddress(detailAddress);
		setRequestDate(requestDate);
	}

	public MemberVO(String memberId, String pw, String name, String birth, String phone, String email,
			String memeberFile, String companyFile, String is_Company, String memberName, String zipCode,
			String address, String detailAddress, String status, String requestDate, String memberDate) {
		setMemberId(memberId);
		setPw(pw);
		setName(memberName);
		setBirth(birth);
		setPhone(phone);
		setEmail(email);
		setMemeberFile(memeberFile);
		setCompanyFile(companyFile);
		setIs_Company(is_Company);
		setMemberName(memberName);
		setZipCode(zipCode);
		setAddress(detailAddress);
		setDetailAddress(detailAddress);
		setStatus(status);
		setRequestDate(requestDate);
		setMemberDate(memberDate);
	}

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

	public String getBirth() {
		return birth;
	}

	public void setBirth(String birth) {
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

	public String getMemeberFile() {
		return memeberFile;
	}

	public void setMemeberFile(String memeberFile) {
		this.memeberFile = memeberFile;
	}

	public String getCompanyFile() {
		return companyFile;
	}

	public void setCompanyFile(String companyFile) {
		this.companyFile = companyFile;
	}

	public String getIs_Company() {
		return is_Company;
	}

	public void setIs_Company(String is_Company) {
		this.is_Company = is_Company;
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

	public String getRequestDate() {
		return requestDate;
	}

	public void setRequestDate(String requestDate) {
		this.requestDate = requestDate;
	}

	public String getMemberDate() {
		return memberDate;
	}

	public void setMemberDate(String memberDate) {
		this.memberDate = memberDate;
	}

}
