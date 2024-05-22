package zesinc.intra.form.domain;

import java.util.Date;
import java.util.List;

import zesinc.component.file.domain.FileVO;
import zesinc.web.validate.annotation.marker.Digits;
import zesinc.web.validate.annotation.marker.MaxLength;
import zesinc.web.validate.annotation.marker.Required;
import zesinc.web.vo.PageVO;

public class FormInfoVO extends PageVO {

	/** serialVersionUID */
	private static final long serialVersionUID = 458612851954468135L;

	/** 폼일련번호 */
	@Required
	@Digits
	private Integer formSn;

	/** 폼일련번호 */
	private String formSns;

	/** 폼제목 */
	@Required
	@MaxLength(max = 256)
	private String formTtl;
	
	/** 폼설명 */
	@MaxLength(max = 4000)
	private String formExpln;

	/** 사이트일련번호 */
	@Digits
	private String siteSn;

	/** 시작일자 */
	private Date bgngYmd;

	/** 종료일자 */
	private Date endYmd;

	/** 응답인원수 */
	@Digits
	private String rspnsNope;

	/** 제한인원수 */
	@Digits
	private String lmtNope;

	/** 파일일련번호 */
	@Digits
	private Integer fileSn;

	/** 파일경로명 */
	private String filePathNm;

	/** 선착순여부 */
	private String frstcmYn;


	/** 게시여부 */
	private String pstgYn;

	/** 작성자정보수집상태번호 */
	private String wrtrInfoClctSttsNo;

	/** 개인정보수집동의내용 */
	private String prvcClctAgreCn;

	/** 삭제여부 */
	private String delYn;

	/** 삭제일시 */
	private Date delDt;

	/** 등록자ID */
	@MaxLength(max = 20)
	private String rgtrId;

	/** 등록일시 */
	private Date regDt;

	/** 수정자ID */
	@MaxLength(max = 20)
	private String mdfrId;

	/** 수정일시 */
	private Date updtDt;


	/** 시작상태 */
	private String statusBegin;

	/** 종료상태 */
	private String statusEnd;

	/** 도메인코드 목록 */
	@Required
	private Integer[] siteSns;

	/** 폼 도메인 목록 */
	private List<FormDomnVO> domnList;

	/** 파일 ID 목록 */
	private String[] fileIds;

	/** 첨부파일 목록 */
	private List<FileVO> fileList;

	/** 시작일시 - 문자열 */
	@Required
	private String bgngYmdStr;

	/** 종료일시 - 문자열 */
	@Required
	private String endYmdStr;
	
	/** 시작시간 */
	@MaxLength(max = 5)
	private String bgngHr;
	
	/** 종료시간 */
	@MaxLength(max = 5)
	private String endHr;
	
	/** 자료여부 */
	private String dataYn;
	
	/** 그룹일련번호 */
	private Integer groupSn;

	public Integer getFormSn() {
		return formSn;
	}

	public void setFormSn(Integer formSn) {
		this.formSn = formSn;
	}

	public String getFormTtl() {
		return formTtl;
	}

	public void setFormTtl(String formTtl) {
		this.formTtl = formTtl;
	}

	public String getFormExpln() {
		return formExpln;
	}

	public void setFormExpln(String formExpln) {
		this.formExpln = formExpln;
	}

	public String getSiteSn() {
		return siteSn;
	}

	public void setSiteSn(String siteSn) {
		this.siteSn = siteSn;
	}

	public Date getBgngYmd() {
		return bgngYmd;
	}

	public void setBgngYmd(Date bgngYmd) {
		this.bgngYmd = bgngYmd;
	}

	public Date getEndYmd() {
		return endYmd;
	}

	public void setEndYmd(Date endYmd) {
		this.endYmd = endYmd;
	}

	public String getRspnsNope() {
		return rspnsNope;
	}

	public void setRspnsNope(String rspnsNope) {
		this.rspnsNope = rspnsNope;
	}

	public String getLmtNope() {
		return lmtNope;
	}

	public void setLmtNope(String lmtNope) {
		this.lmtNope = lmtNope;
	}

