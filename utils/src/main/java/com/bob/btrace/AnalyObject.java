package com.bob.btrace;

import static com.sun.btrace.BTraceUtils.Strings.str;
import static com.sun.btrace.BTraceUtils.Strings.strcat;
import static com.sun.btrace.BTraceUtils.Threads.jstack;
import static com.sun.btrace.BTraceUtils.println;

import com.sun.btrace.annotations.BTrace;
import com.sun.btrace.annotations.Duration;
import com.sun.btrace.annotations.Kind;
import com.sun.btrace.annotations.Location;
import com.sun.btrace.annotations.OnMethod;
import com.sun.btrace.annotations.Return;

/**
 * 〈〉
 *
 * @author wangxiang
 * @create 2019-04-24
 */
@BTrace(trusted = true)
public class AnalyObject {

  @OnMethod(
      clazz = "kite.lab.utils.NumberUtil",
      method = "sum",
      location = @Location(Kind.RETURN)
  )
  public static void func(@Return int result, @Duration long duration) {
    println("trace: =======================");
    println(strcat("result:", str(result)));
    jstack();
  }


  /**
   * 监控代码是否到达了 demo 类的第20行
   */
  @OnMethod(clazz = "com.kite.demo", location = @Location(value = Kind.LINE, line = 20))
  public static void onBind() {
    println("demo bind reach line 20");
  }
}