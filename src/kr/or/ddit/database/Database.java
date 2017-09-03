package kr.or.ddit.database;

import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kr.or.ddit.vo.BusVO;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.TicketVO;

/**
 * @Class Name : Database.java
 * @Description 데이터 조회 목적
 * @author	김수환
 * @since 2017-08-30
 * @version 1.1
 * @see
 * 
 *<pre>
 *		수정일			수정자		수정내용      
 *   -------        	-------		-------------------            
 *   2017.08.30     	김수환		List에 각VO 세팅
 *   2017.08.31			김수환		VO는 DB에서만 관리(Private)
 *   2017.09.01			김수환		로그인 구간 체크
 *   2017.09.02			김수환		관리자메뉴
 *   2017.09.03			김수환		세부메뉴 기능 구현
 * Copyright (c) 2017 by DDIT  All right reserved
 *</pre>
 */
public class Database {
	SimpleDateFormat df = new SimpleDateFormat("MM.dd hh:mm");
	
	private List<MemberVO> mbList = new ArrayList<MemberVO>();
	private List<BusVO> bsList = new ArrayList<BusVO>();
	private List<TicketVO> tkList = new ArrayList<TicketVO>();

	private int mbIndex = 0;
	private int bsIndex = 0;
	private int tkIndex = 0;

	//테스트용 선기입 데이터, 실제 운용시 삭제
	public Database() {
		BusVO bs = new BusVO(bsIndex++, "대전-서울", "9000", df.format(new Date().getTime() + ( (long)1000*60*60 * 0 )), new int[45], "우등");
		bsList.add(bs);
		BusVO bs1 = new BusVO(bsIndex++, "대전-서울", "5000", df.format(new Date().getTime() + ( (long)1000*60*60 * 1 )), new int[35], "일반");
		bsList.add(bs1);
		BusVO bs2 = new BusVO(bsIndex++, "서울-대전", "9000", df.format(new Date().getTime() + ( (long)1000*60*60 * 2 )), new int[45], "우등");
		bsList.add(bs2);
		BusVO bs3 = new BusVO(bsIndex++, "서울-대전", "5000", df.format(new Date().getTime() + ( (long)1000*60*60 * 3 )), new int[35], "일반");
		bsList.add(bs3);
		BusVO bs4 = new BusVO(bsIndex++, "나주-서울", "9000", df.format(new Date().getTime() + ( (long)1000*60*60 * 4 )), new int[45], "우등");
		bsList.add(bs4);
		BusVO bs5 = new BusVO(bsIndex++, "서울-나주", "5000", df.format(new Date().getTime() + ( (long)1000*60*60 * 5 )), new int[35], "일반");
		bsList.add(bs5);

		MemberVO mb = new MemberVO(mbIndex++, true, "admin", "admin", "관리자", 100000);
		mbList.add(mb);

		TicketVO tk = new TicketVO(tkIndex++, df.format(new Date().getTime()), true, 1, 1, 21);
		tkList.add(tk);
		TicketVO tk2 = new TicketVO(tkIndex++, df.format(new Date().getTime() - ( (long)1000*60*60 * 1 )), true, 1, 2, 20);
		tkList.add(tk2);
		TicketVO tk3 = new TicketVO(tkIndex++, df.format(new Date().getTime() - ( (long)1000*60*60 * 2 )), true, 1, 3, 11);
		tkList.add(tk3);
	}

	/**
	 * 회원DB-회원목록 [관리자모드]
	 * @param 회원index
	 * @return 멤버리스트
	 * <pre>
	 * 관리자모드에서 사용할 회원목록, 회원index를 받아 해당회원 정보를 출력합니다.
	 * </pre>
	 */
	public String getMemberList(int index) {
		String toString = "┃\t"+mbList.get(index).getId() + "\t" + mbList.get(index).getMbUserId() + "\t"
				+ mbList.get(index).getMbUserName() + "\t" + mbList.get(index).getMbUserMoney() + "\t"
				+ mbList.get(index).isAdmin();
		return toString;
	}

	/**
	 * 버스,멤버DB-목록 회원, 관리자메뉴
	 * 
	 * @return 버스리스트
	 */
	public int getListSize(String kind) {
		switch (kind) {
		case "bus":
			return bsList.size();
		case "member":
			return mbList.size();
		}
		return 0;
	}