	public Integer getFileSn() {
		return fileSn;
	}

	public void setFileSn(Integer fileSn) {
		this.fileSn = fileSn;
	}

	public String getFilePathNm() {
		return filePathNm;
	}

	public void setFilePathNm(String filePathNm) {
		this.filePathNm = filePathNm;
	}

	public String getFrstcmYn() {
		return frstcmYn;
	}

	public void setFrstcmYn(String frstcmYn) {
		this.frstcmYn = frstcmYn;
	}


	public String getPstgYn() {
		return pstgYn;
	}

	public void setPstgYn(String pstgYn) {
		this.pstgYn = pstgYn;
	}

	public String getWrtrInfoClctSttsNo() {
		return wrtrInfoClctSttsNo;
	}

	public void setWrtrInfoClctSttsNo(String wrtrInfoClctSttsNo) {
		this.wrtrInfoClctSttsNo = wrtrInfoClctSttsNo;
	}

	public String getPrvcClctAgreCn() {
		return prvcClctAgreCn;
	}

	public void setPrvcClctAgreCn(String prvcClctAgreCn) {
		this.prvcClctAgreCn = prvcClctAgreCn;
	}

	public String getDelYn() {
		return delYn;
	}

	public void setDelYn(String delYn) {
		this.delYn = delYn;
	}

	public Date getDelDt() {
		return delDt;
	}

	public void setDelDt(Date delDt) {
		this.delDt = delDt;
	}

	public String getRgtrId() {
		return rgtrId;
	}

	public void setRgtrId(String rgtrId) {
		this.rgtrId = rgtrId;
	}

	public Date getRegDt() {
		return regDt;
	}

	public void setRegDt(Date regDt) {
		this.regDt = regDt;
	}

	public String getMdfrId() {
		return mdfrId;
	}

	public void setMdfrId(String mdfrId) {
		this.mdfrId = mdfrId;
	}

	public Date getUpdtDt() {
		return updtDt;
	}

	public void setUpdtDt(Date updtDt) {
		this.updtDt = updtDt;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getStatusBegin() {
		return statusBegin;
	}

	public void setStatusBegin(String statusBegin) {
		this.statusBegin = statusBegin;
	}

	public String getStatusEnd() {
		return statusEnd;
	}

	public void setStatusEnd(String statusEnd) {
		this.statusEnd = statusEnd;
	}

	public String getFormSns() {
		return formSns;
	}

	public void setFormSns(String formSns) {
		this.formSns = formSns;
	}

	public Integer[] getSiteSns() {
		return siteSns;
	}

	public void setSiteSns(Integer[] siteSns) {
		this.siteSns = siteSns;
	}

	public List<FormDomnVO> getDomnList() {
		return domnList;
	}

	public void setDomnList(List<FormDomnVO> domnList) {
		this.domnList = domnList;
	}

	public List<FileVO> getFileList() {
		return fileList;
	}

	public void setFileList(List<FileVO> fileList) {
		this.fileList = fileList;
	}

	public String[] getFileIds() {
		return fileIds;
	}

	public void setFileIds(String[] fileIds) {
		this.fileIds = fileIds;
	}

	public String getBgngYmdStr() {
		return bgngYmdStr;
	}

	public void setBgngYmdStr(String bgngYmdStr) {
		this.bgngYmdStr = bgngYmdStr;
	}

	public String getEndYmdStr() {
		return endYmdStr;
	}

	public void setEndYmdStr(String endYmdStr) {
		this.endYmdStr = endYmdStr;
	}
	
	public String getBgngHr() {
		return bgngHr;
	}

	public void setBgngHr(String bgngHr) {
		this.bgngHr = bgngHr;
	}
	
	public String getEndHr() {
		return endHr;
	}

	public void setEndHr(String endHr) {
		this.endHr = endHr;
	}

	public String getDataYn() {
		return dataYn;
	}

	public void setDataYn(String dataYn) {
		this.dataYn = dataYn;
	}

	public Integer getGroupSn() {
		return groupSn;
	}

	public void setGroupSn(Integer groupSn) {
		this.groupSn = groupSn;
	}

}
