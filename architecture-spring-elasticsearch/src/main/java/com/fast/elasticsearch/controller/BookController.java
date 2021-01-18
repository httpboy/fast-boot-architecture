package com.fast.elasticsearch.controller;

import com.alibaba.fastjson.JSON;
import com.fast.elasticsearch.base.BaseResult;
import com.fast.elasticsearch.constant.Constant;
import com.fast.elasticsearch.entity.dto.BookDto;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.ClearScrollRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchScrollRequest;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.Scroll;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * HighLevelRestController
 *
 * @author wliduo[i@dolyw.com]
 * @date 2019/8/14 16:24
 */
@RestController
@RequestMapping("/book")
public class BookController {

    /**
     * logger
     */
    private static final Logger logger = LoggerFactory.getLogger(BookController.class);

    @Autowired
    private RestHighLevelClient restHighLevelClient;

    /**
     * 获取ES信息
     *
     * @param
     * @return com.example.common.BaseResult
     * @throws IOException
     * @author wliduo[i@dolyw.com]
     * @date 2019/8/14 17:11
     */
    @GetMapping("/es")
    public BaseResult getEsInfo() throws IOException {
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        // SearchRequest
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.source(searchSourceBuilder);
        // 查询ES
        SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        return new BaseResult(HttpStatus.OK.value(), "查询成功", searchResponse);
    }

    /**
     * 列表查询
     *
     * @param page
     * @param rows
     * @param keyword
     * @return com.example.common.BaseResult
     * @throws
     * @author wliduo[i@dolyw.com]
     * @date 2019/8/15 16:01
     */
    @GetMapping("/book")
    public BaseResult list(@RequestParam(defaultValue = "1") Integer page,
                           @RequestParam(defaultValue = "10") Integer rows,
                           String keyword) throws IOException {
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        // 分页采用简单的from + size分页，适用数据量小的，了解更多分页方式可自行查阅资料
        searchSourceBuilder.from((page - 1) * rows);
        searchSourceBuilder.size(rows);
        // 查询条件，只有查询关键字不为空才带查询条件
        if (StringUtils.isNoneBlank(keyword)) {

            /**
             * 单个匹配-精确查询
             * 精确查询（必须完全匹配上），Term查询不会对字段进行分词查询，会采用精确匹配。
             * 所以汉字只能查询一个字，英语单词是一个单词（区分大小写）
             *
             * 相当于SQL中的查询语句，select * form tb1 where name = keyword
             * 可以理解为一个name字段内容中包含一个中文汉字（keyword）和一个单词（keyword）
             */
//            QueryBuilder queryBuilder = QueryBuilders.termQuery("name", keyword);


            /**
             *匹配所有文件，没有设置查询条件
             */
//            QueryBuilder queryBuilder = QueryBuilders.matchAllQuery();

            /**
             * 多个匹配-精确查询
             * 精确查询（必须完全匹配上），Term查询不会对字段进行分词查询，会采用精确匹配。
             * 所以汉字只能查询一个字，英语单词是一个单词（区分大小写）。
             * 相当于SQL中的in语句，select * form tb1 where name in (keyword,boy)
             * 可以理解为一个name字段内容中包含一个中文汉字（keyword）或者一个英文单词（boy）
             */
//            QueryBuilder queryBuilder = QueryBuilders.termsQuery("name", keyword, "boy");

            /**
             * 单个匹配-分词查询（采用默认的分词器）
             * 举例说明搜索条件：keyword=科技节,搜索结果：name=科、技、科技节
             */
//            QueryBuilder queryBuilder = QueryBuilders.matchQuery("name", keyword);

            /**
             * 多个匹配-分词查询（采用默认的分词器）
             * 和单个匹配原理相同，就是多了一个字段内容
             */
            QueryBuilder queryBuilder = QueryBuilders.multiMatchQuery(keyword, "name", "desc");
            searchSourceBuilder.query(queryBuilder);
        }
        // 排序，根据ID倒叙
        searchSourceBuilder.sort("id", SortOrder.DESC);
        // SearchRequest
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.source(searchSourceBuilder);
        // 查询ES
        SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        SearchHits hits = searchResponse.getHits();
        // 获取总数
        Long total = hits.getTotalHits().value;
        // 遍历封装列表对象
        List<BookDto> bookDtoList = new ArrayList<>();
        SearchHit[] searchHits = hits.getHits();
        for (SearchHit searchHit : searchHits) {
            bookDtoList.add(JSON.parseObject(searchHit.getSourceAsString(), BookDto.class));
        }
        // 封装Map参数返回
        Map<String, Object> result = new HashMap<String, Object>(16);
        result.put("count", total);
        result.put("data", bookDtoList);
        return new BaseResult(HttpStatus.OK.value(), "查询成功", result);
    }

    /**
     * 根据ID查询
     * <p>
     * select * from user where id=""
     *
     * @param id
     * @return
     * @throws IOException
     */
    @GetMapping("/book/{id}")
    public BaseResult getById(@PathVariable("id") String id) throws IOException {
        // GetRequest
        GetRequest getRequest = new GetRequest(Constant.INDEX, id);
        // 查询ES
        GetResponse getResponse = restHighLevelClient.get(getRequest, RequestOptions.DEFAULT);
        BookDto bookDto = JSON.parseObject(getResponse.getSourceAsString(), BookDto.class);
        return new BaseResult(HttpStatus.OK.value(), "查询成功", bookDto);
    }

