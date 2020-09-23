# pets
 for study cloud
 
 ## Nacos作为服务注册中心
    1. 因为使用了alibaba的nacos服务，所以无需再使用eurka
 ## 服务注册与消费
    1. 通过LoadBalancerClient接口在获取服务实例的时候，已经实现了对服务提供方实例的负载均衡
    2. @LoadBalanced注解，在调用服务接口的时服务名的时候来写请求路径即可。在真正调用的时候，Spring Cloud会将请求拦截下来，然后通过负载均衡器选出节点，并替换服务名部分为具体的ip和端口，从而实现基于服务名的负载均衡调用。
    3. WebClient是Spring 5中最新引入的，可以将其理解为reactive版的RestTemplate。下面举个具体的例子，它将实现与上面RestTemplate一样的请求调用：
    4. 使用@FeignClient注解来指定这个接口所要调用的服务名称，接口中定义的各个函数使用Spring MVC的注解就可以来绑定服务提供方的REST接口，比如下面就是绑定service.最后，在Controller中，注入了Client接口的实现
 ## Nacos作为配置中心
    1. 注解@RefreshScope，主要用来让这个类下的配置内容支持动态刷新，也就是当我们的应用启动之后，修改了Nacos中的配置内容之后，这里也会马上生效，例子中并没有加入nacos的服务发现模块，所以这两个内容是完全可以独立使用的
    2. Data ID中的config-client：对应客户端的配置spring.cloud.nacos.config.prefix，默认值为${spring.application.name}，即：服务名
    3. Data ID中的properties：对应客户端的配置spring.cloud.nacos.config.file-extension，默认值为properties
    4. Group的值DEFAULT_GROUP：对应客户端的配置spring.cloud.nacos.config.group，默认值为DEFAULT_GROUP
  # Nacos配置的多环境管理
    1. ${spring.application.name}-${spring.profiles.active}.properties
        使用后缀名进行管理，xxx.DEV.properties
        需要添加spring.profiles.active=TEST
        这种方式在项目与环境多的时候，配置内容就会显得非常混乱。配置列表中会看到各种不同应用，不同环境的配置交织在一起，非常不利于管理。
    2. 通过区分Group来创建两个不同环境的配置内容,它们的Data ID是完全相同的，只是GROUP不同
        xxx.properties DEV_GROUP/TEST_GROUP
        spring.cloud.nacos.config.group=DEV_GROUP
        由于会占用Group纬度，所以需要对Group的使用做好规划，毕竟与业务上的一些配置分组起冲突等问题。
    3. 不同的命名空间下，可以存在相同的Group或Data ID的配置。Namespace的常用场景之一是不同环境的配置的区分隔离
        spring.cloud.nacos.config.namespace=56b2f590-aa24-41f0-97dd-cdf5fee491a6
        官方建议的方式