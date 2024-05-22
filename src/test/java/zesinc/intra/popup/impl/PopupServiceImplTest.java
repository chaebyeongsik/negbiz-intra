package zesinc.intra.popup.impl;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import zesinc.intra.popup.PopupService;
import zesinc.intra.popup.domain.PopupVO;
import zesinc.web.support.pager.Pager;

@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:/config/spring/context-*.xml"})
public class PopupServiceImplTest {
	
	@Autowired
    private PopupService opPopupService;

	/**
	 * 팝업 리스트 테스트
	 */
	@Test
	public void testSelectPopupPageList() {
		PopupVO popupVO = new PopupVO(); 
		HashMap<String, Object> paramMap = new HashMap<>();
		
		paramMap.put("q_rowPerPage", 10);
		paramMap.put("q_pagingStartNum", 0);
		popupVO.setParamMap(paramMap);
		
		Pager<PopupVO> list = opPopupService.selectPopupPageList(popupVO);
	    assertNotNull(list);
	    assertTrue(list.getList().size() > 0);

	}

}
