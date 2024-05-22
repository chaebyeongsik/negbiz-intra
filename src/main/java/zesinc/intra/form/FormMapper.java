package zesinc.intra.form;

import java.util.HashMap;
import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;
import zesinc.component.excel.download.domain.ExcelDownloadVO;
import zesinc.component.file.domain.FileVO;
import zesinc.intra.form.domain.FormDomnVO;
import zesinc.intra.form.domain.FormGroupIemVO;
import zesinc.intra.form.domain.FormGroupVO;
import zesinc.intra.form.domain.FormIemOptVO;
import zesinc.intra.form.domain.FormInfoVO;
import zesinc.intra.form.domain.FormResponseVO;

@Mapper("opFormDao")
public interface FormMapper {

	/**
	 * 폼 목록 조회
	 */
	List<FormInfoVO> selectFormList(FormInfoVO formInfoVO);
	
	
	/**
	 * 폼 목록 조회 건수
	 */
	Integer selectFormListCount(FormInfoVO formInfoVO);

    /**
     * 폼 상세 조회
     */
	FormInfoVO selectForm(FormInfoVO formInfoVO);


	/**
	 * 폼 신규 등록
	 */
	Integer insertForm(FormInfoVO formInfoVO);
	
    /**
     * 폼그룹 등록
     */
    Integer insertFormGroup(FormGroupVO formGroupVO);
   
    
    /**
     * 폼 항목 등록
     */
    Integer insertFormGroupIem(FormGroupIemVO formGroupIemVO);
    
    
    /**
     * 폼 그룹 및 항목 신규등록
     */
    Integer insertFormGroupBasic(FormInfoVO formInfoVO);

    
    /**
     * 폼 삭제
     */
    Integer deleteForm(Integer formSn);

    
    /**
     * 폼 그룹 삭제
     */
    Integer deleteFormGroup(Integer formSn);
	
    
    /**
     * 폼 그룹 항목 삭제
     */
    Integer deleteFormGroupItem(Integer formSn);
	
    
    /**
     * 폼 응답 전체 삭제
     */
    Integer deleteFormResponse(Integer formSn);
    
    
    /**
     * 폼 응답 헤더 전체 삭제
     */
    Integer deleteFormResponseHead(Integer rspnsHeadNo);
    
    
    /**
     * 폼 응답자 삭제
     */
    Integer deleteIndivResponse(Integer rspnsHeadNo);

    
    /**
     * 폼 응답자 삭제
     */
    Integer deleteIndivResponseHead(Integer rspnsHeadNo);
    

	/**
	 * 폼 응담관리 목록 조회
	 */
	List<FormResponseVO> selectFormResponseList(FormResponseVO formResponseVO);


	/**
	 * 폼 응답관리 목록 건수 조회
	 */
	Integer selectFormResponseListCount(FormResponseVO formResponseVO);


	/**
	 * 폼 기본정보 수정
	 */
	Integer updateForm(FormInfoVO formInfoVO);

	/**
	 * 폼 그룹 조회
	 */
	FormInfoVO selectFormGroup(FormInfoVO formInfoVO);

    /**
     * 폼 그룹 조회
     */
	List<FormGroupVO> selectFormGroupList(FormGroupVO formGroupVO);


	/**
	 * 그룹 및 항목 > 폼 그룹 삭제
	 */
	Integer delIndivGroup(FormGroupVO formGroupVO);
	
	
	/**
	 * 그룹 및 항목 > 폼 그룹 삭제 -> 항목
	 */
	Integer delIndivGroupIem(FormGroupVO formGroupVO);
	
	
	/**
	 * 그룹 및 항목 > 폼 그룹 삭제 -> 옵션
	 */
	Integer delIndivGroupIemOpt(FormGroupVO formGroupVO);


	/**
	 * 항목 삭제
	 */
	Integer delIndivItem(FormGroupIemVO formGroupIemVO);
	
	
	/**
	 * 항목 삭제 -> 옵션 삭제
	 */
	Integer delIndivItemOpt(FormGroupIemVO formGroupIemVO);
	
