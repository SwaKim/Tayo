package kr.or.ddit.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.database.Database;
import kr.or.ddit.vo.TicketVO;

/**
 * @Class Name : ServiceImpl.java
 * @Description 
 * @Modification Information
 * @author 현우석, 이중우, 김수환
 * @since 2017.08.28.
 * @version 1.0
 * @see
 * 
 *      <pre>
 * << 개정이력(Modification Information) >>
 *    수정일       
 *    -------      -------    -------------------
 *    2017.08.29.   현우석      최초생성
 * Copyright (c) 2017 by DDIT  All right reserved
 *      </pre>
 */
public class ServiceImpl implements Service {
	Database db = new Database();

	// 회원추가
	@Override
	public boolean joinMember(Map<String, String> member) {

		return db.createMember(member);
	}

	// 회원삭제
	@Override
	public boolean deleteMember(int id) {

		return db.deleteMember(id);
	}

	// 회원가입시 아이디 중복체크
	@Override
	public boolean checkJoinId(String menberid) {

		return db.idCheck(menberid);
	}

	// 아이디체크
	@Override
	public int loginCheck(Map<String, String> login) {

		try {
			if (db.readIdPwFromDB(login).get("userId").equals(login.get("userId"))) {	// 회원

				if (!db.readIdPwFromDB(login).get("userPw").equals(login.get("userPw"))) {
					return -1;// 비밀번호틀림

				} else {
					if (db.readIdPwFromDB(login).get("isAdmin").equals("true")) {
						return -2;// 관리자 로그인

					} else {
						return Integer.parseInt(db.readIdPwFromDB(login).get("id"));	// 로그인 완료, 해당 회원 인덱스값 반환
					}
				}
			}
		} catch (NullPointerException e) {
			return -3;
		}


		return -3;																		// 아이디 없음.
	}

	// 버스추가 미완
	@Override
	public boolean addBus(Map<String, String> busAdd) {

		return db.createBus(busAdd);// ;
	}

	// 버스삭제
	@Override
	public boolean removeBus(int id) {

		return db.deleteBus(id);
	}

	// 충전
	@Override
	public int chargeMoney(int id, int money) {
		int result = db.chargeMoney(id, money);
		return result;
	}

	// 티켓구입 = 티켓 생성
	@Override
	public int payBusTicket(Map<String, String> ticket) {

		int result = db.createTicket(ticket); 
		if(result == -1){
			return -1;
		}else if(result == -2){
			return -2;
		}else if(result == -3){
			return -3;
		}

		return result;// db.createTicket(ticket);
	}



	// 환불 -1  티켓이 없다. -2 구매자가 아니다.
	@Override
	public int refundTicket(int loginid, int tinput) {
		int result = db.deleteTicket(loginid, tinput);
		if (result == -1) {
			return -1;
		} else if (result == -2) {
			return -2;
		}
		return result;
	}

	// 버스리스트
	public boolean showBusList() {
		// busVosize
		if (db.getListSize("bus") == 0) {
			return false;
		} else {
			for (int i = 0; i < db.getListSize("bus"); i++) {
				System.out.println(db.getBusList(i));
			}
			return true;
		}
	}

	// 회원 맴버리스트
	@Override
	public boolean showMemberList() {
		if (db.getListSize("member") == 0) {
			return false;
		} else {
			for (int i = 0; i < db.getListSize("member"); i++) {

				System.out.println(db.getMemberList(i));

			}


		}
		return true;
	}

	// 회원메뉴티켓리스트
	@Override
	public boolean showTicketList(int loginid) {
		for (int i = 0; i < db.getTotalTicketList().size(); i++) {
			if (db.getTicketListString(loginid).get(i)!=null) {
				System.out.println(db.getTicketListString(loginid).get(i));
			}
		}
		return true;
	}

	// 관리자용티켓리스트
	@Override
	public boolean showTotalTicketList() {
		for (int i = 0; i < db.getTotalTicketList().size(); i++) {
			System.out.println(db.getTotalTicketList().get(i));
		}
		return true;
	}


	// 총금액
	@Override
	public int calcTotal() {
		int money = db.allPayMoney();
		return money;
	}


}