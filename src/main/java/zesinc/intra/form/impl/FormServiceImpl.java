package zesinc.intra.form.impl;


import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.poi.hssf.usermodel.HSSFHyperlink;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.stereotype.Service;

import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import zesinc.component.excel.download.ExcelDownloadDAO;
import zesinc.component.excel.download.domain.ExcelDownloadVO;
import zesinc.component.file.FileService;
import zesinc.component.file.domain.FileVO;
import zesinc.core.lang.Validate;
import zesinc.intra.form.FormMapper;
import zesinc.intra.form.FormService;
import zesinc.intra.form.domain.FormDomnVO;
import zesinc.intra.form.domain.FormGroupIemVO;
import zesinc.intra.form.domain.FormGroupVO;
import zesinc.intra.form.domain.FormIemOptVO;
import zesinc.intra.form.domain.FormInfoVO;
import zesinc.intra.form.domain.FormResponseVO;
import zesinc.web.support.helper.CacheHelper;
import zesinc.web.support.pager.Pager;

@Service("opFormService")
public class FormServiceImpl extends EgovAbstractServiceImpl implements FormService{
	
	/** SL4J 로깅 */
    private static Logger logger = LoggerFactory.getLogger(FormServiceImpl.class);
    
    @Resource(name = "opFormDao")
    private FormMapper opFormDao;
    
    // 첨부파일
    @Resource(name = "opFileService")
    private FileService opFileService;
    
    @Resource
    private ExcelDownloadDAO opExcelDownloadDao;
    
    /**
     * 폼 상세보기
     */
    @Override
	public FormInfoVO selectForm(FormInfoVO formInfoVO) {

		FormInfoVO dataVo = opFormDao.selectForm(formInfoVO);

		dataVo = opFormDao.selectForm(formInfoVO);
		// 도메인
		dataVo.setDomnList(opFormDao.selectFormDomnList(dataVo));

		// 첨부파일
		if (Validate.isNotEmpty(dataVo.getFileSn())) {
			dataVo.setFileList(opFileService.selectFileList(dataVo.getFileSn()));
		}

		return dataVo;
	}

