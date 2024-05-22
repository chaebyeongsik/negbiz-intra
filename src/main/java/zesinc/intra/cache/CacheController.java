/*
 * Copyright (c) 2015 ZES Inc. All rights reserved.
 * This software is the confidential and proprietary information of ZES Inc.
 * You shall not disclose such Confidential Information and shall use it
 * only in accordance with the terms of the license agreement you entered into
 * with ZES Inc. (http://www.zesinc.co.kr/)
 */
package zesinc.intra.cache;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import zesinc.core.cache.Cache;
import zesinc.core.cache.CacheLocation;
import zesinc.core.cache.CacheService;
import zesinc.core.config.Config;
import zesinc.core.http.METHOD;
import zesinc.core.http.PROTOCOL;
import zesinc.core.lang.Validate;
import zesinc.core.utils.HttpUtil;
import zesinc.web.auth.AuthType;
import zesinc.web.auth.anotation.OpenworksAuth;
import zesinc.web.spring.controller.IntraController;
import zesinc.web.support.BaseConfig;
import zesinc.web.support.cache.CacheNameHolder;
import zesinc.web.support.helper.SpringHelper;
import zesinc.web.utils.MessageUtil;
import zesinc.web.vo.BaseVO;
import zesinc.web.vo.cache.DomnCacheVO;
import zesinc.web.vo.cache.DomnGroupCacheVO;

/**
 * 케시정보를 새로 갱신시키기 위한 웹 API 컨트롤러
 * 
 * <pre>
 * << 개정이력(Modification Information) >>
 *    
 *     수정일       수정자   수정내용
 * --------------  --------  -------------------------------
 *  2015. 3. 19.    방기배   최초작성
 * </pre>
 * 
 * @author (주)제스아이엔씨 기술연구소
 * @see
 */
@Controller("케시새로고침관리")
@RequestMapping(value = { "/intra/cache" })
public class CacheController extends IntraController {

    /** 캐시를 동기화하기 위한 URI */
    private static String CACHE_URI = Config.getString("locations-config.cacheUri");

    /**
     * 케시 정보를 리로드 하기 위한 웹 API
     */
    @OpenworksAuth(authType = AuthType.MANAGER)
    @RequestMapping(value = { "BD_updateCacheForm.do" })
    public void updateCacheForm(HttpServletRequest request, Model model, BaseVO baseVo) {
        List<Map<String, String>> cacheList = CacheNameHolder.getList();

        @SuppressWarnings("unchecked")
        List<DomnCacheVO> domnList = (List<DomnCacheVO>) CacheService.get(BaseConfig.DOMN_LIST_CACHE_KEY);
        model.addAttribute(BaseConfig.KEY_DOMAIN_LIST, domnList);

        model.addAttribute("cacheList", cacheList);
    }

    /**
     * 케시 정보를 리로드 하기 위한 웹 API
     */
    @OpenworksAuth(authType = AuthType.MANAGER)
    @RequestMapping(value = { "ND_updateCache.do" }, method = RequestMethod.POST)
    public String updateCache(HttpServletRequest request, Model model, BaseVO baseVo) {

        List<String> results = new ArrayList<String>();

        String[] cacheNames = baseVo.getStrings("q_cacheName");
        Integer[] siteSns = baseVo.getIntegers("q_siteSn");

        // 두개의 파라미터중 하나라도 없다면 오류
        if(Validate.isEmpty(cacheNames) || Validate.isEmpty(siteSns)) {
            results.add("도메인(사이트)과 캐시이름은 반드시 1개 이상씩 선택되어야 합니다.");
            return responseJson(model, Boolean.FALSE, results, MessageUtil.getMessage("common.processFail"));
        }

        for(String cacheName : cacheNames) {
            // 캐시 조회
            Cache cache = (Cache) SpringHelper.findBean(cacheName);
            if(cache != null) {

                for(Integer siteSn : siteSns) {
                    /*
                     * 관리서버는 고정 상수로 0을 사용. 사이트관리에는 없음.
                     * 사용자단에서만 존재하는 캐시는 무시한다.
                     */
                    if(siteSn.equals(new Integer(0))) {
                        if(!cache.getLocation().equals(CacheLocation.USER)) {
                            cache.reloadCache();
                        }
                    } else {
                        if(cache.getLocation().equals(CacheLocation.INTRA)) {
                            continue;
                        }
                        @SuppressWarnings("unchecked")
                        Map<Integer, DomnCacheVO> siteSnMap = (HashMap<Integer, DomnCacheVO>) CacheService.get(BaseConfig.SITE_SN_MAP_CACHE_KEY);
                        DomnCacheVO domnVo = siteSnMap.get(siteSn);

                        Map<String, String> param = new HashMap<String, String>();
                        param.put("q_cacheName", cacheName);
                        param.put("q_siteSn", siteSn.toString());

                        // https 사용 여부
                        String httpsYn = domnVo.getHttpsYn();

                        String uri = "";
                        // 실제 컨텍스트인 경우만 컨텍스트 설정을 추가해줌
                        if(domnVo.getActlStngYn().equals("Y")) {
                            uri = domnVo.getSitePathNm();
                        }
                        if(uri.endsWith("/")) {
                            uri = uri.substring(0, uri.length() - 1);
                        }
                        uri += CACHE_URI;

                        int port = domnVo.getPortSn();
                        if(httpsYn.equals("Y") && Validate.isNotEmpty(domnVo.getScrtyPortSn())) {
                            port = domnVo.getScrtyPortSn();
                        }

                        List<DomnGroupCacheVO> groupList = domnVo.getDomnGroupList();

                        String result = "";
                        if(Validate.isNotEmpty(groupList)) {
                            for(DomnGroupCacheVO groupVo : groupList) {
                                if(httpsYn.equals("Y")) {
                                    result = HttpUtil
                                        .get(PROTOCOL.HTTPS, groupVo.getSrvrIpAddr(), groupVo.getGroupPortSn(), uri, param, 10000, METHOD.POST);
                                } else {
                                    result = HttpUtil.get(groupVo.getSrvrIpAddr(), groupVo.getGroupPortSn(), uri, param, 10000, METHOD.POST);
                                }
                                if(result.startsWith("ERROR")) {
                                    result = domnVo.getSiteExpln() + " : " + result;
                                    results.add(result);
                                }
                            }
                        } else {
                            if(httpsYn.equals("Y")) {
                                result = HttpUtil.get(PROTOCOL.HTTPS, domnVo.getSiteNm(), port, uri, param, 10000, METHOD.POST);
                            } else {
                                result = HttpUtil.get(domnVo.getSiteNm(), port, uri, param, 10000, METHOD.POST);
                            }
                            if(result.startsWith("ERROR")) {
                                result = domnVo.getSiteExpln() + " : " + result;
                                results.add(result);
                            }
                        }
                    }
                }
            }
        }

        if(!results.isEmpty()) {
            return responseJson(model, Boolean.FALSE, results, MessageUtil.getMessage("common.processFail"));
        }
        return responseJson(model, Boolean.TRUE, MessageUtil.getMessage("common.processOk"));
    }

}
