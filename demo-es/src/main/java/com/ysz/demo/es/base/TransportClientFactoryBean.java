package com.ysz.demo.es.base;

import com.google.common.base.Preconditions;
import com.google.common.base.Splitter;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang.ArrayUtils;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;

import java.net.InetAddress;
import java.util.Arrays;
import java.util.List;

/**
 * <B>描述：</B><br/>
 * <B>作者：</B> carl.yu <br/>
 * <B>时间：</B> 2017/9/29 <br/>
 * <B>版本：</B><br/>
 */
@Slf4j
public class TransportClientFactoryBean implements FactoryBean<TransportClient>, DisposableBean,
    InitializingBean {

  private TransportClient transportClient;

  @Setter
  @Getter
  private String clusterName;

  @Setter
  @Getter
  private String nodes;

  @Override
  public TransportClient getObject() throws Exception {
    return transportClient;
  }

  @Override
  public void destroy() throws Exception {
    System.err.println("TransportClientFactoryBean destroy");
    log.info("TransportClientFactoryBean destroy");
    if (transportClient != null) {
      transportClient.close();
    }
  }

  @Override
  public void afterPropertiesSet() throws Exception {
    Preconditions.checkState(
        clusterName != null &&
            !"".equals(clusterName), "clusterName can't be null");
    Settings settings = Settings.builder()
        .put("cluster.name", clusterName)
        .build();
    InetSocketTransportAddress[]
        nodeList = parseNodesToTransportAddressArray(nodes);
    Preconditions.checkState(ArrayUtils.isNotEmpty(nodeList), "es cluster nodes can't be empty");
    log.debug("es cluster nodes parse success:{}", Arrays.toString(nodeList));
    transportClient =
        new PreBuiltTransportClient(settings)
            .addTransportAddresses(nodeList);
  }

  private InetSocketTransportAddress[] parseNodesToTransportAddressArray(String nodes) {
    InetSocketTransportAddress[] addressArray = null;
    try {
      List<String> addrAndPortList = Splitter.on(",").omitEmptyStrings().trimResults().splitToList(nodes);
      addressArray = new InetSocketTransportAddress[addrAndPortList.size()];
      int i = 0;
      for (String item : addrAndPortList) {
        List<String> addrAndPort = Splitter.on(":").omitEmptyStrings().splitToList(item);
        addressArray[i++] = new InetSocketTransportAddress(
            InetAddress.getByName(addrAndPort.get(0)),
            Integer.parseInt(addrAndPort.get(1)));
      }

    } catch (Exception e) {
      log.error("parseNodesToTransportAddressArray failed", e);
    }
    return addressArray;
  }

  @Override
  public Class<?> getObjectType() {
    return TransportClient.class;
  }
}
