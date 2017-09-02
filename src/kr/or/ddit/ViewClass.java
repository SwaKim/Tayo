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
 * @Description 고속버스 예매 시스템
 * @author
 * @since 2017-08-31
 * @version 0.1
 * @see
 * 
 *      <pre>
 *   수정일         수정자      수정내용      
 *   -------         -------      -------------------            
 *   2017.08.31      이중우      메인메뉴 시작
 * </pre>
 * 
 *      <pre>
 *  해야할것
 *    : 프로토타이핑툴
 * </pre>
 */
public class ViewClass {
   Pattern p = Pattern.compile("^[0-9a-z-A-Z]+$"); // 아이디 비번 확인용
   Pattern p2 = Pattern.compile("^[가-힣a-zA-Z]+$"); // 이름 확인용 정규식
   Pattern p3 = Pattern.compile("^[1-3]+$"); // 버스 시간 선택용

   private Service service = new ServiceImpl();
   Scanner sc = new Scanner(System.in);

   int session = -1;// 로그인 안된상태

   // 정규식은 뷰에서 !!!!!!
   // 메인메뉴
   void startMethod() {
      while (true) {
         System.out.println("타요 버스에 오신것을 환영합니다.");
         System.out.println("1 : 로그인");
         System.out.println("2 : 회원가입");
         System.out.println("원하는 메뉴의 숫자를 입력하세요 ");
         String input = sc.next();

         switch (input) {
         case "1":
         case "로그인":
            login();
            break;
         case "2":
         case "회원가입":
            joinMenu();
            break;
         default:
            System.out.println("잘못된 입력입니다.");
            continue;
         }
      }
   }

   public void joinMenu() {
      Map<String, String> join = new HashMap<String, String>();

      System.out.println("=== 회원가입");
      String userId;

      do {
         System.out.print("아이디 : ");
         userId = sc.next();
         Matcher m1 = p.matcher(userId);

         if (m1.find()) {
            if (!service.checkJoinId(userId)) { // 아이디 중복일때
               System.out.print("id중복입니다. 다시 아이디를 입력해주세요\n아이디 : ");
               userId = sc.next();
            } else {
               join.put("userId", userId);// true;
               break;
            }
         } else {
            System.out.println("잘못된 문자를 입력하셨습니다.");
         }

      } while (true);

      // 비밀번호 입력구간 시작
      String userPw;
      String userPwChk = null;
      boolean pCheck = true;
      do {
         System.out.print("비밀번호 : ");
         userPw = sc.next();
         Matcher m2 = p.matcher(userPw);

         if (m2.find()) {
            System.out.print("암호확인 : ");
            userPwChk = sc.next();
         } else {
            System.out.println("잘못된 문자를 입력하셨습니다.");
         }
         pCheck = !userPw.equals(userPwChk);

      } while (pCheck);
      join.put("userPw", userPw);
      // 비밀번호 입력구간 끝

      System.out.print("이름 : ");
      String name = sc.next();
      Matcher m3 = p2.matcher(name);

      if (m3.find()) { // 입력한 id가 정규식에 맞을경우 중복확인
         join.put("userName", name);
      } else {
         System.out.println("잘못된 문자를 입력하셨습니다.");
      }

      boolean joinMenu = service.joinMb(join);

      if (joinMenu) {
         System.out.println("회원가입을 환영합니다.");
      } else {
         System.out.println("다시한번 확인해 주세요.");
      }

   }

   /*
    * // 관리자용 회원목록보기 public void memberList() {
    * System.out.println("회원목록을 표시합니다.");
    * System.out.println(service.memberList()); // 무조건 관리자
    * 
    * }
    */

