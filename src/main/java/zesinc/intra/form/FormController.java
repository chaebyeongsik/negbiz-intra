package zesinc.intra.form;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import zesinc.component.excel.download.ExcelDownloadService;
import zesinc.component.excel.download.domain.ExcelDownloadVO;
import zesinc.component.file.support.UploadHelper;
import zesinc.core.cache.CacheService;
import zesinc.core.config.Config;
import zesinc.core.lang.Validate;
import zesinc.intra.cms.support.domain.CmsReferenceVO;
import zesinc.intra.form.domain.FormGroupIemVO;
import zesinc.intra.form.domain.FormGroupVO;
import zesinc.intra.form.domain.FormInfoVO;
import zesinc.intra.form.domain.FormResponseVO;
import zesinc.login.domain.LoginVO;
import zesinc.web.auth.AuthSupport;
import zesinc.web.auth.AuthType;
import zesinc.web.auth.anotation.OpenworksAuth;
import zesinc.web.spring.controller.IntraController;
import zesinc.web.support.BaseConfig;
import zesinc.web.support.helper.OpHelper;
import zesinc.web.support.pager.Pager;
import zesinc.web.utils.MessageUtil;
import zesinc.web.validate.ValidateResultHolder;
import zesinc.web.validate.ValidateUtil;
import zesinc.web.vo.cache.DomnCacheVO;


@Controller("폼 관리")
@RequestMapping(value = { "/intra/form" })
public class FormController extends IntraController {
	
	@Resource(name = "opFormService")
    private FormService opFormService;
	
   
    @Resource(name = "opExcelDownloadService")
    private ExcelDownloadService service;
    
    /**
     * 사용자 메뉴관리에서 본 기능의 링크를 획득하기 위한 함수
     * 
     * @return
     */
    public List<CmsReferenceVO> getCmsRefrence(CmsReferenceVO cmsRVO) {
        List<CmsReferenceVO> reference = new ArrayList<CmsReferenceVO>();
        CmsReferenceVO cmsReferenceVo = new CmsReferenceVO();
        /*
         * 각 구분마다의 키를 할당한다. 게시판은 당연히 게시판 코드
         * 키로 사용할 무언가가 없다면 임의의 문자열을 설정한다.
         * 필수로 key 를 등록해야 한다. 없는 경우 CMS에서 오작동 함
         * 설문은 도메인으로 구분되기 때문에 도메인코드를 설정
         */
        cmsReferenceVo.setName("폼빌더");
        
        // 대표 URL
        cmsReferenceVo.setUserMainMenuUrl("/user/form/BD_selectFormList.do");
        // 자식 URL
        List<String> lwprtMenuUrl = new ArrayList<String>();
        lwprtMenuUrl.add("/user/form/BD_insertFormRspns.do");
        lwprtMenuUrl.add("/user/form/BD_selectForm.do");

        cmsReferenceVo.setLwprtMenuUrlAddr(lwprtMenuUrl);
        cmsReferenceVo.setMngrMenuUrlAddr("/intra/form/BD_selectFormList.do");
        reference.add(cmsReferenceVo);
        
        return reference;
    }
    
	/**
	 * 폼 관리 페이지 목록
	 * @throws ParseException 
	 * @throws NumberFormatException 
	 */
    @OpenworksAuth(authType = AuthType.READ)
    @RequestMapping(value = "BD_selectFormList.do")
    public void selectFormList(HttpServletRequest request, Model model, FormInfoVO formInfoVO) throws NumberFormatException, ParseException {

        @SuppressWarnings("unchecked")
        List<DomnCacheVO> domnList = (List<DomnCacheVO>) CacheService.get(BaseConfig.DOMN_LIST_CACHE_KEY);
        
        /*if(request.getParameter("q_siteSn")!= null) {
        	Integer seq = Integer.parseInt(request.getParameter("q_siteSn"));
        	formInfoVO.setFormSn(seq);
        }*/
        
        
        model.addAttribute(BaseConfig.KEY_DOMAIN_LIST, domnList);
        model.addAttribute(BaseConfig.KEY_PAGER, opFormService.selectFormPageList(formInfoVO));    
        
        
        String beginDt = "2022-01-01";
		String endDt = "2022-01-31";
		String beginTime = "09:00";
		String endTime = "18:00";
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		//SimpleDateFormat newSdf = new SimpleDateFormat("yyyyMMddHHmm");
		
		
		//int beginS = Integer.parseInt(newSdf.format(sdf.parse(beginDt) + " " + sdf.parse(beginTime)));
		//int endS = Integer.parseInt(newSdf.format(sdf.parse(endDt) + " " + sdf.parse(endTime)));	
		/*int now = Integer.parseInt(newSdf.format(new Date()));
		
		if(beginS > now) {
			System.out.println("접수대기");
		}
		else if(beginS <= now && now <=endS) {
			System.out.println("접수중");
		}
		else {
			System.out.println("마감");
		}*/
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Date beginS = dateFormat.parse(beginDt + " " + beginTime);
		Date endS = dateFormat.parse(endDt + " " + endTime);
		Date today = new Date();
		
		if(today.before(beginS)){
			
			System.out.println("접수대기");
		}   
		else if(today.after(beginS) || today.before(endS)){
			System.out.println("접수중");
		}
		else{
			System.out.println("마감");
		}
    }
    
