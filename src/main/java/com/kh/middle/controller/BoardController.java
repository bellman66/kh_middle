package com.kh.middle.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.javassist.expr.NewArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.kh.middle.notice.db.service.NoticeService;
import com.kh.middle.notice.vo.Notice;
import com.kh.middle.notice.vo.Paging;
import com.kh.middle.notice.vo.PageDefault;

@Controller
@RequestMapping("/board")
@Transactional(rollbackFor = { Exception.class })
public class BoardController extends HttpServlet {

	private static final Logger logger = LoggerFactory.getLogger(StellerController.class);

	@Resource(name = "NoticeService")
	NoticeService noticeService;

	/* @CrossOrigin(origins = "*") */
	@RequestMapping("index.do")
	public ModelAndView Board_indexView(ModelAndView mv, HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PageDefault pageDefault = new PageDefault();
		int pageNum = Integer.parseInt(request.getParameter("pageNum"));
		pageDefault.setPageNum(pageNum);

		Paging paging = new Paging(pageDefault, noticeService.select_board_total_count());

		mv.addObject("noticeList", noticeService.select_notice_paging(pageDefault));
		mv.addObject("pageMaker", paging);

		mv.setViewName("/board/index");

		return mv;

	}

	@RequestMapping("write.do")
	public ModelAndView Board_write(ModelAndView mv, HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		mv.setViewName("/board/write");
		return mv;

	}

	@RequestMapping("upload.do")
	public ModelAndView Board_upload(ModelAndView mv, HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		Notice notice = new Notice();
		notice.setNotice_title(request.getParameter("notice_title"));
		notice.setNotice_content(request.getParameter("notice_content"));
		request.setAttribute("notice", notice);

		noticeService.insert_notice(notice);

		mv.setViewName("redirect:/board/index.do?pageNum=1");

		return mv;
	}

	@RequestMapping("detail.do")
	public ModelAndView Board_detail(ModelAndView mv, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Notice notice = new Notice();
		int notice_num = Integer.parseInt(request.getParameter("noticeNum"));

		noticeService.update_notice_count(notice_num);
		notice = noticeService.select_notice_detail(notice_num);

		mv.addObject("detail", notice);
		mv.setViewName("/board/detail");

		return mv;
	}

	@ExceptionHandler(value = Exception.class)
	public ModelAndView handleException(Exception e) {

//		String getMessage() : 발생된 예외의 메시지를 리턴한다. 
//		String toString() : 발생된 예외 클래스명과 메시지를 리턴한다. 
//		String pritnStackTrace() : 발생된 예외를 역추적하기 위해 표준 예외 스트림을 출력한다. 
//		예외 발생시 예외가 발생한 곳을 알아낼 때 주로 사용된다. 

		String error = e.getMessage();

		ModelAndView model = new ModelAndView();
		logger.info(error);
		model.addObject("error", error);

		return model;
	}
}