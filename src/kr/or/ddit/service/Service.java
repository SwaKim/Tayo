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
		 * @return boolean
		 */
		public boolean memberList();
		
		/**
		 * 관리자:버스리스트
		 * 
		 * @return boolean
		 */
		public boolean busList();

		/**
		 * 회원가입:아이디 중복체크
		 * @param 입력된 멤버id
		 * @return true(적합) / false(부적합)
		 */
		public boolean checkId(String menberid);
		
		/**
		 * 노선 추가
		 * busVO의 데이터를 view에서 입력받은 뒤 받아와서
		 * @param bsRoute
		 * @return boolean
		 */
		boolean addBus(Map<String, String> busadd);
		
		
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
		 * 구매목록 조회
		 * 로그인된 아이디의 구매리스트를 반환
		 * @param 로그인된 회원의 인덱스값
		 * @return 리스트
		 */
		public List<TicketVO> ticketList(String loginid);
		
		/**
		 * 노선변경
		 * 노선의 아이디를 받아서 전에 있던 노선과 바꿀 노선을 바꿔준다.
		 * @param id , bus
		 * @param Map
		 */
		public boolean changeBus(String id,BusVO bus);

		/**
		 * 충전
		 * 유저 아이디 를 받아와 돈을 충전해준다.
		 * @param money
		 * @return int
		 */
		public int chargeMoney(String id, int money);
		
		/**
		 * 결제
		 * @param TicketVO 소유권 정보가 담긴
		 * @return 성공여부
		 */
		public boolean payBusTicket(Map<String, String> ticket);
		
		/**
		 * 환불메서드
		 * 회원이 가지고있는 티켓이면 환불 아니면 실패 
		 * @param 로그인된 회원index, 티켓 index
		 * @return 환불성공여부
		 */
		public boolean refundTicket(String loginid, String tinput);
	
//----------------------시간 관리에 관한 메서드---------------------
	//구입시간
	
	//출발시간(만료시간은 여기에 해당) = 프로그램 실행하는 현재시간 + 버스시간간격
	
	
	
	
	
//-----------------------View에 관한 메서드----------------------
	
	//메인메뉴
	
	//입력

	//출력
	
	//회원메뉴
	
	//버스예매메뉴
	
	//가입메뉴(회원 가입 메세지 뷰)
	
	//관리자메뉴
}
