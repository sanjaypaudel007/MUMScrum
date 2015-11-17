package edu.mum.mumscrum.enums;

public enum RoleType {
	NOT_ASSIGNED("NOT_ASSIGNED"),
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
