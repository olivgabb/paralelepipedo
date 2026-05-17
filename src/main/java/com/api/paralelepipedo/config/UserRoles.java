package com.api.paralelepipedo.config;

public enum UserRoles {
	ADMIN("admin"),
	ALUNO("aluno"),
	PROFESSOR("professor");
	
	private String role;
	
	UserRoles(String role)
	{
		this.role = role;
	}
	
	public String getRole()
	{
		return this.role;
	}
}
