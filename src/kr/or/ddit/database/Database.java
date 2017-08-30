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
	
	boolean deleteMember(String mem_id){
		for (int i = 0; i < mbList.size(); i++) {
			if(mbList.get(i).getId() == mem_id){
				mbList.remove(i);
				return true;
			}
		}
		
		return false;
	}
}
