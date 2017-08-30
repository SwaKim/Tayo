package kr.or.ddit.service;

import java.util.Scanner;

import kr.or.ddit.vo.BusVO;

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

	Scanner sc = new Scanner(System.in);

	@Override
	public void joinMb(String mbUserId, String mbUserPw) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean delMb(String id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addBus(BusVO busvo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeBus(String id) {
		return false;
		// TODO Auto-generated method stub
		
	}

	@Override
	public void changeBus(String asId, String forId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int chargeMoney(int money) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int payBusTicket(String id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int refundTicket(String id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String input() {
		// TODO Auto-generated method stub
		return null;
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
