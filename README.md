# 设计模式之模板方式 - 结合java-backend group project使用

## 背景
```
  springcloud服务间调用通信时可能会定义一个通用的model来通信, 此时一般都存在重复代码的编写,
  eg: 
    1. 先创建服务间通信Model: FeignMessage
    2. 调用相应逻辑组件想要的数据
    3. 将数据set进FeignMessage
    4. 若有业务异常也将异常信息set进FeignMessage

  该项目想做的事就是利用模板方法将重复代码抽出来。    
```

### 目录
1. no-tempate-method项目是没使用模板方法版本, 可以看到封装FeignMessage和解析
   FeignMessage都有大量的重复代码.
2. use-template-method项目是使用了模板方法, 具体大家可以仔细看.

#### 期望结果
```
  服务间调用只需要执行一行代码即可
```

##### 规则
```
 Feign client之间通信规则:
 code != null => feign client调用发生异常, 需要将异常code(Constants.DEFAULT_ERROR_CODE)
                 和message填充至对应的字段
 code == null => 无异常信息, 使用putAttribute方式和getAttribute方式传值
```