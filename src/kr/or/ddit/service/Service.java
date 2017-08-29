package kr.or.ddit.service;

import java.util.List;

import kr.or.ddit.vo.BusVO;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.TicketVO;

/**
 * 중우 그 자체.
 * @author 이중우
 *
 *해야될것 view 캡슐화
 */
public interface Service {
	List<MemberVO> getListMember(); // 멤버VO list 얻어오기
	List<TicketVO> getListTicket(); // 버스VO list 얻어오기
	List<BusVO> getListBus(); // 티켓VO list 얻어오기
//-----------------------CRUD에 관한 메서드---------------------	
	//회원추가
	void joinMb();
	
	//회원삭제
	void mb();
	
	//노선추가
	void addBus();
	
	//노선삭제
	void removeBus();

	//노선변경
	void changeBus();

	//충전
	int chargeMoney();
	
	//결제
	int payBusTicket();
	
	//환불
	int refundTicket();
	
//----------------------시간 관리에 관한 메서드---------------------
	//구입시간
	
	//출발시간(만료시간은 여기에 해당) = 프로그램 실행하는 현재시간 + 버스시간간격
	
	
	
	
	
//-----------------------View에 관한 메서드----------------------
	
	//메인메뉴
	void mainMenu();
	
	//입력
	
	//출력
	
	//회원메뉴
	
	//버스예매메뉴
	
	//가입메뉴(회원 가입 메세지 뷰)
	
	//관리자메뉴
}
