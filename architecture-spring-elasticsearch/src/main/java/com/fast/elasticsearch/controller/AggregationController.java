package com.fast.elasticsearch.controller;

import com.fast.elasticsearch.constant.Constant;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.ParsedStringTerms;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.ParsedAvg;
import org.elasticsearch.search.aggregations.metrics.ParsedSum;
import org.elasticsearch.search.aggregations.metrics.ParsedValueCount;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 聚合函数使用案例
 */
@RestController
@Slf4j
@RequestMapping(value = "/aggregation")
public class AggregationController {
    @Autowired
    private RestHighLevelClient restHighLevelClient;

    /**
     * 根据姓名，分组统计个数
     *
     * select count(id) from table group by name
     *
     * @return
     */
    @RequestMapping(value = "/countTimesGroupByName")
    public Object countTimesGroupByName() {
        try {
            SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
            //根据姓名进行分组统计个数
            TermsAggregationBuilder field = AggregationBuilders.terms("terms_name").field("name.keyword");
            AggregationBuilder countField = AggregationBuilders.count("count_name").field("name.keyword");
            field.subAggregation(countField);
            field.size(100);
            searchSourceBuilder.aggregation(field);
            SearchRequest searchRequest = new SearchRequest(Constant.INDEX).source(searchSourceBuilder);
            SearchResponse response = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
            //分组在es中是分桶
            ParsedStringTerms termsName = response.getAggregations().get("terms_name");
            List<? extends Terms.Bucket> buckets = termsName.getBuckets();
            log.info("分组统计 size " + buckets.size());
            List<Map<String, String>> resultList = new ArrayList<>();
            buckets.forEach(naem -> {
                String key = (String) naem.getKey();
                ParsedValueCount countName = naem.getAggregations().get("count_name");
                double value = countName.value();
                log.info("分组统计-{}-{}", key, (int) value + "\n");
                HashMap<String, String> map = new HashMap<>(16);
                map.put(key, (int) value + "");
                resultList.add(map);
            });
            return resultList;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 统计姓名为xxx的 平均价格
     * select avg(age) age from user where name=xxx
     *
     * @return List<User>
     */
    @RequestMapping(value = "/countAge")
    public Object countAge(String name) {
        try {
            SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
            QueryBuilder names = QueryBuilders.termQuery("name.keyword", name);
            AggregationBuilder avgAge = AggregationBuilders.avg("avg_price").field("price");
            searchSourceBuilder.aggregation(avgAge);
            searchSourceBuilder.query(names);
            searchSourceBuilder.size(0);
            SearchRequest searchRequest = new SearchRequest(Constant.INDEX).source(searchSourceBuilder);
            SearchResponse response = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
            ParsedAvg avgPrice = response.getAggregations().get("avg_price");
            Map<String, String> map = new HashMap<>(16);
            map.put(name, avgPrice.value() + "");
            return map;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 统计姓名为xxx的年龄总和
     * select sum(age) age from user where name=xxx
     *
     * @param name
     * @return
     */
    @RequestMapping(value = "/sumAge")
    public Object sumAge(String name) {
        try {
            SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
            QueryBuilder names = QueryBuilders.termQuery("name.keyword", name);
            AggregationBuilder sumAge = AggregationBuilders.sum("total_sum").field("price");
            searchSourceBuilder.aggregation(sumAge);
            searchSourceBuilder.query(names);
            searchSourceBuilder.size(0);
            SearchRequest searchRequest = new SearchRequest(Constant.INDEX).source(searchSourceBuilder);
            SearchResponse response = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
            ParsedSum.SingleValue singleValue = response.getAggregations().get("total_sum");
            Map<String, String> map = new HashMap<>(16);
            map.put(name, singleValue.value() + "");
            return map;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 求出name=xxx中价格的最低值
     * select min(xxx) from table where name=xxx
     *
     * @param name
     * @return
     */
    @RequestMapping(value = "/minPrice")
    public Object minPrice(String name) {
        try {
            SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
            QueryBuilder names = QueryBuilders.termQuery("name.keyword", name);
            AggregationBuilder sumAge = AggregationBuilders.min("min").field("price");
            searchSourceBuilder.aggregation(sumAge);
            searchSourceBuilder.query(names);
            searchSourceBuilder.size(0);
            SearchRequest searchRequest = new SearchRequest(Constant.INDEX).source(searchSourceBuilder);
            SearchResponse response = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
            ParsedSum.SingleValue singleValue = response.getAggregations().get("min");
            Map<String, String> map = new HashMap<>(16);
            map.put(name, singleValue.value() + "");
            return map;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



}
