package kr.or.ddit;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import kr.or.ddit.service.Service;
import kr.or.ddit.service.ServiceImpl;

/**
 * @Class Name : ViewClass.java
 * @Description 콘솔 화면에 보여지는 프론트엔드 입출력을 담당
 * @author	이중우
 * @since 2017-08-30
 * @version 0.1
 * @see
 * 
 *<pre>
 *		수정일			수정자		수정내용      
 *   -------        	-------		-------------------            
 *	2017.08.30			이중우		메인메뉴 시작
 *	2017.08.31			이중우		회원메뉴 작성
 *	2017.09.01			이중우		로그인 구간 체크
 *	2017.09.02			이중우		관리자메뉴
 *	2017.09.03			이중우		세부메뉴 기능 구현
 *	2017.09.04			김수환		노가다
 *Copyright (c) 2017 by DDIT  All right reserved
 * </pre>
 */
public class ViewClass {
	Pattern p = Pattern.compile("^[0-9a-z-A-Z]+$");								// 아이디 비번 확인용
	Pattern p2 = Pattern.compile("^[가-힣a-zA-Z]+$");								// 이름 확인용 정규식

	private Service service = new ServiceImpl();
	Scanner sc = new Scanner(System.in);

	int session = -1;															// 로그인 상태. -1 = 비회원

