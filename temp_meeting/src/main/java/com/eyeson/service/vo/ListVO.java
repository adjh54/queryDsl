package com.eyeson.service.vo;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;


/**
 * @author 			: yunjin_choi
 * @package_name	: com.meucci.meeting.common.vo
 * @Date 			: May 17, 2019
 * 
 * list.do 에서 방에 대한 정보를 return 하기 위해 사용하는 vo
 * 
 */
public class ListVO {
	private String type;
	private List<?> list;
	private int count;
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public List<?> getList() {
		return list;
	}
	public void setList(List<?> list) {
		this.list = list;
		this.count = list.size();
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
}
