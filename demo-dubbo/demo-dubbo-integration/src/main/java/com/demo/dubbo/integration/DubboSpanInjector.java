package com.demo.dubbo.integration;

import com.alibaba.dubbo.rpc.RpcContext;

import org.springframework.cloud.sleuth.Span;
import org.springframework.cloud.sleuth.SpanInjector;

import java.util.Map;

/**
 * injector：职责，从span中提取属性，注入 carrier中
 */

public class DubboSpanInjector implements SpanInjector<RpcContext> {
  @Override
  public void inject(Span span, RpcContext carrier) {
    Map<String, String> attachments = carrier.getAttachments();
    //1. 注入 traceId
    if (span.getTraceId() != 0) {
      attachments.put(Span.TRACE_ID_NAME, Span.idToHex(span.getTraceId()));
    }
    //2. 注入 spanId
    if (span.getSpanId() != 0) {
      attachments.put(Span.SPAN_ID_NAME, Span.idToHex(span.getSpanId()));
    }
    //3. 注入 sampler
    attachments.put(Span.SAMPLED_NAME, span.isExportable() ? Span.SPAN_SAMPLED : Span.SPAN_NOT_SAMPLED);
    //4. 注入 span名称
    attachments.put(Span.SPAN_NAME_NAME, span.getName());
    // 5. 提取parentId 并且注入
    Long parentId = getParentId(span);
    if (parentId != null && parentId != 0) {
      attachments.put(Span.PARENT_ID_NAME, Span.idToHex(parentId));
    }
    // 6. 注入进程Id
    attachments.put(Span.PROCESS_ID_NAME, span.getProcessId());

  }

  private Long getParentId(Span span) {
    return !span.getParents().isEmpty() ? span.getParents().get(0) : null;
  }

}
