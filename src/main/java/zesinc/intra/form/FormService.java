package zesinc.intra.form;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import zesinc.component.excel.download.domain.ExcelDownloadVO;
import zesinc.intra.form.domain.FormGroupIemVO;
import zesinc.intra.form.domain.FormGroupVO;
import zesinc.intra.form.domain.FormIemOptVO;
import zesinc.intra.form.domain.FormInfoVO;
import zesinc.intra.form.domain.FormResponseVO;
import zesinc.web.support.pager.Pager;

public interface FormService {

	/**
	 * 폼 목록 조회
	 * @param formInfoVO
	 * @return
	 */
	Pager<FormInfoVO> selectFormPageList(FormInfoVO formInfoVO);
	
	
    /**
     * 폼 상세 조회
     *
     * @param bbsSetupVo
     * @return
     */
	FormInfoVO selectForm(FormInfoVO formInfoVO);
	
	
	/**
	 * 폼 신규 등록
	 */
	Integer insertForm(FormInfoVO formInfoVO);
	
	
	/**
	 * 폼 응답관리 조회
	 */
	Pager<FormResponseVO> selectFormResponsePageList(FormResponseVO formResponseVO);


   /**
    * 폼 그룹 등록
    * 
    * @param formGroupVO
    * @return
    */
   Integer insertFormGroup(FormGroupVO formGroupVO);

   
   /**
    * 폼 항목 등록
    * 
    * @param formGroupIemVo
    * @return
    */
   Integer insertFormGroupIem(FormGroupIemVO formGroupIemVO);
   
   
    /**
     * 폼 목록에서 삭제
     *
     * @param formInfoVO
     * @return
     */
    Integer deleteFormList(FormInfoVO formInfoVO);
    

    /**
     * 폼 삭제
     * @param formInfoVO
     * @return
     */
	Integer deleteForm(FormInfoVO formInfoVO);
	
	
    /**
     * 폼 응답관리 목록에서 삭제
     *
     * @param formResponseVO
     * @return
     */
    Integer deleteIndivResponseList(FormResponseVO formResponseVO);
    
    
    /**
     * 폼 응답관리 삭제
     * @param formResponseVO
     * @return
     */
	Integer deleteIndivResponseHead(FormResponseVO formResponseVO);
	
	
    /**
     * 폼 정보 변경시 캐시정보를 리로드한다.
     *
     * @return
     */
    Boolean reloadCache();


	/**
	 * 폼 수정
	 * @param formInfoVO
	 * @return
	 */
	Integer updateForm(FormInfoVO formInfoVO);


	/**
	 * 폼 그룹 조회
	 * @param formInfoVO
	 * @return
	 */
	HashMap<String,Object> selectFormGroupList(FormGroupVO formGroupVO);
	Integer chkBgngYmd(FormInfoVO formInfoVO);
	
	Integer deleteFormGroup(FormGroupVO formGroupVO);

	
	/**
	 * 폼 그룹 항목 수정
	 * @param formGroupIemVo
	 * @return
	 */
	Integer updateFormGroupIem(FormGroupIemVO formGroupIemVO);

	
	/**
	 * 폼 그룹 항목 조회
	 * @param formInfoVO
	 * @return
	 */
	List<FormGroupIemVO> selectFormGroupIemList(FormGroupIemVO formGroupIemVo);

	
	/**
	 * 폼 항목 삭제
	 * @param formGroupVO
	 * @return
	 */
	Integer delIndivItem(FormGroupIemVO formGroupIemVO);

	
	/**
	 * 폼 그룹 항목 옵션 조회
	 */
	List<FormIemOptVO> selectFormIemOptList(FormIemOptVO formIemOptVO);

	
	/**
	 * 폼 항목 삭제 -> 옵션 삭제
	 */
	Integer delIndivIemOpt(FormGroupIemVO formGroupIemVO);

	
	/**
	 * 폼 그룹 수정
	 */
	Integer updateFormGroup(FormGroupVO formGroupVo);

	
	/**
	 * 폼 응답관리 엑셀 수정
	 */
	ArrayList<List<String>> selectFormExcelList(String queryId, String[] headerId, String[] headerNm, ExcelDownloadVO excelDownloadVo);

	
	/**
	 * 폼 엑셀 생성
	 */
	HSSFWorkbook formExcelCreate(ArrayList<List<String>> excelList);

	
	/*
	 * 그룹 정렬순 수정
	 */
	Integer updateGroupSortSn(FormGroupVO formGroupVO);
	
	
	/**
	 * 항목 정렬순 수정
	 */
	Integer updateIemSortSn(FormGroupIemVO formGroupIemVO);

}
