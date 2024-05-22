package zesinc.intra.popup;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import zesinc.intra.popup.domain.PopupVO;

@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration("file:C:/Projects/business/openworks4-zes-v310-standard-mysql/openworks4/openworks-intra/src/main/webapp")
@ContextConfiguration(locations={"classpath:/config/spring/context-*.xml",
		"classpath:/config/spring/dispatcher-servlet.xml"})
public class PopupControllerTest {
	 
    @Autowired
    private WebApplicationContext wac;
 
    private MockMvc mockMvc;
    //protected MockHttpSession session;
 
	
	@Before
    public void setup(){
        //this.mockMvc=MockMvcBuilders.standaloneSetup(new PopupController()).build();
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
		 //session = new MockHttpSession();
		 //Locale locale = new Locale("ko-KR");
		 //session.setAttribute("localhost.zesinc.web.spring.resolver.OpenworksLocaleResolver.LOCALE", locale);
    }
	
	@Test
	@WithUserDetails(value = "QVBtEpzDFgZpEpWZmymFPg==" , userDetailsServiceBeanName = "authJdbcDaoImpl")
	public void testSelectPopupList() throws Exception {
		HashMap<String, Object> paramMap = new HashMap<>();
		paramMap.put("q_pagePerPage", 10);
		paramMap.put("q_pagingEndNum", 10);
		paramMap.put("q_rowPerPage", 10);
		paramMap.put("q_pagingStartNum", 0);
		paramMap.put("q_currPage", 1);
		PopupVO popupVO = new PopupVO(); 
		popupVO.setParamMap(paramMap);

		
	  
		this.mockMvc.perform(get("/intra/popup/BD_selectPopupList.do")
		//.session(session)
		.accept(MediaType.parseMediaType("application/html;charset=UTF-8"))
		.param("paramMap[q_pagePerPage]", "10")
		.param("paramMap[q_pagingEndNum]", "10")
		.param("paramMap[q_rowPerPage]", "10")
		.param("paramMap[q_pagingStartNum]", "0")
		.param("paramMap[q_currPage]", "1"))
		.andExpect(status().isOk());
	}

}
