package com.example.SummaryStatistics.domain;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@lombok.Data
@AllArgsConstructor
public class Statistics {

	private Double sum;
	private Double avg;
	private Double max;
	private Double min;
	private Long count;

	public Statistics(){
		sum = avg = max = min = new Double(0);
		count = new Long(0);
	}
	protected boolean canEqual(Object other) {
		return other instanceof Statistics;
	}
}
