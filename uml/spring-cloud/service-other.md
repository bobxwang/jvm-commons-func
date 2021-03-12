@startuml

interface DiscoveryClient {
 + List<ServiceInstance> getInstances(String serviceId)
 + List<String> getServices()
}
class EurekaDiscoveryClient
DiscoveryClient <|-- EurekaDiscoveryClient

class RestTemplate
class LoadBalancerInterceptor
note bottom of LoadBalancerInterceptor : 拦截，配合注解 LoadBalanced 使用

class EurekaClientAutoConfiguration {
  + EurekaClientConfigBean()
  + EurekaInstanceConfigBean()
}
class EurekaRibbonClientConfiguration {
  + IPing()
  + ServerList()
  + ServerIntrospector()
}
class RibbonClientConfiguration {
  + IRule()
  + ServerListUpdater()
}

@enduml
