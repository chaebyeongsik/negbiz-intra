/*
 * Copyright (c) 2015 ZES Inc. All rights reserved.
 * This software is the confidential and proprietary information of ZES Inc.
 * You shall not disclose such Confidential Information and shall use it
 * only in accordance with the terms of the license agreement you entered into
 * with ZES Inc. (http://www.zesinc.co.kr/)
 */
package zesinc.intra.bbs;

import java.util.Collections;
import java.util.Map;
import java.util.WeakHashMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import zesinc.core.lang.Validate;
import zesinc.intra.bbs.domain.BbsCmntVO;
import zesinc.login.domain.LoginVO;
import zesinc.web.spring.controller.IntraController;
import zesinc.web.support.BaseConfig;
import zesinc.web.utils.MessageUtil;
import zesinc.web.validate.ValidateResultHolder;
import zesinc.web.validate.ValidateUtil;

/**
 * 게시판커멘트 정보 컨트롤러 클레스
 * 
 * <pre>
 * << 개정이력(Modification Information) >>
 *    
 *     수정일       수정자   수정내용
 * --------------  --------  -------------------------------
 *  2015-11-19.    방기배   최초작성
 * </pre>
 * 
 * @author (주)제스아이엔씨 기술연구소
 * @see
 */
@Controller("게시판커멘트 관리")
@RequestMapping(value = { "/intra/bbs" })
public class BbsCmntController extends IntraController {

    /** 추천/비추천/신고 중복차단용 플레시 형식으로 DB 저장은 하지 않는다. IP와 TYPE을 합한 키를 사용 */
    private static Map<String, Boolean> INCREASE_ATTR = Collections.synchronizedMap(new WeakHashMap<String, Boolean>());

    @Resource(name = "opBbsCmntService")
    private BbsCmntService opBbsCmntService;

    /**
     * 게시판커멘트 레이아웃
     */
    @RequestMapping(value = "ND_selectBbsCmnt.do")
    public void selectBbsCmnt(HttpServletRequest request, Model model, BbsCmntVO bbsCmntVo) {

        model.addAttribute(BaseConfig.KEY_LOGIN_VO, getMgrSession());
        model.addAttribute(BaseConfig.KEY_PAGER, opBbsCmntService.selectBbsCmntPageList(bbsCmntVo));
    }

    /**
     * 게시판커멘트 페이지 목록
     */
    @RequestMapping(value = "INC_selectBbsCmntList.do")
    public String selectBbsCmntList(HttpServletRequest request, HttpServletResponse response, Model model, BbsCmntVO bbsCmntVo) {
        response.setContentType("text/html;charset=UTF-8");

        model.addAttribute(BaseConfig.KEY_LOGIN_VO, getMgrSession());
        model.addAttribute(BaseConfig.KEY_PAGER, opBbsCmntService.selectBbsCmntPageList(bbsCmntVo));

        return "intra/bbs/INC_selectBbsCmntList";
    }

    /**
     * 게시판커멘트 등록
     */
    @RequestMapping(value = "ND_insertBbsCmnt.do", method = RequestMethod.POST)
    public String insertBbsCmnt(HttpServletRequest request, Model model, BbsCmntVO bbsCmntVo) {
        // 인증 여부 확인
        LoginVO loginVo = (LoginVO) getMgrSession();
        if(Validate.isEmpty(loginVo)) {
            return responseJson(model, Boolean.FALSE, MessageUtil.getMessage("common.needAuth"));
        }

        // 검증
        ValidateResultHolder holder = ValidateUtil.validate(bbsCmntVo);
        if(holder.isValid()) {
            bbsCmntVo.setRgtrId(loginVo.getPicId());
            bbsCmntVo.setRgtrNm(loginVo.getPicNm());
            bbsCmntVo.setIpAddr(getIpAddr());

            Integer key = opBbsCmntService.insertBbsCmnt(bbsCmntVo);
            if(key != 1) {
                return responseJson(model, Boolean.FALSE, MessageUtil.getMessage("common.processFail"));
            }
        } else {
            return responseJson(model, Boolean.FALSE, MessageUtil.getMessage("common.validateFail"));
        }
        return responseJson(model, Boolean.TRUE, MessageUtil.getMessage("common.insertOk"));
    }

    /**
     * 게시판커멘트 수정
     */
    @RequestMapping(value = "ND_updateBbsCmnt.do", method = RequestMethod.POST)
    public String updateBbsCmnt(HttpServletRequest request, Model model, BbsCmntVO bbsCmntVo) {
        // 검증
        ValidateResultHolder holder = ValidateUtil.validate(bbsCmntVo);
        if(holder.isValid()) {

            String IpAdres = getIpAddr();
            String type = bbsCmntVo.getType();
            String key = IpAdres + type + bbsCmntVo.getOpnnSn();
            if(Validate.isEmpty(type)) {
                return responseJson(model, Boolean.FALSE, MessageUtil.getMessage("common.invalidParam"));
            }

            Boolean hasAttr = INCREASE_ATTR.get(key);
            if(Validate.isNotEmpty(hasAttr) && hasAttr) {
                return responseJson(model, Boolean.FALSE, MessageUtil.getMessage("common.alreadyApply"));
            }

            // 수정 실행
            Integer cnt = opBbsCmntService.updateBbsCmnt(bbsCmntVo);
            if(cnt != 1) {
                return responseJson(model, Boolean.FALSE, MessageUtil.getMessage("common.processFail"));
            }
            INCREASE_ATTR.put(new String(key), Boolean.TRUE);
        } else {
            return responseJson(model, Boolean.TRUE, MessageUtil.getMessage("common.validateFail"));
        }

        return responseJson(model, Boolean.TRUE, MessageUtil.getMessage("common.processOk"));
    }

    /**
     * 게시판커멘트 삭제
     */
    @RequestMapping(value = "ND_deleteBbsCmnt.do", method = RequestMethod.POST)
    public String deleteBbsCmnt(HttpServletRequest request, Model model, BbsCmntVO bbsCmntVo) throws Exception {

        LoginVO loginVo = (LoginVO) getMgrSession();

        BbsCmntVO dataVo = opBbsCmntService.selectBbsCmnt(bbsCmntVo);
        if(Validate.isNotEmpty(dataVo) && dataVo.getRgtrId().equals(loginVo.getPicId())) {
            Integer cnt = opBbsCmntService.deleteBbsCmnt(bbsCmntVo);
            if(cnt != 1) {
                return responseJson(model, Boolean.FALSE, MessageUtil.getMessage("common.processFail"));
            }
        } else {
            return responseJson(model, Boolean.FALSE, MessageUtil.getMessage("common.notAllow"));
        }

        return responseJson(model, Boolean.TRUE, MessageUtil.getMessage("common.deleteOk"));
    }

    /**
     * 게시판커멘트 삭제
     */
    @RequestMapping(value = "ND_updateBbsDisplayCmnt.do", method = RequestMethod.POST)
    public String updateBbsDisplayCmnt(HttpServletRequest request, Model model, BbsCmntVO bbsCmntVo) throws Exception {

        LoginVO loginVo = (LoginVO) getMgrSession();

        bbsCmntVo.setPicId(loginVo.getPicId());
        Integer cnt = opBbsCmntService.updateBbsDisplayCmnt(bbsCmntVo);
        if(cnt != 1) {
            return responseJson(model, Boolean.FALSE, MessageUtil.getMessage("common.processFail"));
        }

        return responseJson(model, Boolean.TRUE, MessageUtil.getMessage("common.deleteOk"));
    }

}
