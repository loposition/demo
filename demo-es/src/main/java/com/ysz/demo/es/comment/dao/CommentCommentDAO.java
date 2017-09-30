package com.ysz.demo.es.comment.dao;

import com.ysz.demo.es.comment.dao.dataobject.CommentCommentDO;

import java.util.List;
import java.util.Map;

/**
 * <B>描述：</B><br/>
 * <B>作者：</B> carl.yu <br/>
 * <B>时间：</B> 2017/9/26 <br/>
 * <B>版本：</B><br/>
 */
public interface CommentCommentDAO {

  String save(CommentCommentDO commentComment);

  CommentCommentDO findById(String id);

  void bulkSave(List<CommentCommentDO> commentCommentList);

  /**
   * 根据 senderId统计 count信息
   */
  Map<Long, Long> countGroupBySenderId();
}
