@startuml

title Composition

class Company
class DepartmentA
class DepartmentB

note top of Company : 表示整体由部分组成，但整体跟部分是强依赖，整体不存在了部分也就不存在了。

Company *-- DepartmentA
Company *-- DepartmentB

@enduml