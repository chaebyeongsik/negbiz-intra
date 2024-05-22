package zesinc.intra.form.domain;

import java.util.List;

import zesinc.web.validate.annotation.marker.Digits;
import zesinc.web.validate.annotation.marker.MaxLength;
import zesinc.web.validate.annotation.marker.Required;

public class FormIemOptVO {

	/** serialVersionUID */
	private static final long serialVersionUID = 523754851954468135L;
	
	/** 폼일련번호 */
	@Digits
	private Integer formSn;
	
	/** 그룹일련번호 */
	@Digits
	private Integer groupSn;

	/** 항목일련번호 */
	@Required
	@Digits
	private Integer artclSn;

	/** 옵션일련번호 */
	@Required
	@Digits
	private Integer optSn;
	
	/** 옵션내용 */
	@MaxLength(max = 1000)
	private String optCn;
	
	
	private List<String> optCns;

	public Integer getArtclSn() {
		return artclSn;
	}

	public void setArtclSn(Integer artclSn) {
		this.artclSn = artclSn;
	}

	public Integer getOptSn() {
		return optSn;
	}

	public void setOptSn(Integer optSn) {
		this.optSn = optSn;
	}

	public String getOptCn() {
		return optCn;
	}

	public void setOptCn(String optCn) {
		this.optCn = optCn;
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

	public Integer getGroupSn() {
		return groupSn;
	}

	public void setGroupSn(Integer groupSn) {
		this.groupSn = groupSn;
	}

	public List<String> getOptCns() {
		return optCns;
	}

	public void setOptCns(List<String> optCns) {
		this.optCns = optCns;
	}
}
