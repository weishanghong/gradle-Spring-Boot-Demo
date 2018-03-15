package com.weiresearch.pojoentity;

import com.weiresearch.entity.Province;

import java.util.List;

public class ProvinceFindControllerResponse {

	private Pagination pagination;
	private List<Province> list;

	public ProvinceFindControllerResponse() {
	}

	public ProvinceFindControllerResponse(Pagination pagination, List<Province> list) {
		this.pagination = pagination;
		this.list = list;
	}

	public Pagination getPagination() {
		return pagination;
	}

	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}

	public List<Province> getList() {
		return list;
	}

	public void setList(List<Province> list) {
		this.list = list;
	}

}
