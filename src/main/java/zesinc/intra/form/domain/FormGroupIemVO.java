package zesinc.intra.form.domain;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import zesinc.web.validate.annotation.marker.Digits;
import zesinc.web.validate.annotation.marker.MaxLength;
import zesinc.web.validate.annotation.marker.Required;
import zesinc.web.vo.PageVO;

public class FormGroupIemVO extends PageVO {
	
	/** serialVersionUID */
    private static final long serialVersionUID = 195147851954468135L;
    
    /** 항목일련번호 */
    @Required
    @Digits
    private Integer artclSn;

    /** 항목명 */
    @Required
    @MaxLength(max = 100)
    private String artclNm;

    /** 항목설명 */
    @MaxLength(max = 1000)
    private String artclExpln;

    /** 항목유형코드 */
    @Required
    private String artclTypeCd;

    /** 폼일련번호 */
    @Digits
    private Integer formSn;

    /** 그룹일련번호 */
    @Digits
    private Integer groupSn;
    
    /** 필수여부 */
    @Required
    private String esntlYn;
    
    /** 제한값 */
    @Digits
    private String lmtVl;
    
    /** 초기값 */
    @Digits
    private String initVl;
    
    /** 제한파일크기 */
    @Digits
    private Integer lmtFileSz;
    
    /** 허가파일확장자내역 */
    private String prmsnFileExtnMttr;
    
    /** 등록자ID */
    @MaxLength(max = 20)
    private String rgtrId;
    
    /** 등록일시 */
    private Date regDt;
    
    /** 등록일시 */
    private String groupNm;

    /** 그룹 설명*/
	@MaxLength(max = 4000)
	private String groupExpln;
	
	/** 옵션일련번호 */
	@Digits
	private Integer optSn;
	
	/** 옵션내용 */
	@MaxLength(max = 1000)
	private String optCn;
	
	/** 복수선택수 */
	@Digits
	private Integer plChcCnt;
	
	/** 옵션내용 모음 */
	private List<String> optCns;
	
	/** 사용여부 */
	@Required
	private String useYn;
	
	/** 항목 사용여부 */
	private String iemUseYn;
	
	/** 항목일련벼호 모음 */
	private String[] artclSns;
	
	/** 항목 정렬 일련번호 */
	private Integer sortSn;
	
	/** 항목 정렬 일련번호 */
	private Integer iemSortSn;

	
	public Integer getArtclSn() {
		return artclSn;
	}

	public void setArtclSn(Integer artclSn) {
		this.artclSn = artclSn;
	}

	public String getArtclNm() {
		return artclNm;
	}

	public void setArtclNm(String artclNm) {
		this.artclNm = artclNm;
	}

	public String getArtclExpln() {
		return artclExpln;
	}

	public void setArtclExpln(String artclExpln) {
		this.artclExpln = artclExpln;
	}

	public String getArtclTypeCd() {
		return artclTypeCd;
	}

	public void setArtclTypeCd(String artclTypeCd) {
		this.artclTypeCd = artclTypeCd;
	}

	public Integer getFormSn() {
		return formSn;
	}

	public void setFormSn(Integer formSn) {
		this.formSn = formSn;
	}

	public Integer getGroupSn() {
		return groupSn;
	}

	public void setGroupSn(Integer groupSn) {
		this.groupSn = groupSn;
	}

	public String getEsntlYn() {
		return esntlYn;
	}

	public void setEsntlYn(String esntlYn) {
		this.esntlYn = esntlYn;
	}

	public String getLmtVl() {
		return lmtVl;
	}

	public void setLmtVl(String lmtVl) {
		this.lmtVl = lmtVl;
	}

	public String getInitVl() {
		return initVl;
	}

	public void setInitVl(String initVl) {
		this.initVl = initVl;
	}

	public Integer getLmtFileSz() {
		return lmtFileSz;
	}

	public void setLmtFileSz(Integer lmtFileSz) {
		this.lmtFileSz = lmtFileSz;
	}

	public String getPrmsnFileExtnMttr() {
		return prmsnFileExtnMttr;
	}

	public void setPrmsnFileExtnMttr(String prmsnFileExtnMttr) {
		this.prmsnFileExtnMttr = prmsnFileExtnMttr;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getGroupNm() {
		return groupNm;
	}

	public void setGroupNm(String groupNm) {
		this.groupNm = groupNm;
	}

	public String getGroupExpln() {
		return groupExpln;
	}

	public void setGroupExpln(String groupExpln) {
		this.groupExpln = groupExpln;
	}

	public Integer getPlChcCnt() {
		return plChcCnt;
	}

	public void setPlChcCnt(Integer plChcCnt) {
		this.plChcCnt = plChcCnt;
	}

	public String getOptCn() {
		return optCn;
	}

	public void setOptCn(String optCn) {
		this.optCn = optCn;
	}

	public Integer getOptSn() {
		return optSn;
	}

	public void setOptSn(Integer optSn) {
		this.optSn = optSn;
	}

	public List<String> getOptCns() {
		return optCns;
	}

	public void setOptCns(List<String> optCns) {
		this.optCns = optCns;
	}

	public String getUseYn() {
		return useYn;
	}

	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}

	public String getIemUseYn() {
		return iemUseYn;
	}

	public void setIemUseYn(String iemUseYn) {
		this.iemUseYn = iemUseYn;
	}

	public Integer getSortSn() {
		return sortSn;
	}

	public void setSortSn(Integer sortSn) {
		this.sortSn = sortSn;
	}

	public String[] getArtclSns() {
		return artclSns;
	}

	public void setArtclSns(String[] artclSns) {
		this.artclSns = artclSns;
	}

	public Integer getIemSortSn() {
		return iemSortSn;
	}

	public void setIemSortSn(Integer iemSortSn) {
		this.iemSortSn = iemSortSn;
	}
	
}
    


