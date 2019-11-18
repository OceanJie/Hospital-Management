package main.java;

import java.util.ArrayList;
import java.util.List;

/**
 * Admin class
 * @author Sheng Jie Ooi
 */
public class Admin {

	private int adminId;
	private String adminName;
	private String adminPassword;

	public Admin(int adminId, String adminName, String adminPassword){
		this.adminId = adminId;
		this.adminName =adminPassword;
		this.adminPassword = adminPassword;
	}
	public int getAdminId() {
		return adminId;
	}

	public String getAdminName() {
		return adminName;
	}

	public String getAdminPassword() {
		return adminPassword;
	}
}

