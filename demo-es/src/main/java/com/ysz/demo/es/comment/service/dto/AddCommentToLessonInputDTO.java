package com.ysz.demo.es.comment.service.dto;

import lombok.Data;

/**
 * <B>描述：</B><br/>
 * <B>作者：</B> carl.yu <br/>
 * <B>时间：</B> 2017/9/26 <br/>
 * <B>版本：</B><br/>
 */
@Data
public class AddCommentToLessonInputDTO {

  private Long userId;
  private Long referId;
  private Byte referType;
  private String content;
  private Long mediaId;
  private Byte mediaType;
  private String mediaUrl;

  /**
   * 判断对象中是否包含了media信息
   *
   * @return true :表示包含媒体信息 false: 表示没有包含
   */
  public boolean containsMedia() {
    return mediaId != null && mediaId > 0L;
  }
}
