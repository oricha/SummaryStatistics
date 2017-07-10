package com.example.SummaryStatistics.controller

import com.example.SummaryStatistics.service.StatisticsService
import groovy.json.JsonSlurper
import groovy.json.JsonOutput
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.web.client.RestTemplate
import static org.springframework.http.HttpStatus.*
import spock.lang.Specification

import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*

class StatisticsControllerSpec extends Specification {

    def url = 'http://localhost:8080'
    def controller = new StatisticsController()
    def serviceMock = Mock(StatisticsService)
    RestTemplate rt

    MockMvc mockMvc = standaloneSetup(controller).build()

    def setup() {
        controller.service = serviceMock
        rt = new RestTemplate()
    }

    def 'test home'() {
        when:
        def response = mockMvc.perform(get(url +'/')).andReturn().response
        then:
        response.status == OK.value()
    }

    def 'test addTransactions' (){
        given:
        def transaction = ['amount': 300, 'timestamp': 1498262995230]
        def json =JsonOutput.toJson(transaction)
        when:
        def response = mockMvc.perform(post(url +'/transactions')
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(json)
                ).andReturn().response
        then:
        response.status == CREATED.value()

    }

    def 'test getStatistics' (){
       given:
            def jsonSlurper = new JsonSlurper()
       when:
            def response = mockMvc.perform(get(url +'/statistics')).andReturn().response
       then:
            response.status == OK.value()
    }
}