	/**
	 * 티켓DB-관리자메뉴 티켓목록
	 * 
	 * @return 버스리스트
	 */
	public int getTicketListSize(int buyId) {
		int size = 0;
		for (int i = 0; i < tkList.size(); i++) {
			if (tkList.get(i).getMemId() == buyId) {
				size++;
			}
		}
		return size;
	}

	/**
	 * 버스DB-버스목록 회원, 관리자메뉴
	 * 
	 * @return 버스리스트
	 */
	public String getBusList(int index) {
		// "번호\t노선\t출발시간\t버스등급\t좌석"'
		String toString = "┃\t"+bsList.get(index).getId() + "\t" + // 번호
				bsList.get(index).getBsRoute() + "\t" + // 노선
				bsList.get(index).getBsDepartureTime() + "\t" + // 출발시간
				bsList.get(index).getBsKind() + "\t" + // 버스등급
				bsList.get(index).getBsPrice() + "\t" + // 가격
				bsList.get(index).getBsSeatlist().length + "\t";// 좌석
		return toString;
	}
/*
	public String getBusList(int busIndex) {

		String toString = "┃\t"+bsList.get(busIndex).getId() + "\t"
				+ bsList.get(busIndex).getBsRoute() + "\t"
				+ bsList.get(busIndex).getBsPrice() + "\t"
				+ bsList.get(busIndex).getBsDepartureTime() + "\t";
		return toString;
	}
*/
	
	/**
	 * 티켓DB-티켓목록 관리자메뉴
	 * 
	 * @return 티켓리스트 여러 문자열이 담긴 Map
	 */
	public Map<Integer, String> getTotalTicketList() {
		Map<Integer, String> result = new HashMap<Integer, String>();
		for (int i = 0; i < tkList.size(); i++) {
			String toString = "┃\t"+tkList.get(i).getId() + "\t" +							// 번호
					bsList.get(tkList.get(i).getBusId()).getBsRoute() + "\t" +			// 노선
					bsList.get(tkList.get(i).getBusId()).getBsDepartureTime() + "\t" +	// 출발시간
					tkList.get(i).getTkBuyTime() + "\t" + 								// 구매시간
					bsList.get(tkList.get(i).getBusId()).getBsKind() + "\t" +			// 버스등급
					tkList.get(i).getSeat() + "\t"+ 									// 좌석
					bsList.get(tkList.get(i).getBusId()).getBsPrice() + "\t";			// 가격
		}


		return result;
	}



	/**
	 * 회원DB-회원추가 생성된 회원객체를 DB에서 찾아, 중복된 ID가 없을시 생성수행
	 * 
	 * @param 회원VO
	 * @return 결과,리스트추가
	 */
	public boolean createMember(Map<String, String> memberInfo) {// 중복체크는 별도의
		// 메서드
		for (int i = 0; i < mbList.size(); i++) {
			if (mbList.get(i).getMbUserId().equals(memberInfo.get("userId"))) {
				return false;
			}
		} // 가입된 회원중 ID가 중복되는 값이 없을 경우
		MemberVO newVO = new MemberVO();

		newVO.setId(mbIndex++);
		; // 인덱스
		newVO.setMbUserId(memberInfo.get("userId")); // 아이디
		newVO.setMbUserPw(memberInfo.get("userPw"));
		; // 비밀번호
		newVO.setMbUserName(memberInfo.get("userName")); // 이름
		return mbList.add(newVO);
	}

	/**
	 * 회원DB-ID중복체크 생성된 회원객체를 DB에서 찾아, 중복된 ID가 없을시 생성수행
	 * 
	 * @param 입력
	 *            회원ID
	 * @return 결과
	 */
	public boolean idCheck(String userId) {
		for (int i = 0; i < mbList.size(); i++) {
			if (mbList.get(i).getMbUserId().equals(userId)) { // 회원 중복 체크
				return false; // 중복이면 가입을 중지
			}
		}
		return true;
	}

