功能：
这个包完成了在运行期，server通过jmx暴露信息，client通过jmx访问信息。
其中的亮点是把Spring的框架的bean Factory通过groovy暴露出去，理论上，通过这个口子，能完成所有的spring的类的所有操作。
对于排查线上问题，查看线上运行状态非常有用。


注意：
1）使用groovy脚本，需要配置对应的org.codehaus.groovy


sesamegu
