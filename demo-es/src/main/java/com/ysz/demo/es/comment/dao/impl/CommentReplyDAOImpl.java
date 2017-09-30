package com.ysz.demo.es.comment.dao.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.PropertyPreFilter;
import com.google.common.collect.ImmutableSet;
import com.ysz.demo.es.base.DaoException;
import com.ysz.demo.es.comment.dao.CommentReplyDAO;
import com.ysz.demo.es.comment.dao.constants.CommentEsConstant;
import com.ysz.demo.es.comment.dao.dataobject.CommentReplyDO;

import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.join.query.JoinQueryBuilders;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

import java.util.Map;

/**
 * <B>描述：</B><br/>
 * <B>作者：</B> carl.yu <br/>
 * <B>时间：</B> 2017/9/29 <br/>
 * <B>版本：</B><br/>
 */
@Repository
public class CommentReplyDAOImpl implements CommentReplyDAO {


  @Resource
  private TransportClient transportClient;


  @Override
  public String save(CommentReplyDO commentReplyDO) {
    try {
      IndexResponse response = transportClient.prepareIndex(CommentEsConstant.ES_COMMENT_INDEX,
          CommentEsConstant.ES_COMMENT_REPLY_TYPE)
          .setSource(Mapper.toJsonStr(commentReplyDO), XContentType.JSON)
          .setParent(commentReplyDO.getCommentId())
          .get();
      return response.getId();
    } catch (Exception e) {
      throw new DaoException("save commentComment failed", e);
    }
  }


  @Override
  public Integer countReplyByCommentId(String commentId) {
    SearchResponse searchResponse = transportClient
        .prepareSearch(CommentEsConstant.ES_COMMENT_INDEX)
        .setTypes(CommentEsConstant.ES_COMMENT_REPLY_TYPE)
        .setSource(new SearchSourceBuilder()
            .size(0)
            .query(JoinQueryBuilders
                .hasParentQuery(CommentEsConstant.ES_COMMENT_COMMENT_TYPE,
                    QueryBuilders.termQuery("_id", commentId),
                    false)))
        .get();
    SearchHits hits = searchResponse.getHits();
    return hits == null ? 0 : (int) hits.getTotalHits();
  }

  private static class Mapper {

    private static final ImmutableSet<String> ignoreProperties = ImmutableSet.of("id", "commentId");

    private static String toJsonStr(CommentReplyDO src) {
      return src == null ? null : JSON.toJSONString(src, (PropertyPreFilter) (serializer, object, name) -> {
        System.err.println("name:" + name);
        if (name != null && ignoreProperties.contains(name.toLowerCase())) {
          return false;
        }
        return true;
      });
    }
  }
}
