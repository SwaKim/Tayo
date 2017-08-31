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
	
	public boolean joinMb(MemberVO member);
	
	/**
	 * 회원 삭제
	 * List에서 id와 일치하는 것을 찾아
	 * remove해준다.
	 * @param id
	 * @return boolean
	 */
	public boolean delMb(String id);
	
	/**
	 * 노선 추가
	 * busVO의 데이터를 view에서 입력받은 뒤 받아와서
	 * @param bsRoute
	 * @return boolean
	 */
	public boolean addBus(BusVO busvo);
	
	/**
	 * 노선삭제
	 * @param id
	 */
	public boolean removeBus(String id);

	/**
	 * 노선변경
	 * 선택한 노선의 인덱스로 노선 삭제메서드를 실행하고 변경할 노선의 VO를 생성한다
	 * @param asId
	 * @param forId
	 */
	public boolean changeBus(String id,BusVO bus);

	/**
	 * 충전
	 * 로그인된 유저의 돈을 충전한다.
	 * @param money
	 * @return int
	 */
	public int chargeMoney(String id, int money);
	
	/**
	 * 결제
	 * @param id
	 * @return int
	 */
	public int payBusTicket(String id);
	
	/**
	 * 환불
	 * @param id
	 * @return int
	 */
	public int refundTicket(String id);
	
//----------------------시간 관리에 관한 메서드---------------------
	//구입시간
	
	//출발시간(만료시간은 여기에 해당) = 프로그램 실행하는 현재시간 + 버스시간간격
	
	
	
	
	
//-----------------------View에 관한 메서드----------------------
	
	//메인메뉴
	
	//입력
	public String input();
	//출력

	public String input(int index);
	
	//회원메뉴
	
	//버스예매메뉴
	
	//가입메뉴(회원 가입 메세지 뷰)
	
	//관리자메뉴
}
