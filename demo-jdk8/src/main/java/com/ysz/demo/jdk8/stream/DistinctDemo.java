package com.ysz.demo.jdk8.stream;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <B>描述：</B><br/>
 * <B>作者：</B> carl.yu <br/>
 * <B>时间：</B> 2017/9/13 <br/>
 * <B>版本：</B><br/>
 */
public class DistinctDemo {


  public static void main(String[] args) {
    // 生成一个集合有重复元素
    List<Integer> src = Lists.newArrayList();
    src.add(null);
    System.err.println(src.stream().distinct().collect(Collectors.<Integer>toList()));
  }

}
