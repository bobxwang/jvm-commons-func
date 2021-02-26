@startuml

package com.netflix.loadbalancer {
  class Server {
    - String host
    - int port
    - String zone
  }
  interface ServerList<? extends Server>{
    List<T> getInitialListOfServers()
    List<T> getUpdatedListOfServers()
  }
}
package com.netflix.niws.loadbalancer {
  class DiscoveryEnabledServer
  class DiscoveryEnabledNIWSServerList<? extends DiscoveryEnabledServer> {
    - List<DiscoveryEnabledServer> obtainServersViaDiscovery()
  }
}
note top of DiscoveryEnabledServer : netflix 抽象出来的一个远端服务实例信息
package org.springframework.cloud.netflix.ribbon.eureka {
 class DomainExtractingServer
 class DomainExtractingServerList<? extends DiscoveryEnabledServer>
}
note bottom of DomainExtractingServer : spring 针对 netflix 的服务继续抽象出来的一个远端服务实例信息，类似的有 ConsulServer
Server <|-- DiscoveryEnabledServer
DiscoveryEnabledServer <|-- DomainExtractingServer

note top of DiscoveryEnabledNIWSServerList : netflix 抽象出来的可发现远端服务列表
note bottom of DomainExtractingServerList : spring 针对 netflix 的服务继续抽象出来的可发现远端服务列表
ServerList <|-- DiscoveryEnabledNIWSServerList
ServerList <|-- DomainExtractingServerList

package com.netflix.discovery {
  interface EurekaClient
}

DiscoveryEnabledNIWSServerList *--> EurekaClient

@enduml
