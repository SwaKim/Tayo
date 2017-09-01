package kr.or.ddit.service;

import java.util.List;
import java.util.Map;

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
	/**
	 * 회원 가입
	 * 회원 가입에 필요한 id pwd 이름 생성해준다.
	 * 
	 * @param member
	 * @return MemberVO
	 */
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
	 * 관리자:회원리스트
	 * 
	 * @param 
	 * @return boolean
	 */
	public boolean memberList();
	
	/**
	 * 노선 추가
	 * busVO의 데이터를 view에서 입력받은 뒤 받아와서
	 * @param bsRoute
	 * @return boolean
	 */
	boolean addBus(BusVO busvo);
	

	/**
	 * 로그인 (정규표현식)
	 * @param alba_pw
	 * @return true(적합) / false(부적합)
	 * 기준 = 알파벳소문자, 대문자, 숫자만 가능하며 6~12글자만.
	 * 
	 */
	public boolean checkId(String menberid);
	
	/**
	 * 노선삭제
	 * @param id
	 */
	public boolean removeBus(String id);

	
	/**
	 * 로그인체크
	 * id와 pwd 체크해준다. 
	 * 
	 * @param userid, userpw
	 * @return int
	 */
	public int loginCheck(String userid, String userpw);
	
	
	/**
	 * 충전
	 * 유저 아이디 를 받아와 돈을 충전해준다.
	 * @param money
	 * @return int
	 */
	public boolean busList();
	
	/**
	 * 표 리스트
	 * 현재 로그인 된 아이디에 예약가능한 표를 보여준다.
	 * @param loginid
	 * @return  List<TicketVO>
	 */
	public List<TicketVO> ticketList(String loginid);
	
	/**
	 * 노선변경
	 * 노선의 아이디를 받아서 전에 있던 노선과 바꿀 노선을 바꿔준다.
	 * @param id , bus
	 * @param boolean
	 */
	public boolean changeBus(String id,BusVO bus);

	/**
	 * 충전
	 * 유저 아이디 를 받아와 돈을 충전해준다.
	 * @param money
	 * @return int
	 */
	int chargeMoney(String id, int money);
	
	/**
	 * 버스결제
	 * 사용자가 티켓을 결제
	 * @param ticket
	 * @return boolean
	 */
	public boolean payBusTicket(Map<String, String> ticket);
	
	/**
	 * 티켓 환불
	 * 사용자가 티켓을 환불.
	 * @param loginid, tinput
	 * @return boolean
	 */
	//public boolean refundTicket(String id);
	public boolean refundTicket(String loginid, String tinput);

	
	String input(int index);

	

}
