package zesinc.intra.form.domain;

import java.util.Date;
import java.util.List;

import zesinc.web.validate.annotation.marker.Digits;
import zesinc.web.validate.annotation.marker.MaxLength;
import zesinc.web.validate.annotation.marker.Required;
import zesinc.web.vo.PageVO;

public class FormGroupVO extends PageVO {

	/** serialVersionUID */
	private static final long serialVersionUID = 984654851954468135L;

	/** 그룹일련번호 */
	@Required
	@Digits
	private Integer groupSn;

	/** 그룹명 */
	@Required
	@MaxLength(max = 100)
	private String groupNm;

	/** 그룹설명 */
	@MaxLength(max = 4000)
	private String groupExpln;

	/** 폼일련번호 */
	@Digits
	private Integer formSn;

	/** 등록자ID */
	@MaxLength(max = 20)
	private String rgtrId;
	
	/** 항목리스트 */
	private List<FormGroupIemVO> iemList;
	
	/** 사용여부 */
	@Required
	private String useYn;
	
	/** 그룹 일련번호 모음 */
	private String[] groupSns;
	
	/** 그룹 정렬 일련번호 */
	private Integer sortSn;
	
	/** 그룹 정렬 일련번호 */
	private Integer grpSortSn;

	/** 등록일시 */
	private Date regDt;
	
	/** 자료여부 */
	private String dataYn;
	

	public Integer getGroupSn() {
		return groupSn;
	}

	public void setGroupSn(Integer groupSn) {
		this.groupSn = groupSn;
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

	public Integer getFormSn() {
		return formSn;
	}

	public void setFormSn(Integer formSn) {
		this.formSn = formSn;
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

	public List<FormGroupIemVO> getIemList() {
		return iemList;
	}

	public void setIemList(List<FormGroupIemVO> iemList) {
		this.iemList = iemList;
	}

	public String getUseYn() {
		return useYn;
	}

	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}

	public String[] getGroupSns() {
		return groupSns;
	}

	public void setGroupSns(String[] groupSns) {
		this.groupSns = groupSns;
	}

	public Integer getSortSn() {
		return sortSn;
	}

	public void setSortSn(Integer sortSn) {
		this.sortSn = sortSn;
	}

	public Integer getGrpSortSn() {
		return grpSortSn;
	}

	public void setGrpSortSn(Integer grpSortSn) {
		this.grpSortSn = grpSortSn;
	}

	public String getDataYn() {
		return dataYn;
	}

	public void setDataYn(String dataYn) {
		this.dataYn = dataYn;
	}
	
	

}
