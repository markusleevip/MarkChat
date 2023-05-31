package com.mark;

import com.mark.chat.server.MarkChatStart;

/**
 * @author Mark
 * @date 2023/5/12
 */
public class Main {
    public static void main(String[] args) {
        int gatewayPort = 3030;
        int externalPort = 5050;
        new MarkChatStart().run(externalPort,gatewayPort,false);
    }
}
