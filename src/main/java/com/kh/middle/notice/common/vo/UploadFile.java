package com.kh.middle.notice.common.vo;

import com.oreilly.servlet.MultipartRequest;

public class UploadFile {

	private boolean isSuccess = false;
	private String originFileName;
	private String renameFileName;
	private String savePath;
	private MultipartRequest mRequest;

	public UploadFile() {
		super();
	}

	public boolean isSuccess() {
		return isSuccess;
	}

	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}

	public String getOriginFileName() {
		return originFileName;
	}

	public void setOriginFileName(String originalFileName) {
		this.originFileName = originalFileName;
	}

	public String getRenameFileName() {
		return renameFileName;
	}

	public void setRenameFileName(String renameFileName) {
		this.renameFileName = renameFileName;
	}

	public String getSavePath() {
		return savePath;
	}

	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}

	public MultipartRequest getmRequest() {
		return mRequest;
	}

	public void setmRequest(MultipartRequest mRequest) {
		this.mRequest = mRequest;
	}

	@Override
	public String toString() {
		return "UploadFile [isSuccess=" + isSuccess + ", originalFileName=" + originFileName + ", renameFileName="
				+ renameFileName + ", savePath=" + savePath + ", mRequest=" + mRequest + "]";
	}
}
