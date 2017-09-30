package com.ysz.demo.es.comment.dao.dataobject;

import lombok.AllArgsConstructor;
import lombok.Data;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Parent;

import java.util.Date;

/**
 * <B>描述：</B><br/>
 * <B>作者：</B> carl.yu <br/>
 * <B>时间：</B> 2017/9/29 <br/>
 * <B>版本：</B><br/>
 */
@Data
@AllArgsConstructor
@Document(indexName = "comment", type = "commentReply", createIndex = false)
public class CommentReplyDO {
  @Id
  private String id;

  @Parent(type = "commentComment")
  private String commentId;

  private String contentBody;

  private Long fromUserId;

  private Long toUserId;
  private Date gmtCreate;
  private Date gmtUpdate;

  public CommentReplyDO() {
  }
}
