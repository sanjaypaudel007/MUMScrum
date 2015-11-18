package edu.mum.mumscrum.enums;

public enum RoleType {
	SCRUM_MASTER("SCRUM_MASTER"),
	DEVELOPER("DEVELOPER"),
	TESTER("TESTER"),
	ADMIN("ADMIN");
	
	String roles;

	private RoleType(String roles) {
		this.roles = roles;
	}

	/**
	 * @return the roles
	 */
	public String getRoles() {
		return roles;
	}
	
	
	
}
