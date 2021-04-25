> 定时器事件是一个被定义好的被定时触发的事件，定时器事件可以用在开始事件、中间事件、边界事件上，边界事件可以是中断和非中断边界事件
>
- 固定时间
```xml
<timerEventDefinition xmlns="http://www.omg.org/spec/BPMN/20100524/MODEL">
  <timeDate>2011-03-11T12:12:12</timeDate>
</timerEventDefinition>
```

- 时间段
> 指明多久之后触发定时器事件
```xml
<timerEventDefinition xmlns="http://www.omg.org/spec/BPMN/20100524/MODEL">
  <timeDuration>P10DT5M</timeDuration>
</timerEventDefinition>
```

- 时间周期
> 指定一个重复的时间间隔，这对于那些需要周期性发起流程或者重复发送超时提醒的任务很有用
```xml
<timerEventDefinition xmlns="http://www.omg.org/spec/BPMN/20100524/MODEL">
  <timeCycle>R3/PT10H</timeCycle>
</timerEventDefinition>
```

- 更改时间
```java
managementService.setJobDuedate(String jobId, Date newDuedate)

managementService.setJobDuedate(String jobId, Date newDuedate, boolean cascade)
```

