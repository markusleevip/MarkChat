# MarkChat
MarkChat 聊天软件，使用ioGame框架

## 说明文档  User manual
https://www.yuque.com/markus/ufnllx/lyr9gv8ilerpnssa
## 视频 Video
https://www.bilibili.com/video/BV1ks4y1z7Uo/

## 通讯协议
### 客户端请求参数

| 名称 |	类型 | 说明 |
| --- | ----| ----- |
| routeId |	int32 |	路由ID |
| data | byte[] |	消息体 |

### 服务端响应参数(包括广播消息)
| 名称 |	类型 | 说明 |
| --- | ---- | ------ |
|routeId|	int32|	路由ID | 
|state|	int32|	状态 0 正常, 其他为错误码|
|data|	byte[]|	消息体|

### 路由ID 
| routeId |	说明 |
| --- | ---- |
|10003|	用户登录|
|30001|	发送私信|
|30005|	接收私信|


### 启动服务器
运行 mark-chat-start模块下的MarkChatApplication文件

### 启动客户端
运行 mark-chat-client模块下的ChatClient1和ChatClient2模拟两个用户发送私信。 
