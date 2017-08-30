package kr.or.ddit.vo;

import java.util.Date;

public class TicketVO{
	//어느 VO클래스건 index의 id
	private String id;			//기본키
	
	private Date tkBuyTime;		//구매시간

	private boolean isTkUsed;	//사용여부

	//티켓은 외래키로 각 정보를 빌려서 받아온다.
	//참조
	private String mem_id;		//소유자 = 주인?
	private String bus_id;		//버스에서 가져온 정보 = 노선, 요금, 출발시간, 좌석, 종류	

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	public String getMem_id() {
		return mem_id;
	}
	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}
	public String getBus_id() {
		return bus_id;
	}
	public void setBus_id(String bus_id) {
		this.bus_id = bus_id;
	}
	@Override
	public String toString() {
		return "TicketVO [id=" + id + ", tkBuyTime=" + tkBuyTime + ", isTkUsed=" + isTkUsed + ", mem_id=" + mem_id
				+ ", bus_id=" + bus_id + "]";
	}
}
