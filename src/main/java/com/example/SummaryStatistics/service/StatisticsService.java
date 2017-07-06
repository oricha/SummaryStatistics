package com.example.SummaryStatistics.service;

import java.text.ParseException;
import java.time.Instant;
import java.util.Collections;
import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.SummaryStatistics.domain.Statistics;
import com.example.SummaryStatistics.domain.Transaction;

@Service
public class StatisticsService {

	NavigableMap<Long, Double> transMap = Collections
			.synchronizedNavigableMap(new TreeMap<Long, Double>());

	public void addTransaction(Transaction param) throws MyCustomException {

		long nowMinus60s = Instant.now().plusSeconds(-60).toEpochMilli();

		if (param.getTimestamp() < nowMinus60s) {
			throw new MyCustomException("No Valid TimeStamp");
		}
		transMap.put(param.getTimestamp(), param.getAmount());
	}

	public Statistics getstatistics() throws ParseException {

		Statistics statistics = new Statistics();
		long nowMinus60s = Instant.now().plusSeconds(-60).toEpochMilli();

		Map<Long, Double> subMap = transMap.subMap(nowMinus60s, Instant.now()
				.toEpochMilli());

		if (!subMap.isEmpty()) {
			DoubleSummaryStatistics sumaryStat = subMap.values()
					.parallelStream()
					.collect(Collectors.summarizingDouble(Double::doubleValue));
			statistics.setAvg(sumaryStat.getAverage());
			statistics.setMax(sumaryStat.getMax());
			statistics.setMin(sumaryStat.getMin());
			statistics.setCount(sumaryStat.getCount());
			statistics.setSum(sumaryStat.getSum());
		}
		return statistics;
	}

	class MyCompr implements Comparator<Double> {
		@Override
		public int compare(Double str1, Double str2) {
			return str1.compareTo(str2);
		}
	}

}
