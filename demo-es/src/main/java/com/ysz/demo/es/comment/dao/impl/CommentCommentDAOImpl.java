package com.ysz.demo.es.comment.dao.impl;

import com.alibaba.fastjson.JSON;
import com.ysz.demo.es.base.DaoException;
import com.ysz.demo.es.comment.dao.CommentCommentDAO;
import com.ysz.demo.es.comment.dao.constants.CommentEsConstant;
import com.ysz.demo.es.comment.dao.dataobject.CommentCommentDO;

import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

import java.util.List;

/**
 * <B>描述：</B><br/>
 * <B>作者：</B> carl.yu <br/>
 * <B>时间：</B> 2017/9/26 <br/>
 * <B>版本：</B><br/>
 */

@Repository
public class CommentCommentDAOImpl implements CommentCommentDAO {

  @Resource
  private TransportClient transportClient;

  @Override
  public String save(CommentCommentDO commentComment) {
    try {
      IndexResponse response = transportClient.prepareIndex(CommentEsConstant.ES_COMMENT_INDEX, CommentEsConstant.ES_COMMENT_COMMENT_TYPE)
          .setSource(Mapper.toJsonStr(commentComment), XContentType.JSON)
          .get();
      return response.getId();
    } catch (Exception e) {
      throw new DaoException("save commentComment failed", e);
    }
  }

  @Override
  public void bulkSave(List<CommentCommentDO> commentCommentList) {
    try {
      BulkRequestBuilder bulkRequestBuilder = transportClient.prepareBulk();
      for (CommentCommentDO commentCommentDO : commentCommentList) {
        if (commentCommentDO != null) {
          bulkRequestBuilder.add(new IndexRequest(CommentEsConstant.ES_COMMENT_INDEX, CommentEsConstant.ES_COMMENT_COMMENT_TYPE).source
              (Mapper.toJsonStr
                      (commentCommentDO),
                  XContentType.JSON));
        }
      }
      bulkRequestBuilder.execute().get();
    } catch (Exception e) {
      throw new DaoException("CommentCommentDAOImpl bulkSave failed", e);
    }
  }


  @Override
  public CommentCommentDO findById(String id) {
    try {
      GetResponse getResponse = transportClient.prepareGet(CommentEsConstant.ES_COMMENT_INDEX, CommentEsConstant.ES_COMMENT_COMMENT_TYPE, id).get();
      return Mapper.toCommentCommentDO(getResponse);
    } catch (Exception e) {
      throw new DaoException("find by id failed", e);
    }
  }

  private static class Mapper {

    private static String toJsonStr(CommentCommentDO commentCommentDO) {
      return commentCommentDO == null ? null : JSON.toJSONString(commentCommentDO, false);
    }

    private static CommentCommentDO toCommentCommentDO(GetResponse getResponse) {
      if (getResponse == null) {
        return null;
      }
      CommentCommentDO dest = JSON.parseObject(getResponse.getSourceAsString(),
          CommentCommentDO.class);
      dest.setId(getResponse.getId());
      return dest;
    }
  }
}
