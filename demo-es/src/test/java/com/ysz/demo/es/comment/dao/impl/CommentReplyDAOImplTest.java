package com.ysz.demo.es.comment.dao.impl;

import com.ysz.demo.es.comment.dao.CommentReplyDAO;
import com.ysz.demo.es.comment.dao.dataobject.CommentReplyDO;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Date;

/**
 * <B>描述：</B><br/>
 * <B>作者：</B> carl.yu <br/>
 * <B>时间：</B> 2017/9/29 <br/>
 * <B>版本：</B><br/>
 */
public class CommentReplyDAOImplTest {
  private ClassPathXmlApplicationContext ctx;
  private CommentReplyDAO commentReplyDAO;

  @After
  public void tearDown() throws Exception {
    ctx.close();
  }

  @Before
  public void setUp() throws Exception {
    ctx = new ClassPathXmlApplicationContext("classpath:comment-context.xml");
    commentReplyDAO = ctx.getBean(CommentReplyDAO.class);
  }

  @Test
  public void shouldSaveReply() throws Exception {
    commentReplyDAO.save(newReply("AV7SDvaX1PhnZgqQuKON"));
  }

  @Test
  public void shouldCountReply() throws Exception {
    int count = commentReplyDAO.countReplyByCommentId("AV7SDvaX1PhnZgqQuKON");
    System.err.println("count:" + count);
  }


  private CommentReplyDO newReply(String commentId) {
    CommentReplyDO replyDO = new CommentReplyDO();
    replyDO.setContentBody("不可以");
    replyDO.setGmtUpdate(new Date());
    replyDO.setGmtUpdate(new Date());
    replyDO.setFromUserId(100001L);
    replyDO.setToUserId(100002L);
    replyDO.setCommentId(commentId);
    return replyDO;
  }
}