	/**
	 * 회원DB-회원삭제 회원인덱스을 DB에서 찾아 삭제수행
	 * 
	 * @param 회원
	 *            인덱스
	 * @return 삭제결과
	 */
	public boolean deleteMember(int mem_id) {
		for (int i = 0; i < mbList.size(); i++) {
			if (mbList.get(i).getId() == mem_id) {
				mbList.remove(i);
				return true;
			}
		}

		return false;
	}

	/**
	 * 버스DB-노선추가 생성된 버스객체를 DB에 저장된 객체와 비교하여, '노선과 종류 모두' 중복되는 항목이 없을시 생성수행
	 * 
	 * @param 버스에
	 *            대한 입력내용을 담고있는 Map
	 * @return 결과,리스트추가
	 */
	public boolean createBus(Map<String, String> busInfo) {
		for (int i = 0; i < bsList.size(); i++) {
			if (bsList.get(i).getBsRoute().equals(busInfo.get("bsRoute"))
					&& bsList.get(i).getBsKind().equals(busInfo.get("bsKind"))) {
				return false;
			}
		} // 노선과 버스종류 모두 중복되는 값이 없을 경우
		if(Integer.parseInt(busInfo.get("numberService"))<=0)return false;	//운행횟수가 0보다 적으면 추가하지 않음
		BusVO newVO = new BusVO();
		for (int i = 0; i < Integer.parseInt(busInfo.get("numberService")); i++) {
			newVO = new BusVO();
			newVO.setId(bsIndex++);																			// 인덱스
			newVO.setBsRoute(busInfo.get("bsRoute"));														// 노선
			newVO.setBsPrice(busInfo.get("bsPrice"));														// 요금
			newVO.setBsDepartureTime(df.format(new Date().getTime() + ( (long) 1000 * 60 * 60 * i )));		// 출발시간
			newVO.setBsKind(busInfo.get("bsKind")); 														// 일반-우등
			if(busInfo.get("bsKind").equals("우등")){															// 좌석
				newVO.setBsSeat(35);
			}else{
				newVO.setBsSeat(35+10);
			};
			if(0<=i && i<Integer.parseInt(busInfo.get("numberService"))-1){
				bsList.add(newVO);
			}
		}
		return bsList.add(newVO);		
	}

	/**
	 * 버스DB-노선삭제 회원인덱스을 DB에 저장된 객체와 비교하여 삭제수행
	 * 
	 * @param 회원인덱스
	 * @return 삭제결과
	 */
	public boolean deleteBus(int bus_id) {
		for (int i = 0; i < bsList.size(); i++) {
			if (bsList.get(i).getId() == bus_id) {
				bsList.remove(i);
				return true;
			}
		}

		return false;
	}

	/**
	 * 총 매출
	 * @return 판매량 총합
	 */
	public int allPayMoney() {
		int sum = 0;
		for (int i = 0; i < tkList.size(); i++) {
			sum += Integer.parseInt(bsList.get(tkList.get(i).getBusId()).getBsPrice());
		}
		return sum;
	}


	/**
	 * 티켓DB-티켓목록 회원메뉴
	 * 전체 티켓목록 중에서 해당 회원이 구입한 리스트를 추려내 반환
	 * @param 구매회원 인덱스
	 * @return 투스트링
	 */
	public String getTkListString(int index) {
		// "번호\t노선\t출발시간\t구매시간\t버스등급\t좌석"'
		String toString = "┃\t"+tkList.get(index).getId() + "\t" +							// 번호
				bsList.get(tkList.get(index).getBusId()).getBsRoute() + "\t" +			// 노선
				bsList.get(tkList.get(index).getBusId()).getBsDepartureTime() + "\t" +	// 출발시간
				tkList.get(index).getTkBuyTime() + "\t" + 								// 구매시간
				bsList.get(tkList.get(index).getBusId()).getBsKind() + "\t" +			// 버스등급
				bsList.get(tkList.get(index).getBusId()).getBsPrice() + "\t" +			// 가격
				tkList.get(index).getSeat() + "\t"; // 좌석
		return toString;
	}

