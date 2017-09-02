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
 * @author 현우석, 이중우, 김수환
 * @since 2017.08.28.
 * @version 1.0
 * @see <pre>
 * << 개정이력(Modification Information) >>
 *    수정일       
 *    -------      -------    -------------------
 *    2017.08.29.   현우석      최초생성
 * Copyright (c) 2017 by DDIT  All right reserved
 * </pre>
 */
public class ServiceImpl implements Service {
   Database db = new Database();


   // 회원추가
   @Override
   public boolean joinMb(Map<String, String> member) {


      return db.createMember(member);
   }

   // 회원삭제
   @Override
   public boolean delMb(int id) {

      return db.deleteMember(id);
   }

   // 회원가입시 아이디 중복체크
   @Override
   public boolean checkJoinId(String menberid) {

      return db.idCheck(menberid);
   }

   // 아이디체크
   // 아이디체크
   @Override
    public int loginCheck(Map<String, String> login){
      
      if (db.readIdPwFromDB(login).get("userId").equals(login.get("id"))) { // 회원

         if (!db.readIdPwFromDB(login).get("userPw").equals(login.get("pw"))) {
            return -1;//비밀번호틀림
         }else{
            if(db.readIdPwFromDB(login).get("isAdmin").equals("true")){
               return -2;//관리자 로그인
               
            }else{
               return Integer.parseInt(db.readIdPwFromDB(login).get("id"));//일반 로그인완료
            }
         }
      }
      
      return -3;//아이디 없음.      
   }

   // 버스추가 미완
   @Override
   public boolean addBus(Map<String, String> busAdd) {

      return db.createBus(busAdd);// ;
   }

   // 버스삭제
   @Override
   public boolean removeBus(int id) {
      
      return db.deleteBus(id);
   }

//   @Override
//   public boolean changeBus(String oldid, Map<String, String> businpo) {
//      
//      
//      
//      return false;
//
//   }

   // 충전
   @Override
   public int chargeMoney(int id, int money) {

      return 0;
   }

   // 티켓구입 = 티켓 생성
   @Override
   public boolean payBusTicket(Map<String, String> ticket) {

      return false;// db.createTicket(ticket);
   }
   
   // 티켓구입 = 티켓 생성
   @Override
   public boolean deleteTicket(int ticketid) {

      return db.deleteTicket(ticketid);// db.createTicket(ticket);
   }

   // 환불
   @Override
   public boolean refundTicket(int loginid, int tinput) {
      List<TicketVO> tl = db.getTkList(loginid);
      for (int i = 0; i < tl.size(); i++) {
         if (tl.get(i).getMemId() == tinput) {
            return db.deleteTicket(tinput);

         }

      }
      return false;
   }

   // 버스리스트
   public boolean busList() {
      // busVosize
      if (db.getListSize("bus") == 0) {
         return false;
      } else {
         for (int i = 0; i < db.getListSize("bus"); i++) {
            System.out.println(db.getBsList(i));
         }
         return true;
      }
   }

   // 회원 맴버리스트
   @Override
   public boolean memberList() {
      if (db.getListSize("member") == 0) {
         return false;
      } else {
         for (int i = 0; i < db.getListSize("member"); i++) {

            System.out.println(db.getMbList(i));

         }

         return true;
      }
   }

   //티켓리스트
   @Override
   public List<TicketVO> ticketList(int loginid) {
      
      return db.getTkList(loginid);
   }
   
   
   
   
   //버스종류 자리 리스트
   @Override
   public boolean busKind(String kindbus) {
      
      
      return false;
   }
   
   //총금액
   @Override
   public boolean allPayMoney(){
      
      
      
      return false;      
   }
}