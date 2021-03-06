package com.mycompany.gofarm.donation.dao;

import java.util.List;

import com.mycompany.gofarm.donation.dto.Do_conditionDTO;
import com.mycompany.gofarm.donation.dto.DonationDTO;
import com.mycompany.gofarm.donation.dto.PageDTO;
import com.mycompany.gofarm.user.dto.UserDTO;

public interface DonationDAO {
	//나눔 리스트를 불러온다.
	public int count();
	public List<DonationDTO> donationListMethod(PageDTO pageDTO);
	//나눔글의 정보를 가져온다.
	public DonationDTO donationViewMethod(int docode);
	//나눔신청현황을 가져온다.
	public List<Do_conditionDTO> do_conditionListMethod(int docode);
	//나눔글을 작성한다.
	public void donationProduceMethod(DonationDTO dodto);
	//나눔글의 정보를 업데이트한다.
	public void donationUpdateMethod(DonationDTO dodto);
	//나눔글을 삭제한다.
	public void donationDeleteMethod(int docode);
	//나눔을 신청하기 위해 신청정보를 저장
	public void donationRequestMethod(Do_conditionDTO condto);
	//나눔 승인시 신청현황의 상태 변경
	public void do_conditionStateUpdMethod(Do_conditionDTO condto);
	//나눔승인시 나눔 테이블 개수 차감
	public void donationCntMethod(int docode);
	//나눔신청인의 정보를 가져옴
	public UserDTO userInfoMethod(int do_name);
	
	
	
	
}
