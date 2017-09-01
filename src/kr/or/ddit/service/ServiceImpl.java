package kr.or.ddit.service;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

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
	Database db = new Database();
	
	int idIndex = 0;//디비들 인덱스값...

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

	//회원가입시 아이디 중복체크
	@Override
	public boolean checkId(String menberid){
			
		return db.idCheck(menberid);		
	}
	
	//아이디체크
	@Override
	public int loginCheck(String userid, String userpw){
/*		for (int i = 0; i < db.getMbList().size(); i++) {
			if((db.getMbList().get(i).getMbUserId().equals(userid))){		//회원 중복 체크
				if(!(db.getMbList().get(i).getMbUserPw().equals(userpw))){
					return 1;
				}
			}
		}	
		if(!(db.getMbList().contains(userid))){
			return 2;
		}*/
		return 0;		
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
	
	//티켓구입
	@Override
	public boolean payBusTicket(Map<String, String> ticket) {
	      return db.createTicket(ticket);
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
	
	//버스리스트
	public boolean busList(){
		//busVosize
		if(db.getListSize("bus")==0){
			return false;
		}else{
			for (int i = 0; i < db.getListSize("bus"); i++) {
				System.out.println(db.getBsList(i));
			}
			return true;
		}
	}

	//회원 맴버리스트
	@Override
	public boolean memberList(){
		if(db.getListSize("member")==0){
			return false;
		}else{
			for (int i = 0; i < db.getListSize("member"); i++) {
				
				System.out.println(db.getMbList(i));
				
			}
			
			return true;
		}
	}

	@Override
	public List<TicketVO> ticketList(String loginid) {
		// TODO Auto-generated method stub
		return null;
	}

}