	/**
	 * 폼 목록 조회
	 */
	@Override
	public Pager<FormInfoVO> selectFormPageList(FormInfoVO formInfoVO) {

		List<FormInfoVO> dataList = opFormDao.selectFormList(formInfoVO);
		Integer totalNum = opFormDao.selectFormListCount(formInfoVO);
		
		 
		List<FormGroupIemVO> mapList = opFormDao.selectFormListExcelTest(formInfoVO);
		List<HashMap<String, String>> dataMapList = opFormDao.selectFormMapList(formInfoVO);
		List<FormResponseVO> rspnsList = opFormDao.selectRspnsExcelTest(formInfoVO);
		

		JSONParser jsonParser = new JSONParser();
		
		String[] headers = {"테스트"};
        
        String[] items = {"테스트답변"};
        
        List<String> headersList = new ArrayList<>(Arrays.asList(headers));
        List<String> itemsList = new ArrayList<>(Arrays.asList(items));
        
        if(mapList != null) {
        	for (FormGroupIemVO name : mapList) {
        		headersList.add(name.getArtclNm());	//headers : 항목명
        		itemsList.add(name.getArtclNm());	//items : 매칭에 사용 -> 컬럼id(암호화값)
			}
        }
        
		try {
			if(rspnsList != null) {
				for (FormResponseVO formResponseVO : rspnsList) {
					Object obj = jsonParser.parse(formResponseVO.getRspnsAnsCn());
					String rspnsHeadNo = formResponseVO.getRspnsHeadNo().toString();
					
					JSONObject jsonObj = (JSONObject) obj;
					
					for (HashMap<String, String> dataMap : dataMapList) {
						String rspnsHeadNo2 = String.valueOf(dataMap.get("RSPNS_HEAD_NO"));
						if(rspnsHeadNo2.equals(rspnsHeadNo)) {
							for (int i = 0; i < mapList.size(); i++) {
								dataMap.put(mapList.get(i).getArtclNm(), (String) jsonObj.get(mapList.get(i).getArtclNm()));
							}
						}
					}
				}
			}
			
		} catch (org.json.simple.parser.ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		headers = headersList.toArray(headers);
		items = itemsList.toArray(items);
		
		for (HashMap<String, String> dataMap : dataMapList) {
			
			for (String items2: itemsList) {
				String test = dataMap.get(items2);
				System.out.println(test);
			}
		}
		return new Pager<FormInfoVO>(dataList, formInfoVO, totalNum);
	}

    /**
     * 폼 그룹 조회
     */
    @Override
    public HashMap<String,Object> selectFormGroupList(FormGroupVO formGroupVO) {
    	HashMap<String,Object>  resultMap= new HashMap<>();
    	
    	try {
    		FormGroupIemVO formGroupIemVO = new FormGroupIemVO();
    		FormIemOptVO formIemOptVO = new FormIemOptVO();
        	
    		//그릅 리스트
        	List<FormGroupVO> grpList = opFormDao.selectFormGroupList(formGroupVO);
        	
        	//항목 리스트
            formGroupIemVO.setFormSn(grpList.get(0).getFormSn());
        	List<FormGroupIemVO> iemList = selectFormGroupIemList(formGroupIemVO); 
        	
        	//옵션 리스트
        	formIemOptVO.setFormSn(grpList.get(0).getFormSn());
        	List<FormIemOptVO> optList = selectFormIemOptList(formIemOptVO); 

        	
            for(int i = 0; i < grpList.size(); i++) {
            	 
            	//그룹별 항목 삽입
            	List<FormGroupIemVO> iemListForGroup = new ArrayList<FormGroupIemVO>();
            	
            	for(FormGroupIemVO item : iemList) {
            		if(item.getGroupSn().equals(grpList.get(i).getGroupSn())) {
            			iemListForGroup.add(item);
            		}
            	}
             	resultMap.put("grpIemList_"+grpList.get(i).getGroupSn() , iemListForGroup);
 
             	//그룹 및 항목별 옵션 삽입
             	for(FormGroupIemVO item : iemListForGroup) {
                 	List<FormIemOptVO> optListForGroup = new ArrayList<FormIemOptVO>();
             		for(FormIemOptVO itemOpt : optList) {	
	            		if(item.getGroupSn().equals(itemOpt.getGroupSn())
	            				&& item.getArtclSn().equals(itemOpt.getArtclSn())
	            				) {
	            			optListForGroup.add(itemOpt);
	            		}
	            		resultMap.put("grpOptList_"+grpList.get(i).getGroupSn()+"_"+item.getArtclSn() , optListForGroup);
            		}
           		}
            }
            resultMap.put("grpList" , grpList);
		} catch (Exception e) {
			System.out.println(e);
		}
       
   	return resultMap;
   }
	

   /*
    * 시작일 체크
    */
   @Override
   public Integer chkBgngYmd(FormInfoVO formInfoVO) {
	
	    FormInfoVO dataVo = opFormDao.selectForm(formInfoVO);
	   	Date getChkBgngYmd = dataVo.getBgngYmd();
	   	String ChkRspnsNope = dataVo.getRspnsNope();
	   	
	   	// 시작일 생성
	   	String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(getChkBgngYmd);
	   	
	   	// 오늘 날짜 생성
	   	String todayfm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(System.currentTimeMillis()));
	   	
	   	// yyyy-MM-dd HH:mm:ss 형식으로 포맷 설정
	   	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	   	
	   	// 시작일(date)과 오늘 날짜(todayfm)를 데이터 포맷으로 변경
	   	Date today = null;
			try {
				today = new Date(dateFormat.parse(todayfm).getTime());
			} catch (ParseException e) {
				e.printStackTrace();
			}
	   	
	   	Date ymd = null;
			try {
				ymd = new Date(dateFormat.parse(date).getTime());
			} catch (ParseException e) {
				e.printStackTrace();
			}
		
		// compareTo 메서드를 이용한 날짜 비교
	   	int compare = ymd.compareTo(today);
	   	
	   	//시작일이 지나지 않았을 때
	   	if(compare > 0) {
	   		compare = 2;
	   	// 시작일이 지나고 응답인원이 없을 때
	   	}else if(compare <= 0 && ChkRspnsNope == null) {
	   		compare = -1;
	    // 시작일이 지나고 응답인원이 있을 때
	   	}else if(compare <= 0 && Integer.parseInt(ChkRspnsNope) != 0) {
	   		System.out.println(ChkRspnsNope);
	   		compare = -2;
	   	}	   	

	   	return compare;
	   		   	   	
	   }
   
	/**
	 * 폼 그룹 항목 조회
	 */
	@Override
	public List<FormGroupIemVO> selectFormGroupIemList(FormGroupIemVO formGroupIemVO) {

		List<FormGroupIemVO> dataList = opFormDao.selectFormGroupIemList(formGroupIemVO);

		return dataList;
	}

