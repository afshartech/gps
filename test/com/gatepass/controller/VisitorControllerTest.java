package com.gatepass.controller;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.hasItem; //is
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post; // For get/Post
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import com.gatepass.model.Visitor;
import com.gatepass.service.VisitorService;
//is
/**
 * @author Afshar
 * @see http://www.petrikainulainen.net/programming/spring-framework/unit-testing-of-spring-mvc-controllers-normal-controllers/
 * @see http://docs.spring.io/spring/docs/current/spring-framework-reference/html/testing.html
 * @see 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@TestPropertySource("classpath:/resourcebundle/db.properties")
@ContextHierarchy({
    @ContextConfiguration("classpath:/config/app-context.xml"),
    @ContextConfiguration("classpath:/config/servlet-context.xml")
})
//@RunWith(MockitoJUnitRunner.class)
//@TestPropertySource("/WEB-INF/classes/resourcebundle/db.properties")
//@TestPropertySource(properties = { "timezone = GMT", "port: 4242" })
/*@TestPropertySource(
	    locations = "/resourcebundle/db.properties",
	    properties = {"timezone = GMT", "port: 4242"}
)*/
//@ContextConfiguration(classes = {TestContext.class, WebApplicationContext.class})
//@ContextConfiguration("/config/servlet-context.xml")
public class VisitorControllerTest {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;
    
    @InjectMocks
    private VisitorController visitorController;
    
    @Mock
    private VisitorService visitorService;

	@Before
	public void setUp() throws Exception {
		System.out.println("------ Setup Started ------");
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
		visitorService = Mockito.mock(VisitorService.class);
		
		System.out.println(visitorService);
		System.out.println("------ Setup Complete ------");
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link com.gatepass.controller.VisitorController#getDetails(java.lang.Integer)}.
	 */
	@Test
	public void testGetDetails() throws Exception {
		//fail("Not yet implemented");
		Visitor v1 = new Visitor();
		v1.setVisitornumber(1);
		v1.setVisitorName("Afshar");
		
		//Visitor v2 = new Visitor();
		//when(visitorServiceMock.getVisitorById(1)).thenReturn(Arrays.asList(v1, v2));
		when(visitorService.getVisitorById(1)).thenReturn(v1); 
        MvcResult result = mockMvc.perform(post("/getVisitorDetails").param("visitorID", "1"))
                .andExpect(status().isOk())
                .andReturn()
                //.andExpect(view().name("/jsp/UpdateVisitor.jsp"))
                //.andExpect(forwardedUrl("/jsp/UpdateVisitor.jsp"))
               // .andExpect(model().attribute("imageData", ""))
//                .andExpect(model().attribute("visitorObj", hasItem(
//                        allOf(
//                                hasProperty("visitornumber", is(1)),
//                                hasProperty("visitorName", is("Afshar"))
//                        )
//                )))
                //.andReturn()
                ;
		ModelAndView m = result.getModelAndView();
		System.out.println(m);
		verify(visitorService).getVisitorById(1);
//		verifyNoMoreInteractions(visitorService);
	}

	/**
	 * Test method for {@link com.gatepass.controller.VisitorController#printDetails(javax.servlet.http.HttpServletRequest)}.
	 */
//	@Test
//	public void testPrintDetails() {
//		//fail("Not yet implemented");
//	}

	/**
	 * Test method for {@link com.gatepass.controller.VisitorController#addVisitor(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)}.
	 */
//	@Test
//	public void testAddVisitor() {
//		//fail("Not yet implemented");
//	}

	/**
	 * Test method for {@link com.gatepass.controller.VisitorController#updateVisitor(java.lang.Integer, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)}.
	 */
//	@Test
//	public void testUpdateVisitor() {
//		//fail("Not yet implemented");
//	}

	/**
	 * Test method for {@link com.gatepass.controller.VisitorController#deleteVisitor(java.lang.Integer, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)}.
	 */
//	@Test
//	public void testDeleteVisitor() {
//		//fail("Not yet implemented");
//	}

}
