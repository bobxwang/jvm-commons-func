@startuml

package org.springframework.cloud.client.loadbalancer {
interface ServiceInstanceChooser {
 + ServiceInstance choose(String serviceId)
}
interface LoadBalancerClient {
 + <T> T execute(String serviceId, LoadBalancerRequest<T> request);
}
}
ServiceInstanceChooser <|-- LoadBalancerClient
package org.springframework.cloud.netflix.ribbon {
 class RibbonLoadBalancerClient {
  + RibbonLoadBalancerClient(SpringClientFactory s)
  - ILoadBalancer getLoadBalancer(String serviceId)
  - Server getServer(String serviceId)
 }
}
LoadBalancerClient <|-- RibbonLoadBalancerClient
class SpringClientFactory
note left of SpringClientFactory : 一种SPI机制，运行时才会去动态解析
RibbonLoadBalancerClient *--> SpringClientFactory

package com.netflix.loadbalancer {
 interface ILoadBalancer {
  + Server chooseServer(Object o)
 }
 class DynamicServerListLoadBalancer<? extends Server>
 class ZoneAwareLoadBalancer<? extends Server>
 interface IRule {
   + Server choose(Object var1)
   + void setLoadBalancer(ILoadBalancer var1)
   + ILoadBalancer getLoadBalancer()
 }
 class ZoneAvoidanceRule
 interface IPing
 interface ServerListUpdater
 class PollingServerListUpdater
 interface ServerListFilter
}
package org.springframework.cloud.netflix.ribbon {
  class ZonePreferenceServerListFilter
}
note bottom of ZonePreferenceServerListFilter : spring 实现的一种区域感知服务过滤
ServerListUpdater <|-- PollingServerListUpdater
ServerListFilter <|-- ZonePreferenceServerListFilter
IRule <|--ZoneAvoidanceRule
note bottom of IRule : 通过 ILoadBalancer 获得所有服务，然后根据一定规则进行选择
IPing <|-- NIWSDiscoveryPing
DynamicServerListLoadBalancer *--> IRule
DynamicServerListLoadBalancer *--> IPing
DynamicServerListLoadBalancer *--> ServerListUpdater
DynamicServerListLoadBalancer *--> ServerListFilter
ILoadBalancer <|-- DynamicServerListLoadBalancer
DynamicServerListLoadBalancer <|-- ZoneAwareLoadBalancer

@enduml