	/**
	 * 폼 그룹 항목 옵션 조회
	 */
	@Override
	public List<FormIemOptVO> selectFormIemOptList(FormIemOptVO formIemOptVO) {

		List<FormIemOptVO> dataList = opFormDao.selectFormIemOptList(formIemOptVO);

		return dataList;
	}

	/**
	 * 폼 신규 등록
	 */
	@Override
	public Integer insertForm(FormInfoVO formInfoVO) {

		// 파일
		if (Validate.isNotEmpty(formInfoVO.getFileList())) {
			Integer fileSn = opFileService.insertFileList(formInfoVO.getFileList());
			formInfoVO.setFileSn(fileSn);
		}

		if (Validate.isNotEmpty(formInfoVO.getBgngYmd())) {
			formInfoVO.setBgngYmd(formInfoVO.getBgngYmd());
		}
		
		if (Validate.isNotEmpty(formInfoVO.getEndYmd())) {
			formInfoVO.setEndYmd(formInfoVO.getEndYmd()); 
		}
		 

		if (Validate.isNotEmpty(formInfoVO.getBgngYmdStr())) {
			formInfoVO.setBgngYmdStr(formInfoVO.getBgngYmdStr() + " " + formInfoVO.getBgngHr());
		}
	  
		if (Validate.isNotEmpty(formInfoVO.getEndYmdStr())) {
			formInfoVO.setEndYmdStr(formInfoVO.getEndYmdStr() + " " + formInfoVO.getEndHr());
		}
		

		Integer insertCnt = opFormDao.insertForm(formInfoVO);

		// 도메인

		if (Validate.isNotEmpty(formInfoVO.getSiteSns())) {
			FormDomnVO formDomnVo = new FormDomnVO();
			formDomnVo.setFormSn(formInfoVO.getFormSn());

			for (Integer siteSn : formInfoVO.getSiteSns()) {
				formDomnVo.setSiteSn(siteSn);
				opFormDao.insertFormDomn(formDomnVo);
			}
		}
		return insertCnt;

	}

	/**
	 * 폼 그룹 등록
	 */
	@Override
    public Integer insertFormGroup(FormGroupVO formGroupVO) {
		
        //그룹 추가
        Integer insertCnt = 0;
        insertCnt += opFormDao.insertFormGroup(formGroupVO);

        return insertCnt;
    }
    
    /**
     * 폼  항목 등록
     * @param formGroupIemVo
     * @return
     */
    @Override
    public Integer insertFormGroupIem(FormGroupIemVO formGroupIemVO) {
        
        Integer insertCnt = 0;
        
        List<String> optCns = formGroupIemVO.getOptCns();
             
        insertCnt += opFormDao.insertFormGroupIem(formGroupIemVO);
        
        //옵션 추가 (셀렉트박스, 라디오박스, 체크박스 해당)
        if(optCns != null) {
        	FormIemOptVO paramVo = new FormIemOptVO();
            paramVo.setArtclSn(formGroupIemVO.getArtclSn());
            paramVo.setGroupSn(formGroupIemVO.getGroupSn());
            paramVo.setFormSn(formGroupIemVO.getFormSn());
           
            for(String iem : optCns) {
                paramVo.setOptCn(iem);
                paramVo.setFormSn(formGroupIemVO.getFormSn());
                paramVo.setGroupSn(formGroupIemVO.getGroupSn());

                opFormDao.insertFormGroupIemOpt(paramVo);
            }
        }
        updateDataYn(formGroupIemVO.getFormSn());
        return insertCnt;
    }
    
