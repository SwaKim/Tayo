package kr.or.ddit.database;

import java.util.ArrayList;
import java.util.List;

import kr.or.ddit.vo.BusVO;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.TicketVO;

public class Database {
	private List<MemberVO> mbList = new ArrayList<MemberVO>();
	private List<BusVO> bsList = new ArrayList<BusVO>();
	private List<TicketVO> tkList = new ArrayList<TicketVO>();
	
	/**회원DB-회원목록
	 * 관리자모드에서 사용할 회원목록 조회
	 *@return 멤버리스트
	 */
	public List<MemberVO> getMbList() {
		return mbList;
	}
	
	/**버스DB-버스목록
	 * 회원, 관리자메뉴
	 *@return 버스리스트
	 */
	public List<BusVO> getBsList() {
		return bsList;
	}
	/**티켓DB-티켓목록
	 * 전체 티켓목록 중에서 해당 회원이 구입한 리스트를 추려내 반환
	 *@param 구매회원 인덱스
	 *@return 티켓리스트
	 */
	public List<TicketVO> getTkList(String mem_id) {
		List<TicketVO> purchasedList = new ArrayList<TicketVO>();
		for (int i = 0; i < tkList.size(); i++) {
			if(tkList.get(i).getMem_id() == mem_id){
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
	public boolean createMember(MemberVO membervo){
//		mbList.contains()
		for (int i = 0; i < mbList.size(); i++) {
			if(mbList.get(i).getMbUserId() == membervo.getMbUserId()){		//회원 중복 체크
				return false;												//중복이면 가입을 중지
			}
		}
		return mbList.add(membervo);
	}
	
	/**회원DB-회원삭제
	 * 회원인덱스을 DB에서 찾아 삭제수행
	 *@param 회원 인덱스
	 *@return 삭제결과
	 */
	public boolean deleteMember(String mem_id){
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
	 *@param 버스VO
	 *@return 결과,리스트추가
	 */
	public boolean createBus(BusVO busVO){
		for (int i = 0; i < bsList.size(); i++) {
			if(bsList.get(i).getBsRoute() == busVO.getBsRoute() && bsList.get(i).getBsKind() == busVO.getBsKind() ){
				return false;
			}
		}
		return bsList.add(busVO);
	}
	
	/**버스DB-노선삭제
	 * 회원인덱스을 DB에 저장된 객체와 비교하여 삭제수행
	 *@param 회원 인덱스
	 *@return 삭제결과
	 */
	public boolean deleteBus(String bus_id){
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
	public boolean createTicket(TicketVO ticketVO){
		for (int i = 0; i < tkList.size(); i++) {
			if(tkList.get(i).getBus_id() == ticketVO.getBus_id()){
				return false;
			}
		}
		return tkList.add(ticketVO);
	}
	
	/**티켓DB-구매된 티켓삭제
	 * 티켓인덱스을 DB에 저장된 객체와 비교하여 해당 티켓 환불수행
	 *@param 티켓 인덱스
	 *@return 삭제결과
	 */
	public boolean deleteTicket(String ticket_id){
		for (int i = 0; i < tkList.size(); i++) {
			if(tkList.get(i).getId() == ticket_id){
				tkList.remove(i);
				return true;
			}
		}
		return false;
	}
}