    /**
	 * 폼 신규등록 폼
	 */
	@OpenworksAuth(authType = AuthType.BASIC)
	@RequestMapping(value = { "BD_insertFormInfo.do" })
	public void insertFormInfo(HttpServletRequest request, Model model, FormInfoVO formInfoVO) {

		@SuppressWarnings("unchecked")
		List<DomnCacheVO> domnList = (List<DomnCacheVO>) CacheService.get(BaseConfig.DOMN_LIST_CACHE_KEY);
		model.addAttribute(BaseConfig.KEY_DOMAIN_LIST, domnList);

		// 선착순여부, 게시여부, 작성자정보수집상태번호 default
		formInfoVO.setFrstcmYn(Config.getString("form-config.default-values.frstcmYn"));
		formInfoVO.setPstgYn(Config.getString("form-config.default-values.pstgYn"));
		formInfoVO.setWrtrInfoClctSttsNo(Config.getString("form-config.default-values.wrtrInfoClctSttsNo"));
 
		// 스크립트단 validator 정보
		ValidateResultHolder holder = ValidateUtil.validate(formInfoVO);
		model.addAttribute(BaseConfig.KEY_VALIDATE, holder);
		model.addAttribute(BaseConfig.KEY_DATA_VO, formInfoVO);

	}

	/**
	 * 폼 신규 등록
	 */
	@OpenworksAuth(authType = AuthType.BASIC)
	@RequestMapping(value = "ND_insertForm.do", method = RequestMethod.POST)
	public String insertForm(HttpServletRequest request, Model model, FormInfoVO formInfoVO) {

		// 검증
		ValidateResultHolder holder = ValidateUtil.validate(formInfoVO);
		if (holder.isValid()) {
			LoginVO loginVo = (LoginVO) getMgrSession();
			formInfoVO.setRgtrId(loginVo.getPicId());
			formInfoVO.setIpAddr(getIpAddr());
			formInfoVO.setFileList(UploadHelper.upload(request, "form", Boolean.TRUE));

			Integer key = opFormService.insertForm(formInfoVO);
			if (Validate.isEmpty(key)) {
				return alertAndBack(model, MessageUtil.getMessage("common.processFail"));
			}
		} else {
			return alertAndBack(model, MessageUtil.getMessage("common.validateFail"));
		}

		opFormService.reloadCache();

		String url = "BD_updateFormInfo.do?" + OpHelper.getReplaceParamValue(OpHelper.getSearchQueryString(request),
				"q_formSn", formInfoVO.getFormSn());
		return alertAndRedirect(model, MessageUtil.getMessage("common.insertOk"), url);

	}
    
    /**
    * 폼 수정 폼
    */
   @OpenworksAuth(authType = AuthType.READ)
   @RequestMapping(value = { "BD_updateFormInfo.do" })
   public void updateFormInfo(HttpServletRequest request, Model model, FormInfoVO formInfoVO) {

      @SuppressWarnings("unchecked")
      List<DomnCacheVO> domnList = (List<DomnCacheVO>) CacheService.get(BaseConfig.DOMN_LIST_CACHE_KEY);
      model.addAttribute(BaseConfig.KEY_DOMAIN_LIST, domnList);

      // 스크립트단 validator 정보
      ValidateResultHolder holder = ValidateUtil.validate(formInfoVO);
      model.addAttribute(BaseConfig.KEY_VALIDATE, holder);
      model.addAttribute(BaseConfig.KEY_DATA_VO, opFormService.selectForm(formInfoVO));
   }

