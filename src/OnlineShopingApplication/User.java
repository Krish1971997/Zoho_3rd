package OnlineShopingApplication;

public class User {
    public String name;
    public String email;
    public String mobilenumber;
    public String password;
    public String CreatedTime;
    public String UpdatedTime;
    public String role;

    public User(String name, String email, String mobilenumber, String password,String role) {
        this.name = name;
        this.email = email;
        this.mobilenumber = mobilenumber;
        this.role = role;
        this.password =  PasswordUtility.encrpte(password);
        this.CreatedTime = PasswordUtility.timeGenerate();
        this.UpdatedTime = PasswordUtility.timeGenerate();
}
    public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getMobilenumber() {
		return mobilenumber;
	}


	public void setMobilenumber(String mobilenumber) {
		this.mobilenumber = mobilenumber;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getCreatedTime() {
		return CreatedTime;
	}


	public void setCreatedTime(String createdTime) {
		CreatedTime = createdTime;
	}


	public String getUpdatedTime() {
		return UpdatedTime;
	}


	public void setUpdatedTime(String updatedTime) {
		UpdatedTime = updatedTime;
	}


	public String getRole() {
		return role;
	}


	public void setRole(String role) {
		this.role = role;
	}


	@Override
    public String toString() {

        return "Customer{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", mobilenumber='" + mobilenumber + '\'' +
                ", password='" +  password + '\'' +
                ", CreatedTime='" + CreatedTime + '\'' +
                ", UpdatedTime='" + UpdatedTime + '\'' +
                ", role='" + role + '\'' +
                '}';
                
    }
}
