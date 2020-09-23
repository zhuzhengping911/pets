# pets
 for study cloud
 
    1. 因为使用了alibaba的nacos服务，所以无需再使用eurka
    2. 通过LoadBalancerClient接口在获取服务实例的时候，已经实现了对服务提供方实例的负载均衡
    3. @LoadBalanced注解，在调用服务接口的时服务名的时候来写请求路径即可。在真正调用的时候，Spring Cloud会将请求拦截下来，然后通过负载均衡器选出节点，并替换服务名部分为具体的ip和端口，从而实现基于服务名的负载均衡调用。
    4. WebClient是Spring 5中最新引入的，可以将其理解为reactive版的RestTemplate。下面举个具体的例子，它将实现与上面RestTemplate一样的请求调用：