   /**
    * 폼 수정
    */
   @OpenworksAuth(authType = AuthType.MANAGER)
   @RequestMapping(value = "ND_updateForm.do", method = RequestMethod.POST)
   public String updateForm(HttpServletRequest request, Model model, FormInfoVO formInfoVO) {
	   
      FormInfoVO dataVo = opFormService.selectForm(formInfoVO);
      // 권한 확인
      if(AuthSupport.isAuth(dataVo.getRgtrId())) {
    	  // 검증
	      ValidateResultHolder holder = ValidateUtil.validate(formInfoVO);
	      if (holder.isValid()) {
	         LoginVO loginVo = (LoginVO) getMgrSession();
	         formInfoVO.setMdfrId(loginVo.getPicId());
	         formInfoVO.setIpAddr(getIpAddr());
	         formInfoVO.setFileList(UploadHelper.upload(request, "form", Boolean.TRUE));
	
	         // 수정 실행
	         Integer key = opFormService.updateForm(formInfoVO);
	         if (key != 1) {
	            return alertAndBack(model, MessageUtil.getMessage("common.processFail"));
	         }
	      } else {
	         return alertAndBack(model, MessageUtil.getMessage("common.validateFail"));
	      }
	      String url = "BD_updateFormInfo.do?" + OpHelper.getSearchQueryString(request);
	      return alertAndRedirect(model, MessageUtil.getMessage("common.updateOk"), url);
      }
       return alertAndBack(model, MessageUtil.getMessage("common.notAllow"));
   }
	
    /**
     * 폼  그룹 폼
     */
    @OpenworksAuth(authType = AuthType.READ)
    @RequestMapping(value = "BD_insertFormGroup.do")
    public void insertFormGroupInfo(HttpServletRequest request, Model model, FormGroupVO formGroupVO, FormInfoVO formInfoVO) {

    	Integer chkymd = opFormService.chkBgngYmd(formInfoVO);
    	
    	model.addAttribute("chkymd", chkymd); 
    	
    	// 사용여부 default
    	formGroupVO.setUseYn(Config.getString("form-config.default-values.useYn"));
    	model.addAttribute(BaseConfig.KEY_DATA_VO, formGroupVO);
    	
        // 스크립트단 validator 정보
        ValidateResultHolder holder = ValidateUtil.validate(formGroupVO);
        model.addAttribute(BaseConfig.KEY_VALIDATE, holder);
        
    }
    
    /**
     * 폼  그룹 목록 폼
     * @param formInfoVO 
     */
    @OpenworksAuth(authType = AuthType.READ)
    @RequestMapping(value = "INC_selectFormGroupList.do")
    public void selectFormGroupList(HttpServletRequest request, Model model, FormGroupVO formGroupVO, FormInfoVO formInfoVO) {  
    	
        ValidateResultHolder holder = ValidateUtil.validate(formGroupVO);
        model.addAttribute(BaseConfig.KEY_VALIDATE, holder);	
    }
    
    /**
     * 폼  항목추가 폼
     */
    @OpenworksAuth(authType = AuthType.READ)
    @RequestMapping(value = "INC_insertGroupIem.do")
    public void insertGroupIem(HttpServletRequest request, Model model, FormGroupVO formGroupVO, FormGroupIemVO formGroupIemVO, FormInfoVO formInfoVO) {

        // 스크립트단 validator 정보
    	Integer chkymd = opFormService.chkBgngYmd(formInfoVO);		
    	
    	model.addAttribute("chkymd", chkymd);
    	
    	//필수여부 및 사용여부 default
    	formGroupIemVO.setUseYn(Config.getString("form-config.default-values.useYn"));
    	formGroupIemVO.setEsntlYn(Config.getString("form-config.default-values.esntlYn"));
    	
    	model.addAttribute(BaseConfig.KEY_DATA_VO, formGroupIemVO);
    	
        ValidateResultHolder holder = ValidateUtil.validate(formGroupIemVO);
        model.addAttribute(BaseConfig.KEY_VALIDATE, holder);
        
        model.addAttribute(BaseConfig.KEY_DATA_LIST, opFormService.selectFormGroupList(formGroupVO));
    }
      
