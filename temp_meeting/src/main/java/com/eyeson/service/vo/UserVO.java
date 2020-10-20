package com.eyeson.service.vo;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.websocket.Session;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.web.socket.WebSocketSession;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Lombok Annotation Description
 * @Getter: VO내에 값 불러오기 
 * @Setter: VO내에 값 지정하기 
 * @ToString: VO내의 모든값 불러오기(retrun: String)
 * 
 * JPA Annotation Description 
 * @Entity:	클래스와 테이블 매핑
 * @Table:	매핑할 테이블 정보 명시
 */
@Getter @Setter
@ToString
@Entity
@Table(name="tb_user_list")
public class UserVO {
	
	/**
	 * JPA Annotation Description
	 * @ID:					PrimaryKey값 지정
	 * @GeneratedValue:		Sequence 값 지정
	 * @Column: 			Column명 및 속성 지정
	 * @CreationTimestamp: 	TimeStamp 값 생성
	 */
	
	@Id
	@GeneratedValue
	@Column(name = "user_seq", length = 11, nullable = false)
	private Long userSeq;

	@Column(name = "email", length = 50, unique = true)
	private String email;

	@Column(name = "user_pw", length = 72)
	private String userPw;

	@Column(name = "authority", length = 20, columnDefinition = "varchar(20) default 'ROLE_USER'")
	private String authority;

	@Column(name = "enabled", length = 1, columnDefinition = "varchar(1) default '1'")
	private String enabled;

	@Column(name = "user_name", length = 100)
	private String userName;

	@Column(name = "legacyId", length = 50)
	private String legacyId;

	@Column(name = "country", length = 50)
	private String country;

	@Column(name = "department", length = 50)
	private String department;

	@Column(name = "lang", length = 30, nullable = false, columnDefinition = "varchar(30) default 'en'")
	private String lang;

	@Column(name = "reg_id", length = 50, nullable = false)
	private String regId;

	@Column(name = "reg_date")
	@CreationTimestamp
	private LocalDateTime regDate;

	@Column(name = "mod_id", length = 50)
	private String modId;

	@Column(name = "modDate")
	@CreationTimestamp
	private LocalDateTime mod_date;
	
	/*
	 * 테이블 매핑이 아닌 데이터 
	 */
	@Transient
	private String userId;

	@Transient
	private String type;

	@Transient
	private String afterUserPw; 
	
	@Transient
	private String confUuid;
	
	@Transient
	private String token;

	@Transient
	private Session session;

	@Transient
	private String userGroupCd;
	
	@Transient
	private WebSocketSession socketSession;

	@Transient
	private String ovSessionId;
	
}
