package kr.or.ddit.service;

import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

import javax.xml.bind.ParseConversionEvent;

import kr.or.ddit.database.Database;
import kr.or.ddit.vo.BusVO;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.TicketVO;

/**
 * @Class Name : ServiceImpl.java
 * @Description : 
 * @Modification Information
 * @author 현우석
 * @since  2017.08.28.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *    수정일       
 *    -------      -------    -------------------
 *    2017.08.29.	현우석		최초생성
 * Copyright (c) 2017 by DDIT  All right reserved
 * </pre>
 */
public class ServiceImpl implements Service{
	private static ServiceImpl service = null;
	Database db = new Database();
	
	int idIndex = 0;//디비들 인덱스값...
	Scanner sc = new Scanner(System.in);
	
	
	@Override
	public boolean joinMb(MemberVO member) {
		
		member.setId(Integer.toString(idIndex));

		idIndex++;
		return db.createMember(member);
	}

	@Override
	public boolean delMb(String id) {
		
		
		return db.deleteMember(id);
	}

	@Override
	public boolean addBus(BusVO busvo) {
		
		return db.createBus(busvo);
	}

	@Override
	public boolean removeBus(String id) {
		
		return db.deleteBus(id);
		
	}
	@Override
	public boolean craTicket(TicketVO ticket){
		
		
		return db.createTicket(ticket);
		
	}
	//아이디 중복체크
	
	//페스워드
	@Override
	public boolean checkId(String menberid){
			
		return db.idCheck(menberid);		
	}
	
	@Override
	public boolean loginCheck(String userid, String userpw){
		for (int i = 0; i < db.getMbList().size(); i++) {
			if(mbList.get(i).getMbUserId().equals(muid)){		//회원 중복 체크
				return false;												//중복이면 가입을 중지
			}
		}	
		
		return false;		
	}
	
	@Override
	public List<MemberVO> memberList(Boolean isAdmin){
		if(isAdmin == true){
			return db.getMbList();
		}
		return null;
	}
	
	@Override
	public List<TicketVO> ticketList(String loginid){
		
		return db.getTkList(loginid);
	}

	@Override
	public boolean changeBus(String id,BusVO bus) {
		return false;
		
	}
	/**
	 * 환불메서드
	 * 회원이 가지고있는 티켓이면 환불 아니면 실패 
	 * @param 로그인된 회원index, 티켓 index
	 * @return 티켓 가격
	 */
	@Override
	public int chargeMoney(String id, int money) {
		return 0;
	}
	
	/**
	 * 티켓구입메서드
	 * 회원이 티켓을 구매  
	 * @param 로그인 Id, 티켓 금액
	 * @return 티켓 가격
	 */
	@Override
	public int payBusTicket(String id, int money) {
		return 0;
	}
	
	
	/**
	 * 환불메서드
	 * 회원이 가지고있는 티켓이면 환불 아니면 실패 
	 * @param 로그인된 회원index, 티켓 index
	 * @return 환불성공여부
	 */
 
	@Override
	public boolean refundTicket(String loginid, String tinput) {
		List<TicketVO> tl = db.getTkList(loginid);
		for (int i = 0; i < tl.size(); i++) {
			if(tl.get(i).getMem_id().equals(tinput)){
				return db.deleteTicket(tinput);
				
			}
			
		}
		return false;
		
	}



	@Override
	public String input(int index) {
		try {
			String input = sc.next();
			return input;
		} catch (Exception e) {
			return "다시 입력해주세요";
		}
	}

	




}
