package com.ysz.demo.es.comment.dao.impl;

import com.ysz.demo.es.comment.dao.CommentCommentDAO;
import com.ysz.demo.es.comment.dao.CommentReplyDAO;
import com.ysz.demo.es.comment.dao.dataobject.CommentCommentDO;
import com.ysz.demo.es.comment.dao.dataobject.CommentReplyDO;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <B>描述：</B><br/>
 * <B>作者：</B> carl.yu <br/>
 * <B>时间：</B> 2017/9/27 <br/>
 * <B>版本：</B><br/>
 */
public class CommentCommentDAOImplTest {

  private ClassPathXmlApplicationContext ctx;
  private CommentCommentDAO commentCommentDAO;
  private CommentReplyDAO commentReplyDAO;


  @After
  public void tearDown() throws Exception {
    ctx.close();
  }

  @Before
  public void setUp() throws Exception {
    ctx = new ClassPathXmlApplicationContext("classpath:comment-context.xml");
    commentCommentDAO = ctx.getBean(CommentCommentDAO.class);
    commentReplyDAO = ctx.getBean(CommentReplyDAO.class);
  }

  @Test
  public void shouldAddReply() throws Exception {

  }

  @Test
  public void save() throws Exception {
    String id = commentCommentDAO.save(newComment());
    System.err.println("保存成功,id是:" + id);
    System.err.println(commentCommentDAO.findById(id));
    CommentReplyDO replyDO = newReply(id);
    String replyId = commentReplyDAO.save(replyDO);
    System.err.println("replyId:" + replyId);
  }


  @Test
  public void shouldFindById() throws Exception {
    while (true) {
      try {
        System.out.println(commentCommentDAO.findById("AV7SDvaX1PhnZgqQuKON"));
      } catch (Exception e) {
        e.printStackTrace();
      }
      Thread.sleep(1000L);
    }
  }

  private CommentReplyDO newReply(String commentId) {
    CommentReplyDO replyDO = new CommentReplyDO();
    replyDO.setContentBody("强哥说可以");
    replyDO.setGmtUpdate(new Date());
    replyDO.setGmtUpdate(new Date());
    replyDO.setFromUserId(100001L);
    replyDO.setToUserId(100002L);
    replyDO.setCommentId(commentId);
    return replyDO;
  }

  private CommentCommentDO newComment() {
    CommentCommentDO commentCommentDO = new CommentCommentDO();
    commentCommentDO.setContentBody("测试内容");
    commentCommentDO.setGmtCreate(new Date());
    commentCommentDO.setGmtUpdate(new Date());
    commentCommentDO.setPriority(1);
    commentCommentDO.setSenderId(10000L);
    commentCommentDO.setReferId(1L);
    commentCommentDO.setReferType((byte) 1);
    return commentCommentDO;
  }

  @Test
  public void ShouldReturnWithId() throws Exception {
    System.err.println("ok");
  }

  @Test
  public void shouldCountGroupBySenderId() throws Exception {
    Map<Long, Long> groupBySenderIdMap = commentCommentDAO.countGroupBySenderId();
    System.err.println(groupBySenderIdMap);
  }

  @Test
  public void shouldBulkIndex() throws Exception {
    List<CommentCommentDO> commentCommentDOList = generateCommentCommentList();
    commentCommentDAO.bulkSave(commentCommentDOList);
  }

  private static String[] contents = {"你好，中国", "我也好，中国", "中国有一个故事", "中国老司机", "乌克兰老司机"};
  private static String[] dates = {
      "2016-01-01",
      "2016-02-01",
      "2016-03-01",
      "2016-04-01",
      "2016-05-01",
      "2017-01-01",
      "2017-02-01",
      "2017-03-01",
      "2017-04-01"
  };

  private static Long[] sendIds = {1001L, 1002L, 1003L, 1004L, 1005L};

  private List<CommentCommentDO> generateCommentCommentList() throws ParseException {
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    int limit = 500;
    List<CommentCommentDO> commentCommentDOList = new ArrayList<>(limit);
    for (int i = 0; i < limit; i++) {
      CommentCommentDO commentCommentDO = new CommentCommentDO();
      commentCommentDOList.add(commentCommentDO);
      commentCommentDO.setContentBody(contents[i % contents.length]);
      commentCommentDO.setGmtCreate(format.parse(dates[i % dates.length]));
      commentCommentDO.setGmtUpdate(format.parse(dates[i % dates.length]));
      commentCommentDO.setPriority(1);
      commentCommentDO.setSenderId(sendIds[i % sendIds.length]);
      commentCommentDO.setReferId((long) i);
      commentCommentDO.setReferType((byte) (i % 3));
    }
    return commentCommentDOList;
  }
}