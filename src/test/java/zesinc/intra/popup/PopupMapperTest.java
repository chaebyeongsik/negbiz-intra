package zesinc.intra.popup;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import zesinc.intra.popup.domain.PopupVO;


@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:/config/spring/context-*.xml"})
public class PopupMapperTest {
	@Autowired
	private PopupMapper popupMapper;

	/**
	 * 팝업 목록 테스트
	 */
	@Test
	public void selectPopupTest() {
		PopupVO popupVO = new PopupVO(); 
		HashMap<String, Object> paramMap = new HashMap<>();
		
		paramMap.put("q_rowPerPage", 10);
		paramMap.put("q_pagingStartNum", 0);
		popupVO.setParamMap(paramMap);
		
		List<PopupVO> list = popupMapper.selectPopupList(popupVO);
	    assertNotNull(list);
	    assertTrue(list.size() > 0);
	}

}
