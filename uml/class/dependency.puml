@startuml

title dependency

class Vihicle {
    move(MoveBehavior)
}

interface MoveBehavior {
    move()
}

Vihicle ..> MoveBehavior

note "MoveBehavior.move()" as N
Vihicle .. N

note "跟关联不同的是依赖是在运行过程中起作用的，发生依赖主要三种形式\r\n1 A类是B类中的某个方法局部变量\r\n2 A类是B类方法当中的一个参数\r\n3 A类向B类发送消息从而影响B类变化" as M

@enduml