package com.kh.middle.controller;

import java.util.HashMap; 
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.kh.middle.bean.customer.Comment;
import com.kh.middle.bean.customer.Criteria;
import com.kh.middle.bean.customer.Customer;
import com.kh.middle.bean.customer.PageMaker;
import com.kh.middle.bean.customer.SearchCriteria;
import com.kh.middle.bean.member.Member;
import com.kh.middle.customerdb.service.CustomerService;
import com.kh.middle.notice.vo.Notice;

@Controller
@RequestMapping("/customer")
@Transactional(rollbackFor = { Exception.class })
public class CustomerController {
	
	@Resource(name = "CustomerService")
	CustomerService customerService;
	private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);
	
	@RequestMapping(value = "s_board.do", method = RequestMethod.GET)
	public String pageList(@ModelAttribute("cri") Criteria cri, Model model) throws Exception {
		
		List<Customer> list = customerService.pageList(cri);
		
		PageMaker pageMaker = new PageMaker();
		
		pageMaker.setCri(cri);
		int totalCount = customerService.countBoardList();
		pageMaker.setTotalCount(totalCount);
		
		model.addAttribute("pageMaker", pageMaker);
		model.addAttribute("list", list);
		
	    return "customer/s_board";
	}
	
	@RequestMapping("writer_page.do")
	public String writerPage() {
		logger.info("writerPage() start === ");
		
		return "customer/writer_page";
	}
	
	@RequestMapping(value = "insert.do", method = RequestMethod.POST)
	public String boardWriter(Customer cus, ModelAndView mv, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		logger.info("boardWriter() start === ");
		
		Member m = (Member) request.getSession().getAttribute("userData");
		cus.setS_writer(m.getNick_name());
		
		customerService.boardWriter(cus);
		return "redirect:s_board.do";
	}
	
	@RequestMapping(value = "s_read.do", method = RequestMethod.GET)
	public String boardRead(@RequestParam("s_no") int s_no, Model model) throws Exception {
		logger.info("boardRead() start === ");
		
		List<Comment> comment = customerService.select_comment(s_no);
		
		Customer data = customerService.boardRead(s_no);
		model.addAttribute("data", data);
		model.addAttribute("comment", comment);
		return "customer/s_read";
	}
	
//	@RequestMapping("s_read.do")
//    public ModelAndView Board_detail(ModelAndView mv, HttpServletRequest request, HttpServletResponse response)
//            throws Exception {
//		
//        int s_no = Integer.parseInt(request.getParameter("s_no"));
//        Customer data = customerService.boardRead(s_no);
//        List<Comment> comments = customerService.select_comment(s_no);
//        
//        mv.addObject("data", data);
//        mv.addObject("comment", comments);
//        mv.setViewName("/customer/s_read");
//        return mv;
//    }
	
	@RequestMapping(value = "update.do", method = RequestMethod.GET)
	public String boardUpdatedo(@RequestParam("s_no") int s_no, Model model) throws Exception {
		logger.info("update start === ");
		
		Customer data = customerService.boardRead(s_no);
		model.addAttribute("data", data);
		return "customer/s_update";
	}
	
	@RequestMapping(value = "s_update.do", method = RequestMethod.POST)
	public String boardUpdate(Customer cus) throws Exception {
		logger.info("boardUpdate() start === ");
		
		customerService.boardUpdate(cus);
		return "redirect:s_board.do";
	}
	
	@RequestMapping(value = "delete.do", method = RequestMethod.GET)
	public String boardDelete(@RequestParam("s_no") int s_no) throws Exception {
		logger.info("boardDelete() start === ");
		
		customerService.boardDelete(s_no);
		return "redirect:s_board.do";
	}
	
	@RequestMapping("comment.do")
    public ModelAndView Board_comment(ModelAndView mv, HttpServletRequest request, RedirectAttributes ra)
            throws Exception {
        String s_no = request.getParameter("s_no");
        Comment comment = new Comment();
        Member m = (Member) request.getSession().getAttribute("userData");
        comment.setS_no(Integer.parseInt(s_no));
        comment.setComment_content(request.getParameter("comment_content"));
        comment.setComment_id(m.getNick_name());
        customerService.insert_comment(comment);
        mv.setViewName("redirect:/customer/s_read.do?s_no=" + s_no);
        return mv;
    }
	
	@RequestMapping("commentdelete.do")
    public ModelAndView Board_comment_delete(ModelAndView mv, HttpServletRequest request, RedirectAttributes ra)
            throws Exception {
        // 댓글삭제
        int comment_num = Integer.parseInt(request.getParameter("comment_num"));
        customerService.delete_comment(comment_num);
        String s_no = request.getParameter("s_no");
        mv.setViewName("redirect:/customer/s_read.do?s_no=" + s_no);
        return mv;
    }
	
	@RequestMapping("commentmodify.do")
    public ModelAndView Board_comment_modify(@RequestParam("comment_num") Integer comment_num, 
            @RequestParam("s_no") String s_no, @RequestParam("comment_content") String comment_content,
            ModelAndView mv, HttpServletRequest request)
            throws Exception {
        Comment comment = new Comment();
        comment.setComment_content(comment_content);
        comment.setComment_num(comment_num);
        
        customerService.update_comment_modify(comment);
        mv.setViewName("redirect:/customer/s_read.do?s_no=" + s_no);
        
        return mv;
    }
	
}