    /**
     * 폼  그룹 등록
     */
    @OpenworksAuth(authType = AuthType.MANAGER)
    @RequestMapping(value = "ND_insertFormGroup.do", method = RequestMethod.POST)
    public String insertFormGroup(HttpServletRequest request, Model model, FormGroupVO formGroupVO) {

        // 검증
        ValidateResultHolder holder = ValidateUtil.validate(formGroupVO);
        if(holder.isValid()) {
            LoginVO loginVo
            = (LoginVO) getMgrSession();
            formGroupVO.setRgtrId(loginVo.getPicId());

            Integer key = opFormService.insertFormGroup(formGroupVO);
            if(key != 1) {
            	return responseJson(model, MessageUtil.FALSE, MessageUtil.getMessage("common.processFail"));
            }
        } else {
            return responseJson(model, MessageUtil.FALSE, MessageUtil.getMessage("common.validateFail"));
        }
        opFormService.reloadCache();
        
        return responseJson(model, MessageUtil.TRUE, MessageUtil.getMessage("common.insertOk"));
    }
       
    /**
     * 폼 항목 등록
     */
    @OpenworksAuth(authType = AuthType.MANAGER)
    @RequestMapping(value = "ND_insertFormGroupIem.do", method = RequestMethod.POST)
    public String insertFormGroupIem(HttpServletRequest request, Model model, FormGroupIemVO formGroupIemVO) {
        
        // 검증
        ValidateResultHolder holder = ValidateUtil.validate(formGroupIemVO);
        model.addAttribute(BaseConfig.KEY_VALIDATE, holder);
        if(holder.isValid()) {
            LoginVO loginVo = (LoginVO) getMgrSession();
            formGroupIemVO.setRgtrId(loginVo.getPicId());

            Integer key = opFormService.insertFormGroupIem(formGroupIemVO);
            if(key != 1) {
                return responseJson(model, MessageUtil.FALSE, MessageUtil.getMessage("common.processFail"));
            }
        } 
        else {
            return responseJson(model, MessageUtil.FALSE, MessageUtil.getMessage("common.validateFail"));
        }
        
        opFormService.reloadCache(); 
        return responseJson(model, MessageUtil.TRUE, MessageUtil.getMessage("common.insertOk"));
    }
    
    /**
     * 폼 관리 응답관리 폼
     */
    @OpenworksAuth(authType = AuthType.READ)
    @RequestMapping(value = "BD_selectFormResponseList.do")
    public void selectFormResponseList(HttpServletRequest request, Model model, FormResponseVO formResponseVO) {

        @SuppressWarnings("unchecked")
        List<DomnCacheVO> domnList = (List<DomnCacheVO>) CacheService.get(BaseConfig.DOMN_LIST_CACHE_KEY);
        model.addAttribute(BaseConfig.KEY_DOMAIN_LIST, domnList);
        
        model.addAttribute(BaseConfig.KEY_PAGER, opFormService.selectFormResponsePageList(formResponseVO));

    }
    
    /**
     * 폼 그룹 수정
     */
    @OpenworksAuth(authType = AuthType.MANAGER)
    @RequestMapping(value = "ND_updateFormGroup.do", method = RequestMethod.POST)
    public String updateFormGroup(HttpServletRequest request, Model model, FormGroupVO formGroupVo) {
        // 검증
        ValidateResultHolder holder = ValidateUtil.validate(formGroupVo);
        if(holder.isValid()) {
            LoginVO loginVo = (LoginVO) getMgrSession();
            formGroupVo.setRgtrId(loginVo.getPicId());
            
            // 수정 실행
            Integer key = opFormService.updateFormGroup(formGroupVo);
            if(key != 1) {
                return responseJson(model, MessageUtil.FALSE, key, MessageUtil.getMessage("common.processFail"));
            }
        } else {
            return responseJson(model, MessageUtil.FALSE, MessageUtil.getMessage("common.validateFail"));
        }

        opFormService.reloadCache();
        return responseJson(model, MessageUtil.TRUE, MessageUtil.getMessage("common.updateOk"));
    }
    
