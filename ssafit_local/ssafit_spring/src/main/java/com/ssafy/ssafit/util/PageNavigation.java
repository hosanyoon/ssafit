package com.ssafy.ssafit.util;

public class PageNavigation {
	int displayedArticle = 20;
	int totalArticle;
	int totalPage;
	int currentPage;
	int firstPageNum;
	int lastPageNum;
	boolean prevBtnActivation = false;
	boolean nextBtnActivation = false;	
	
	public PageNavigation(int totalArticleCount, int pageNum) {
		totalArticle = totalArticleCount;
		
		totalPage = (totalArticle-1) / displayedArticle+1;
		
		currentPage = pageNum;
		
		firstPageNum = (pageNum-1)/10*10 + 1;
		
		lastPageNum = firstPageNum + 9;
		if(lastPageNum>totalPage) {
			lastPageNum = totalPage;
		}
		
		if(firstPageNum != 1) prevBtnActivation = true;
		if(lastPageNum != totalPage) nextBtnActivation = true;		
		
	}

	public int getDisplayedArticle() {
		return displayedArticle;
	}

	public void setDisplayedArticle(int displayedArticle) {
		this.displayedArticle = displayedArticle;
	}

	public int getTotalArticle() {
		return totalArticle;
	}

	public void setTotalArticle(int totalArticle) {
		this.totalArticle = totalArticle;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getFirstPageNum() {
		return firstPageNum;
	}

	public void setFirstPageNum(int firstPageNum) {
		this.firstPageNum = firstPageNum;
	}

	public int getLastPageNum() {
		return lastPageNum;
	}

	public void setLastPageNum(int lastPageNum) {
		this.lastPageNum = lastPageNum;
	}

	public boolean isPrevBtnActivation() {
		return prevBtnActivation;
	}

	public void setPrevBtnActivation(boolean prevBtnActivation) {
		this.prevBtnActivation = prevBtnActivation;
	}

	public boolean isNextBtnActivation() {
		return nextBtnActivation;
	}

	public void setNextBtnActivation(boolean nextBtnActivation) {
		this.nextBtnActivation = nextBtnActivation;
	}
}
