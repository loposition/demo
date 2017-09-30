package com.ysz.demo.es.comment.dao.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.PropertyPreFilter;
import com.google.common.collect.ImmutableSet;
import com.ysz.demo.es.base.DaoException;
import com.ysz.demo.es.comment.dao.CommentReplyDAO;
import com.ysz.demo.es.comment.dao.dataobject.CommentReplyDO;

import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * <B>描述：</B><br/>
 * <B>作者：</B> carl.yu <br/>
 * <B>时间：</B> 2017/9/29 <br/>
 * <B>版本：</B><br/>
 */
@Repository
public class CommentReplyDAOImpl implements CommentReplyDAO {

  private static final String ES_INDEX = "comment";
  private static final String ES_TYPE = "commentReply";

  @Resource
  private TransportClient transportClient;


  @Override
  public String save(CommentReplyDO commentReplyDO) {
    try {
      IndexResponse response = transportClient.prepareIndex(ES_INDEX, ES_TYPE)
          .setSource(Mapper.toJsonStr(commentReplyDO), XContentType.JSON)
          .setParent(commentReplyDO.getCommentId())
          .get();
      return response.getId();
    } catch (Exception e) {
      throw new DaoException("save commentComment failed", e);
    }
  }

  private static class Mapper {

    private static final ImmutableSet<String> ignoreProperties = ImmutableSet.of("id", "commentId");

    private static String toJsonStr(CommentReplyDO src) {
      return src == null ? null : JSON.toJSONString(src, new PropertyPreFilter() {
        @Override
        public boolean apply(JSONSerializer serializer, Object object, String name) {
          System.err.println("name:" + name);
          if (name != null && ignoreProperties.contains(name.toLowerCase())) {
            return false;
          }
          return true;
        }
      });
    }


  }
}