    /**
     * 폼 응답관리 조회
     */
    @Override
    public Pager<FormResponseVO> selectFormResponsePageList(FormResponseVO formResponseVO) {
    	Integer totalNum = opFormDao.selectFormResponseListCount(formResponseVO);
    	
    	//항목리스트
        FormGroupIemVO formGroupIemVO = new FormGroupIemVO();
        formGroupIemVO.setFormSn(formResponseVO.getInteger("q_formSn"));
        
    	//응답헤더 리스트
    	List<FormResponseVO> rspnsHeadList = opFormDao.selectFormResponseList(formResponseVO);	
        Integer fileSn = null;

        try {
        	
        	//응답항목명, 응답헤더답변내용 배열 생성
        	for(int i =0; i< rspnsHeadList.size(); i++) {
            	String[] rspnsArray = rspnsHeadList.get(i).getRspnsHeadAnsCn().split("\\[!@]",-1);
        		String[] artclNmArray = rspnsHeadList.get(i).getRspnsArtclNm().split("\\[!@]",-1);
            	for(int j =0; j< rspnsArray.length; j++) {	 
            		if(rspnsArray[j].equals("")) { 
	   					 rspnsArray[j] = "-";
	   				 }
            		else if(rspnsArray[j].contains("fileIem_")){ 
    	        		fileSn = Integer.parseInt(rspnsArray[j].substring(8, rspnsArray[j].length()));
    	        		FileVO fileVO = opFormDao.selectRspnsFile(fileSn);    	        		
    	        		rspnsArray[j] = fileVO.getFileSn() +"[##]"+ fileVO.getFileId() +"[##]"+ fileVO.getOrgnlFileNm();    				 
    	        	}
    	        	rspnsHeadList.get(i).setRspnsArtclNmList(Arrays.asList(artclNmArray));
    	        	rspnsHeadList.get(i).setRspnsHeadAnsCnList(Arrays.asList(rspnsArray));
            	}
            }
        	
        	//폼에 대한 응답자가 있는 상태에서 항목이 추가될 경우 기존 응답헤더 update (임시)
        	/*List<FormGroupIemVO> iemList = opFormDao.selectRspnsIemList(formGroupIemVO);
        	
        	FormResponseVO rspnsAnsList = new FormResponseVO();
            String[] rspnsAnsArray = new String[iemList.size()];
            String artclNms = "";
            String rspnsAns = "";
            
            
            
        	for(int i =0; i< rspnsHeadList.size(); i++) {
        		String[] rspnsArray = rspnsHeadList.get(i).getRspnsHeadAnsCn().split("\\[!@]",-1);

        		if(rspnsArray.length != iemList.size()) {
        			formResponseVO.setRspnsHeadNo(rspnsHeadList.get(i).getRspnsHeadNo());
        			//응답리스트
        	        List<FormResponseVO> rspnsList = opFormDao.selectRspns(formResponseVO);
        	        
        			//응답항목명 String 생성
                	for(int k =0; k< iemList.size(); k++) {
            			artclNms += iemList.get(k).getArtclNm();
            			artclNms +="[!@]";
                	}
                	
            		//응답헤더에 응답헤더답변내용 넣기
            		Arrays.fill(rspnsAnsArray, "-");
            		for(FormResponseVO rspns:rspnsList) {
                    	for(int k =0; k< iemList.size(); k++) {
            				if(rspns.getGroupSn().toString().equals(iemList.get(k).getGroupSn().toString()) 
            						&& rspns.getArtclSn().toString().equals(iemList.get(k).getArtclSn().toString())){					
            	        		if(rspns.getRspnsAnsCn() != null) {
            	    				if(iemList.get(k).getArtclTypeCd().equals("fileIem")) {
            	    					rspnsAnsArray[k] = "fileIem_"+rspns.getRspnsAnsCn();
            	    				}
            	    				else if(iemList.get(k).getArtclTypeCd().equals("addrIem")) {
            	    					String[] str = rspns.getRspnsAnsCn().split("\\[##]",-1);
            	    					rspnsAnsArray[k] = str[1] + " " + str[2] + " (" + str[0] + ")";
            	    				}
            	    				else if(rspns.getRspnsAnsCn().contains("[##]")){ 
            	    	        		String[] str = rspns.getRspnsAnsCn().split("\\[##]",-1);
            	    	        		rspnsAnsArray[k] = "";
            	    	        		for (int j = 0; j < str.length; j++) {
            	    	        			rspnsAnsArray[k] += str[j] + " / ";	
            	    					}
            	    	        		rspnsAnsArray[k] = rspnsAnsArray[k].substring(0, rspnsAnsArray[k].length() - 2);
            	    	        	}
            	    				else {
            	    					rspnsAnsArray[k] = rspns.getRspnsAnsCn();
            	    				}
            	    				
            	    			}
            				}
                    	}
                    }
            		
            		for(int k =0; k< rspnsAnsArray.length; k++) {
            			rspnsAns += rspnsAnsArray[k] + "[!@]";
            		}
            		rspnsAnsList.setRspnsAnsCn(rspnsAns.substring(0, rspnsAns.length() - 4));
            		rspnsAnsList.setRspnsHeadNo(rspnsHeadList.get(i).getRspnsHeadNo()); 
            		rspnsAnsList.setRspnsArtclNm(artclNms.substring(0, artclNms.length() - 4));
            		opFormDao.updateRspnsHeadAnsCn(rspnsAnsList);
            		
        		}
        		
    			String[] artclNmArray = rspnsHeadList.get(i).getRspnsArtclNm().split("\\[!@]",-1);
            	for(int j =0; j< rspnsArray.length; j++) {	 
            		if(rspnsArray[j].equals("")) { 
	   					 rspnsArray[j] = "-";
	   				 }
            		else if(rspnsArray[j].contains("fileIem_")){ 
    	        		fileSn = Integer.parseInt(rspnsArray[j].substring(8, rspnsArray[j].length()));
    	        		FileVO fileVO = opFormDao.selectRspnsFile(fileSn);    	        		
    	        		rspnsArray[j] = fileVO.getFileSn() +"[##]"+ fileVO.getFileId() +"[##]"+ fileVO.getOrgnlFileNm();    				 
    	        	}
    	        	rspnsHeadList.get(i).setRspnsArtclNmList(Arrays.asList(artclNmArray));
    	        	rspnsHeadList.get(i).setRspnsHeadAnsCnList(Arrays.asList(rspnsArray));
            	}
    			
        	}*/
        	
		} catch (Exception e) {
			e.printStackTrace();
		}
        return new Pager<FormResponseVO>(rspnsHeadList, formResponseVO, totalNum);
    }
    
    
    /**
     * 폼 수정
     */
    @Override
    public Integer updateForm(FormInfoVO formInfoVO) {
       
       
       FormInfoVO dataVo = selectForm(formInfoVO);


         // 첨부파일
         if(Validate.isNotEmpty(formInfoVO.getFileIds())) {
             opFileService.deleteFiles(dataVo.getFileSn(), formInfoVO.getFileIds());
         }
         if(Validate.isNotEmpty(formInfoVO.getFileList())) {
             Integer fileSn = opFileService.insertFileList(dataVo.getFileSn(), formInfoVO.getFileList());
             formInfoVO.setFileSn(fileSn);
         }

         // 도메인
         if(Validate.isNotEmpty(formInfoVO.getSiteSns())) {
             // 기존 목록 삭제
             opFormDao.deleteFormDomn(formInfoVO.getFormSn());

             FormDomnVO formDomnVo = new FormDomnVO();
             formDomnVo.setFormSn(formInfoVO.getInteger("q_formSn"));
             for(Integer siteSn : formInfoVO.getSiteSns()) {
                formDomnVo.setSiteSn(siteSn);
                 opFormDao.insertFormDomn(formDomnVo);
             }
         }

         if(Validate.isNotEmpty(formInfoVO.getBgngYmd())) {
            formInfoVO.setBgngYmd(formInfoVO.getBgngYmd());
         }
         if(Validate.isNotEmpty(formInfoVO.getEndYmd())) {
            formInfoVO.setEndYmd(formInfoVO.getEndYmd());
         }
         if (Validate.isNotEmpty(formInfoVO.getBgngYmdStr())) {
 			formInfoVO.setBgngYmdStr(formInfoVO.getBgngYmdStr() + " " +formInfoVO.getBgngHr());
 		 }

         if (Validate.isNotEmpty(formInfoVO.getEndYmdStr())) {
 			formInfoVO.setEndYmdStr(formInfoVO.getEndYmdStr() + " " +formInfoVO.getEndHr());
 		 }

         return opFormDao.updateForm(formInfoVO);
    }

	
	/**
	 * 폼 그룹 수정
	 */
	@Override
	public Integer updateFormGroup(FormGroupVO formGroupVo) {
		Integer updateCnt = 0;
        updateCnt += opFormDao.updateFormGroup(formGroupVo);

        updateDataYn(formGroupVo.getFormSn());
        
        return updateCnt;
	}

