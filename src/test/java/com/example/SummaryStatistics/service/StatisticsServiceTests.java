package com.example.SummaryStatistics.service;

import static org.junit.Assert.assertEquals;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.example.SummaryStatistics.domain.Statistics;
import com.example.SummaryStatistics.domain.Transaction;

@RunWith(SpringJUnit4ClassRunner.class)
public class StatisticsServiceTests {

	StatisticsService service;
	
	@Before
	public void setUp() {
		service = new StatisticsService();
	}
	
	
	@Test
	public void testAddTransaction() throws Exception {
		
		long now = Instant.now().toEpochMilli();

		Transaction trans1 = new Transaction(now, new Double(10) );
		service.addTransaction(trans1);
		
		now = Instant.now().minus(1, ChronoUnit.SECONDS).toEpochMilli();
		Transaction trans2 = new Transaction(now, new Double(20) );
		service.addTransaction(trans2);
		
		now = Instant.now().minus(2, ChronoUnit.SECONDS).toEpochMilli();
		Transaction trans3 = new Transaction(now, new Double(30) );
		service.addTransaction(trans3);
		
		Statistics stats = service.getstatistics();
		assertEquals(new Double(60), stats.getSum());
		assertEquals(new Long(3), stats.getCount());
		assertEquals(new Double(30), stats.getMax());
		assertEquals(new Double(10), stats.getMin());
	}
	
	
}
