package com.ysz.demo.es.comment.service;

/**
 * <B>描述：</B><br/>
 * <B>作者：</B> carl.yu <br/>
 * <B>时间：</B> 2017/9/26 <br/>
 * <B>版本：</B><br/>
 */
public interface CommentService {

  /**
   * 创建评论
   *
   * @param senderId    创建者Id
   * @param contentBody 内容
   * @param referId     引用id ，可能是博客id等等
   * @param referType   引用类型
   * @return true:创建成功 false:创建失败
   */
  Boolean addComment(Long senderId, String contentBody, Long referId, Byte referType);

  Integer countReply(String commentId);

  Integer countLike(String commentId);
}