   // 로그인메뉴
   public void login() {
      Map<String, String> login = new HashMap<String, String>();

      System.out.println("환영합니다.");
      System.out.print("아이디 :");
      String userId = sc.next();

      Matcher m1 = p.matcher(userId);

      if (m1.find()) {
         login.put("userId", userId);
      }

      System.out.print("비번 : ");
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
         adminMenu(); // 관리자메뉴
      } else {
         memberMenu(); // 회원메뉴
      }
   }

   // 회원메뉴
   public void memberMenu() {
      while (true) {
         System.out.println("=== 회원메뉴");
         System.out.println("1 : 버스 예매하기");
         System.out.println("2 : 예매확인 및 취소");
         System.out.println("3 : 충전/잔고");
         System.out.println("4 : 로그아웃");
         System.out.println("원하는 메뉴를 입력하세요 ");
         String input = sc.next();

         switch (input) {
         case "1":
            ticketing(); // 버스예매
            break;
         case "2":
            confirmBus(); // 예매확인 및 취소
            break;
         case "3":
            chargeMoney(); // 충전/잔고 이동
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

      System.out.println("=== 버스예매");
      System.out.println("노선정보");
      System.out.println("번호\t노선\t출발시간\t\t버스등급\t가격\t좌석");
      service.busList();

      System.out.println("=== 목적지 선택");
      System.out.print("노선에 해당하는 번호를 선택해 주세요.");
      String bsRoute = sc.next();

      System.out.println("=== 좌석 선택");
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

   // 예매확인 및 취소
   public void confirmBus() {
      System.out.println("=== 예매확인 및 취소");
      System.out.println("번호\t노선\t출발시간\t\t구매시간\t\t버스등급\t좌석\t가격");
      service.ticketList(session); // ? ? 볼수가 없다.
      System.out.print("취소할 티켓을 선택해주세요 : ");
      int tiket = sc.nextInt();
      service.refundTicket(session, tiket);
      if (tiket == 0) {
         System.out.println("티켓이 환불되었습니다.");
      } else if (tiket == -1) {
         System.out.println("해당티켓이 없습니다.");
      } else if (tiket == -2) {
         System.out.println("해당티켓의 구매자가 아닙니다.");
      }
   }

   // 충전,잔고
   public void chargeMoney() {
      System.out.println("=== 충전/잔고");
      System.out.print("충전하실 금액을 입력해 주세요 : ");
      int money = 0;
      try {
         money = sc.nextInt();
      } catch (Exception e) {
         System.out.println("너무 과분한 돈입니다.");
      }
      System.out.println("=== 잔고");
      int money2 = service.chargeMoney(session, money);
      System.out.println(money2);

   }

   // 관리자메뉴
   public void adminMenu() {
      while (true) {
         // System.out.println("현우석");
         System.out.println("1 : 회원관리");
         System.out.println("2 : 노선관리");
         System.out.println("3 : 정산");
         System.out.println("4 : 로그아웃");
         System.out.println("원하는 메뉴를 입력하세요 : ");
         String input = sc.next();

         switch (input) {
         case "1":
            managementUser(); // 회원관리 이동
            break;
         case "2":
            managementRoute(); // 노선관리 이동
            break;
         case "3":
            calc(); // 정산 이동
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
   public void managementUser() {
      System.out.println("=== 회원관리");
      System.out.println("번호\tID\t이름\t잔고\t관리자");
      service.memberList();
      System.out.println("삭제할 회원의 번호를 입력하세요 : ");
      int delUserIndex = 0;

      try {
         delUserIndex = sc.nextInt();
      } catch (Exception e) {
         System.out.println("숫자만 입력해 주세요.");
      }
      boolean UserCheck = service.delMb(delUserIndex);

      if (UserCheck) {
         System.out.println("삭제에 성공하셨습니다.");
      } else {
         System.out.println("다시한번 확인해 주세요.");
      }
   }

   public void managementRoute() {
      while (true) {
         System.out.println("=== 노선관리");
         System.out.println("번호\t노선\t출발시간\t\t버스등급\t가격\t좌석");
         service.busList();
         System.out
               .println("==============================================");
         System.out.println("1 : 노선추가");
         System.out.println("2 : 노선삭제");
         System.out.println("3 : 노선변경");
         System.out.println("4 : 이전메뉴");
         System.out.println("원하는 메뉴를 입력하세요 : ");

         String input = sc.next();
         switch (input) {
         case "1":
            addBus("추가"); // 노선추가
            break;
         case "2":
            delBus("삭제"); // 노선삭제
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

   public void addBus(String method) {
      Map<String, String> temp = new HashMap<String, String>();

      System.out.println("=== 노선" + method);
      System.out.print(method + "노선을 입력해주세요 [출발지-도착지] : ");
      String bsRoute = sc.next();
      System.out.print("가격을 입력해주세요 : ");
      String bsPrice = sc.next();
      System.out.print("종류를 입력해주세요 : ");
      String bsKind = sc.next();
      System.out.print("운행 횟수를 입력해주세요 : ");
      String numberService = sc.next();

      temp.put("bsRoute", bsRoute);
      temp.put("bsPrice", bsPrice);
      temp.put("bsKind", bsKind);
      temp.put("numberService", numberService);

      boolean busCheck = service.addBus(temp);

      if (busCheck) {
         System.out.println(method + " 성공하셨습니다.");
      } else {
         System.out.println("다시한번 확인해 주세요.");
      }
   }

   public void delBus(String method) {
      System.out.println("=== 노선삭제");
      System.out.println("번호\t노선\t출발시간\t\t버스등급\t가격\t좌석");
      service.busList();
      System.out.println("==============================================");
      System.out.println(method + "할 노선을 선택해주세요");
      int removeBusIndex = 0;

      try {
         removeBusIndex = sc.nextInt();
      } catch (Exception e) {
         System.out.println("숫자만 입력해 주세요.");
      }

      boolean busCheck = service.removeBus(removeBusIndex);

      if (busCheck) {
         System.out.println(method + " 성공하셨습니다.");
      } else {
         System.out.println("다시한번 확인해 주세요.");
      }
   }

   public void reBus() {
      System.out.println("=== 노선변경");
      delBus("삭제"); // 노선삭제
      addBus("변경"); // 노선추가
   }

    public void calc() {
         System.out.println("=== 정산");
         System.out.println("번호\t노선\t출발시간\t\t구매시간\t\t버스등급\t좌석\t가격");
         service.totalTicketList();
         int sum = service.allPayMoney();   // 타입이든 뭐든 수정해야해요 !!
         System.out.println("\t\t\t\t\t\t\t\t"+service.allPayMoney());
      }

}