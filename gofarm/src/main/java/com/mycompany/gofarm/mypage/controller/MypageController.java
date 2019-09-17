package com.mycompany.gofarm.mypage.controller;

import java.text.DateFormat;
import static javax.swing.JOptionPane.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mycompany.gofarm.job.dto.JobCheckDTO;
import com.mycompany.gofarm.job.dto.JobDTO;
import com.mycompany.gofarm.job.dto.JobSearchDTO;
import com.mycompany.gofarm.mypage.dto.MileageDTO;
import com.mycompany.gofarm.mypage.service.MypageService;
import com.mycompany.gofarm.user.dto.UserDTO;

@Controller
public class MypageController {
	
	@Autowired
	private MypageService mypageService;
	
	public void setMypageService(MypageService mypageService) {
		this.mypageService = mypageService;
	}

	public MypageController() {
		// TODO Auto-generated constructor stub
	}
	
	@RequestMapping("/mypage.do")
	public String mypage() {
		return "mypage/mypage";
	}
	
	@RequestMapping("/intro.do")
	public String intro() {
		return "mypage/intro";
	}
			
	@RequestMapping("/product.do")
	public String product() {
		return "mypage/product";
	}
	
	@RequestMapping("/auction.do")
	public String auction() {
		return "mypage/auction";
	}
	
	@RequestMapping("/donation.do")
	public String donation() {
		return "mypage/donation";
	}
	
	
	
	@RequestMapping("/mileage.do")
	public ModelAndView mileage(HttpSession session, ModelAndView mav) {
		UserDTO user = (UserDTO)session.getAttribute("loginOk");
		if(user.getUserlvl()==2) {
			int usercode = user.getUsercode();
			mav.addObject("mileage_dto", mypageService.mileageListService(usercode));
			mav.setViewName("mypage/mileage");
		}else {
			mav.addObject("msg", "해당 기능을 사용하기에는 권한이 부족합니다.");
			mav.addObject("url", "/gofarm/mypage.do"); 
			mav.setViewName("mypage/redirect");
		}

		return mav;
	}
	
	@RequestMapping("/mileage_charge.do")
	public ModelAndView mileage_charge(HttpSession session, ModelAndView mav) {
		UserDTO user = (UserDTO)session.getAttribute("loginOk");
		if(user.getUserlvl()==2) {
			int usercode = user.getUsercode();
			UserDTO userDetail = mypageService.userDetailViewService(usercode);
			user.setUser_home(userDetail.getUser_home());
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date date = new Date();
			String sysdate = dateFormat.format(date).toString();
			mav.addObject("sysdate", sysdate);
			mav.addObject("userDTO", user);
			mav.setViewName("mypage/mileage_charge");
		}else {
			mav.addObject("msg", "해당 기능을 사용하기에는 권한이 부족합니다.");
			mav.addObject("url", "/gofarm/mypage.do"); 
			mav.setViewName("mypage/redirect");
		}
		return mav;
	}
	
	@RequestMapping("/mileage_charge_end.do")
	public String mileage_charge_end(HttpSession session, ModelAndView mav, int m_list) {
		UserDTO user = (UserDTO)session.getAttribute("loginOk");
		int usercode = user.getUsercode();
		MileageDTO dto = mypageService.mileageViewService(usercode);
		dto.setM_list(m_list);
		dto.setM_total(dto.getM_total()+m_list);
		dto.setM_content("마일리지 충전");
		mav.addObject("mileage_dto", dto);
		mypageService.mileageInsertService(dto);
		mypageService.mileageUpdateService(dto);
		return "redirect:/mileage.do";
	}
		
	@RequestMapping("/mileage_payment.do")
	public ModelAndView mileage_payment(HttpSession session, ModelAndView mav) {
		UserDTO user = (UserDTO)session.getAttribute("loginOk");
		if(user.getUserlvl()==2) 
			mav.setViewName("mypage/mileage_payment");
		else {
			mav.addObject("msg", "해당 기능을 사용하기에는 권한이 부족합니다.");
			mav.addObject("url", "/gofarm/mypage.do"); 
			mav.setViewName("mypage/redirect");
		}
		
		return mav;
	}
	
	@RequestMapping("/mileage_payment_end.do")
	public String mileage_payment_end(HttpSession session, ModelAndView mav, int m_list) {
		UserDTO user = (UserDTO)session.getAttribute("loginOk");
		int usercode = user.getUsercode();
		MileageDTO dto = mypageService.mileageViewService(usercode);
		dto.setM_list(-m_list);
		dto.setM_total(dto.getM_total()-m_list);
		dto.setM_content("마일리지 출금");
		mav.addObject("mileage_dto", dto);
		mypageService.mileageInsertService(dto);
		mypageService.mileageUpdateService(dto);
		return "redirect:/mileage.do";
	}
	
	@RequestMapping("/paymentPorm.do")
	public ModelAndView paymentPro(HttpSession session, ModelAndView mav) {
		UserDTO user = (UserDTO)session.getAttribute("loginOk");
		int usercode = user.getUsercode();
		UserDTO dto = mypageService.userDetailViewService(usercode);
		dto.setName(user.getName());
		mav.addObject("userDTO", dto);
		mav.setViewName("mypage/paymentPorm");
		return mav;
	}
	
	@RequestMapping("/paymentList.do")
	public ModelAndView paymentList(HttpSession session, ModelAndView mav) {
		UserDTO user = (UserDTO)session.getAttribute("loginOk");
		int usercode = user.getUsercode();
		mav.addObject("mileage_dto", mypageService.paymentListService(usercode));
		mav.setViewName("mypage/paymentList");
		return mav;
	}
	
	@RequestMapping("/jobView.do")
	public ModelAndView jobView(HttpSession session, ModelAndView mav) {
		UserDTO user = (UserDTO)session.getAttribute("loginOk");
		int usercode = user.getUsercode();
		mav.addObject("job_dto", mypageService.jobListService(usercode));
		mav.setViewName("mypage/job");
		return mav;
	}
	
	@RequestMapping("/jobSearchView.do")
	public ModelAndView jobSearchView(HttpSession session, ModelAndView mav) {
		UserDTO user = (UserDTO)session.getAttribute("loginOk");
		int usercode = user.getUsercode();
		JobSearchDTO jobSearchDto = mypageService.jobSearchListService(usercode);
		List<JobDTO> jobCheckDto = mypageService.jobcheckListService(jobSearchDto.getJobsearchcode());
		mav.addObject("jobSearch_dto", jobSearchDto);
		mav.addObject("jobCheck_dto", jobCheckDto);
		mav.setViewName("mypage/jobSearch");
		return mav;
	}
	
}
