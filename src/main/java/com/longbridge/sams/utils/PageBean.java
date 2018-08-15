package com.longbridge.sams.utils;


import java.util.List;

import org.springframework.data.domain.Page;


public class PageBean {
	
	private List data;
	private int draw;
	
	
	public int getDraw() {
		return draw;
	}
	public void setDraw(int draw) {
		this.draw = draw;
	}
	private long recordsTotal;
	private long recordsFiltered;
	public List getData() {
		return data;
	}
	public void setData(List data) {
		this.data = data;
	}
	public long getRecordsTotal() {
		return recordsTotal;
	}
	public void setRecordsTotal(long recordsTotal) {
		this.recordsTotal = recordsTotal;
	}
	public long getRecordsFiltered() {
		return recordsFiltered;
	}
	public void setRecordsFiltered(long recordsFiltered) {
		this.recordsFiltered = recordsFiltered;
	}
	
	public PageBean(){
		
	}

	public PageBean(List data, int draw, long recordsTotal, long recordsFiltered) {
		super();
		this.data = data;
		this.draw = draw;
		this.recordsTotal = recordsTotal;
		this.recordsFiltered = recordsFiltered;
	}
	
	public PageBean(Page<Object> p){
		this.setData(p.getContent());
		this.setRecordsFiltered(p.getTotalElements());
		this.setRecordsTotal(p.getTotalElements());
	}
	
	
}
