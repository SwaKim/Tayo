package kr.or.ddit.vo;

import java.util.Date;

public class TicketVO{
	//어느 VO클래스건 index의 id
	private String id; //기본키
	
	private Date tkBuyTime;	//구매시간

	private boolean isTkUsed;	//사용여부

	//티켓은 외래키로 각 정보를 빌려서 받아온다.
	//참조
	private MemberVO tkbuyer;	//소유자 = 주인?
	private BusVO tkBus;	//버스에서 가져온 정보 = 노선, 요금, 출발시간, 좌석, 종류
	
	public TicketVO(String id, Date tkBuyTime, boolean isTkUsed, MemberVO tkbuyer, BusVO tkBus) {
		super();
		this.id = id;
		this.tkBuyTime = tkBuyTime;
		this.isTkUsed = isTkUsed;
		this.tkbuyer = tkbuyer;
		this.tkBus = tkBus;
	}
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
	public MemberVO getTkbuyer() {
		return tkbuyer;
	}
	public void setTkbuyer(MemberVO tkbuyer) {
		this.tkbuyer = tkbuyer;
	}
	public BusVO getTkBus() {
		return tkBus;
	}
	public void setTkBus(BusVO tkBus) {
		this.tkBus = tkBus;
	}
	
	@Override
	public String toString() {
		return "TicketVO [id=" + id + ", tkBuyTime=" + tkBuyTime + ", isTkUsed=" + isTkUsed + ", tkbuyer=" + tkbuyer
				+ ", tkBus=" + tkBus + "]";
	}
}