    /**
     * 폼 그룹 항목 조회
     */
	List<FormGroupIemVO> selectFormGroupIemList(FormGroupIemVO formGroupIemVO);

	/**
     * 폼 도메인 목록
     */
    List<FormDomnVO> selectFormDomnList(FormInfoVO formInfoVO);

    
    /**
     * 폼 도메인 등록
     */
    Integer insertFormDomn(FormDomnVO formDomnVO);


    /**
     * 폼 도메인 삭제
     */
	Integer deleteFormDomn(Integer formSn);


	/**
	 * 폼 항목 옵션 추가
	 */
	Integer insertFormGroupIemOpt(FormIemOptVO formIemOptVO);

	
	/**
     * 폼 그룹 항목 옵션 조회
     */
	List<FormIemOptVO> selectFormIemOptList(FormIemOptVO formIemOptVO);

	/**
	 * 폼 삭제 > 항목 옵션 삭제
	 */
	Integer deleteFormItemOpt(Integer formSn);


	/**
	 * 폼 항목 옵션 삭제
	 */
	Integer delIndivIemOpt(FormGroupIemVO formGroupIemVO);


	/**
	 * 폼 그룹 삭제 > 응답헤더 삭제
	 */
	Integer delIndivGroupRspnsHead(FormGroupVO formGroupVO);

	
	/**
	 * 폼 그룹 삭제 > 응답자 삭제
	 */
	Integer delIndivGroupRspns(FormGroupVO formGroupVO);
	
	
	/**
	 * 폼 항목 삭제 > 응답헤더 삭제
	 */
	Integer delIndivIemRspnsHead(FormGroupIemVO formGroupIemVO);

	
	/**
	 * 폼 항목 삭제 > 응답자 삭제
	 */
	Integer delIndivIemRspns(FormGroupIemVO formGroupIemVO);


	/**
	 * 폼 그룹 항목 수정
	 */
	Integer formGroupIemUpdate(FormGroupIemVO formGroupIemVO);
	
	
	/**
	 * 폼 그룹 항목 옵션 수정
	 */
	Integer formGroupIemOptUpdate(FormIemOptVO formIemOptVO);


	/**
	 * 폼 그룹 수정
	 */
	Integer updateFormGroup(FormGroupVO formGroupVo);

	
	/**
	 * 응답리스트 조회
	 */
	List<FormResponseVO> selectFormResponse(ExcelDownloadVO excelDownloadVo);

	/**
	 * 응답리스트 조회
	 */
	List<FormResponseVO> selectRspns(FormResponseVO formResponseVO);

	/**
	 * 응답관리 > 응답항목명 수정
	 */
	Integer updateRspnsHeadAnsCn(FormResponseVO noList);


	/**
	 * 엑셀출력
	 */
	FileVO selectRspnsFile(Integer fileSn);
	
	
	/*
	 * 그룹 정렬순 수정
	 */
	Integer updateGroupSortSn(FormGroupVO formGroupVO);
	
	
	/**
	 * 항목 정렬순 수정
	 */
	Integer updateIemSortSn(FormGroupIemVO formGroupIemVO);


	/*
	 * 응답관리 항목 출력
	 */
	List<FormGroupIemVO> selectRspnsIemList(FormGroupIemVO formGroupIemVO);


	/*
	 * 응답관리 삭제 시 신청인원 감소
	 */
	Integer updateFormRspnsCnt(FormResponseVO paramVo);


	/*
	 * 폼의 그룹 개수 조회
	 */
	List<FormInfoVO> selectGroupCount(FormInfoVO formInfoVO);


	/*
	 * 그룹유무 수정
	 */
	Integer updateDataYn(FormInfoVO formVO);
	
	
	List<FormResponseVO> selectFormRspnsTest(ExcelDownloadVO excelDownloadVo);
	
	List<FormGroupIemVO> selectFormRspnsOptTest(ExcelDownloadVO excelDownloadVo);


	List<FormGroupIemVO> selectFormListExcelTest(FormInfoVO formInfoVO);


	List<FormResponseVO> selectRspnsExcelTest(FormInfoVO formInfoVO);


	List<HashMap<String, String>> selectFormMapList(FormInfoVO formInfoVO);
	
}
