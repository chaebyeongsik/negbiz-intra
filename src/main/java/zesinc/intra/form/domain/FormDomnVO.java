package zesinc.intra.form.domain;

import zesinc.web.vo.BaseVO;


public class FormDomnVO extends BaseVO {

    /** serialVersionUID */
   private static final long serialVersionUID = 6584508114404004193L;
   
   /** 폼 일련번호 */
    private Integer formSn;
    /** 사이트 일련번호 */
    private Integer siteSn;

    /** 도메인명 */
    private String siteNm;
    /** 도메인설명 */
    private String siteExpln;
    
    /**
     * Integer formSn을 반환
     * 
     * @return Integer formSn
     */
    public Integer getFormSn() {
        return formSn;
    }

    /**
     * formSn을 설정
     * 
     * @param formSn 을(를) Integer formSn로 설정
     */
    public void setFormSn(Integer formSn) {
        this.formSn = formSn;
    }

    /**
     * Integer siteSn을 반환
     * 
     * @return Integer siteSn
     */
    public Integer getSiteSn() {
        return siteSn;
    }

    /**
     * siteSn을 설정
     * 
     * @param siteSn 을(를) Integer siteSn로 설정
     */
    public void setSiteSn(Integer siteSn) {
        this.siteSn = siteSn;
    }

	public String getSiteNm() {
		return siteNm;
	}

	public void setSiteNm(String siteNm) {
		this.siteNm = siteNm;
	}

	public String getSiteExpln() {
		return siteExpln;
	}

	public void setSiteExpln(String siteExpln) {
		this.siteExpln = siteExpln;
	}
}