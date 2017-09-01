package kr.or.ddit;

import java.util.Scanner;

import kr.or.ddit.service.Service;
import kr.or.ddit.service.ServiceImpl;
import kr.or.ddit.vo.MemberVO;

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

	Scanner sc = new Scanner(System.in);
	// 정규식은 뷰에서 !!!!!!
	// 메인메뉴
	void startMethod() {
		while(true){
			System.out.println("타요 버스에 오신것을 환영합니다.");
			System.out.println("1 : 로그인");
			System.out.println("2 : 회원가입");
			System.out.println("원하는 메뉴의 숫자를 입력하세요 ");

			//try-catch
			String input = sc.next();

			switch (input) {
			case "1":	case "로그인":
				login();
				break;
			case "2":	case "회원가입":
				joinMenu();
				break;
			default:
				System.out.println("잘못된 입력입니다.");
				continue;
			}
		}

	}

	public void joinMenu(){
		MemberVO join = new MemberVO();

		System.out.println("=== 회원가입");
		System.out.print("아이디 : ");
		String userId = sc.next();
//		List<MemberVO> checkList = service.memberList(true);
//		for (int i = 0; i < checkList.size(); i++) {
//			if(checkList.get(i).getMbUserId().equals(userId)){
//				return;
//			}
//		}

		if(!(service.checkId(userId))){
			System.out.println("id중복입니다.");
			return;
		}else{
			join.setMbUserId(userId);
		}
		//비밀번호 입력구간 시작
				String userPw;
				String userPwChk;
				do {
					System.out.print("비밀번호 : ");
					userPw = sc.next();

					System.out.print("암호확인 : ");
					userPwChk = sc.next();

				} while (!userPw.equals(userPwChk));
				join.setMbUserPw(userPw);
		//비밀번호 입력구간 끝


		System.out.print("이름 : ");
		join.setMbUserName(sc.next());
		//메인메뉴로 돌아감
	}

	// 관리자용 회원목록보기
		public void memberList(){
			System.out.println("회원목록을 표시합니다.");
			System.out.println(service.memberList(true)); 	//무조건 관리자  

		}

	//로그인메뉴
	public void login(){
		System.out.println("환영합니다.");
		System.out.print("아이디 :");
		String userId = sc.next();

		System.out.print("비번 : ");
		String userPw = sc.next();

		service.loginCheck(userId,userPw);

		memberMenu();
		//아이디가 존재 할경우 >> 회원메뉴 이동
	}

	//회원메뉴
	public void memberMenu(){
		System.out.println("=== 회원메뉴");
		System.out.println("1 : 버스 예매하기");
		System.out.println("2 : 예매확인 및 취소");
		System.out.println("3 : 충전/잔고");
		System.out.println("4 : 로그아웃");
		System.out.println("원하는 메뉴를 입력하세요 ");
		String input = sc.next();

		switch (input) {
		case "1":
			//버스예매 이동
			break;
		case "2":
			//예매확인 및 취소 이동
			break;
		case "3":
			//충전/잔고 이동
			break;
		case "4":
			//메인메뉴 이동
			break;

		default:
			System.out.println("잘못된 입력입니다.");
			break;
		}
	}

	//버스예매하기
	public void ticketing(){ 
		System.out.println("=== 버스예매");
		//목적지 선택
		//버스등급 선택
		//좌석 선택

	}
	public void ticket(){
		System.out.println("=== 목적지 선택");
	}

	public void seletBus(){
		System.out.println("=== 버스등급 선택");

	}

	public void seat(){
		System.out.println("=== 좌석 선택");

	}
	//예매확인 및 취소
	public void confirmBus(){
		System.out.println("===예매확인 및 취소");
		//예매한 버스에 대한 뷰?
		System.out.println("취소할 좌석을 선택해주세요 : ");
	}

	public void ticketBus(){
		System.out.println("=== 예매한티켓");
	}

	//충전,잔고
	public void chargeMoney(){
		System.out.println("=== 충전/잔고");
		//잔액에대한 뷰?
		//충전
	}


	//관리자메뉴
	public void adminMenu(){
		System.out.println("현우석");
		System.out.println("1 : 회원관리");
		System.out.println("2 : 노선관리");
		System.out.println("3 : 정산");
		System.out.println("4 : 로그아웃");
		System.out.println("원하는 메뉴를 입력하세요 : ");
		String input = sc.next();

		switch (input) {
		case "1":
			//회원관리 이동
			break;
		case "2":
			//노선관리 이동
			break;
		case "3":
			//정산 이동
			break;
		case "4":
			//로그아웃 이동
			break;

		default:
			System.out.println("잘못된 입력입니다.");
			break;
		}

	}

	public void   managementUser(){
		System.out.println("=== 회원관리");
		//회원목록에대한 뷰?
		System.out.println("삭제할 회원을 입력하세요 : ");
		String delUser = sc.next();
	}

	public void managementRoute(){
		System.out.println("=== 노선관리");
		System.out.println("1 : 노선추가");
		System.out.println("2 : 노선삭제");
		System.out.println("3 : 노선변경");
		System.out.println("원하는 메뉴를 입력하세요 : ");

		//	      String input = service.input();
		String input = sc.next();
		switch (input) {
		case "1":
			//노선추가 이동
			break;
		case "2":
			//노선삭제 이동
			break;
		case "3":
			//노선변경 이동
			break;

		default:
			System.out.println("잘못된 입력입니다.");
			break;
		}
	}

	public void addBus(){
		System.out.println("=== 노선추가");
	}

	public void delBus(){
		System.out.println("=== 노선삭제");
	}

	public void reBus(){
		System.out.println("=== 노선변경");
	}

	public void calc(){
		System.out.println("=== 정산");
		//정산된 뷰
	}

}
