package userRMIServer;

import java.io.Serializable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1464002385204833616L;
	@SerializedName("success")
	@Expose
	private Boolean success;
	@SerializedName("msg")
	@Expose
	private String msg;
	@SerializedName("token")
	@Expose
	private String token;
	@SerializedName("userName")
	@Expose
	private String userName;
	@SerializedName("password")
	@Expose
	private String password;
	@SerializedName("email")
	@Expose
	private String email;
	@SerializedName("mobileNumber")
	@Expose
	private String mobileNumber;

	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

}