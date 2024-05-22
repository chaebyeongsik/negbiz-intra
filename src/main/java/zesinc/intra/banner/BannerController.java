/*
 * Copyright (c) 2015 ZES Inc. All rights reserved.
 * This software is the confidential and proprietary information of ZES Inc.
 * You shall not disclose such Confidential Information and shall use it
 * only in accordance with the terms of the license agreement you entered into
 * with ZES Inc. (http://www.zesinc.co.kr/)
 */
package zesinc.intra.banner;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import zesinc.component.file.support.UploadHelper;
import zesinc.core.cache.CacheService;
import zesinc.core.lang.Validate;
import zesinc.intra.banner.domain.BannerVO;
import zesinc.login.domain.LoginVO;
import zesinc.web.auth.AuthSupport;
import zesinc.web.auth.AuthType;
import zesinc.web.auth.anotation.OpenworksAuth;
import zesinc.web.spring.controller.IntraController;
import zesinc.web.support.BaseConfig;
import zesinc.web.support.helper.OpHelper;
import zesinc.web.utils.MessageUtil;
import zesinc.web.validate.ValidateResultHolder;
import zesinc.web.validate.ValidateUtil;
import zesinc.web.vo.cache.CodeCacheVO;
import zesinc.web.vo.cache.DomnCacheVO;

/**
 * 배너 정보 컨트롤러 클레스
 * 
 * <pre>
 * << 개정이력(Modification Information) >>
 *    
 *     수정일       수정자   수정내용
 * --------------  --------  -------------------------------
 *  2015-07-19.    방기배   최초작성
 * </pre>
 * 
 * @author (주)제스아이엔씨 기술연구소
 * @see
 */
@Controller("배너 관리")
@RequestMapping(value = { "/intra/banner" })
public class BannerController extends IntraController {

    @Resource(name = "opBannerService")
    private BannerService opBannerService;

    /**
     * 배너 페이지 목록
     */
    @OpenworksAuth(authType = AuthType.READ)
    @RequestMapping(value = "ND_selectBannerDetailTy.do")
    public String selectBannerDetailTy(HttpServletRequest request, Model model, BannerVO bannerVo) {

        String hghrkCdId = bannerVo.getString("q_hghrkCdId");
        String cdId = bannerVo.getString("q_cdId");

        if(Validate.isEmpty(hghrkCdId) || Validate.isEmpty(cdId)) {
            return responseJson(model, Boolean.FALSE, MessageUtil.getMessage("common.invalidParam"));
        }

        @SuppressWarnings("unchecked")
        Map<String, Map<String, List<CodeCacheVO>>> codeListCache =
            (Map<String, Map<String, List<CodeCacheVO>>>) CacheService.get(BaseConfig.CODE_LIST_CACHE_KEY);

        Map<String, List<CodeCacheVO>> listCache = codeListCache.get(hghrkCdId + "_" + cdId);
        if(Validate.isEmpty(listCache)) {
            return responseJson(model, Boolean.TRUE, Collections.EMPTY_LIST);
        }

        return responseJson(model, Boolean.TRUE, listCache.get(BaseConfig.DEFAULT_LWPRT_KEY));
    }

    /**
     * 배너 페이지 목록
     */
    @OpenworksAuth(authType = AuthType.READ)
    @RequestMapping(value = "BD_selectBannerList.do")
    public void selectBannerList(HttpServletRequest request, Model model, BannerVO bannerVo) {

        @SuppressWarnings("unchecked")
        List<DomnCacheVO> domnList = (List<DomnCacheVO>) CacheService.get(BaseConfig.DOMN_LIST_CACHE_KEY);
        model.addAttribute(BaseConfig.KEY_DOMAIN_LIST, domnList);

        model.addAttribute(BaseConfig.KEY_PAGER, opBannerService.selectBannerPageList(bannerVo));
    }

    /**
     * 배너 페이지 목록
     */
    @OpenworksAuth(authType = AuthType.READ)
    @RequestMapping(value = "PD_selectBannerSortOrdrList.do")
    public void selectBannerSortSnList(HttpServletRequest request, Model model, BannerVO bannerVo) {

        @SuppressWarnings("unchecked")
        List<DomnCacheVO> domnList = (List<DomnCacheVO>) CacheService.get(BaseConfig.DOMN_LIST_CACHE_KEY);
        model.addAttribute(BaseConfig.KEY_DOMAIN_LIST, domnList);

        String bnnTypeNm = bannerVo.getString("q_bnnTypeNm");
        if(Validate.isNotEmpty(bnnTypeNm)) {
            model.addAttribute(BaseConfig.KEY_DATA_LIST, opBannerService.selectBannerSortSnList(bannerVo));
        }
    }

    /**
     * 배너 입력 폼
     */
    @OpenworksAuth(authType = AuthType.BASIC)
    @RequestMapping(value = { "BD_insertBannerForm.do" })
    public void insertBannerForm(HttpServletRequest request, Model model, BannerVO bannerVo) {

        @SuppressWarnings("unchecked")
        List<DomnCacheVO> domnList = (List<DomnCacheVO>) CacheService.get(BaseConfig.DOMN_LIST_CACHE_KEY);
        model.addAttribute(BaseConfig.KEY_DOMAIN_LIST, domnList);

        ValidateResultHolder holder = ValidateUtil.validate(bannerVo);
        model.addAttribute(BaseConfig.KEY_VALIDATE, holder);
    }

