package com.ysz.demo.es.comment.service.impl;

import com.ysz.demo.es.base.DaoException;
import com.ysz.demo.es.base.ServiceException;
import com.ysz.demo.es.comment.dao.CommentCommentDAO;
import com.ysz.demo.es.comment.dao.CommentReplyDAO;
import com.ysz.demo.es.comment.dao.dataobject.CommentCommentDO;
import com.ysz.demo.es.comment.service.CommentService;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import java.util.Date;

/**
 * <B>描述：</B><br/>
 * <B>作者：</B> carl.yu <br/>
 * <B>时间：</B> 2017/9/26 <br/>
 * <B>版本：</B><br/>
 */
@Service
@Slf4j
public class CommentServiceImpl implements CommentService {

  @Resource
  private CommentCommentDAO commentCommentDAO;

  @Resource
  private CommentReplyDAO commentReplyDAO;

  // 主要是针对 评论的各种查询 ...
  @Override
  public Boolean addComment(Long senderId, String contentBody, Long referId, Byte referType) {
    try {
      CommentCommentDO commentCommentDO = new CommentCommentDO().builder().contentBody(contentBody)
          .referId(referId)
          .referType(referType)
          .gmtCreate(new Date())
          .gmtUpdate(new Date())
          .referId(referId)
          .referType(referType)
          .build();
      commentCommentDAO.save(commentCommentDO);
      return Boolean.TRUE;
    } catch (DaoException e) {
      throw new ServiceException(e);
    }
  }

  @Override
  public Integer countReply(String commentId) {
    try {
      return commentReplyDAO.countReplyByCommentId(commentId);
    } catch (DaoException e) {
      throw new ServiceException(e);
    }
  }

  @Override
  public Integer countLike(String commentId) {
    // 使用 redis 实现 这里不做了 ...
    return null;
  }
}
