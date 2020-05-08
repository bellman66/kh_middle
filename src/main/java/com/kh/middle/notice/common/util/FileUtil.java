package com.kh.middle.notice.common.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.web.servlet.ModelAndView;

import com.kh.middle.notice.common.vo.UploadFile;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class FileUtil {

	public UploadFile fileUpload(String uploadFolder, HttpServletRequest request) {
		// common.vo.UploadFile
		UploadFile uploadFile = new UploadFile();
		int maxSize = 1024 * 1024 * 10;
		String originFileName = "";
		String renameFileName = "";
		
		String savePath = request.getSession().getServletContext().getRealPath("/") + uploadFolder;
		
		UUID uuid = UUID.randomUUID();
		MultipartRequest mRequest;
		try {
			mRequest = new MultipartRequest(request, savePath, maxSize, "UTF-8", new DefaultFileRenamePolicy());
			//upload.jsp file 파라미터 값
			originFileName = mRequest.getFilesystemName("noticeFile");
			if(originFileName != null) {
				Date now = new Date();
				SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
				String fileName = uuid + "_" + String.valueOf(format.format(now));
				
				renameFileName = fileName + originFileName.substring(originFileName.lastIndexOf("."));
				
				//경로 + fileName
				File originFile = new File(savePath + "\\" + originFileName);
				File renameFile = new File(savePath + "\\" + renameFileName);
				
				originFile.renameTo(renameFile);
			}
			
			uploadFile.setSuccess(true);
			uploadFile.setOriginFileName(originFileName);
			uploadFile.setRenameFileName(renameFileName);
			uploadFile.setSavePath(savePath);
			uploadFile.setmRequest(mRequest);
			
		} catch (IOException e) {
			e.printStackTrace();
			uploadFile.setSuccess(false);
			uploadFile.setOriginFileName(originFileName);
		}

		return uploadFile;

	}
	
	public boolean fileDownload(ModelAndView model, HttpServletResponse response, HttpServletRequest request) {
		boolean res = false;
		File downFile = new File((String)request.getAttribute("path"));
		String ofname = (String)request.getAttribute("ofname");
		ServletOutputStream downOutput;
		
		try {
			response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(ofname, "UTF-8"));
			downOutput = response.getOutputStream();
			BufferedInputStream bis = new BufferedInputStream(new FileInputStream(downFile));
			int read = 0;
			while((read = bis.read())!= -1) {
				downOutput.write(read);
				downOutput.flush();
			}
			downOutput.close();
			bis.close();
			res = true;
		} catch (IOException e) {
			e.printStackTrace();
			res = false;
		}
		
		return res;
	}
}
