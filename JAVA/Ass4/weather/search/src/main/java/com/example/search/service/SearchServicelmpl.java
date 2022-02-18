package com.example.search.service;

import com.example.search.pojo.City;
import com.example.search.config.SearchEndpoint;
import com.example.search.threadlocal.MyThreadLocal;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.scheduling.annotation.Async;
import java.util.concurrent.CompletableFuture;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Slf4j
@Service
public class SearchServicelmpl implements SearchService{
    private final RestTemplate restTemplate;

    public SearchServicelmpl(RestTemplate getRestTemplate) {
        this.restTemplate = getRestTemplate;
    }

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    @Async
    @Retryable(include = IllegalAccessError.class)
    public List<Map<String, Map>> GetDetails(List<String> city){
        String transactionId = MyThreadLocal.getTransactionId();
        this.logger.info("2. Init operation controller Transaction Id:{}", transactionId);

        List<List<Integer>> temp = new ArrayList<>();
        for(int i = 0; i < city.size(); i++){
            City[] cities = restTemplate.getForObject(SearchEndpoint.queryWeatherByCity + city.get(i), City[].class);
            List<Integer> ans = new ArrayList<>();
            for(City c: cities) {
                if(c != null && c.getWoeid() != null) {
                    ans.add(c.getWoeid());
                }
            }
            temp.add(ans);
        }

        Map<String,Map> map1 = restTemplate.getForObject(SearchEndpoint.queryWeatherById + temp.get(0).get(0), HashMap.class);
        Map<String,Map> map2 = restTemplate.getForObject(SearchEndpoint.queryWeatherById + temp.get(1).get(0), HashMap.class);


        CompletableFuture<Map<String,Map>>  cf = CompletableFuture.completedFuture(map1);
        CompletableFuture<Map<String,Map>>  cf2 = CompletableFuture.completedFuture(map2);

        List<Map<String,Map>> ans = new ArrayList<>();
        try{
            CompletableFuture.allOf(cf,cf2).join();
            ans.add(cf.get());
            ans.add(cf2.get());
        }catch (Exception e){
            CompletableFuture.allOf(cf,cf2).completeExceptionally(e);
        }
        this.logger.info("4. End point Transaction Id:{}",transactionId);
        return ans;
    }
}