	/**

     * 폼 그룹 항목 수정
     * delete -> insert 방식
     */
	@Override
	public Integer updateFormGroupIem(FormGroupIemVO formGroupIemVO) {
    	
		Integer insertCnt = 0;
		Integer artclSn = 0;
		Integer sortSn = 0;
		
		artclSn = formGroupIemVO.getArtclSn();
		sortSn = formGroupIemVO.getSortSn();
		
		opFormDao.delIndivItem(formGroupIemVO);
		opFormDao.delIndivIemOpt(formGroupIemVO);
        
		
        List<String> optCns = formGroupIemVO.getOptCns();
        
        formGroupIemVO.setArtclSn(artclSn);
        formGroupIemVO.setSortSn(sortSn);
        
        insertCnt += opFormDao.formGroupIemUpdate(formGroupIemVO);
        
        //옵션 추가 (셀렉트박스, 라디오박스, 체크박스 해당)
        if(optCns != null) {
        	FormIemOptVO paramVo = new FormIemOptVO();
            paramVo.setArtclSn(formGroupIemVO.getArtclSn());
            int idx = 1;
            for(String iem : optCns) {
            	paramVo.setOptSn(idx++);
                paramVo.setOptCn(iem);
                paramVo.setFormSn(formGroupIemVO.getFormSn());
                paramVo.setGroupSn(formGroupIemVO.getGroupSn());

                opFormDao.formGroupIemOptUpdate(paramVo);
            }
        }
        updateDataYn(formGroupIemVO.getFormSn());
        return insertCnt;
	}
       

