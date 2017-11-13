package com.ysz.demo.es.log.dao.impl;

import com.ysz.demo.es.log.dao.LogStashDAO;

import org.elasticsearch.client.transport.TransportClient;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * <B>描述：</B><br/>
 * <B>作者：</B> carl.yu <br/>
 * <B>时间：</B> 2017/10/15 <br/>
 * <B>版本：</B><br/>
 */
@Repository
public class LogStashDAOImpl implements LogStashDAO {

  @Resource
  private TransportClient transportClient;

  //分析对个需求，针对某个 bucket，分析它的metrics

}
