package kr.or.ddit.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.vo.BusVO;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.TicketVO;

/**
 * @Class Name : Service.java
 * @Description :
 * @Modification Information
 * @author 현우석, 이중우, 김수환
 * @since 2017.08.28.
 * @version 1.0
 * @see <pre>
 * << 개정이력(Modification Information) >>
 *    수정일       
 *    -------      -------    -------------------
 *    2017.08.29.   이중우      최초생성
 * Copyright (c) 2017 by DDIT  All right reserved
 * </pre>
 */

public interface Service {// 대략적인 기능
   // -----------------------CRUD에 관한 메서드---------------------
   /**
    * 회원 가입 회원 가입에 필요한 id pwd 이름 생성해준다.
    * 
    * @param Map<s,s> member
    * @return boolean
    */
   public boolean joinMb(Map<String, String> member);

   /**
    * 회원 삭제 List에서 id와 일치하는 것을 찾아 remove해준다.
    * 
    * @param id
    * @return boolean
    */
   public boolean delMb(int id);

   /**
    * 관리자:회원리스트
    * 
    * @return boolean
    */
   public boolean memberList();

   /**
    * 관리자:버스리스트
    * 관리자페이지에서 버스 리스트를 보여준다. 
    * @return boolean
    */
   public boolean busList();

   /**
    * 회원가입
    * 아이디 중복체크
    * @param 입력된   멤버id
    * @return true(적합) / false(부적합)
    */
   public boolean checkJoinId(String menberid);

   /**
    * 노선 추가 
    * 버스의 노선을 추가해준다.
    * @param busadd
    * @return boolean
    */
   public boolean addBus(Map<String, String> busadd);

   /**
    * 노선삭제 
    * 버스의 id값을 받아와 삭제한다.
    * @param id
    * @return boolean
    */
   public boolean removeBus(int id);

   /**
    * 로그인체크 id와 pwd와 관리자인지 체크해준다.
    * @param 입력한 login의 정보
    * @return int 0 : 일반회원로그인 -1 : 비밀번호틀림 -2 : 관리자로그인 -3 : 아이디 없음
    */
   public int loginCheck(Map<String, String> login);

   /**
    * 구매목록 조회 로그인된 아이디의 구매리스트를 반환
    * 
    * @param 로그인된  회원의 인덱스값
    * @return boolean
    */
   public boolean ticketList(int loginid);

   /**
    * 충전 
    * 로그인된 유저 아이디에 돈을 충전해준다.
    * @param 유저 id 금액 money
    * @return int money금액
    */
   public int chargeMoney(int id, int money);

   /**
    * 티켓 생성 및 결제
    * 
    * @param Map<s,s> ticket          
    * @return int value 구입후잔액
     *            -1   해당 노선이 존재하지 않습니다
     *            -2   좌석이 이미 판매되었습니다.
     *            -3   잔액이 부족합니다.
    */
   public int payBusTicket(Map<String, String> ticket);

   /**
    * 환불메서드 회원이 가지고있는 티켓이면 환불 아니면 실패
    * 
    * @param 로그인된  회원loginid, 티켓 tinput
    * @return int -1 티켓이 없다. -2 구매자가 아니다.
    */
   public int refundTicket(int loginid, int tinput);

   /**
    * 총 판매 금액 판
    * 
    * @param
    * @return
    */
   public int allPayMoney();

   boolean totalTicketList();

   

   // ----------------------시간 관리에 관한 메서드---------------------
   // 구입시간

   // 출발시간(만료시간은 여기에 해당) = 프로그램 실행하는 현재시간 + 버스시간간격

   // -----------------------View에 관한 메서드----------------------

   // 메인메뉴

   // 입력

   // 출력

   // 회원메뉴

   // 버스예매메뉴

   // 가입메뉴(회원 가입 메세지 뷰)

   // 관리자메뉴
}   