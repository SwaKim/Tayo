package kr.or.ddit.service;

import java.util.List;

import kr.or.ddit.vo.BusVO;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.TicketVO;



/**
 * Service를 위한 클래스
 * @author 현우석
 *
 */
public class ServiceImpl implements Service{
	private static ServiceImpl service = null;

	@Override
	public List<MemberVO> getListMember() {
		// TODO Auto-generated method stub
		return null;
	}		

	@Override
	public List<TicketVO> getListTicket() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BusVO> getListBus() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void joinMb() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mb() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addBus() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeBus() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void changeBus() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int chargeMoney() {
		return 0;
		
	}

	@Override
	public int payBusTicket() {
		return 0;
		
	}

	@Override
	public int refundTicket() {
		return 0;
		
	}

	@Override
	public void mainMenu() {
		System.out.println("선택하거나 명령을 입력하세요");
		System.out.println("1 : 로그인하기 (login)");
		System.out.println("2 : 회원가입하기 (regi)");
		System.out.print("new command");
	}
	

}
