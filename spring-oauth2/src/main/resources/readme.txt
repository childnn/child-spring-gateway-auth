JWT字符串由Header(头部)、Payload(负载)、Signature(签名)三部分组成。

Header: JSON对象，用来描述JWT的元数据,alg属性表示签名的算法,typ标识token的类型

Payload: JSON对象，重要部分，除了默认的字段，还可以扩展自定义字段，比如用户ID、姓名、角色等等

Signature: 对Header、Payload这两部分进行签名，认证服务器使用私钥签名，然后在资源服务器使用公钥验签，防止数据被人动了手脚