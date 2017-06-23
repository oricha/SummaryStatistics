package com.example.SummaryStatistics.domain;

public class Transaction {
	
	private Double amount;
	private Long timestamp;
	
	public Transaction(){}
	
	public Transaction( Long timestamp,Double amount) {
		super();
		this.amount = amount;
		this.timestamp = timestamp;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public Long getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}
}
