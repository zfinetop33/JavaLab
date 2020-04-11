CREATE TABLE `lab_order` (
  `order_id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `order_no` varchar(50) NOT NULL COMMENT '订单号',
  `user_id` int(11) NOT NULL COMMENT '用户ID',
  `goods_id` int(11) NOT NULL COMMENT '商品ID',
  `order_status` tinyint(4) DEFAULT NULL COMMENT '1-已支付 0-未支付 2-退单',
  `is_valid` tinyint(4) DEFAULT '1' COMMENT '1-有效 0-删除',
  `order_time` datetime DEFAULT NULL COMMENT '下单时间',
  `pay_time` datetime DEFAULT NULL COMMENT '支付时间',
  `cancel_time` datetime DEFAULT NULL COMMENT '取消时间',
  PRIMARY KEY (`order_id`),
  UNIQUE KEY `idux_order_orderno` (`order_no`),
  KEY `idx_order_all` (`user_id`,`order_no`,`goods_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单实验表';