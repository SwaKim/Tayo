package kr.or.ddit.vo;

public class BusVO {
	//어느 VO클래스건 index의 id
	private String id; //기본키
	
	private String bsRoute;	//출발지-도착지
	
	private String bsPrice;	//요금
	
	private String bsDepartureTime;	//출발시간
	
	private String bsSeat;	//좌석
	
	private String bsKind;	//일반?우등?

	
	
	public BusVO(String id, String bsRoute, String bsPrice, String bsDepartureTime, String bsSeat, String bsKind) {
		super();
		this.id = id;
		this.bsRoute = bsRoute;
		this.bsPrice = bsPrice;
		this.bsDepartureTime = bsDepartureTime;
		this.bsSeat = bsSeat;
		this.bsKind = bsKind;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBsRoute() {
		return bsRoute;
	}

	public void setBsRoute(String bsRoute) {
		this.bsRoute = bsRoute;
	}

	public String getBsPrice() {
		return bsPrice;
	}

	public void setBsPrice(String bsPrice) {
		this.bsPrice = bsPrice;
	}

	public String getBsDepartureTime() {
		return bsDepartureTime;
	}

	public void setBsDepartureTime(String bsDepartureTime) {
		this.bsDepartureTime = bsDepartureTime;
	}

	public String getBsSeat() {
		return bsSeat;
	}

	public void setBsSeat(String bsSeat) {
		this.bsSeat = bsSeat;
	}

	public String getBsKind() {
		return bsKind;
	}

	public void setBsKind(String bsKind) {
		this.bsKind = bsKind;
	}

	@Override
	public String toString() {
		return "BusVO [id=" + id + ", bsRoute=" + bsRoute + ", bsPrice=" + bsPrice + ", bsDepartureTime="
				+ bsDepartureTime + ", bsSeat=" + bsSeat + ", bsKind=" + bsKind + "]";
	}
	
	
}
