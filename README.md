# 电商项目

## 1. 数据库结构分析

使用MySQL数据库, 数据库版本为5.6

### 1.1 内容分类表

tb_content_category  

| 列名       | 类型        | 含义                                                         |
| ---------- | ----------- | ------------------------------------------------------------ |
| id         | bigint(20)  | 内容分类 ID                                                  |
| parent_id  | bigint(20)  | 父类目 ID=0 时，代表的是一级的类目                           |
| name       | varchar(50) | 分类名称                                                     |
| status     | int(1)      | 状态。可选值:1(正常),2(删除)                                 |
| sort_order | int(4)      | 排列序号，表示同级类目的展现次序，如数值相等 则按名称次序排列。取值范围:大于零的整数 |
| is_parent  | tinyint(1)  | 该类目是否为父类目， 1 为 true， 0 为 false    |
| created    | datetime    | 创建时间                                    |
| updated    | datetime    | 更新时间                                    |                 |

### 1.2 内容表

tb_content  

| 列名        | 类型         | 含义         |
| ----------- | ------------ | ------------ |
| id          | bigint(20)   | 主键         |
| category_id | bigint(20)   | 内容分类 ID  |
| title       | varchar(200) | 内容标题     |
| sub_title   | varchar(100) | 子标题       |
| title_desc  | varchar(500) | 标题描述     |
| url         | varchar(500) | 链接         |
| pic         | varchar(300) | 图片绝对路径 |
| pic2        | varchar(300) | 图片 2       |
| content     | text         | 内容         |
| created     | datetime     | 创建时间     |
| updated     | datetime     | 更新时间     |

### 1.3 商品表

 tb_item  

| 列名 | 类型       | 含义    |
| ---- | ---------- | ------- |
| id   | bigint(20) | 商品 id |
| title      | varchar(100) | 商品标题                                         |
| sell_point | varchar(500) | 商品卖点                                         |
| price      | bigint(20)   | 商品价格，单位为：分                             |
| num        | int(10)      | 库存数量                                         |
| barcode    | varchar(30)  | 商品条形码                                       |
| image      | varchar(500) | 商品图片，以逗号分隔的多个图片的 url 地址字符 串 |
| cid        | bigint(10)   | 所属类目，叶子类目                               |
| status     | tinyint(4)   | 商品状态， 1-正常， 2-下架， 3-删除              |
| created    | datetime     | 创建时间          |
| updated    | datetime     | 更新时间                      |

### 1.4  商品分类表  

tb_item_cat 

| 列名       | 类型        | 含义                                                         |
| ---------- | ----------- | ------------------------------------------------------------ |
| id         | bigint(20)  | 商品分类 ID                                                  |
| parent_id  | bigint(20)  | 父类目 ID=0 时，代表的是一级的类目                           |
| name       | varchar(50) | 类目名称                                                     |
| status     | int(1)      | 状态。可选值:1(正常),2(删除)                                 |
| sort_order | int(4)      | 排列序号，表示同级类目的展现次序，如数值相等 则按名称次序排列。取值范围:大于零的整数 |
| is_parent  | tinyint(1)  | 该类目是否为父类目， 1 为 true， 0 为 false                  |
| created    | datetime    | 创建时间                                                     |
| updated    | datetime    | 更新时间                                                     |

### 1.5  商品描述表  

tb_item_desc  

| 列名      | 类型       | 含义     |
| --------- | ---------- | -------- |
| item_id   | bigint(20) | 商品 ID  |
| item_desc | text       | 商品描述 |
| created   | datetime   | 创建时间 |
| updated   | datetime   | 更新时间 |

### 1.6  商品规格参数模板表  

tb_item_param  

| 列名 | 类型       | 含义 |
| ---- | ---------- | ---- |
| id   | bigint(20) | 主键 |
| item_cat_id | bigint(20) | 商品分类 ID      |
| param_data  | text       | 规格参数模板信息 |
| created     | datetime   | 创建时间         |
| updated     | datetime   | 更新时间         |

### 1.7 商品规格参数信息表  

tb_item_param_item  

| 列名       | 类型       | 含义         |
| ---------- | ---------- | ------------ |
| id         | bigint(20) | 主键         |
| item_id    | bigint(20) | 商品 ID      |
| param_data | text       | 规格参数信息 |
| created    | datetime   | 创建时间     |
| updated    | datetime   | 更新时间     |

### 1.8 订单表

| 列名          | 类型         | 含义                                                         |
| ------------- | ------------ | ------------------------------------------------------------ |
| order_id      | varchar(50)  | 订单 id                                                      |
| payment       | varchar(50)  | 实付金额。精确到 2 位小数;单位:元。如:200.07， 表示:200 元 7 分 |
| payment_type  | int(2)       | 支付类型， 1、在线支付， 2、货到付款                         |
| post_fee      | varchar(50)  | 邮费。精确到 2 位小数;单位:元。如:200.07，表示:200 元 7 分   |
| status        | int(10)      | 状态： 1、未付款， 2、已付款， 3、未发货， 4、已 发货， 5、交易成功， 6、交易关闭 |
| create_time   | datetime     | 订单创建时间                                                 |
| update_time   | datetime     | 订单更新时间                                                 |
| payment_time  | datetime     | 付款时间                                                     |
| consign_time  | datetime     | 发货时间                                                     |
| end_time      | datetime     | 交易完成时间                                                 |
| close_time    | datetime     | 交易关闭时间                                                 |
| shipping_name | varchar(20)  | 物流名称                                                     |
| shipping_code | varchar(20)  | 物流单号                                                     |
| user_id       | bigint(20)   | 用户 id                                                      |
| buyer_message | varchar(100) | 买家留言                                                     |
| buyer_nick    | varchar(50)  | 买家昵称                                                     |
| buyer_rate    | int(2)       | 买家是否已经评价                                             |

### 1.9 订单明细表

tb_order_item  

| 列名 | 类型        | 含义 |
| ---- | ----------- | ---- |
| id   | varchar(20) | 主键 |
| item_id   | varchar(50)  | 商品 id      |
| order_id  | varchar(50)  | 订单 id      |
| num       | int(10)      | 商品购买数量 |
| title     | varchar(200) | 商品标题     |
| price     | bigint(50)   | 商品单价     |
| total_fee | bigint(50)   | 商品总金额   |
| pic_path  | varchar(200) | 商品图片地址 |

### 1.10 物流表

tb_order_shipping  

| 列名              | 类型         | 含义                       |
| ----------------- | ------------ | -------------------------- |
| order_id          | varchar(50)  | 订单 ID                    |
| receiver_name     | varchar(20)  | 收货人全名                 |
| receiver_phone    | varchar(20)  | 固定电话                   |
| receiver_mobile   | varchar(30)  | 移动电话                   |
| receiver_state    | varchar(10)  | 省份                       |
| receiver_city     | varchar(10)  | 城市                       |
| receiver_district | varchar(20)  | 区/县                      |
| receiver_address  | varchar(200) | 收货地址，如： xx 路 xx 号 |
| receiver_zip      | varchar(6)   | 邮政编码,如： 310001       |
| created           | datetime     | 创建时间                   |
| updated           | datetime     | 更新时间                   |

### 1.11 用户表

tb_user  

| 列名     | 类型        | 含义           |
| -------- | ----------- | -------------- |
| id       | bigint(20)  | 用户表主键     |
| username | varchar(50) | 用户名         |
| password | varchar(32) | 密码，加密存储 |
| phone    | varchar(20) | 注册手机号     |
| email    | varchar(50) | 注册邮箱       |
| created  | datetime    | 创建时间       |
| updated  | datetime    | 更新时间       |