	// 메인메뉴
	void startMenu() {
		while (true) {
		    clear();															//화면정리
			System.out.println("┏━━━━타요 버스에 오신것을 환영합니다.━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
			System.out.println("┃");
			System.out.println("┃\t1 : 로그인");
			System.out.println("┃\t2 : 회원가입");
			System.out.println("┃");
			System.out.print("┗━━━━━━━━원하는 메뉴의 숫자를 입력하세요 : ");
			String input = sc.next();
			switch (input) {
			case "1":
			case "로그인":
				login();
				break;
			case "2":
			case "회원가입":
				join();
				break;
			default:
				System.out.println("잘못된 입력입니다.");
				continue;
			}
		}
	}

	//회원가입
	public void join() {
		Map<String, String> join = new HashMap<String, String>();				//입력은 맵타입으로 담아 인자값으로 전달

	    clear();																//화면정리
		System.out.println("┏━━━━회원가입━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
		String userId;

		do {
			System.out.println("┃\t");
			System.out.print("┣━━━━아이디 : ");
			userId = sc.next();
			Matcher m1 = p.matcher(userId);

			if (m1.find()) {
				if (!service.checkJoinId(userId)) {								// 아이디 중복일때
					System.out.print("┃\tid중복입니다. 다시 아이디를 입력해주세요");
				} else {
					join.put("userId", userId);// true;
					break;
				}
			} else {
				System.out.println("┃\t잘못된 문자를 입력하셨습니다.");
			}

		} while (true);

		// 비밀번호 입력구간 시작
		String userPw;
		String userPwChk = null;
		boolean pCheck = true;													// 비밀번호 중복체크			
		do {
			System.out.println("┃\t");
			System.out.print("┣━━━━비밀번호 : ");
			userPw = sc.next();
			Matcher m2 = p.matcher(userPw);

			if (m2.find()) {
				System.out.println("┃");
				System.out.print("┣━━━━암호확인 : ");
				userPwChk = sc.next();
			} else {
				System.out.println("┃\t잘못된 문자를 입력하셨습니다.");
			}
			pCheck = !userPw.equals(userPwChk);

		} while (pCheck);
		join.put("userPw", userPw);
		// 비밀번호 입력구간 끝

		System.out.println("┃");
		System.out.print("┗━━━━이름 : ");
		String name = sc.next();
		Matcher m3 = p2.matcher(name);

		if (m3.find()) {														// 입력한 id가 정규식에 맞을경우 중복확인
			join.put("userName", name);
		} else {
			System.out.println("잘못된 문자를 입력하셨습니다.");
		}

		boolean joinMenu = service.joinMember(join);

		if (joinMenu) {
			System.out.println("회원가입이 완료되었습니다. 환영합니다.");
		} else {
			System.out.println("입력내용을 다시 한번 확인해 주세요.");
		}

	}


	// 로그인메뉴
	public void login() {
		Map<String, String> login = new HashMap<String, String>();

	    clear();																//화면정리
		System.out.println("┏━━━━어서오세요. 아이디를 입력해주세요 ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
		System.out.println("┃");
		System.out.print("┣━━━━아이디 : ");
		String userId = sc.next();
		System.out.println("┃");

		Matcher m1 = p.matcher(userId);

		if (m1.find()) {
			login.put("userId", userId);
		}

		System.out.print("┗━━━━비밀번호 : ");
		String userPw = sc.next();

		Matcher m2 = p.matcher(userPw);

		if (m2.find()) {
			login.put("userPw", userPw);
		}

		session = service.loginCheck(login);
		if (session == -3) {
			System.out.println("없는 아이디입니다.");
		} else if (session == -1) {
			System.out.println("비밀번호가 틀렸습니다.");
		} else if (session == -2) {
			adminMenu();														// 관리자메뉴
		} else {
			memberMenu();														// 회원메뉴
		}
	}

	// 회원메뉴
	public void memberMenu() {
		while (true) {
		    clear();															//화면정리
			System.out.println("┏━━━━회원메뉴━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
			System.out.println("┃");
			System.out.println("┃\t1 : 버스 예매하기");
			System.out.println("┃\t2 : 구매 티켓 확인");
			System.out.println("┃\t3 : 포인트 충전");
			System.out.println("┃\t4 : 로그아웃");
			System.out.println("┃\t");
			System.out.print("┗━━━━━━━━원하는 메뉴를 입력하세요 : ");
			String input = sc.next();

			switch (input) {
			case "1":
				ticketing();													// 버스예매
				break;
			case "2":
				confirmTicket();													// 구매 티켓 확인
				break;
			case "3":
				chargeMoney();													// 충전
				break;
			case "4":
				return;

			default:
				System.out.println("잘못된 입력입니다.");
				continue;
			}
		}
	}

	// 버스예매하기
	public void ticketing() {
		Map<String, String> temp = new HashMap<String, String>();

	    clear();																//화면정리
		System.out.println("┏━━━━버스예매━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
		System.out.println("┃");
		System.out.println("┃\t번호\t노선\t출발시간\t\t버스등급\t가격\t남은 좌석");
		service.showBusList();
		
		System.out.println("┃\t");
		System.out.println("┣━━━━목적지 선택");
		System.out.print("노선에 해당하는 번호를 선택해 주세요.");
		String bsRoute = sc.next();

		System.out.println("┗━━━━좌석 선택 (일반 45, 우등35) :");
		String seat = sc.next();

		temp.put("session", String.valueOf(session));
		temp.put("seat", seat);
		temp.put("bsRoute", bsRoute);

		int payCheck = service.payBusTicket(temp);

		if (payCheck == -1) {
			System.out.println("해당 노선이 존재하지 않습니다");
		} else if (payCheck == -2) {
			System.out.println("좌석이 이미 판매되었습니다.");
		} else if (payCheck == -3) {
			System.out.println("잔액이 부족합니다.");
		} else {
			System.out.println("결제가 완료되었습니다.");
		}

	}

	// 구매티켓확인
	public void confirmTicket() {
		while (true) {
		    clear();															//화면정리
			System.out.println("┏━━━━구매티켓확인  ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
			System.out.println("┃");
			System.out.println("┃\t번호\t노선\t출발시간\t\t구매시간\t\t버스등급\t가격\t좌석 번호");
			service.showTicketList(session);									//로그인한 회원의 구매목록 출력
			System.out.println("┃");
			System.out.println("┣━━━━메뉴");
			System.out.println("┃\t");
			System.out.println("┃\t1 : 티켓 취소하기");
			System.out.println("┃\t2 : 뒤로가기");
			System.out.println("┃\t");
			System.out.print("┗━━━━━━━━원하는 메뉴를 입력하세요 : ");
			String input = sc.next();

			switch (input) {
			case "1":
				System.out.print("취소할 티켓의 번호를 입력해주세요 : ");
				int tiket = sc.nextInt();
				int result = service.refundTicket(session, tiket);
				if (result == -1) {
					System.out.println("해당티켓이 없습니다.");
				} else if (result == -2) {
					System.out.println("해당티켓의 구매자가 아닙니다.");
				} else {
					System.out.println("티켓이 환불되었습니다.");

					System.out.println("고객님께서 현재 보유하신 금액은 "+result+"원 입니다.");
				}
				break;
			case "2":
				return;
			default:
				System.out.println("잘못된 입력입니다.");
				continue;
			}
		}
	}

	// 포인트충전
	public void chargeMoney() {
	    clear();																//화면정리
		System.out.println("┏━━━━포인트 충전━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
		System.out.println("┃");
		System.out.print("┗━━━━선불 결제할 금액을 입력해 주세요 : ");
		int money = 0;
		while (money<=0) {														//양수만 입력 가능. 인출불가
			try {
				money = sc.nextInt();
			} catch (Exception e) {
				System.out.println("너무 과분한 돈입니다.");
			}
			if (money<=0) {
				System.out.println("다시 입력해주세요. 인출은 고객센터로 문의바랍니다.");
			}
		}
		int currentMoney = service.chargeMoney(session, money);					//현재 잔액
		System.out.println("고객님께서 현재 보유하신 금액은 "+currentMoney+"원 입니다.");

	}

	// 관리자메뉴
	public void adminMenu() {
		while (true) {
		    clear();															//화면정리
			System.out.println("┏━━━━어서오세요 관리자님  ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
			System.out.println("┃");
			System.out.println("┃\t1 : 회원관리");
			System.out.println("┃\t2 : 노선관리");
			System.out.println("┃\t3 : 정산");
			System.out.println("┃\t4 : 로그아웃");
			System.out.println("┃\t");
			System.out.print("┗━━━━━━━━원하는 메뉴를 입력하세요 : ");
			String input = sc.next();

			switch (input) {
			case "1":
				manageMember();													// 회원관리 이동
				break;
			case "2":
				manageRoute();													// 노선관리 이동
				break;
			case "3":
				calc();															// 정산 이동
				break;
			case "4":
				return;
			default:
				System.out.println("잘못된 입력입니다.");
				break;
			}
		}

	}

	// 회원관리
	public void manageMember() {
		while(true){

			clear();																//화면정리
			System.out.println("┏━━━━회원관리━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
			System.out.println("┃");
			System.out.println("┃\t번호\tID\t이름\t잔고\t관리자");
			service.showMemberList();
			System.out.println("┃");
			System.out.println("┣━━━━메뉴");
			System.out.println("┃\t");
			System.out.println("┃\t1 : 회원 삭제하기");
			System.out.println("┃\t2 : 뒤로가기");
			System.out.println("┃\t");
			System.out.print("┗━━━━━━━━원하는 메뉴를 입력하세요 : ");
			String input = sc.next();

			switch (input) {
			case "1":
				System.out.print("삭제할 회원의 번호를 입력하세요 : ");
				int delUserIndex = 0;

				try {
					delUserIndex = sc.nextInt();
				} catch (Exception e) {
					System.out.println("숫자만 입력해 주세요.");
				}
				boolean UserCheck = service.deleteMember(delUserIndex);

				if (UserCheck) {
					System.out.println("삭제에 성공하셨습니다.");
				} else {
					System.out.println("다시한번 확인해 주세요.");
				}
				break;
			case "2":
				return;
			default:
				System.out.println("잘못된 입력입니다.");
				continue;
			}
		}
	}

	//노선관리메뉴
	public void manageRoute() {
		while (true) {
		    clear();															//화면정리
			System.out.println("┏━━━━노선관리━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
			System.out.println("┃");
			System.out.println("┃\t번호\t노선\t출발시간\t\t버스등급\t가격\t좌석");
			service.showBusList();
			System.out.println("┃");
			System.out.println("┣━━━━메뉴");
			System.out.println("┃");
			System.out.println("┃\t1 : 노선추가");
			System.out.println("┃\t2 : 노선삭제");
			System.out.println("┃\t3 : 노선변경");
			System.out.println("┃\t4 : 이전메뉴");
			System.out.println("┃");
			System.out.print("┗━━━━━━━━원하는 메뉴를 입력하세요 : ");

			String input = sc.next();
			switch (input) {
			case "1":
				addBus("추가"); // 노선추가
				break;
			case "2":
				removeBus("삭제"); // 노선삭제
				break;
			case "3":
				reBus(); // 노선변경
				break;
			case "4":
				return;

			default:
				System.out.println("잘못된 입력입니다.");
				continue;
			}
		}
	}

	//버스 추가-변경
	public void addBus(String methodKind) {										//코드 재사용. 매개변수로 추가-변경 
		Map<String, String> temp = new HashMap<String, String>();

	    clear();																//화면정리
		System.out.println("┏━━━━노선" + methodKind+"━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
		System.out.println("┃");
		System.out.print("┣━━━━새로 운행할 노선을 입력해주세요 [출발지-도착지] : ");
		String bsRoute = sc.next();
		System.out.println("┃");
		System.out.print("┣━━━━가격을 입력해주세요 : ");
		String bsPrice = sc.next();
		System.out.println("┃");
		System.out.print("┣━━━━버스의 종류를 입력해주세요 : ");
		String bsKind = sc.next();
		System.out.println("┃");
		System.out.print("┗━━━━운행 횟수를 입력해주세요 : ");
		String numberOfService = sc.next();

		temp.put("bsRoute", bsRoute);
		temp.put("bsPrice", bsPrice);
		temp.put("bsKind", bsKind);
		temp.put("numberService", numberOfService);

		boolean busCheck = service.addBus(temp);

		if (busCheck) {
			System.out.println(methodKind +" 되었습니다.");
		} else {
			System.out.println("다시한번 확인해 주세요.");
		}
	}

	//버스 삭제-변경
	public void removeBus(String methodKind) {									//코드 재사용. 매개변수로 삭제-변경 
	    clear();																//화면정리
		System.out.println("┏━━━━노선"+methodKind+"━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
		System.out.println("┃");
		System.out.println("┃\t번호\t노선\t출발시간\t\t버스등급\t가격\t좌석");
		service.showBusList();
		System.out.println("┃");
		System.out.println("┗━━━━"+methodKind + "할 노선의 번호를 입력해주세요");
		int removeBusIndex = 0;

		try {
			removeBusIndex = sc.nextInt();
		} catch (Exception e) {
			System.out.println("숫자만 입력해 주세요.");
		}

		boolean busCheck = service.removeBus(removeBusIndex);

		if (busCheck) {
			System.out.println(removeBusIndex+"번을 "+methodKind+"합니다.");
		} else {
			System.out.println("다시한번 확인해 주세요.");
		}
	}
	
	//버스 변경
	public void reBus() {
		removeBus("변경");														// 노선삭제
		addBus("변경");															// 노선추가
	}
	
	//정산
	public void calc() {
	    clear();																//화면정리
		System.out.println("┏━━━━ 정산   ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
		System.out.println("┃");
		System.out.println("┃\t번호\t노선\t출발시간\t\t구매시간\t\t버스등급\t좌석\t가격\t구매자");
		service.showTotalTicketList();											//모든 티켓 열람
		int sum = service.calcTotal();											//티켓 총 판매금액
		System.out.println("┃");
		System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\t합계\t"+service.calcTotal()+"원");
	}
	
	/**
	 * 회원DB-회원목록 [관리자모드]
	 * @param 회원index
	 * @return 멤버리스트
	 * <pre>
	 * 관리자모드에서 사용할 회원목록, 회원index를 받아 해당회원 정보를 출력합니다.
	 * </pre>
	 */
	public void clear(){
		for (int i = 0; i < 2; i++)	System.out.println("");   
	}

}