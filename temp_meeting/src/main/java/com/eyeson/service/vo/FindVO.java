package com.eyeson.service.vo;

public class FindVO {
	private String status;
	private String email;
	private String legacyId;
	
	public FindVO () {
		
	}
	
	public FindVO (String status, String email) {
		this.status = status;
		this.email = email;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getLegacyId() {
		return legacyId;
	}
	public void setLegacyId(String legacyId) {
		this.legacyId = legacyId;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FindVO [status=");
		builder.append(status);
		builder.append(", email=");
		builder.append(email);
		builder.append(", legacyId=");
		builder.append(legacyId);
		builder.append("]");
		return builder.toString();
	}
	

}
