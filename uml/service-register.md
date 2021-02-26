@startuml

package org.springframework.cloud.client {
  interface ServiceInstance {
    + String getServiceId()
    + String getHost();
    + int getPort();
    + boolean isSecure();
    + Map<String, String> getMetadata();
  }
}

package org.springframework.cloud.client.serviceregistry {
  interface Registration
  interface ServiceRegistry<? extends Registration> {
    + void register(R registration);
    + void deregister(R registration);
    + void close();
    + void setStatus(R registration, String status);
    + <T> T getStatus(R registration);
  }
  interface AutoServiceRegistration
}

package org.springframework.cloud.netflix.eureka.serviceregistry {
  class EurekaRegistration
  class EurekaServiceRegistry<? extends EurekaRegistration>
  class EurekaAutoServiceRegistration
  note left of EurekaAutoServiceRegistration : 委托 ServiceRegistry 进行服务注册与反注册，同时发布一个 InstanceRegisteredEvent 事件出来
}
interface SmartLifecycle {
  + void start()
  + void stop()
}
SmartLifecycle <|-- EurekaAutoServiceRegistration
AutoServiceRegistration <|-- EurekaAutoServiceRegistration
ServiceInstance <|-- Registration
Registration <|-- EurekaRegistration
ServiceRegistry <|-- EurekaServiceRegistry
EurekaAutoServiceRegistration *--> EurekaServiceRegistry
EurekaAutoServiceRegistration *--> EurekaRegistration
EurekaRegistration *--> EurekaClient
package com.netflix.discovery {
  interface EurekaClient
  class DiscoveryClient
}
EurekaClient <|-- DiscoveryClient
package org.springframework.cloud.netflix.eureka {
  class CloudEurekaClient
  class EurekaInstanceConfigBean
  note bottom of EurekaInstanceConfigBean : 读取配置文件中 eureka.instance 的信息，组装一个实例
}
package com.netflix.appinfo {
  class ApplicationInfoManager
}
package com.netflix.discovery.shared.transport {
  interface EurekaHttpClient
  note bottom of EurekaHttpClient: eureka 的注册反注册心跳接口
}

DiscoveryClient <|-- CloudEurekaClient
CloudEurekaClient *--> ApplicationInfoManager
CloudEurekaClient ..|> EurekaHttpClient

@enduml