    /**
     * 배너 등록
     */
    @OpenworksAuth(authType = AuthType.BASIC)
    @RequestMapping(value = "ND_insertBanner.do", method = RequestMethod.POST)
    public String insertBanner(HttpServletRequest request, Model model, BannerVO bannerVo) {

        // 검증
        ValidateResultHolder holder = ValidateUtil.validate(bannerVo);
        if(holder.isValid()) {
            LoginVO loginVo = (LoginVO) getMgrSession();
            bannerVo.setRgtrId(loginVo.getPicId());
            bannerVo.setRgtrNm(loginVo.getPicNm());       
            bannerVo.setIpAddr(getIpAddr());
            bannerVo.setFileList(UploadHelper.upload(request, "banner", Boolean.TRUE));

            Integer key = opBannerService.insertBanner(bannerVo);
            if(key != 1) {
                return alertAndBack(model, MessageUtil.getMessage("common.processFail"));
            }
        } else {
            return alertAndBack(model, MessageUtil.getMessage("common.validateFail"));
        }

        String url = "BD_selectBannerList.do?" + OpHelper.getSearchQueryString(request);
        return alertAndRedirect(model, MessageUtil.getMessage("common.insertOk"), url);
    }

    /**
     * 배너 수정 폼
     */
    @OpenworksAuth(authType = AuthType.READ)
    @RequestMapping(value = { "BD_updateBannerForm.do" })
    public void updateBannerForm(HttpServletRequest request, Model model, BannerVO bannerVo) {

        @SuppressWarnings("unchecked")
        List<DomnCacheVO> domnList = (List<DomnCacheVO>) CacheService.get(BaseConfig.DOMN_LIST_CACHE_KEY);
        model.addAttribute(BaseConfig.KEY_DOMAIN_LIST, domnList);

        ValidateResultHolder holder = ValidateUtil.validate(bannerVo);
        model.addAttribute(BaseConfig.KEY_VALIDATE, holder);

        model.addAttribute(BaseConfig.KEY_DATA_VO, opBannerService.selectBanner(bannerVo));
    }

    /**
     * 배너 수정
     */
    @OpenworksAuth(authType = AuthType.BASIC)
    @RequestMapping(value = "ND_updateBanner.do", method = RequestMethod.POST)
    public String updateBanner(HttpServletRequest request, Model model, BannerVO bannerVo) {

        BannerVO dataVo = opBannerService.selectBanner(bannerVo);
        // 권한 확인
        if(AuthSupport.isAuth(dataVo.getRgtrId())) {
            // 검증
            ValidateResultHolder holder = ValidateUtil.validate(bannerVo);
            if(holder.isValid()) {
                LoginVO loginVo = (LoginVO) getMgrSession();
                bannerVo.setMdfrId(loginVo.getPicId());
                bannerVo.setUpdusrNm(loginVo.getPicNm());
                bannerVo.setIpAddr(getIpAddr());
                bannerVo.setFileList(UploadHelper.upload(request, "banner", Boolean.TRUE));

                // 수정 실행
                Integer key = opBannerService.updateBanner(bannerVo);
                if(key != 1) {
                    return alertAndBack(model, MessageUtil.getMessage("common.processFail"));
                }
            } else {
                return alertAndBack(model, MessageUtil.getMessage("common.validateFail"));
            }

            String url = "BD_updateBannerForm.do?" + OpHelper.getSearchQueryString(request);
            return alertAndRedirect(model, MessageUtil.getMessage("common.updateOk"), url);
        }

        return alertAndBack(model, MessageUtil.getMessage("common.notAllow"));
    }

    /**
     * 배너 삭제
     */
    @OpenworksAuth(authType = AuthType.BASIC)
    @RequestMapping(value = "ND_deleteBanner.do", method = RequestMethod.POST)
    public String deleteBanner(HttpServletRequest request, Model model, BannerVO bannerVo) {

        BannerVO dataVo = opBannerService.selectBanner(bannerVo);
        // 권한 확인
        if(AuthSupport.isAuth(dataVo.getRgtrId())) {
            Integer cnt = opBannerService.deleteBanner(bannerVo);
            if(cnt != 1) {
                return alertAndBack(model, MessageUtil.getMessage("common.processFail"));
            }

            String url = "BD_selectBannerList.do?" + OpHelper.getSearchQueryString(request);
            return alertAndRedirect(model, MessageUtil.getMessage("common.deleteOk"), url);
        }
        return alertAndBack(model, MessageUtil.getMessage("common.notAllow"));
    }

    /**
     * 배너 수정
     */
    @OpenworksAuth(authType = AuthType.MANAGER)
    @RequestMapping(value = "ND_updateBannerSortSn.do", method = RequestMethod.POST)
    public String updateBannerSortSn(HttpServletRequest request, Model model, BannerVO bannerVo) {

        // 수정 실행
        Integer key = opBannerService.updateBannerSortSn(bannerVo);
        if(key < 1) {
            return alertAndBack(model, MessageUtil.getMessage("common.processFail"));
        }

        String url = "PD_selectBannerSortOrdrList.do?" + OpHelper.getSearchQueryString(request);
        return alertAndRedirect(model, MessageUtil.getMessage("common.updateOk"), url);
    }

}
