package kr.or.ddit.database;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kr.or.ddit.vo.BusVO;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.TicketVO;

public class Database {
	private List<MemberVO> mbList = new ArrayList<MemberVO>();
	private List<BusVO> bsList = new ArrayList<BusVO>();
	private List<TicketVO> tkList = new ArrayList<TicketVO>();
	//각 VO기본키 = id
	private int mbIndex = 0;
	private int bsIndex = 0;
	private int tkIndex = 0;
	
	/**회원DB-회원목록
	 * 관리자모드에서 사용할 회원목록 조회
	 *@return 멤버리스트
	 */
	public String getMbList(int index) {
		String toString = mbList.get(index).getId()+"\t"+
				mbList.get(index).getMbUserId()+"\t"+
				mbList.get(index).getMbUserName()+"\t"+
				mbList.get(index).getMbUserMoney()+"\t"+
				mbList.get(index).isAdmin();
		return toString;
	}
	
	public int getListSize(String kind){
		switch (kind) {
		case "bus":
			return bsList.size();
		case "member":
			return mbList.size();
		case "ticket":
			return tkList.size();
		}
		return 0;
	}
	
	/**버스DB-버스목록
	 * 회원, 관리자메뉴
	 *@return 버스리스트
	 */
	public String getBsList(int index) {
		
		String toString = bsList.get(index).getId()+"\t"+
				bsList.get(index).getBsRoute()+"\t"+
				bsList.get(index).getBsPrice()+"\t"+
				bsList.get(index).getBsDepartureTime()+"\t";
		return toString;
	}
	
	/**버스DB-버스좌석
	 * 회원, 관리자메뉴
	 *@return 버스리스트
	 */
	public String getBsSeatList(int busIndex) {
		
		String toString = bsList.get(busIndex).getId()+"\t"+
				bsList.get(busIndex).getBsRoute()+"\t"+
				bsList.get(busIndex).getBsPrice()+"\t"+
				bsList.get(busIndex).getBsDepartureTime()+"\t";
		return toString;
	}
	
	/**티켓DB-티켓목록
	 * 전체 티켓목록 중에서 해당 회원이 구입한 리스트를 추려내 반환
	 *@param 구매회원 인덱스
	 *@return 티켓리스트
	 */
	public List<TicketVO> getTkList(int memId) {
		List<TicketVO> purchasedList = new ArrayList<TicketVO>();
		for (int i = 0; i < tkList.size(); i++) {
			if(tkList.get(i).getMemId() == memId){
				purchasedList.add(tkList.get(i));
			}
		}
		return purchasedList;
	}

	/**회원DB-회원추가
	 * 생성된 회원객체를 DB에서 찾아, 중복된 ID가 없을시 생성수행
	 *@param 회원VO
	 *@return 결과,리스트추가
	 */
	public boolean createMember(Map<String, String> memberInfo){//중복체크는 별도의 메서드
		for (int i = 0; i < mbList.size(); i++) {
			if( mbList.get(i).getMbUserId().equals(memberInfo.get("userId")) ){
				return false;
			}
		}//가입된 회원중 ID가 중복되는 값이 없을 경우
		MemberVO newVO = new MemberVO();
		
		newVO.setId(mbIndex++);;							//인덱스
		newVO.setMbUserId( memberInfo.get("userId") );		//아이디
		newVO.setMbUserPw( memberInfo.get("userPw") );;		//비밀번호
		newVO.setMbUserName( memberInfo.get("userName") );	//이름
		return mbList.add(newVO);
	}
	
	/**회원DB-ID중복체크
	 * 생성된 회원객체를 DB에서 찾아, 중복된 ID가 없을시 생성수행
	 *@param 입력 회원ID
	 *@return 결과
	 */
	public boolean idCheck(String muid){
		for (int i = 0; i < mbList.size(); i++) {
			if(mbList.get(i).getMbUserId().equals(muid)){		//회원 중복 체크
				return false;									//중복이면 가입을 중지
			}
		}
		return true;
	}
	
	/**회원DB-회원삭제
	 * 회원인덱스을 DB에서 찾아 삭제수행
	 *@param 회원 인덱스
	 *@return 삭제결과
	 */
	public boolean deleteMember(int mem_id){
		for (int i = 0; i < mbList.size(); i++) {
			if(mbList.get(i).getId() == mem_id){
				mbList.remove(i);
				return true;
			}
		}
		
		return false;
	}
	
