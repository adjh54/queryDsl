package com.eyeson.service.vo;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString
@Entity
@Table(name = "tb_guest_list")
public class LoginVO implements Serializable{

//	@Column(name = "email", length = 50, nullable = false)
//	private String email = "";
//	
//	@Column(name = "email", length = 50, nullable = false)
//	private String pw = "";
//	private String inviteUuid = "";

	@Id
	@Column(name = "conf_uuid", length = 60, nullable = false)
	private String confUuid = "";

	@Id
	@Column(name = "email", length = 50, nullable = false)
	private String email = "";

	@Column(name = "user_pw", length = 72)
	private String userPw = "";

	@Column(name = "authority", length = 20, columnDefinition = "varchar(10) default 'ROLE_GUEST'")
	private String authority = "";

	@Column(name = "enabled", length = 1, columnDefinition = "varchar(1) default '1'")
	private String enabled = "";

	@Column(name = "user_name", length = 100)
	private String userName = "";

	@Column(name = "invite_uuid", length = 60, nullable = false)
	private String inviteUuid = "";

	@Column(name = "reg_id", length = 50, nullable = false)
	private String regId = "";

	@Column(name = "reg_date", length = 50, nullable = false)
	@CreationTimestamp
	private LocalDateTime regDate;

	@Column(name = "mod_id", length = 50, nullable = false)
	private String mod_id = "";

	@Column(name = "mod_date", length = 50, nullable = false)
	@CreationTimestamp
	private LocalDateTime modDate;
	
}