	/**
	 * 폼 목록 삭제
	 */
	@Override
	public Integer deleteFormList(FormInfoVO formInfoVO) {

		String[] formSns = formInfoVO.getFormSns().split(",");

		Integer affected = formSns.length;
		Integer deleteCnt = 0;

		FormInfoVO paramVo = new FormInfoVO();
		for (String cdId : formSns) {
			paramVo.setFormSn(Integer.parseInt(cdId));
			deleteCnt += deleteForm(paramVo);
		}

		// 전체 선택건과 실 삭제 건의 차이를 반환
		affected = affected - deleteCnt;

		return affected;
	}

	/**
	 * 폼 삭제
	 */
	@Override
    public Integer deleteForm(FormInfoVO formInfoVO) {

        Integer affected = 0;
        formInfoVO.addParam("q_formSn", formInfoVO.getFormSn());

        opFormDao.deleteFormGroup(formInfoVO.getFormSn());
        opFormDao.deleteFormGroupItem(formInfoVO.getFormSn());
        opFormDao.deleteFormItemOpt(formInfoVO.getFormSn());
        opFormDao.deleteFormResponseHead(formInfoVO.getFormSn());
        opFormDao.deleteFormResponse(formInfoVO.getFormSn());
        opFormDao.deleteFormDomn(formInfoVO.getFormSn());
        
        affected += opFormDao.deleteForm(formInfoVO.getFormSn());


        return affected;
    }
    
    
    
    
    /**
     * 폼 응답관리 목록 삭제
     */
    @Override
    public Integer deleteIndivResponseList(FormResponseVO formResponseVO) {

        String[] rspnsHeadNos = formResponseVO.getRspnsHeadNos().split(",");

        Integer affected = rspnsHeadNos.length;
        Integer deleteCnt = 0;
        formResponseVO.setRspnsCnt(affected);
        
        FormResponseVO paramVo = new FormResponseVO();
        
        for(String cdId : rspnsHeadNos) {
            paramVo.setRspnsHeadNo(Integer.parseInt(cdId));
            deleteCnt += deleteIndivResponseHead(paramVo);
            
        }
        opFormDao.updateFormRspnsCnt(formResponseVO);
        
        // 전체 선택건과 실 삭제 건의 차이를 반환
        affected = affected - deleteCnt;

        return affected;
    }

	/**
	 * 폼 그룹 삭제
	 */
	@Override
    public Integer deleteFormGroup(FormGroupVO formGroupVO) {
        Integer affected = 0;
        
        //항목, 항목옵션, 응답, 그룹 삭제
        opFormDao.delIndivGroupIem(formGroupVO);
        opFormDao.delIndivGroupIemOpt(formGroupVO);
        opFormDao.delIndivGroupRspns(formGroupVO);

        affected += opFormDao.delIndivGroup(formGroupVO);
        
        //그룹 삭제 후 해당 폼에 대한 그룹이 없으면 DATA_YN을 N으로 변경
        updateDataYn(formGroupVO.getFormSn());

        return affected;
    }

	 /* 
	 * /** 폼 항목 삭제
	 */
	@Override
    public Integer delIndivItem(FormGroupIemVO formGroupIemVO) {

        Integer affected = 0;
        
        opFormDao.delIndivIemOpt(formGroupIemVO);
        opFormDao.delIndivIemRspns(formGroupIemVO);
        
        affected += opFormDao.delIndivItem(formGroupIemVO);
        
        //항목 삭제 후 해당 폼에 대한 그룹이 없으면 DATA_YN을 N으로 변경
        updateDataYn(formGroupIemVO.getFormSn());
        
        return affected;
    }

	/**
     * 폼 항목 옵션 삭제
     */
    @Override
    public Integer delIndivIemOpt(FormGroupIemVO formGroupIemVO) {

        Integer affected = 0;
        
        affected += opFormDao.delIndivIemOpt(formGroupIemVO);

        return affected;
    }

	/**
	 * 폼 응답관리 삭제
	 */
	@Override
    public Integer deleteIndivResponseHead(FormResponseVO formResponseVO) {

        Integer affected = 0;
        formResponseVO.addParam("q_rspnsHeadNo", formResponseVO.getRspnsHeadNos());

        opFormDao.deleteIndivResponse(formResponseVO.getRspnsHeadNo());

        affected += opFormDao.deleteIndivResponseHead(formResponseVO.getRspnsHeadNo());


        return affected;
    }

