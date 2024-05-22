/*
 * Copyright (c) 2015 ZES Inc. All rights reserved.
 * This software is the confidential and proprietary information of ZES Inc.
 * You shall not disclose such Confidential Information and shall use it
 * only in accordance with the terms of the license agreement you entered into
 * with ZES Inc. (http://www.zesinc.co.kr/)
 */
package zesinc.component.contentbuilder;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import zesinc.core.config.Config;
import zesinc.core.lang.Validate;
import zesinc.web.spring.controller.IntraController;
import zesinc.web.vo.BaseVO;

/**
 * <pre>
 * Contentbuilder를 사용하는 공통 편집화면을 제공하기 위한 컨트롤러.
 * 폅집화면에 컨텐츠를 담아서 제공하는 경우 VO 객체의 paramMap에
 * q_content 파라미터로 컨텐츠를 넘겨주어야 한다.
 * 
 * 추가로 head tag 사이의 css 또는 js 코드는 VO 객체의 paramMap에
 * q_header 파라미터로 주어야 한다.
 * 
 * 아래 예와 같이 폼을 만들어 post로 submit 한다. 'sr-only' CSS class는 화면 미표시 이다.
 * &lt;form name="contentbuilderForm" id="contentbuilderForm" target="_blank" method="post" action="/component/contentbuilder/ND_selectEditor.do"&gt;
 *     &lt;input type="hidden" name="q_cssFileNm" id="q_cssFileNm" value="${baseVo.cssFileNm}" class="sr-only" /&gt;
 *     &lt;textarea name="q_content" id="q_content" class="sr-only"&gt;${dataVo.content}&lt;/textarea&gt;
 *     &lt;textarea name="q_header" id="q_header" class="sr-only"&gt;${dataVo.header}&lt;/textarea&gt;
 *     &lt;button type="submit" class="sr-only"&gt;편집&lt;/button&gt;
 * &lt;/form&gt;
 * 
 * 참조 파일 :
 * /WEB-INF/jsp/component/contentbuilder/ND_selectEditor.jsp
 * /WEB-INF/jsp/component/contentbuilder/ND_selectResrceEditor.jsp
 * </pre>
 * 
 * <pre>
 * << 개정이력(Modification Information) >>
 *    
 *     수정일       수정자   수정내용
 * --------------  --------  -------------------------------
 *  2015. 8. 23.    방기배   최초작성
 * </pre>
 * 
 * @author (주)제스아이엔씨 기술연구소
 * @see
 */
@Controller
@RequestMapping(value = { "/component/contentbuilder" })
public class ContentbuilderController extends IntraController {

    /** 화면사에서 사용될 css 파일이 없다면 기본적으로 사용될 파일. */
    private static final String DEFAULT_CSS = Config.getString("component-config.contentbuilder.defaultCss");
    /** 템프릿목록 */
    private static final String SNIPPET_FILE = Config.getString("component-config.contentbuilder.snippetFile");
    /** 이미지목록 */
    private static final String IMAGE_SELECT = Config.getString("component-config.contentbuilder.imageselect");
    /** 파일목록 */
    private static final String FILE_SELECT = Config.getString("component-config.contentbuilder.fileselect");

    /**
     * Contentbuilder 폅집화면
     */
    @RequestMapping(value = { "ND_selectEditor.do" })
    public void selectEditor(HttpServletRequest request, Model model, BaseVO baseVo) {
        String[] cssFileNms = baseVo.getStrings("q_cssFileNm");
        if(Validate.isNotEmpty(cssFileNms)) {
            model.addAttribute("cssFileNms", cssFileNms);
        } else {
            model.addAttribute("cssFileNms", new String[] { DEFAULT_CSS });
        }

        model.addAttribute("DEFAULT_CSS", DEFAULT_CSS);
        model.addAttribute("SNIPPET_FILE", SNIPPET_FILE);
        model.addAttribute("IMAGE_SELECT", IMAGE_SELECT);
        model.addAttribute("FILE_SELECT", FILE_SELECT);
    }

    /**
     * 자원관리에서 사용하는 Contentbuilder 폅집화면
     */
    @RequestMapping(value = { "ND_selectResrceEditor.do" })
    public void selectResrceEditor(HttpServletRequest request, Model model, BaseVO baseVo) {
        String[] cssFileNms = baseVo.getStrings("q_cssFileNm");
        if(Validate.isNotEmpty(cssFileNms)) {
            model.addAttribute("cssFileNms", cssFileNms);
        } else {
            model.addAttribute("cssFileNms", new String[] { DEFAULT_CSS });
        }

        model.addAttribute("SNIPPET_FILE", SNIPPET_FILE);
        model.addAttribute("IMAGE_SELECT", IMAGE_SELECT);
        model.addAttribute("FILE_SELECT", FILE_SELECT);
    }

}
