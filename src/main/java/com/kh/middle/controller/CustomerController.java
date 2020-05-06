package com.kh.middle.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.kh.middle.bean.customer.Criteria;
import com.kh.middle.bean.customer.Customer;
import com.kh.middle.bean.customer.PageMaker;
import com.kh.middle.bean.customer.SearchCriteria;
import com.kh.middle.customerdb.service.CustomerService;

@Controller
@RequestMapping("/customer")
@Transactional(rollbackFor = { Exception.class })
public class CustomerController {

	@Resource(name = "CustomerService")
	CustomerService customerService;
	private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);

//	@RequestMapping("s_board.do")
//	public ModelAndView s_board() throws Exception {
//
//	logger.info("s_board start === ");
//	ModelAndView mav = new ModelAndView();  
//    List<Customer> list = customerService.select_writingList();
//    
//    mav.setViewName("customer/s_board");
//    mav.addObject("list", list);
//  //mav.addObject("기능 이름","본인이 만든 오브젝트");
//    return mav; // customer/sBoard.jsp로 이동
//	}

// 현재 자주 쓰는 Model 클래스를 DI 하는 방법
	@RequestMapping("s_board.do")
	public String writingList(Model model) throws Exception {

		logger.info("s_board start === ");

		List<Customer> list = customerService.select_writingList(); // list 변수에 결과 값을 담는다
		model.addAttribute("list", list); // model에 데이터 값을 담는다
		return "customer/s_board"; // customer/sBoard.jsp로 이동
	}

	// writer_page.jsp 매핑
	@RequestMapping("writer_page.do")
	public String writerPage() {
		logger.info("writerPage() start === ");

		return "customer/writer_page";
	}

	// 게시글 from 데이터 처리
	@RequestMapping(value = "insert.do", method = RequestMethod.POST)
	public String boardWriter(Customer cus) throws Exception {
		logger.info("boardWriter() start === ");

		customerService.insert_writerBoard(cus);
		return "redirect:s_board.do"; // s_board로 리다이렉트
	}

	// 게시글 상세내용 불러오기
	// 컨트롤러에서 GET방식으로 통신을 할 것을 지정해주고 (method=RequestMethod.GET)
	// 프론트단에서 파라미터값으로 넘어오는 값을 받기 위해 설정해주고 (@RequestParam int s_no)
	// 서비스 메서드를 호출하면서 s_no 값을 넘겨준다. (CustomerService.boardRead(s_no))
	@RequestMapping(value = "s_read.do", method = RequestMethod.GET)
	public String boardRead(@RequestParam int s_no, Model model) throws Exception {
		logger.info("boardRead() start === ");

		Customer data = customerService.boardRead(s_no); // s_no값을 넘김
		model.addAttribute("data", data); // model에 데이터 값을 담는다
		return "customer/s_read"; // customer/s_read.jsp로 이동
	}

	// 게시글 수정 페이지로 이동
	@RequestMapping(value = "update.do", method = RequestMethod.GET)
	public String boardUpdatedo(@RequestParam int s_no, Model model) throws Exception {
		logger.info("update start === ");

		Customer data = customerService.boardRead(s_no); // s_no값을 넘김
		model.addAttribute("data", data); // model에 데이터 값을 담는다
		return "customer/s_update"; // customer/s_update.jsp로 이동
	}

	// 게시글 수정 실행
	@RequestMapping(value = "s_update.do", method = RequestMethod.POST)
	public String boardUpdate(Customer cus) throws Exception {
		logger.info("boardUpdate() start === ");

		customerService.updateBoard(cus);
		return "redirect:s_board.do"; // s_board로 리다이렉트
	}

	// 게시글 삭제 실행
	@RequestMapping(value = "delete.do", method = RequestMethod.GET)
	public String boardDelete(@RequestParam int s_no) throws Exception {
		customerService.deleteBoard(s_no);
		return "redirect:s_board.do"; // s_board로 리다이렉트
	}

//	// 게시판 페이징 + 검색
//	@RequestMapping(value = "boardSearchList.do")
//	public ModelAndView list(SearchCriteria cri) {
//		logger.info("!!!!!search!!!!!");
//
//		ModelAndView mav = new ModelAndView();
//
//		PageMaker pageMaker = new PageMaker();
//		pageMaker.setCri(cri);
//		pageMaker.setTotalCount(customerService.countArticle(cri.getSearchType(), cri.getKeyword()));
//
//		List<Customer> searchList = customerService.searchList(cri);
//		int count = customerService.countArticle(cri.getSearchType(), cri.getKeyword());
//
//		Map<String, Object> map = new HashMap<String, Object>();
//		map.put("searchList", searchList);
//		map.put("count", count);
//		map.put("searchOption", cri.getSearchType());
//		map.put("keyword", cri.getKeyword());
//		mav.addObject("pageMaker", pageMaker);
//		mav.addObject("map", map);
//		return mav;
//	}
}
