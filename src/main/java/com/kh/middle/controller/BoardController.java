package com.kh.middle.controller;
  
import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.PageData;
import org.apache.http.Header;
import org.apache.ibatis.javassist.expr.NewArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.kh.middle.notice.common.util.FileUtil;
import com.kh.middle.notice.common.vo.UploadFile;
import com.kh.middle.notice.db.service.NoticeService;
import com.kh.middle.notice.vo.Comment;
import com.kh.middle.notice.vo.Notice;
import com.kh.middle.notice.vo.PageDefault;
import com.kh.middle.notice.vo.Paging;
 
@Controller
@RequestMapping("/board")
@Transactional(rollbackFor = { Exception.class })
public class BoardController extends HttpServlet {
    private static final long serialVersionUID = 2071996894026653976L;
    private static final Logger logger = LoggerFactory.getLogger(StellerController.class);
    @Resource(name = "NoticeService")
    NoticeService noticeService;
  
    
    /**
     * 1.MethodName : Board_indexView
       2.ClassName : BoardController.java
       3.Comment : 게시판 메인 페이지 및 페이징 구현
       4.작성자 : 김지수
       5.작성일 : 2020. 4. 26.
     */
    @RequestMapping("index.do")
    public ModelAndView Board_indexView(@RequestParam("pageNum") Integer pageNum, 
            ModelAndView mv, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        PageDefault pageDefault = new PageDefault();
        pageDefault.setPageNum(pageNum);
        Paging paging = new Paging(pageDefault, noticeService.select_board_total_count());
        
        mv.addObject("pageNum", pageNum);
        mv.addObject("noticeList", noticeService.select_notice_paging(pageDefault));
        mv.addObject("pageMaker", paging);
        mv.setViewName("/board/index");
        return mv;
    }
    /**
     * 1.MethodName : Board_write
       2.ClassName : BoardController.java
       3.Comment : 게시물 작성을 위한 view 연결 
       4.작성자 : 김지수
       5.작성일 : 2020. 4. 26.
     */
    @RequestMapping("write.do")
    public ModelAndView Board_write(ModelAndView mv, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        mv.setViewName("/board/write");
        return mv;
    }
    /**
     * 1.MethodName : Board_upload
       2.ClassName : BoardController.java
       3.Comment : 게시판 내 파일 및 컨텐츠 업로드 구현
       4.작성자 : 김지수
       5.작성일 : 2020. 4. 27.
     */
    @RequestMapping("upload.do")
    public ModelAndView Board_upload(ModelAndView mv, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String uploadFolder = "resources/upload";
        UploadFile file = new FileUtil().fileUpload(uploadFolder, request);
        Notice notice = null;
        if (file.isSuccess()) {
            notice = new Notice();
            notice.setNotice_title(file.getmRequest().getParameter("notice_title"));
            notice.setNotice_content(file.getmRequest().getParameter("notice_content"));
            notice.setOriginal_filepath(file.getOriginFileName());
            notice.setRename_filepath(file.getRenameFileName());
            noticeService.insert_notice(notice);
            request.setAttribute("notice", notice);
            request.setAttribute("isSuccess", true);
            mv.setViewName("redirect:/board/index.do?pageNum=1");
        } else {
            request.setAttribute("isSuccess", false);
            mv.setViewName("redirect:/board/write.do");
        }
        return mv;
    }
    /**
     * 1.MethodName : Board_detail
       2.ClassName : BoardController.java
       3.Comment : 게시물 내용 보기 및 조회수 증가 처리
       4.작성자 : 김지수
       5.작성일 : 2020. 4. 29.
     */
    @RequestMapping("detail.do")
    public ModelAndView Board_detail(ModelAndView mv, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        Notice notice = new Notice();
        int notice_num = Integer.parseInt(request.getParameter("noticeNum"));
        List<Comment> comments = noticeService.select_notice_comment(notice_num);
        
        String num = request.getParameter("noticeNum");
        String random = UUID.randomUUID().toString();
        // -----------------------------조회수 1일 1회증가
        boolean flg = false;
        Cookie cookies[] = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(num)) {
                    flg = true;
                }
            }
        }
        if (!flg) {
            // 조회수 증가
            noticeService.update_notice_count(notice_num);
            Cookie newCookie = new Cookie(num, num);
            newCookie.setMaxAge(1 * 24 * 60 * 60);
            response.addCookie(newCookie);
        }
        notice = noticeService.select_notice_detail(notice_num);
        mv.addObject("detail", notice);
        mv.addObject("comment", comments);
        mv.setViewName("/board/detail");
        return mv;
    }
    /**
     * 1.MethodName : Board_download
       2.ClassName : BoardController.java
       3.Comment : 게시물 내 첨부파일 다운로드
       4.작성자 : 김지수
       5.작성일 : 2020. 4. 30.
     */
    @RequestMapping("download.do")
    public ModelAndView Board_download(ModelAndView mv, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String realFolder = request.getSession().getServletContext().getRealPath("/resources/upload");
        String path = realFolder + "/" + request.getParameter("rfname");
        String ofname = request.getParameter("ofname");
        request.setAttribute("path", path);
        request.setAttribute("ofname", ofname);
        FileUtil fu = new FileUtil();
        if (fu.fileDownload(mv, response, request)) {
            mv.setViewName("/board/detail");
        } else {
            System.out.println("파일다운로드 실패");
        }
        return mv;
    }
    /**
     * 1.MethodName : Board_modifyNotice
       2.ClassName : BoardController.java
       3.Comment : 게시물 수정 시 게시물에 대한 정보 전달 및 view연결
       4.작성자 : 김지수
       5.작성일 : 2020. 5. 1.
     */
    @RequestMapping(value="modifynotice.do")
    public ModelAndView Board_modifyNotice(@RequestParam("noticeNum") Integer noticeNum ,
            @RequestParam("rename_filepath") String rename_filepath,
            @RequestParam("original_filepath") String original_filepath,
            @RequestParam("notice_content") String notice_content,
            @RequestParam("notice_title") String notice_title,
            ModelAndView mv, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        Notice notice = new Notice();
        notice.setNotice_title(notice_title);
        notice.setNotice_content(notice_content);
        notice.setNotice_num(noticeNum);
        notice.setOriginal_filepath(original_filepath);
        notice.setRename_filepath(rename_filepath);
        
        mv.addObject("modify_notice", notice);
        mv.setViewName("/board/modify");
        return mv;
    }
    /**
     * 1.MethodName : Board_modify
       2.ClassName : BoardController.java
       3.Comment : 게시물 수정 시 제목,내용 수정 및 파일삭제,업로드 분기 처리
       4.작성자 : 김지수
       5.작성일 : 2020. 5. 2.
     */
    @RequestMapping("modify.do")
    public ModelAndView Board_modify(
            ModelAndView mv, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        
        String fileDir = "resources/upload";
        String filePath = "";
        File origin_file = null;
        UploadFile uploadFile = new FileUtil().fileUpload(fileDir, request);
        
        String state = uploadFile.getmRequest().getParameter("state");
        String stateDetail = uploadFile.getmRequest().getParameter("stateDetail");
        int noticeNum = Integer.parseInt(uploadFile.getmRequest().getParameter("noticeNum"));
        String modify_title = uploadFile.getmRequest().getParameter("modify_title");
        String modify_content = uploadFile.getmRequest().getParameter("modify_content");
        
        Notice notice = new Notice();
        notice.setNotice_num(noticeNum);
        if(uploadFile.isSuccess()) {
            notice.setNotice_title(modify_title);
            notice.setNotice_content(modify_content);
            if(state.equals("historyYes") && stateDetail.equals("noUpload")) {
                //파일 삭제만
                filePath = request.getSession().getServletContext().getRealPath(fileDir) 
                        + "/" + uploadFile.getmRequest().getParameter("rename_filepath");
                origin_file = new File(filePath);
                origin_file.delete();
            }else if(state.equals("historyYes") && stateDetail.equals("yesUpload")){
                //삭제 & 업로드
                filePath = request.getSession().getServletContext().getRealPath(fileDir) 
                        + "/" + uploadFile.getmRequest().getParameter("rename_filepath");
                origin_file = new File(filePath);
                origin_file.delete();
                
                notice.setOriginal_filepath(uploadFile.getOriginFileName());
                notice.setRename_filepath(uploadFile.getRenameFileName());
            }else if(state.equals("historyNo") && stateDetail.equals("yesUpload")) {
                //파일 업로드만
                notice.setOriginal_filepath(uploadFile.getOriginFileName());
                notice.setRename_filepath(uploadFile.getRenameFileName());
            }
        }
        
        noticeService.update_modify_notice(notice);
        mv.setViewName("redirect:/board/detail.do?noticeNum="+noticeNum);
        return mv;
    }
    /**
     * 1.MethodName : Board_recommed
       2.ClassName : BoardController.java
       3.Comment : 게시물 추천 시 ajax 통신으로 페이지 이동없이 추천수 증가, 쿠키를 통해 1일 1회 추천 제한 
       4.작성자 : 김지수
       5.작성일 : 2020. 5. 3.
     */
    @RequestMapping("recommend.do")
    public void Board_recommed(@RequestParam("noticeNum") Integer intNoticeNum ,ModelAndView mv, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String stringNoticeNum = request.getParameter("noticeNum");
        int result = 0;
        // -------------------------------------
        boolean flg = false;
        Cookie cookies[] = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(stringNoticeNum+"recommend")) {
                    flg = true;
                }
            }
        }
        if (!flg) {
            Cookie newCookie = new Cookie(stringNoticeNum + "recommend", stringNoticeNum + "recommend");
            newCookie.setMaxAge(1 * 24 * 60 * 60);
            response.addCookie(newCookie);
            noticeService.update_notice_recommend(intNoticeNum);
        }
        
        if(flg) {
            result = 0;
        }else {
            result = 1;
        }
        response.getWriter().print(result);
        response.getWriter().flush();
        response.getWriter().close();
    }
    /**
     * 1.MethodName : Board_comment
       2.ClassName : BoardController.java
       3.Comment : 게시물 댓글 작성 구현
       4.작성자 : 김지수
       5.작성일 : 2020. 5. 4.
     */
    @RequestMapping("comment.do")
    public ModelAndView Board_comment(ModelAndView mv, HttpServletRequest request, RedirectAttributes ra)
            throws Exception {
        String noticeNum = request.getParameter("notice_num");
        Comment comment = new Comment();
        comment.setNotice_num(Integer.parseInt(noticeNum));
        comment.setComment_content(request.getParameter("comment_content"));
//      세션에서 아이디 받아와서 바꿔줄예정 comment.setComment_id(request.getSession().getAttribute("member"));
        comment.setComment_id("김지수");
        noticeService.insert_notice_comment(comment);
        mv.setViewName("redirect:/board/detail.do?noticeNum=" + noticeNum);
        return mv;
    }
    /**
     * 1.MethodName : Board_notice_delete
       2.ClassName : BoardController.java
       3.Comment : 게시글 삭제 기능 구현
       4.작성자 : 김지수
       5.작성일 : 2020. 5. 5.
     */
    @RequestMapping("noticedelete.do")
    public ModelAndView Board_notice_delete(@RequestParam("noticeNum") Integer notice_num,
            @RequestParam("rename_filepath") String rename_filepath,
            ModelAndView mv, HttpServletRequest request, RedirectAttributes ra)
            throws Exception {
        //글+댓삭
        noticeService.delete_notice(notice_num);
        //업로드 파일 삭제
        String fileDir = "/resources/upload"; //파일 디렉토리
        String filePath = request.getSession().getServletContext().getRealPath(fileDir) + "/" + rename_filepath;
        //파일객체 생성
        File file = new File(filePath);
        if(file.exists()) { //파일이 존재하면
            file.delete(); //삭제
        }
        
        mv.setViewName("redirect:/board/index.do?pageNum=1");
        return mv;
    }
    /**
     * 1.MethodName : Board_comment_delete
       2.ClassName : BoardController.java
       3.Comment : 댓글 삭제 기능 구현
       4.작성자 : 김지수
       5.작성일 : 2020. 5. 6.
     */
    @RequestMapping("commentdelete.do")
    public ModelAndView Board_comment_delete(ModelAndView mv, HttpServletRequest request, RedirectAttributes ra)
            throws Exception {
        // 댓글삭제
        int comment_num = Integer.parseInt(request.getParameter("comment_num"));
        noticeService.delete_notice_comment(comment_num);
        String noticeNum = request.getParameter("noticeNum");
        mv.setViewName("redirect:/board/detail.do?noticeNum=" + noticeNum);
        return mv;
    }
    
    /**
     * 1.MethodName : Board_comment_modify
       2.ClassName : BoardController.java
       3.Comment : 댓글 수정 기능 구현
       4.작성자 : 김지수
       5.작성일 : 2020. 5. 7.
     */
    @RequestMapping("commentmodify.do")
    public ModelAndView Board_comment_modify(@RequestParam("comment_num") Integer comment_num, 
            @RequestParam("noticeNum") String noticeNum, @RequestParam("comment_content") String comment_content,
            ModelAndView mv, HttpServletRequest request)
            throws Exception {
        Comment comment = new Comment();
        comment.setComment_content(comment_content);
        comment.setComment_num(comment_num);
        
        noticeService.update_notice_comment_modify(comment);
        mv.setViewName("redirect:/board/detail.do?noticeNum=" + noticeNum);
        
        return mv;
    }
    
    
    /**
     * 1.MethodName : Board_search
       2.ClassName : BoardController.java
       3.Comment : 카카오 검색 api를 통해 드래그 검색 기능 구현(이건 그냥 해보고싶어서)
       4.작성자 : 김지수
       5.작성일 : 2020. 5. 7.
     */
    @RequestMapping("search.do")
    public ModelAndView Board_search(HttpServletRequest request, ModelAndView mv) {
        System.out.println(request.getParameter("search"));
        mv.addObject("search", request.getParameter("search"));
        mv.setViewName("board/search");
        return mv;
    }
    
    @ExceptionHandler(value = Exception.class)
    public ModelAndView handleException(Exception e) {
//      String getMessage() : 발생된 예외의 메시지를 리턴한다. 
//      String toString() : 발생된 예외 클래스명과 메시지를 리턴한다. 
//      String pritnStackTrace() : 발생된 예외를 역추적하기 위해 표준 예외 스트림을 출력한다. 
//      예외 발생시 예외가 발생한 곳을 알아낼 때 주로 사용된다. 
        String error = e.getMessage();
        ModelAndView model = new ModelAndView();
        logger.info(error);
        model.addObject("error", error);
        return model;
    }
}