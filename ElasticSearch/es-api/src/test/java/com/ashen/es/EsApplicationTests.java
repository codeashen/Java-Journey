package com.ashen.es;

import com.alibaba.fastjson.JSON;
import com.ashen.es.pojo.User;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.FetchSourceContext;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

/**
 * ES 7.6.x 高级客户端测试 API
 */
@SpringBootTest
class EsApplicationTests {

    @Autowired
    @Qualifier("restHighLevelClient")
    private RestHighLevelClient client;

    // 创建索引
    @Test
    public void createIndexTest() throws Exception {
        // 1. 创建索引请求
        CreateIndexRequest request = new CreateIndexRequest("lucifer_index");
        // 2. 客户端执行请求 IndicesClient, 请求后获得响应
        CreateIndexResponse response = client.indices().create(request, RequestOptions.DEFAULT);
        System.out.println(response.isAcknowledged());
    }

    // 判断索引是否存在
    @Test
    public void existsIndexTest() throws Exception {
        GetIndexRequest request = new GetIndexRequest("lucifer_index");
        boolean exists = client.indices().exists(request, RequestOptions.DEFAULT);
        System.out.println(exists);
    }

    // 删除索引
    @Test
    public void deleteIndexTest() throws Exception {
        DeleteIndexRequest request = new DeleteIndexRequest("lucifer_index");
        AcknowledgedResponse response = client.indices().delete(request, RequestOptions.DEFAULT);
        System.out.println(response.isAcknowledged());
    }

    // 添加文档
    @Test
    public void addDocumentTest() throws Exception {
        // 创建对象
        User user = new User("张三", 18);
        // 创建请求
        IndexRequest request = new IndexRequest("lucifer_index");
        // 规则 put /lucifer_index/_doc/1
        request.id("1").timeout(TimeValue.timeValueSeconds(1)); // 设置id和超时时间
        // 将数据放入请求 json
        request.source(JSON.toJSONString(user), XContentType.JSON);
        // 客户端发送请求
        IndexResponse response = client.index(request, RequestOptions.DEFAULT);
        System.out.println(String.format("status:%s, response:%s", response.status(), response.toString()));
    }

    // 获取文档, 判断是否存在 get /index/doc/1
    @Test
    public void existsDocumentTest() throws Exception {
        GetRequest request = new GetRequest("lucifer_index", "1");
        // 不获取返回的 _source 的上下文了
        request.fetchSourceContext(new FetchSourceContext(false));
        request.storedFields("_none_");  // 明确指定返回什么fields

        boolean exists = client.exists(request, RequestOptions.DEFAULT);
        System.out.println(exists);  // true
    }

    // 获取文档信息
    @Test
    public void getDocumentTest() throws Exception {
        GetRequest request = new GetRequest("lucifer_index", "1");
        GetResponse response = client.get(request, RequestOptions.DEFAULT);
        System.out.println(response.getSourceAsString());  // 打印文档的内容
        System.out.println(response);  // 打印全部内容
    }

    // 更新文档
    @Test
    public void updateDocumentTest() throws Exception {
        UpdateRequest request = new UpdateRequest("lucifer_index", "1");
        request.timeout("1s");
        User user = new User("李四", 5);
        // 设置数据，指定数据类型
        request.doc(JSON.toJSONString(user), XContentType.JSON);
        UpdateResponse response = client.update(request, RequestOptions.DEFAULT);
        System.out.println(response.status());  // OK
    }

    // 删除文档
    @Test
    public void deleteDocumentTest() throws Exception {
        DeleteRequest request = new DeleteRequest("lucifer_index", "1");
        request.timeout("1s");
        DeleteResponse response = client.delete(request, RequestOptions.DEFAULT);
        System.out.println(response.status());
    }


    // 批量处理数据
    @Test
    public void bulkTest() throws Exception {
        BulkRequest bulkRequest = new BulkRequest();
        bulkRequest.timeout("10s");

        List<User> users = new ArrayList<>();
        users.add(new User("Alice", 4));
        users.add(new User("Frank", 5));
        users.add(new User("Tina", 3));

        // 添加批处理请求
        for (int i = 0; i < users.size(); i++) {
            // 批量更新或者批量删除也是同理
            bulkRequest.add(new IndexRequest("lucifer_index")
                    .id((i + 10) + "")
                    .source(JSON.toJSONString(users.get(i)), XContentType.JSON)
            );
        }
        // 执行批处理
        BulkResponse response = client.bulk(bulkRequest, RequestOptions.DEFAULT);

        System.out.println(response.hasFailures());  // 打印是否有失败，false表示没有失败的，全部成功
        System.out.println(response.status());
    }

    // 查询文档
    @Test
    public void searchTest() throws Exception {
        // 创建搜索请求
        SearchRequest request = new SearchRequest("lucifer_index");
        // 构建搜索条件
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        // 查询条件可以使用 QueryBuilders 工具来实现
        // QueryBuilders.termQuery();     // 精确
        // MatchAllQueryBuilder matchAllQueryBuilder = QueryBuilders.matchAllQuery();// 匹配所有
        TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("name", "Alice");
        sourceBuilder.query(termQueryBuilder);

        sourceBuilder.timeout(TimeValue.timeValueSeconds(60));
        // 组装请求
        request.source(sourceBuilder);

        // 执行搜索
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);

        System.out.println(JSON.toJSONString(response.getHits()));
        System.out.println("==========================");
        for (SearchHit documentFields : response.getHits().getHits()) {
            System.out.println(documentFields.getSourceAsMap());
        }


    }


}
