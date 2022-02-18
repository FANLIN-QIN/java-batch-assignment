package com.example.search.controller;

import com.example.search.service.SearchService;
import com.example.search.threadlocal.MyThreadLocal;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@Api(tags = "search")
@RestController
public class SearchController {
    private final SearchService ss;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public SearchController(SearchService ss) {
        this.ss = ss;
    }

    @ApiOperation("searchByCity")
    @GetMapping("/weather/search")
    public ResponseEntity<?> getDetails(@RequestParam(value="city") List<String> city) {
        //TODO
        String transactionId = MyThreadLocal.getTransactionId();
        this.logger.info("1. Init operation controller Transaction Id:{}", transactionId);
        this.logger.info("3. End point Transaction Id:{}",transactionId);
        return new ResponseEntity<>(ss.GetDetails(city), HttpStatus.OK);
    }
}
