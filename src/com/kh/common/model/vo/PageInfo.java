package com.kh.common.model.vo;

public class PageInfo {
	
	// 7가지 변수들 필드로 정의
	
	// [필드부]
	private int listCount; // 현재 일반게시판의 게시글 총 개수 -> BOARD 로부터 조회 : COUNT(*) 활용해서 STATUS = 'Y' 인 경우
	private int currentPage; // 현재 페이지 (즉, 사용자가 요청한 페이지) -> request.getParameter("currentPage");
	private int pageLimit; // 페이지 하단에 보여질 페이징바의 페이지 최대 개수 -> 관례상 10개로 고정
	private int listLimit; // 한 페이지에 보여질 게시글의 최대 개수 -> 10개로 고정
	private int maxPage; // 가장 마지막 페이지가 몇 번 페이지인지 (즉, 총 페이지 개수) 
	private int startPage; // 페이지 하단에 보여질 페이징바의 시작수 
	private int endPage; // 페이지 하단에 보여질 페이징바의 마지막수 
	
	// [생성자부]
	// 기본생성자
	public PageInfo() {
		super();
	}
	
	// 모든 매개변수 생성자
	public PageInfo(int listCount, int currentPage, int pageLimit, int listLimit, int maxPage, int startPage,
			int endPage) {
		super();
		this.listCount = listCount;
		this.currentPage = currentPage;
		this.pageLimit = pageLimit;
		this.listLimit = listLimit;
		this.maxPage = maxPage;
		this.startPage = startPage;
		this.endPage = endPage;
	}
	
	// [메소드부]
	// getter / setter 메소드
	public int getListCount() {
		return listCount;
	}
	public void setListCount(int listCount) {
		this.listCount = listCount;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getPageLimit() {
		return pageLimit;
	}
	public void setPageLimit(int pageLimit) {
		this.pageLimit = pageLimit;
	}
	public int getListLimit() {
		return listLimit;
	}
	public void setListLimit(int listLimit) {
		this.listLimit = listLimit;
	}
	public int getMaxPage() {
		return maxPage;
	}
	public void setMaxPage(int maxPage) {
		this.maxPage = maxPage;
	}
	public int getStartPage() {
		return startPage;
	}
	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}
	public int getEndPage() {
		return endPage;
	}
	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}
	
	// toString() 메소드
	@Override
	public String toString() {
		return "PageInfo [listCount=" + listCount + ", currentPage=" + currentPage + ", pageLimit=" + pageLimit
				+ ", listLimit=" + listLimit + ", maxPage=" + maxPage + ", startPage=" + startPage + ", endPage="
				+ endPage + "]";
	}
	
}
