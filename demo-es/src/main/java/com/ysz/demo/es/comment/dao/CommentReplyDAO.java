package com.ysz.demo.es.comment.dao;

import com.ysz.demo.es.comment.dao.dataobject.CommentReplyDO;

/**
 * <B>描述：</B><br/>
 * <B>作者：</B> carl.yu <br/>
 * <B>时间：</B> 2017/9/29 <br/>
 * <B>版本：</B><br/>
 */
public interface CommentReplyDAO {

  String save(CommentReplyDO commentReplyDO);

  Integer countReplyByCommentId(String commentId);
}
