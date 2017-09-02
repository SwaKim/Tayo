package kr.or.ddit.vo;

import java.util.Date;

public class TicketVO{
	//어느 VO클래스건 index의 id
	private int id;			//기본키
	
	private Date tkBuyTime;		//구매시간

	private boolean isTkUsed;	//사용여부

	//티켓은 외래키로 각 정보를 빌려서 받아온다.
	//참조
	private int memId;			//소유자
	private int busId;			//버스에서 가져온 정보 = 노선, 요금, 출발시간, 종류
	
	private int seat;			//좌석

	public int getSeat() {
		return seat;
	}
	public void setSeat(int seat) {
		this.seat = seat;
	}
	public int getId() {
		return id;
	}
	public void setId(int mbIndex) {
		this.id = mbIndex;
	}
	public Date getTkBuyTime() {
		return tkBuyTime;
	}
	public void setTkBuyTime(Date tkBuyTime) {
		this.tkBuyTime = tkBuyTime;
	}
	public boolean isTkUsed() {
		return isTkUsed;
	}
	public void setTkUsed(boolean isTkUsed) {
		this.isTkUsed = isTkUsed;
	}
	public int getMemId() {
		return memId;
	}
	public void setMemId(int memId) {
		this.memId = memId;
	}
	public int getBusId() {
		return busId;
	}
	public void setBusId(int busId) {
		this.busId = busId;
	}
	@Override
	public String toString() {
		return "TicketVO [id=" + id + ", tkBuyTime=" + tkBuyTime + ", isTkUsed=" + isTkUsed + ", memId=" + memId
				+ ", busId=" + busId + "]";
	}
}
