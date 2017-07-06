package com.example.SummaryStatistics.domain;


import lombok.NoArgsConstructor;

@lombok.Data
@NoArgsConstructor
public class Transaction {
	
	private Double amount;
	private Long timestamp;

	public Transaction( Long timestamp,Double amount) {
		super();
		this.amount = amount;
		this.timestamp = timestamp;
	}
}
