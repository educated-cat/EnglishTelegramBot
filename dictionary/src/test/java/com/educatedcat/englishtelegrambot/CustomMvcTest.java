package com.educatedcat.englishtelegrambot;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.node.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.autoconfigure.web.servlet.*;
import org.springframework.boot.test.context.*;
import org.springframework.http.*;
import org.springframework.test.web.servlet.*;
import org.springframework.test.web.servlet.request.*;

import java.io.*;

@SpringBootTest
@AutoConfigureMockMvc
public class CustomMvcTest {
	@Autowired
	protected MockMvc mvc;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	protected ResultActions performRequest(MockHttpServletRequestBuilder builder, Object payload) throws Exception {
		return performRequest(builder, payload, null);
	}
	
	protected <T> CustomResultActions<T> performRequest(MockHttpServletRequestBuilder builder, Class<T> result)
			throws Exception {
		return performRequest(builder, null, result);
	}
	
	protected <T> CustomResultActions<T> performRequest(MockHttpServletRequestBuilder builder, Object payload,
	                                                    Class<T> result)
			throws Exception {
		ResultActions ra = mvc.perform(jsonRequest(builder, payload));
		return getResultActions(ra, result == null ? null : parseResult(ra.andReturn(), result));
	}
	
	protected MockHttpServletRequestBuilder jsonRequest(MockHttpServletRequestBuilder builder,
	                                                    Object object) throws JsonProcessingException {
		return builder
				.contentType(MediaType.APPLICATION_JSON)
				.content(object instanceof String ? (String) object : objectMapper.writeValueAsString(object));
	}
	
	protected ObjectNode parseResult(MvcResult result) throws IOException {
		return objectMapper.readValue(result.getResponse().getContentAsString(), ObjectNode.class);
	}
	
	@SuppressWarnings({"unchecked"})
	protected <T> T parseResult(MvcResult result, Class<T> tClass) throws IOException {
		return tClass.equals(String.class)
		       ? (T) result.getResponse().getContentAsString()
		       : objectMapper.readValue(result.getResponse().getContentAsString(), tClass);
	}
	
	private <T> CustomResultActions<T> getResultActions(ResultActions ra, T result) {
		return new CustomResultActions<T>() {
			
			@Override
			public CustomResultActions<T> andExpect(ResultMatcher resultMatcher) throws Exception {
				ra.andExpect(resultMatcher);
				return this;
			}
			
			@Override
			public CustomResultActions<T> andDo(ResultHandler resultHandler) throws Exception {
				ra.andDo(resultHandler);
				return this;
			}
			
			@Override
			public MvcResult andReturn() {
				return ra.andReturn();
			}
			
			@Override
			public T andReturnDto() {
				return result;
			}
		};
	}
	
	public interface CustomResultActions<T> extends ResultActions {
		@Override
		CustomResultActions<T> andExpect(ResultMatcher matcher) throws Exception;
		
		@Override
		CustomResultActions<T> andDo(ResultHandler handler) throws Exception;
		
		T andReturnDto();
	}
}
