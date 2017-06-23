package com.example.SummaryStatistics.controller;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.SummaryStatistics.domain.Statistics;
import com.example.SummaryStatistics.domain.Transaction;
import com.example.SummaryStatistics.service.MyCustomException;
import com.example.SummaryStatistics.service.StatisticsService;

@Controller
public class StatisticsController {
	@Autowired
	StatisticsService service;

	@RequestMapping(value = { "", "/" }, method = RequestMethod.GET)
	public String home(Model model) {
		return "home";
	}

	/**
	 * Add transactions from the last 60s
	 * @param param
	 * @return
	 */
	@RequestMapping(value = { "/transactions" }, method = RequestMethod.POST)
	public ResponseEntity<Void> addTransactions(@RequestBody Transaction param) {

		try {
			service.addTransaction(param);
			
		} catch (MyCustomException e) {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}

	/**
	 * It returns the statistic based on the transactions which happened in the
	 * last 60 seconds.
	 * 
	 * @param model
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping(value = { "/statistics" }, method = RequestMethod.GET)
	@ResponseBody
	public Statistics  getStatistics(Model model) throws ParseException {

		return service.getstatistics();
	}

}
