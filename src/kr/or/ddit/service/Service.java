package kr.or.ddit.service;

import java.util.List;

import kr.or.ddit.vo.BusVO;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.TicketVO;

/**
 * @Class Name : Service.java
 * @Description : 
 * @Modification Information
 * @author 이중우
 * @since  2017.08.28.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *    수정일       
 *    -------      -------    -------------------
 *    2017.08.29.	이중우		최초생성
 * Copyright (c) 2017 by DDIT  All right reserved
 * </pre>
 */

public interface Service {//대략적인 기능
//-----------------------CRUD에 관한 메서드---------------------	
	
	public void joinMb(String mbUserId, String mbUserPw) ;
	
	/**
	 * 회원 삭제
	 * List에서 id와 일치하는 것을 찾아
	 * remove해준다.
	 * @param id
	 * @return boolean
	 */
	boolean delMb(String id);
	
	/**
	 * 노선 추가
	 * busVO의 데이터를 view에서 입력받은 뒤 받아와서
	 * @param bsRoute
	 * @return boolean
	 */
	boolean addBus(BusVO busvo);
/*	view  class
	BusVO bus = new ;
	bus.setid, setruuajkllllkljk
	성공*/
	
	/**
	 * 노선삭제
	 * @param id
	 */
	boolean removeBus(String id);

	/**
	 * 노선변경
	 * @param asId
	 * @param forId
	 */
	void changeBus(String asId, String forId);

	/**
	 * 충전
	 * @param money
	 * @return int
	 */
	int chargeMoney(int money);
	
	/**
	 * 결제
	 * @param id
	 * @return int
	 */
	int payBusTicket(String id);
	
	/**
	 * 환불
	 * @param id
	 * @return int
	 */
	int refundTicket(String id);
	
//----------------------시간 관리에 관한 메서드---------------------
	//구입시간
	
	//출발시간(만료시간은 여기에 해당) = 프로그램 실행하는 현재시간 + 버스시간간격
	
	
	
	
	
//-----------------------View에 관한 메서드----------------------
	
	//메인메뉴
	
	//입력
	String input();
	//출력

	String input(int index);
	
	//회원메뉴
	
	//버스예매메뉴
	
	//가입메뉴(회원 가입 메세지 뷰)
	
	//관리자메뉴
}