    /**
     * 添加文档
     *
     * @param bookDto
     * @return
     * @throws IOException
     */
    @PostMapping("/book")
    public BaseResult add(@RequestBody BookDto bookDto) throws IOException {
        // IndexRequest
        IndexRequest indexRequest = new IndexRequest(Constant.INDEX);
        Long id = System.currentTimeMillis();
        bookDto.setId(id);
        String source = JSON.toJSONString(bookDto);
        indexRequest.id(id.toString()).source(source, XContentType.JSON);
        // 操作ES
        IndexResponse indexResponse = restHighLevelClient.index(indexRequest, RequestOptions.DEFAULT);
        return new BaseResult(HttpStatus.OK.value(), "添加成功", indexResponse);
    }

    /**
     * 修改文档
     *
     * @param bookDto
     * @return
     * @throws IOException
     */
    @PutMapping("/book")
    public BaseResult update(@RequestBody BookDto bookDto) throws IOException {
        // UpdateRequest
        UpdateRequest updateRequest = new UpdateRequest(Constant.INDEX, bookDto.getId().toString());
        updateRequest.doc(JSON.toJSONString(bookDto), XContentType.JSON);
        // 操作ES
        UpdateResponse updateResponse = restHighLevelClient.update(updateRequest, RequestOptions.DEFAULT);
        return new BaseResult(HttpStatus.OK.value(), "修改成功", updateResponse);
    }

    /**
     * 删除文档
     *
     * @param id
     * @return
     * @throws IOException
     */
    @DeleteMapping("/book/{id}")
    public BaseResult deleteById(@PathVariable("id") String id) throws IOException {
        // DeleteRequest
        DeleteRequest deleteRequest = new DeleteRequest(Constant.INDEX, id);
        // 操作ES
        DeleteResponse deleteResponse = restHighLevelClient.delete(deleteRequest, RequestOptions.DEFAULT);
        return new BaseResult(HttpStatus.OK.value(), "删除成功", deleteResponse);
    }

    /**
     * 根据索引查询10条数据(默认10条数据)
     */

    @RequestMapping(value = "/selectAll")
    public BaseResult selectAll() {
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        QueryBuilder queryBuilder = QueryBuilders.matchAllQuery();
        searchSourceBuilder.query(queryBuilder);
        searchSourceBuilder.size(100);
        SearchRequest searchRequest = new SearchRequest(Constant.INDEX).source(searchSourceBuilder);

        SearchResponse response = null;
        try {
            response = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        SearchHit[] searchHits = response.getHits().getHits();
        // 遍历封装列表对象
        List<BookDto> bookDtoList = new ArrayList<>();
        for (SearchHit searchHit : searchHits) {
            bookDtoList.add(JSON.parseObject(searchHit.getSourceAsString(), BookDto.class));
        }
        // 封装Map参数返回
        Map<String, Object> result = new HashMap<String, Object>(16);
        result.put("count", searchHits.length);
        result.put("data", bookDtoList);

        return new BaseResult(HttpStatus.OK.value(), "获取数据成功", result);
    }

    /**
     * 使用游标一次查出全部结果
     * <p>
     * Elasticsearch中进行大数据量查询时，往往因为设备、网络传输问题影响查询数据的效率；
     * Elasticsearch中提供了Scroll（游标）的方式对数据进行少量多批次的滚动查询，
     * 来提高查询效率
     */

    @RequestMapping(value = "/scrollSearchAll")
    public BaseResult scrollSearchAll() {
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchAllQuery());
        SearchRequest searchRequest = new SearchRequest(Constant.INDEX).source(searchSourceBuilder);
        Scroll scroll = new Scroll(TimeValue.timeValueSeconds(5));
        searchRequest.scroll(scroll);
        SearchResponse searchResponse = null;
        try {
            searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String scrollId = searchResponse.getScrollId();
        SearchHit[] hits = searchResponse.getHits().getHits();
        List<SearchHit> resultSearchHit = new ArrayList<>();
        while (ArrayUtils.isNotEmpty(hits)) {
            for (SearchHit hit : hits) {
                resultSearchHit.add(hit);
            }
            SearchScrollRequest searchScrollRequest = new SearchScrollRequest(scrollId);
            searchScrollRequest.scroll(scroll);
            SearchResponse searchScrollResponse = null;
            try {
                searchScrollResponse = restHighLevelClient.searchScroll(searchScrollRequest, RequestOptions.DEFAULT);
            } catch (IOException e) {
                e.printStackTrace();
            }
            scrollId = searchScrollResponse.getScrollId();
            hits = searchScrollResponse.getHits().getHits();
        }
        //及时清除es快照，释放资源
        ClearScrollRequest clearScrollRequest = new ClearScrollRequest();
        clearScrollRequest.addScrollId(scrollId);
        try {
            restHighLevelClient.clearScroll(clearScrollRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 遍历封装列表对象
        List<BookDto> bookDtoList = new ArrayList<>();
        for (SearchHit searchHit : resultSearchHit) {
            bookDtoList.add(JSON.parseObject(searchHit.getSourceAsString(), BookDto.class));
        }
        // 封装Map参数返回
        Map<String, Object> result = new HashMap<String, Object>(16);
        result.put("count", resultSearchHit.size());
        result.put("data", bookDtoList);
        return new BaseResult(HttpStatus.OK.value(), "获取数据成功", result);
    }
}
