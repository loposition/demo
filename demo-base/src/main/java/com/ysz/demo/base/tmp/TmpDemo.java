package com.ysz.demo.base.tmp;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;

import java.util.HashSet;
import java.util.Set;

/**
 * <B>描述：</B><br/>
 * <B>作者：</B> carl.yu <br/>
 * <B>时间：</B> 2017/9/25 <br/>
 * <B>版本：</B><br/>
 */
public class TmpDemo {

  private static final ImmutableSet<Integer> DEFAULT_QUERY_BIZ_TYPE = ImmutableSet.of(1, 2, 3, 4,
      5, 6);

  public static void main(String[] args) {
    Set<Integer> s1 = DEFAULT_QUERY_BIZ_TYPE;
    s1.remove(1);
    HashSet<Integer> NOW_BIZ_TYPE = Sets.newHashSet(DEFAULT_QUERY_BIZ_TYPE);
    // 写的时候复制
    NOW_BIZ_TYPE.removeAll(Sets.newHashSet(1, 3, 4));
    System.err.println(DEFAULT_QUERY_BIZ_TYPE);
    System.err.println(NOW_BIZ_TYPE);
  }
}