	@Override
	public Boolean reloadCache() {
		CacheHelper.cacheReload("opBbsConfigCache");

		return Boolean.TRUE;
	}
	

	/**
	 * 폼 응답관리 엑셀 출력
	 * @return 
	 */
	@Override
	public ArrayList<List<String>> selectFormExcelList(String queryId, String[] headerId, String[] headerNm, ExcelDownloadVO excelDownloadVo) {
		logger.debug("=====================selectFormExcelList===============================");
		List<Map> rspnsList = opExcelDownloadDao.dataList(queryId, excelDownloadVo);
        List<FormResponseVO> dataList = opFormDao.selectFormResponse(excelDownloadVo);
        logger.debug("=====================dataList===============================" +dataList);
	    String[] headerNms = null;
	    String[] rspnsArray = null;
    
	    ArrayList<String> artclNmArrayList = new ArrayList<String>(Arrays.asList(headerNm));
	    ArrayList<String> idArrayList = new ArrayList<String>(Arrays.asList(headerId));
	    Integer fileSn = null;
	    
	    List<FormGroupIemVO> selectFormRspnsOptTest = opFormDao.selectFormRspnsOptTest(excelDownloadVo);
	    List<FormResponseVO> selectFormRspnsTest = opFormDao.selectFormRspnsTest(excelDownloadVo);
	    
	    HashMap<String, String> artclNms = new HashMap<String, String>();
	    for (int i = 0; i < selectFormRspnsOptTest.size(); i++) {
	    	String key = null;
	    	key = selectFormRspnsOptTest.get(i).getGroupSn() + "_" + selectFormRspnsOptTest.get(i).getArtclSn();
	    	
	    	String value = null;
	    	value = selectFormRspnsOptTest.get(i).getArtclNm();
	    	
	    	if(selectFormRspnsOptTest.get(i).getArtclTypeCd().equals("fileIem")) {
	    		key += selectFormRspnsOptTest.get(i).getArtclTypeCd();
	    	}
	    	
	    	artclNms.put(key, value);
	    	artclNmArrayList.add(value);
	    	idArrayList.add(key);
	    	
		}
	    
	    
    	for (int j = 0; j < rspnsList.size(); j++) {
    		for (int k = 0; k < selectFormRspnsTest.size(); k++) {
    			
    			String key2 = null;
    			String value = null;
    	    	key2 = selectFormRspnsTest.get(k).getGroupSn() + "_" + selectFormRspnsTest.get(k).getArtclSn();

    	    	
    	    	String rspnsHeadNo = rspnsList.get(j).get("RSPNS_HEAD_NO").toString();
    	    	if(selectFormRspnsTest.get(k).getRspnsHeadNo().equals(Integer.parseInt(rspnsHeadNo))) {	
    	    		for (String key : idArrayList) {
    	    			if(key.equals(key2)) {
        	    			value = selectFormRspnsTest.get(k).getRspnsAnsCn();
        	    			
        	    			//HashMap<String, String> rspnsAnsCns = new HashMap<String, String>();
        	    			//rspnsAnsCns.put(key2, value);
        	    			rspnsList.get(j).put(key2, value);
        	    		}
    	    			else if(key.contains("fileIem") && key.contains(key2)) {
    	    				key2 = key;
    	    				
    	    				fileSn = Integer.parseInt(selectFormRspnsTest.get(k).getRspnsAnsCn());
			        		FileVO fileVO = opFormDao.selectRspnsFile(fileSn);
			        		value = fileVO.getOrgnlFileNm() 
			        				+ "[##]" + "http://localhost:9900/component/file/ND_fileDownload.do?q_fileSn="+fileVO.getFileSn() + "&q_fileId=" + fileVO.getFileId();
			        		
			        		rspnsList.get(j).put(key2, value);
    	    			}
					}
    	    		
    	    	}
			}
	    
	    	
		}

		// 출력할 목록을 불러온다.
        ArrayList<List<String>> excelList = new ArrayList<List<String>>();
      
        for(int i = 0 ; i < rspnsList.size() ; i++) {
            if(i == 0) {
                // 리스트의 첫라인에 컬럼명을 추가
            	
            	ArrayList<String> headerNmList = artclNmArrayList;
                excelList.add(i, headerNmList);
            }

            HashMap dataMap = (HashMap) rspnsList.get(i);

            List<String> rowData = new ArrayList<String>();
            for(int j = 0 ; j < idArrayList.size() ; j++) {
                // 헤더ID를 기준으로 출력할 목록을 재가공한다.(null인 경우에는 공백처리)
                String colData = dataMap.get(idArrayList.get(j)) == null ? "" : String.valueOf(dataMap.get(idArrayList.get(j)));
                rowData.add(colData);

                
            }
            excelList.add(i + 1, rowData);
        }
        logger.debug("======================end======================");
		return excelList;
	}

