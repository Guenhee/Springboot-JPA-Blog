package com.gh.blog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
//ORM -> Java(다른어어) Object -> 테이블로 매핑
@Entity //클래스가 MySQL에 테이블이 생성된다.
public class Reply {
	
	@Id //Primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY)//auto_increment
	private int id;
	
	@Column(nullable = false , length  = 200)
	private String content;
	
	@ManyToOne
	@JoinColumn(name = "boardId")
	private Board board;
	
	@ManyToOne
	@JoinColumn(name = "userId")
	private User user;
	
	@CreationTimestamp
	private Timestamp createDate;
	
	
}