    /**
     * 폼 그룹 항목 수정
     */
    @OpenworksAuth(authType = AuthType.MANAGER)
    @RequestMapping(value = "ND_updateFormGroupIem.do", method = RequestMethod.POST)
    public String updateFormGroupIem(HttpServletRequest request, Model model, FormGroupIemVO formGroupIemVO) {
        // 검증
        ValidateResultHolder holder = ValidateUtil.validate(formGroupIemVO);
        if(holder.isValid()) {
            LoginVO loginVo = (LoginVO) getMgrSession();
            formGroupIemVO.setRgtrId(loginVo.getPicId());
            
            // 수정 실행
            Integer key = opFormService.updateFormGroupIem(formGroupIemVO);
            if(key != 1) {
                return responseJson(model, MessageUtil.FALSE, key, MessageUtil.getMessage("common.processFail"));
            }
        } else {
            return responseJson(model, MessageUtil.FALSE, MessageUtil.getMessage("common.validateFail"));
        }

        opFormService.reloadCache();
        return responseJson(model, MessageUtil.TRUE, MessageUtil.getMessage("common.updateOk"));
    }

    
    /**
     * 폼 삭제
     */
    @OpenworksAuth(authType = AuthType.MANAGER)
    @RequestMapping(value = "ND_deleteFormList.do", method = RequestMethod.POST)
    public String deleteFormList(HttpServletRequest request, Model model, FormInfoVO formInfoVO) {

        Integer affected = opFormService.deleteFormList(formInfoVO);
        String url = "BD_selectFormList.do?" + OpHelper.getSearchQueryString(request);

        String msg = "";
        if(affected == 0) {
            msg = MessageUtil.getMessage("common.deleteOk");
        }
        opFormService.reloadCache();

        return alertAndRedirect(model, msg, url);
    }
    
    /**
     * 폼 그룹 삭제
     */
    @OpenworksAuth(authType = AuthType.MANAGER)
    @RequestMapping(value = "ND_deleteFormGroup.do", method = RequestMethod.POST)
    public String deleteQestnarGroup(HttpServletRequest request, Model model, FormGroupVO formGroupVO) {

    	
        Integer cnt = opFormService.deleteFormGroup(formGroupVO);
        
        if(cnt < 0) {
            return alertAndBack(model, "시작일이 지난 폼에 대한 그룹은 삭제할 수 없습니다.");
        }
		  if(cnt != 1) { return responseJson(model, MessageUtil.FALSE, cnt,
		  MessageUtil.getMessage("common.processFail")); }
		 
        opFormService.reloadCache();
        return responseJson(model, MessageUtil.TRUE, MessageUtil.getMessage("common.deleteOk"));
    }
     
    /**
     * 폼 항목 삭제
     */
    @OpenworksAuth(authType = AuthType.MANAGER)
    @RequestMapping(value = "ND_deleteFormGroupIem.do", method = RequestMethod.POST)
    public String deleteFormGroupIem(HttpServletRequest request, Model model, FormGroupIemVO formGroupIemVO) {

    	
        Integer cnt = opFormService.delIndivItem(formGroupIemVO);

        if(cnt != 1) {
            return responseJson(model, MessageUtil.FALSE, cnt, MessageUtil.getMessage("common.processFail"));
        }
        opFormService.reloadCache();
        return responseJson(model, MessageUtil.TRUE, MessageUtil.getMessage("common.deleteOk"));
    }
    

    /**
     * 응답관리 삭제
     */
    @OpenworksAuth(authType = AuthType.MANAGER)
    @RequestMapping(value = "ND_deleteIndivResponseList.do", method = RequestMethod.POST)
    public String deleteIndivResponseList(HttpServletRequest request, Model model, FormResponseVO formResponseVO) {

        Integer affected = opFormService.deleteIndivResponseList(formResponseVO);
        String url = "BD_selectFormResponseList.do?" + OpHelper.getSearchQueryString(request);

        String msg = "";
        if(affected == 0) {
            msg = MessageUtil.getMessage("common.deleteOk");
        }
        opFormService.reloadCache();

        return alertAndRedirect(model, msg, url);
    }
    