	/**
	 * 폼 응답관리 엑셀 생성
	 */
	@Override
	public HSSFWorkbook formExcelCreate(ArrayList<List<String>> excelList) {
		 HSSFWorkbook workbook = new HSSFWorkbook();

	        HSSFSheet sheet = workbook.createSheet();

	        CellStyle hyperLinkStyle = workbook.createCellStyle();
	        Font hyperLinkFont = workbook.createFont();
	        
	        
	        Cell cell = null;
	        String[] rspnsArray = null;
	        // row수 만큼 loop를 돌림
	        for(int i = 0 ; i < excelList.size() ; i++) {
	            Row row = sheet.createRow(i);
	            List<String> celList = excelList.get(i);
	            int colIndex = 0;

	            // col수 만큼 loop를 돌면서 각각 col에 데이터 삽입
	            for(int j = 0 ; j < celList.size() ; j++) {
	                
	            	//파일항목에 대한 하이퍼링크 설정
	                if(celList.get(j).contains("localhost")) {
	                	HSSFHyperlink test = new HSSFHyperlink(HSSFHyperlink.LINK_URL);
	                	rspnsArray = celList.get(j).split("\\[##]");
	                	
	                	test.setAddress(rspnsArray[1].trim());
	                	
	                	cell = row.createCell(colIndex++);
	                	
	                	cell.setCellValue(rspnsArray[0].trim());
	                	cell.setHyperlink(test);
	                	hyperLinkFont.setUnderline(Font.U_SINGLE);
	        	        hyperLinkFont.setColor(IndexedColors.BLUE.getIndex());
	        	        hyperLinkStyle.setFont(hyperLinkFont);
	                	cell.setCellStyle(hyperLinkStyle);
	                }
	                else if(celList.get(j).contains("[##]")) {
	                	celList.set(j, celList.get(j).replace("[##]", ","));
	                	row.createCell(colIndex++).setCellValue(celList.get(j));
	                }
	                else {
	                	row.createCell(colIndex++).setCellValue(celList.get(j));
	                    
	                }
	                sheet.autoSizeColumn(j);
		        	sheet.setColumnWidth(j, (sheet.getColumnWidth(j))+1024); //너비 더 넓게
		        	
	            }

	        }

	        return workbook;
	}
	
	/**
	 * 그룹 정렬순 수정
	 */
	@Override
	public Integer updateGroupSortSn(FormGroupVO formGroupVO) {
		Integer updateCnt = 0;

		int sortSn = 1;
		String[] groupSns = formGroupVO.getGroupSns();
        if(Validate.isNotEmpty(groupSns)) {
            for(String groupSn : groupSns) {
            	formGroupVO.setGroupSn(Integer.parseInt(groupSn));
            	formGroupVO.setSortSn(sortSn);
            	sortSn++;
            	
                updateCnt += opFormDao.updateGroupSortSn(formGroupVO);
            }
        }
        
        return updateCnt;
    
	}
	
	/**
	 * 항목 정렬순 수정
	 */
	@Override
	public Integer updateIemSortSn(FormGroupIemVO formGroupIemVO) {
		Integer updateCnt = 0;

		int sortSn = 1;
		String[] artclSns = formGroupIemVO.getArtclSns();
        if(Validate.isNotEmpty(artclSns)) {
            for(String artclSn : artclSns) {
            	formGroupIemVO.setArtclSn(Integer.parseInt(artclSn));
            	formGroupIemVO.setSortSn(sortSn);
            	sortSn++;
            	
                updateCnt += opFormDao.updateIemSortSn(formGroupIemVO);
            }
        }
        return updateCnt;
	}
	
	public void updateDataYn(Integer formSn) {
    	
    	FormInfoVO formInfoVO = new FormInfoVO();
		formInfoVO.setFormSn(formSn);
		
		List<FormInfoVO> pstg = opFormDao.selectGroupCount(formInfoVO);       
       
        if(pstg.size()==0) {
        	formInfoVO.setDataYn("N");
        	opFormDao.updateDataYn(formInfoVO);
		}
        else {
        	formInfoVO.setDataYn("Y");
        	opFormDao.updateDataYn(formInfoVO);
        }
    }
	
}
