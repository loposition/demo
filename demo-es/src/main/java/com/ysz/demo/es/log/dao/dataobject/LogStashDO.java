package com.ysz.demo.es.log.dao.dataobject;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

/**
 * <B>描述：</B><br/>
 * <B>作者：</B> carl.yu <br/>
 * <B>时间：</B> 2017/10/15 <br/>
 * <B>版本：</B><br/>
 */
@Data
@Builder
@AllArgsConstructor
public class LogStashDO {

  private String message;
  private String tags;
  private Date timestamp;
  private String version;
  private String agent;
  private Long bytes;
  private String clientip;
  private String extension;

}