    /**
     * 폼 응답관리 엑셀 출력
     * @param request
     * @param model
     * @param excelDownloadVo
     * @return
     */
    @RequestMapping(value = "ND_formExcelDownload.do")
    public String excelDownload(HttpServletRequest request, Model model, ExcelDownloadVO excelDownloadVo) {
   
    	logger.debug("=====================excelDownload===============================");
        // queryId는 해당메뉴의 sql.xml에 쿼리 생성 resultType은 hmap으로 리턴
        String queryId = Config.getString("excel-config." + excelDownloadVo.getExcelKey() + ".queryId");
        String[] headerId = Config.getString("excel-config." + excelDownloadVo.getExcelKey() + ".headerId").split(",");
        String[] headerNm = Config.getString("excel-config." + excelDownloadVo.getExcelKey() + ".headerNm").split(",");
        logger.debug("=====================queryId===============================" +queryId);
        logger.debug("=====================headerId==============================="+headerId);
        logger.debug("=====================headerId==============================="+headerNm);
        logger.debug("=====================excelDownloadVo==============================="+excelDownloadVo);
        String excelFileName = "excel";
       
        // Taglib 파일명을 입력시 excel-commons0config.xml의 fileNm보다 우선시 됨
        if (Validate.isEmpty(excelDownloadVo.getExcelFileNm())) {
            excelFileName = Config.getString("excel-config." + excelDownloadVo.getExcelKey() + ".fileNm");
        } else {
            excelFileName = excelDownloadVo.getExcelFileNm();
        }
      
        model.addAttribute(BaseConfig.EXCEL_FILE_NAME, excelFileName);
        logger.debug("=====================model==============================="+model);
   
        ArrayList<List<String>> excelList = opFormService.selectFormExcelList(queryId, headerId, headerNm, excelDownloadVo);
   
        HSSFWorkbook workbook = opFormService.formExcelCreate(excelList);
        logger.debug("=====================workbook==============================="+workbook);
        return responseExcel(model, workbook);
       
    }
    
    /**
     * 그룹 정렬수정
     */
    @OpenworksAuth(authType = AuthType.MANAGER)
    @RequestMapping(value = "ND_updateSortGroup.do", method = RequestMethod.POST)
    public String updateSortGroup(HttpServletRequest request, Model model,FormGroupVO formGroupVO) {

    	String[] groupSns = Arrays.stream(request.getParameterValues("groupSns[]"))
    			.filter(s -> s != null && !s.isEmpty()).toArray(String[]::new);
    	
    	//그룹 정보 추가
    	String[] formSns = request.getParameterValues("formSn");
    	Integer formSn = Integer.parseInt(formSns[0]);
    	
    	formGroupVO.setGroupSns(groupSns);
    	formGroupVO.setFormSn(formSn);
    	
        Integer key = opFormService.updateGroupSortSn(formGroupVO);
        if(key < 1) {
            return responseJson(model,MessageUtil.FALSE, MessageUtil.getMessage("common.processFail"));
        }

        opFormService.reloadCache();
        return responseJson(model, MessageUtil.TRUE, MessageUtil.getMessage("common.updateOk"));
    }
    
    /**
     * 항목 정렬수정
     */
    @OpenworksAuth(authType = AuthType.MANAGER)
    @RequestMapping(value = "ND_updateSortIem.do", method = RequestMethod.POST)
    public String updateSortIem(HttpServletRequest request, Model model,FormGroupIemVO formGroupIemVO) {
    	
    	String[] artclSns = Arrays.stream(request.getParameterValues("artclSns[]"))
    			.filter(s -> s != null && !s.isEmpty()).toArray(String[]::new);
    	
    	//항목 정보 추가
    	String[] formSns = request.getParameterValues("formSn");
    	String[] groupSns = request.getParameterValues("groupSn");

    	Integer formSn = Integer.parseInt(formSns[0]);
    	Integer groupSn = Integer.parseInt(groupSns[0]);
    	
    	formGroupIemVO.setArtclSns(artclSns);
    	formGroupIemVO.setFormSn(formSn);
    	formGroupIemVO.setGroupSn(groupSn);

    	
        Integer key = opFormService.updateIemSortSn(formGroupIemVO);
        if(key < 1) {
            return responseJson(model,MessageUtil.FALSE, MessageUtil.getMessage("common.processFail"));
        }

        opFormService.reloadCache();
        return responseJson(model, MessageUtil.TRUE, MessageUtil.getMessage("common.updateOk"));
    }
 
}