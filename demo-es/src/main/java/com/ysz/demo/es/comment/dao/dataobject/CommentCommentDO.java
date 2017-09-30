package com.ysz.demo.es.comment.dao.dataobject;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.util.Date;

/**
 * <B>描述：</B><br/>
 * <B>作者：</B> carl.yu <br/>
 * <B>时间：</B> 2017/9/26 <br/>
 * <B>版本：</B><br/>
 */
@Document(indexName = "comment", type = "commentComment", createIndex = false)
@Data
@AllArgsConstructor
@Builder
public class CommentCommentDO {

  @Id
  private String id;

  private Long senderId;

  private Long referId;

  private Byte referType;

  private String contentBody;

  private Integer likeCount;

  private Integer replyCount;

  private Integer priority;

  private Date gmtCreate;

  private Date gmtUpdate;

  public CommentCommentDO() {
  }
}