	/**
	 * 티켓DB-구매된 티켓추가 생성된 티켓객체를 DB에 저장된 객체와 비교하여, '노선과 종류, 좌석' 중복되는 항목이 없을시 생성수행
	 * 
	 * @param 버스VO
	 * @return value 구입후잔액, -1 해당 노선이 존재하지 않습니다, -2 좌석이 이미 판매되었습니다, -3	 잔액이 부족합니다.
	 */
	public int createTicket(Map<String, String> ticketInfo) {

		for (int j = 0; j < bsList.size(); j++) {
			if (String.valueOf(bsList.get(j).getId()).equals(ticketInfo.get("bsRoute"))) { // 해당 노선이 있을때
				// 팔린좌석인지 체크하는 반복문
				for (int i = 0; i < tkList.size(); i++) {
					if (tkList.get(i).getBusId() == bsList.get(j).getId() && // 티켓의 외래키(버스id)로 버스정보 얻어오기-
							tkList.get(i).getSeat() == Integer.parseInt(ticketInfo.get("seat"))) { // 팔린좌석인가?
						return -2;	//좌석이 이미 판매되었습니다.
					}
				}// 안팔렸다!
				TicketVO newVO = new TicketVO();

				newVO.setId(bsIndex++);											// 티켓인덱스
				newVO.setMemId(Integer.parseInt(ticketInfo.get("session")));	// 구매자를 외래키를 이용하여 호출
				newVO.setBusId(bsList.get(j).getId());							// 버스정보를 외래키를 이용하여 호출
				newVO.setTkBuyTime(df.format(new Date()));						// 구매시간
				tkList.add(newVO);												// 티켓목록에 새로운 티켓추가
				for (int i = 0; i < mbList.size(); i++) {
					if (mbList.get(i).getId() == Integer.parseInt(ticketInfo.get("session"))) {
						if(mbList.get(i).getMbUserMoney() >= Integer.parseInt(bsList.get(j).getBsPrice())){	//현재 잔액이 결제금액보다 많으면
							mbList.get(i).setMbUserMoney(-Integer.parseInt(bsList.get(j).getBsPrice()));	//결제가능
							return mbList.get(i).getMbUserMoney();											//결제후 남은 금액 반환
						}
					}
				}
				// ;
				return -3;//돈이 부족합니다.
			}
		} // 노선이 없을 경우
		return -1; //해당 노선이 존재하지 않습니다.
	}

	/**
	 * 티켓DB-구매된 티켓삭제 티켓인덱스을 DB에 저장된 객체와 비교하여 해당 티켓 환불수행
	 * 
	 * @param 티켓
	 *            인덱스
	 * @return 환불후잔액, -1 해탕티켓이 존재하지않음, -2 해당티켓의 구매자가 아님
	 */
	public int deleteTicket(int loginId, int ticketId) {
		int price = 0;
		for (int j = 0; j < mbList.size(); j++) {
			if (mbList.get(j).getId() == loginId) {
				for (int i = 0; i < tkList.size(); i++) {
					if (tkList.get(i).getId() == ticketId) {
						tkList.remove(i);
						price = Integer.parseInt(bsList.get(tkList.get(ticketId).getBusId()).getBsPrice());
						mbList.get(j).setMbUserMoney(price);
						return mbList.get(j).getMbUserMoney();
					}
				}
				return -1; // 해당 티켓이 없어서 환불불가
			}
		}
		return -2; // 구매자가 아니라서 환불 불가
	}

	/**
	 * 회원DB-잔액 충전 회원인덱스을 DB에 충전 수행
	 * 
	 * @param 회원
	 *            인덱스
	 * @return 충전후 잔액
	 */
	public int chargeMoney(int id, int addMoney) {
		for (int i = 0; i < mbList.size(); i++) {
			if (mbList.get(i).getId() == id) {
				mbList.get(i).setMbUserMoney(addMoney);
				return mbList.get(i).getMbUserMoney();
			}
		}
		return -1;
	}

	public Map<String, String> readIdPwFromDB(Map<String, String> input) {
		Map<String, String> readData = new HashMap<String, String>();
		for (int i = 0; i < mbList.size(); i++) {
			if (mbList.get(i).getMbUserId().equals(input.get("userId"))) {
				readData.put("id", String.valueOf(mbList.get(i).getId()));
				readData.put("userId", mbList.get(i).getMbUserId());
				readData.put("userPw", mbList.get(i).getMbUserPw());
				readData.put("isAdmin", String.valueOf(mbList.get(i).isAdmin()));
			}
		}
		return readData;
	}

}