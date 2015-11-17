package edu.mum.mumscrum.tester;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoder {
	public static void main(String[] args) {
		String password = "admin";
		BCryptPasswordEncoder pe = new BCryptPasswordEncoder();
		System.out.println(pe.encode(password));
	}
}
