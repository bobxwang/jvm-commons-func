@startuml

title Aggregation

class Computer
class Keyboard
class Mouse
class Screen

note top of Computer : 表示整体由部分组成，但整体跟部分不是强依赖，整体不存在了部分还是会存在。

Computer o-- Keyboard
Computer o-- Mouse
Computer o-- Screen

@enduml