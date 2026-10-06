package kr.swdl.model;

public class AdminVO {
	private String request_date;
	private String status;
	private String member_name;
	private String name;
	
	private String birth;
	private String phone;
	private String email;
	private String address;
	
	private String member_file;
	private String company_file;
	
	
	public AdminVO() {}
	
	
	
	public AdminVO(String request_date, String member_name, String name, String birth, String phone, String email,
			String address, String member_file, String company_file) {
		setRequest_date(request_date);
		setMember_name(member_name);
		setName(name);
		setBirth(birth);
		setPhone(phone);
		setEmail(email);
		setAddress(address);
	
		setMember_file(member_file);
		setCompany_file(company_file);
		}



	public AdminVO(String request_date, String name, String birth, String phone,
			String email, String address, String member_file) {
		setRequest_date(request_date);
		setName(name);
		setBirth(birth);
		setPhone(phone);
		setEmail(email);
		setAddress(address);

		setMember_file(member_file);
	}


	public AdminVO(String request_date, String status, String member_name, String name) {
		setRequest_date(request_date);
		setStatus(status);
		setMember_name(member_name);
		setName(name);
	}	

	
	
	
	public String getCompany_file() {
		return company_file;
	}



	public void setCompany_file(String company_file) {
		this.company_file = company_file;
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



	public String getAddress() {
		return address;
	}



	public void setAddress(String address) {
		this.address = address;
	}




	public String getMember_file() {
		return member_file;
	}



	public void setMember_file(String member_file) {
		this.member_file = member_file;
	}



	public String getRequest_date() {
		return request_date;
	}
	public void setRequest_date(String request_date) {
		this.request_date = request_date;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMember_name() {
		return member_name;
	}
	public void setMember_name(String member_name) {
		this.member_name = member_name;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}



	@Override
	public String toString() {
		return "AdminVO [request_date=" + request_date + ", status=" + status + ", member_name=" + member_name
				+ ", name=" + name + ", birth=" + birth + ", phone=" + phone + ", email=" + email + ", address="
				+ address + ", member_file=" + member_file + ", company_file=" + company_file + "]";
	}



	



	


}
