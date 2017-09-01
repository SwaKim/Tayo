package kr.or.ddit.service;

import java.util.List;
import java.util.Map;
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
	
	//회원추가
	@Override
	public boolean joinMb(MemberVO member) {
		
		member.setId(Integer.toString(idIndex));

		idIndex++;
		return db.createMember(member);
	}
	
	//회원삭제
	@Override
	public boolean delMb(String id) {
				
		return db.deleteMember(id);
		
	}
	
	//버스추가
	@Override
	public boolean addBus(BusVO busvo) {
		
		return db.createBus(busvo);
		
	}
	//버스삭제
	@Override
	public boolean removeBus(String id) {
		
		return db.deleteBus(id);
		
	}
	
	//페스워드
	@Override
	public boolean checkId(String menberid){
			
		return db.idCheck(menberid);		
	}
	
	//아이디체크
	@Override
	public int loginCheck(String userid, String userpw){
		for (int i = 0; i < db.getMbList().size(); i++) {
			if((db.getMbList().get(i).getMbUserId().equals(userid))){		//회원 중복 체크
				if(!(db.getMbList().get(i).getMbUserPw().equals(userpw))){
					return 1;
				}
			}
		}	
		if(!(db.getMbList().contains(userid))){
			return 2;
		}
		return 0;		
	}
	
	//회원 맴버리스트
	@Override
	public boolean memberList(){
		if(db.getListSize("member")==0){
			return false;
		}else{
			for (int i = 0; i < db.getListSize("member"); i++) {
				
				System.out.println(db.getMbList());
				
			}
			
			return true;
		}
	}
	
	//티켓 리스트+
	@Override
	public List<TicketVO> ticketList(String loginid){
		
		return db.getTkList(loginid);
	}
	
	//노선변경
	@Override
	public boolean changeBus(String id,BusVO bus) {
		return false;
		
	}

	//충전
	@Override
	public int chargeMoney(String id, int money) {
		return 0;
	}
	
	//티켓구입
	/*@Override
	public boolean payBusTicket(Map<String, String> ticket) {
	      return db.createTicket(ticket);
	}*/

	//버스리스트
	public boolean busList(){
		//busVosize
		if(db.getListSize("bus")==0){
			return false;
		}else{
			for (int i = 0; i < db.getListSize("bus"); i++) {
				
				System.out.println(db.getBsList());
				
			}
			
			return true;
		}
	}

	//환불
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

	@Override
	public boolean payBusTicket(Map<String, String> ticket) {
		// TODO Auto-generated method stub
		return false;
	}

	




}
