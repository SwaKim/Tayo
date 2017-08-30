package kr.or.ddit;

import java.util.Scanner;

import kr.or.ddit.service.Service;
import kr.or.ddit.service.ServiceImpl;

/**
 * @Class	Name : ViewClass.java
 * @Description 고속버스 예매 시스템
 * @author
 * @since 2017-08-31
 * @version 0.1
 * @see
 * <pre>
 *	수정일			수정자		수정내용		
 *	-------			-------		-------------------				
 *	2017.08.31		이중우		메인메뉴 시작
 * </pre>		
 * <pre>
 *  해야할것
 *    : 프로토타이핑툴
 * </pre>
 */
public class ViewClass {
	private Service service = new ServiceImpl();

	void startMethod() {
		System.out.println("타요 버스에 오신것을 환영합니다.");
		System.out.println("원하는 메뉴를 입력하세요.");
		System.out.println("1 : 로그인하기 (login)");
		System.out.println("2 : 회원가입하기 (regi)");
		System.out.print("입력칸");
		String input = service.input();
		
		if (input.equals("1")||input.equals("로그인")||input.equals("login")) {
			memberMenu();
		}else if (input.equals("2")||input.equals("회원가입")||input.equals("regi")){
			
		}

	}
	//-----------------------View에 관한 메서드----------------------
	
	//회원메뉴
	private void memberMenu() {
		System.out.println("버스!");
		System.out.println("1 : 버스 예매하기 (영어?)");
		System.out.println("2 : 예매확인 및 취소 (영어?)");
		System.out.println("3 : 충전/잔고 (영어?)");
		System.out.println("써! ");
		service.input();
	};
		
	//입력
		
	//출력
		
	//버스예매메뉴
		
	//가입메뉴(회원 가입 메세지 뷰)
	
		
	//관리자메뉴
	private void adminMenu() {
		System.out.println("아 무 렇 게 나 !");
		System.out.println("1 : 회원관리 (영어?)");
		System.out.println("2 : 노선관리 (영어?)");
		System.out.println("3 : 정산 (영어?)");
		System.out.println("써! ");
	}
}
