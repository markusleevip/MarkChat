package com.mark.chat.client;

import com.google.protobuf.ByteString;
import com.mark.chat.pb.message.ChatPB;
import com.mark.chat.pb.message.UserPB;
import lombok.SneakyThrows;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.nio.ByteBuffer;

/**
 * @Author: Mark
 * @Date: 2023/5/16
 */
public class ChatClient1 extends Thread {


    private static final int head_request_size = 4;
    private static final int head_response_size = 8;

    WebSocketClient webSocketClient = null;

    public ChatClient1(WebSocketClient webSocketClient) {
        this.webSocketClient = webSocketClient;
    }

    public static void main(String[] args) throws Exception {
        var wsUrl = "ws://127.0.0.1:5050/websocket";

        WebSocketClient webSocketClient = new WebSocketClient(new URI(wsUrl)) {
            @Override
            public void onOpen(ServerHandshake serverHandshake) {
                System.out.println("连接成功。");
                ByteString value = UserPB.LoginReq.newBuilder().setUid(1001).build().toByteString();
                this.sendMsg(10003, value);
            }

            private void sendMsg(int routeId, ByteString value) {
                ByteBuffer content = ByteBuffer.allocate(value.size() + head_request_size);
                content.putInt(routeId);
                content.put(value.toByteArray());
                this.send(content.array());
            }

            @Override
            public void onMessage(String s) {

            }

            @SneakyThrows
            @Override
            public void onMessage(ByteBuffer byteBuffer) {
                int routeId = byteBuffer.getInt();
                int state = byteBuffer.getInt();
                System.out.println("onMessage:routeId:" + routeId + ",state:" + state);
                byte[] dataContent = byteBuffer.array();
                byte[] msgData = new byte[byteBuffer.array().length - head_response_size];
                System.arraycopy(dataContent, head_response_size, msgData, 0, msgData.length);

                if (routeId == 10003) {
                    UserPB.LoginRes res = UserPB.LoginRes.parseFrom(msgData);
                    System.out.println("登录返回:uid:" + res.getUid() + ",nickname:" + res.getNickname());
                } else if (routeId == 30001) {
                    ChatPB.SendMessageRes res = ChatPB.SendMessageRes.parseFrom(msgData);
                    System.out.println("发送消息返回:state:" + res.getState());
                }else if (routeId == 23002){
                    // 收到广播私信
                    ChatPB.ReceivePrivateMessageRes res = ChatPB.ReceivePrivateMessageRes.parseFrom(msgData);
                    System.out.println("收到广播私信:senderId:"+res.getSenderId());
                    System.out.println("收到广播私信:body:"+res.getBody());
                    System.out.println("收到广播私信:sendTime:"+res.getSendTime());
                }
            }

            @Override
            public void onClose(int i, String s, boolean b) {

            }

            @Override
            public void onError(Exception e) {

            }
        };
        webSocketClient.connect();
        Thread.sleep(3000);

        var client = new ChatClient1(webSocketClient);
        client.start();

    }


    @Override
    public void run() {
        int loop = 0;
        while (true) {
            try {
                Thread.sleep(4000L);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            loop++;
            ByteString value = ChatPB.SendPrivateMessageReq.newBuilder().setToId(1002).setMsgType(1).setBody("你好，我是Mark" + loop).build().toByteString();
            ByteBuffer content = ByteBuffer.allocate(value.size() + head_request_size);
            content.putInt(30001);
            content.put(value.toByteArray());
            this.webSocketClient.send(content.array());

        }
    }

}

