package kr.or.ddit.vo;

import java.util.Map;

public class BusVO {
   //어느 VO클래스건 index의 id
   private int id; //기본키
   
   private String bsRoute;   //출발지-도착지
   
   private String bsPrice;   //요금
   
   private String bsDepartureTime;   //출발시간
   
   private int[] bsSeatList;   //좌석정보
   
   private String bsKind;   //일반?우등?


   public BusVO(int id, String bsRoute, String bsPrice,
         String bsDepartureTime, int[] bsSeatList, String bsKind) {
      super();
      this.id = id;
      this.bsRoute = bsRoute;
      this.bsPrice = bsPrice;
      this.bsDepartureTime = bsDepartureTime;
      this.bsSeatList = bsSeatList;
      this.bsKind = bsKind;
   }

   public BusVO() {
      // TODO Auto-generated constructor stub
   }

   public int getId() {
      return id;
   }

   public void setId(int id) {
      this.id = id;
   }

   public String getBsRoute() {
      return bsRoute;
   }

   public void setBsRoute(String bsRoute) {
      this.bsRoute = bsRoute;
   }

   public String getBsPrice() {
      return bsPrice;
   }

   public void setBsPrice(String bsPrice) {
      this.bsPrice = bsPrice;
   }

   public String getBsDepartureTime() {
      return bsDepartureTime;
   }

   public void setBsDepartureTime(String bsDepartureTime) {
      this.bsDepartureTime = bsDepartureTime;
   }

   
   public int[] getBsSeatlist() {
      return bsSeatList;
   }

   public void setBsSeat(int size) {
      int[] seat = new int[size];; 
      for (int i = 0; i < seat.length; i++) {
         seat[i] = i;
      }
      this.bsSeatList = seat; 
   }

   public String getBsKind() {
      return bsKind;
   }

   public void setBsKind(String bsKind) {
      this.bsKind = bsKind;
   }

   @Override
   public String toString() {
      return "BusVO [id=" + id + ", bsRoute=" + bsRoute + ", bsPrice=" + bsPrice + ", bsDepartureTime="
            + bsDepartureTime + ", bsSeat=" + bsSeatList + ", bsKind=" + bsKind + "]";
   }
   
   
}