package zesinc.intra.form.domain;

import java.util.Date;
import java.util.List;

import zesinc.web.validate.annotation.marker.Digits;
import zesinc.web.validate.annotation.marker.MaxLength;
import zesinc.web.validate.annotation.marker.Required;
import zesinc.web.vo.PageVO;

public class FormResponseVO extends PageVO {
	
	/** serialVersionUID */
    private static final long serialVersionUID = 195147851954276287L;
    
/****** TN_FORM_RSPNS_OPT_HEAD **********************/
    
    /** 응답헤더번호 */
    @Digits
    private Integer rspnsHeadNo;

    /** 응답일시 */
    private Date rspnsDt;

    /** 응답자명 */
    @MaxLength(max = 100)
    private String rspnsNm;

    /** 응답자이메일아이디 */
    @MaxLength(max = 100)
    private String rspnsEmlId;

    /** 응답자이메일사이트명 */
    @MaxLength(max = 100)
    private String rspnsEmlSiteNm;

    /** 응답자지역전화번호 */
    @MaxLength(max = 4)
    private String rgnTelno;

    /** 응답자국전화번호 */
    @MaxLength(max = 4)
    private String telofcTelno;

    /** 응답자개별전화번호 */
    @MaxLength(max = 4)
    private String indivTelno;

    /** 응답항목명 */
    private String rspnsArtclNm;
    
    /** 응답헤더답변내용 */
    private String  rspnsHeadAnsCn;	
    
    /****** TN_FORM_RSPNS **********************/
    
    /** 폼 일련번호 */
    @Required
    @Digits
    private Integer formSn;

    /** 항목일련번호 */
    @Required
    @Digits
    private Integer artclSn;

    /** 응답일련번호 */
    @Required
    @Digits
    private Integer rspnsSn;
    
    /** 그룹일련번호 */
    @Required
    @Digits
    private Integer groupSn;
    
    /** 응답답변내용 */
    private String rspnsAnsCn;

    /** 응답헤더번호모음 */
    private String rspnsHeadNos;
    
    /** 응답항목명 리스트 */
    private List<String> rspnsArtclNmList;
    
    /** 응답헤더답변내용 리스트 */
    private List<String> rspnsHeadAnsCnList;
    
    /** 응답인원수 */
    private Integer rspnsCnt;


	public String getRgnTelno() {
		return rgnTelno;
	}

	public void setRgnTelno(String rgnTelno) {
		this.rgnTelno = rgnTelno;
	}

	public String getTelofcTelno() {
		return telofcTelno;
	}

	public void setTelofcTelno(String telofcTelno) {
		this.telofcTelno = telofcTelno;
	}

	public String getIndivTelno() {
		return indivTelno;
	}

	public void setIndivTelno(String indivTelno) {
		this.indivTelno = indivTelno;
	}

	public void setRspnsHeadNo(Integer rspnsHeadNo) {
		this.rspnsHeadNo = rspnsHeadNo;
	}

	public Integer getRspnsHeadNo() {
		return rspnsHeadNo;
	}

	public Date getRspnsDt() {
		return rspnsDt;
	}

	public void setRspnsDt(Date rspnsDt) {
		this.rspnsDt = rspnsDt;
	}

	public String getRspnsNm() {
		return rspnsNm;
	}

	public void setRspnsNm(String rspnsNm) {
		this.rspnsNm = rspnsNm;
	}

	public String getRspnsEmlId() {
		return rspnsEmlId;
	}

	public void setRspnsEmlId(String rspnsEmlId) {
		this.rspnsEmlId = rspnsEmlId;
	}

	public String getRspnsEmlSiteNm() {
		return rspnsEmlSiteNm;
	}

	public void setRspnsEmlSiteNm(String rspnsEmlSiteNm) {
		this.rspnsEmlSiteNm = rspnsEmlSiteNm;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Integer getFormSn() {
		return formSn;
	}

	public void setFormSn(Integer formSn) {
		this.formSn = formSn;
	}

	public Integer getArtclSn() {
		return artclSn;
	}

	public void setArtclSn(Integer artclSn) {
		this.artclSn = artclSn;
	}

	public Integer getRspnsSn() {
		return rspnsSn;
	}

	public void setRspnsSn(Integer rspnsSn) {
		this.rspnsSn = rspnsSn;
	}

	public String getRspnsHeadNos() {
		return rspnsHeadNos;
	}

	public void setRspnsHeadNos(String rspnsHeadNos) {
		this.rspnsHeadNos = rspnsHeadNos;
	}

	public String getRspnsArtclNm() {
		return rspnsArtclNm;
	}

	public void setRspnsArtclNm(String rspnsArtclNm) {
		this.rspnsArtclNm = rspnsArtclNm;
	}

	public String getRspnsHeadAnsCn() {
		return rspnsHeadAnsCn;
	}

	public void setRspnsHeadAnsCn(String rspnsHeadAnsCn) {
		this.rspnsHeadAnsCn = rspnsHeadAnsCn;
	}

	public List<String> getRspnsArtclNmList() {
		return rspnsArtclNmList;
	}

	public void setRspnsArtclNmList(List<String> rspnsArtclNmList) {
		this.rspnsArtclNmList = rspnsArtclNmList;
	}

	public List<String> getRspnsHeadAnsCnList() {
		return rspnsHeadAnsCnList;
	}

	public void setRspnsHeadAnsCnList(List<String> rspnsHeadAnsCnList) {
		this.rspnsHeadAnsCnList = rspnsHeadAnsCnList;
	}

	public String getRspnsAnsCn() {
		return rspnsAnsCn;
	}

	public void setRspnsAnsCn(String rspnsAnsCn) {
		this.rspnsAnsCn = rspnsAnsCn;
	}

	public Integer getGroupSn() {
		return groupSn;
	}

	public void setGroupSn(Integer groupSn) {
		this.groupSn = groupSn;
	}

	public Integer getRspnsCnt() {
		return rspnsCnt;
	}

	public void setRspnsCnt(Integer rspnsCnt) {
		this.rspnsCnt = rspnsCnt;
	}
}
