package per.eicho.demo.jdk.java.io;

/**
 * <p>Serializable接口的使用例</p>
 * <pre>
 *  {@link java.io.Serializable}接口是Java的序列化接口。其实一个标记接口（Marker Interface）
 *  接口定义内部没有任何方法定义。
 * 
 *  在《Effective Java Third Edition》里第十二章《12 Serialization》专门讲解了序列化相关的坑。
 *  其中就提到Java序列化有严重的安全漏洞且有性能问题，并写到
 *    “... There is no reason to use Java serialization in any new system you write ...”
 *    “... 你没有任何理由在一个新构筑的系统里使用Java序列化 ...”
 *  很明确提到了不要在使用Java序列化。
 * </pre>
 * 
 * <pre>
 *  序列化是把一个实例的数据转化为byte数组数据的一种处理，是一种编码（Encode）处理。
 *  反序列化是从byte数组数据中恢复一个实例的一种处理，是一种解码（Decode）处理。
 *  
 *  硬件间传输数据、进程之间传输数据（无论是本地进程之间的Local IO、或是远程进程之间的Network IO）
 *  都是基于byte（字节）。byte数组本身是没有任何意义的，赋予byte数组意义的是“协议（Protocol）”。
 *  协议定义如何从byte数组获取数据、及这些数据的含义。
 *    
 *     byte数组：  [-------------------------------------------------] 本身没有任何含义
 *     tcp协议 ：  [--TCP Header--][----Data Used by Next Layer -----] 赋予头部数据含义
 *     http协议：                  [--HTTP Header--][-- HTTP Body ---] 赋予数据含义
 *     http body是文本的： 通常就通过文字解码器拿到对应的String对象
 *     文本可以时JSON格式的：可以用JSON解析器解析String对象到JSON对象自己玩
 *                         也可以用某些JSON解析框架直接解析并映射（Mapping）
 *                         String对象到一个业务类实例
 *     文本也可以是XML格式的：与上面JSON类似处理...
 * </pre>
 * 
 * <pre>
 *  Java序列化的替代品：
 *    通过上面协议的例子序列化在RPC（Remote Procedure Call）、数据传输、数据持久化
 *    等方面有重要的意义，那么如果不能使用Java序列化，我们用什么好呢？
 *    我们需要知道这个赛道的一些常见产品：
 *     - Java Serialization：性能差、不跨语言、安全性差
 *     - JSON/XML文本式序列化：性能差、跨语言、安全性好、可读性好
 *     - Kryo库（强烈推荐）：性能高、不跨语言、
 *        - 小蓝鸟、雅虎等就用到这个库。
 *        - 地址：https://github.com/EsotericSoftware/kryo
 *     - Protobuf库：性能高、跨语言、使用繁琐
 *        - 由谷歌开发
 *        - 地址：https://github.com/protocolbuffers/protobuf。
 *     - ProtoStuff库：性能高、跨语言、基于Protobuf提供了更多便利的用法。
 *        - 地址：https://github.com/protostuff/protostuff
 *     - hessian2库：性能中、跨语言、基于hessian2的hessian lite（By 阿里）是dubbo rpc的默认序列化方式。
 *        - dubbo官网一篇说明也提到将逐步替换hessian到Kryo库
 *          “有鉴于此，我们为dubbo引入Kryo和FST这两种高效Java序列化实现，来逐步取代hessian2。”
 *        - Ref：https://dubbo.apache.org/zh/docs/v2.7/user/serialization/
 * </pre>
 */
final class SerializableSample {

    /* 推荐使用Kryo库作为Java2Java的序列化工具，跨语言仍需后续调研... */

    private SerializableSample() {}
}
