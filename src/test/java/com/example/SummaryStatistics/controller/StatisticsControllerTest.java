package com.example.SummaryStatistics.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.nio.charset.Charset;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.example.SummaryStatistics.domain.Transaction;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;


@RunWith(SpringRunner.class)
@SpringBootTest
public class StatisticsControllerTest {
	
	private MediaType contentType = new MediaType(
			MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

	@Autowired
	private WebApplicationContext webApplicationContext;

	private MockMvc mockMvc;

	@Before
	public void setUp() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(
				this.webApplicationContext).build();
	}

	@Test
	public void getHomeTest() throws Exception {

		this.mockMvc.perform(MockMvcRequestBuilders.get("/")).andExpect(
				MockMvcResultMatchers.status().isOk());
	}

	@Test
	public void addBadTransaction() throws Exception {
		// Two hours late
		long late = Instant.now().minus(2, ChronoUnit.HOURS).toEpochMilli();
		String param = json(new Transaction(late, new Double(12)));
		mockMvc.perform(
				post("/transactions").content(param).contentType(contentType))
				.andExpect(MockMvcResultMatchers.status().isNoContent());
	}

	@Test
	public void addransaction() throws Exception {
		// onTime
		long now = Instant.now().minus(10, ChronoUnit.SECONDS).toEpochMilli();
		String param = json(new Transaction(now, new Double(123)));
		mockMvc.perform(
				post("/transactions").content(param).contentType(contentType))
				.andExpect(MockMvcResultMatchers.status().isCreated());
	}

	@Test
	public void getStatistics() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/statistics"))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}
	


	protected String json(Object o) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		return ow.writeValueAsString(o);
	}

}