	/**버스DB-노선추가
	 * 생성된 버스객체를 DB에 저장된 객체와 비교하여, '노선과 종류 모두' 중복되는 항목이 없을시 생성수행
	 *@param 버스에 대한 입력내용을 담고있는 Map
	 *@return 결과,리스트추가
	 */
	public boolean createBus(Map<String, String> busInfo){
		for (int i = 0; i < bsList.size(); i++) {
			if(bsList.get(i).getBsRoute().equals(busInfo.get("bsRoute")) &&
				bsList.get(i).getBsKind().equals(busInfo.get("bsKind")) ){
				return false;
			}
		}//노선과 버스종류 모두 중복되는 값이 없을 경우
		BusVO newVO = new BusVO();
		
		newVO.setId(bsIndex++);;							//인덱스
		newVO.setBsRoute(busInfo.get("bsRoute"));;			//노선
		newVO.setBsPrice(busInfo.get("bsPrice"));;			//요금
		newVO.setBsDepartureTime(busInfo.get("bsTime"));	//출발시간
		newVO.setBsKind(busInfo.get("bsKind"));				//일반-우등
		return bsList.add(newVO);
	}
	
	/**버스DB-노선삭제
	 * 회원인덱스을 DB에 저장된 객체와 비교하여 삭제수행
	 *@param 회원 인덱스
	 *@return 삭제결과
	 */
	public boolean deleteBus(int bus_id){
		for (int i = 0; i < bsList.size(); i++) {
			if(bsList.get(i).getId() == bus_id){
				bsList.remove(i);
				return true;
			}
		}
		
		return false;
	}
	
	/**티켓DB-구매된 티켓추가
	 * 생성된 티켓객체를 DB에 저장된 객체와 비교하여, '노선과 종류, 좌석 모두' 중복되는 항목이 없을시 생성수행
	 *@param 버스VO
	 *@return 결과,리스트추가
	 */
	public boolean createTicket(Map<String, String> ticketInfo){

		for (int j = 0; j < bsList.size(); j++) {
			if(bsList.get(j).getBsRoute().equals(ticketInfo.get("bsRoute")) &&
				bsList.get(j).getBsKind().equals(ticketInfo.get("bsKind")) ){				
				for (int i = 0; i < tkList.size(); i++) {
					if(tkList.get(i).getBusId() == bsList.get(j).getId() &&
						tkList.get(i).getSeat() == Integer.parseInt(ticketInfo.get("bsSeat")) ){
					}
				}//노선과 버스종류 모두 중복되는 값이 없을 경우
				TicketVO newVO = new TicketVO();
				
				newVO.setId(bsIndex++);;								//인덱스
				newVO.setBusId(bsList.get(j).getId());;					//외래키를 이용한 버스정보
				//bsKind seat bsRoute bsTime
				return tkList.add(newVO);
			}
		}
		return false;
	}
	
	/**티켓DB-구매된 티켓삭제
	 * 티켓인덱스을 DB에 저장된 객체와 비교하여 해당 티켓 환불수행
	 *@param 티켓 인덱스
	 *@return 삭제결과
	 */
	public boolean deleteTicket(int ticket_id){
		for (int i = 0; i < tkList.size(); i++) {
			if(tkList.get(i).getId() == ticket_id){
				tkList.remove(i);
				return true;
			}
		}
		return false;
	}
	
	/**회원DB-잔액 충전
	 * 회원인덱스을 DB에 충전 수행
	 *@param 회원 인덱스
	 *@return 충전후 잔액
	 */
	public int chargeMoney(int id, int money){
		for (int i = 0; i < tkList.size(); i++) {
			if(mbList.get(i).getId() == id){
				mbList.get(i).setMbUserMoney(money);
				return mbList.get(i).getMbUserMoney();
			}
		}
		return -1;
	}
	
	public Map<String, String> readIdPwFromDB(Map<String, String> input){
		Map<String, String> readData = new HashMap<String, String>();
		for (int i = 0; i < tkList.size(); i++) {
			if(mbList.get(i).getMbUserId().equals(input.get("id"))){
				readData.put("id", String.valueOf(mbList.get(i).getId()) );
				readData.put("userId", mbList.get(i).getMbUserId());
				readData.put("userPw", mbList.get(i).getMbUserPw());
				readData.put("isAdmin", String.valueOf(mbList.get(i).isAdmin()));
			}
		}
		return input;
	}
	
}
