package kr.or.ddit.database;

import java.util.ArrayList;
import java.util.List;

import kr.or.ddit.vo.BusVO;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.TicketVO;

public class Database {
	private List<MemberVO> mbList = new ArrayList<MemberVO>();
	private List<BusVO> bsList = new ArrayList<BusVO>();
	private List<TicketVO> tkList = new ArrayList<TicketVO>();
	
	public List<MemberVO> getMemberVOList() {
		return mbList;
	}
	
	public List<BusVO> getBusVOList() {
		return bsList;
	}
	
	public List<TicketVO> getTicketVOList() {
		return tkList;
	}
	
	public Database(){
		//초기값을 테스트로 넣어준다
		MemberVO admin1 = new MemberVO("0",true,"admin","admin","관리자","10000");
		MemberVO user1 = new MemberVO("1",false,"kkk3206","1234","김수환","90000");

		mbList.add(admin1);
		mbList.add(user1);
	}
}
