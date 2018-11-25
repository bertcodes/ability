# 分布式事务TCC实现原理
#### 一、业务场景介绍
假设有一个电商系统，里面有一个支付订单的场景。

订单支付后，我们需要做下面的步骤：

* 更改订单的状态为“已支付” 

* 扣减商品库存

* 给会员增减积分

* 创建销售出货单通知仓库发货
![image](https://github.com/bertcodes/ability/blob/master/distributedtransaction/tcc/tcc-1td.png)


