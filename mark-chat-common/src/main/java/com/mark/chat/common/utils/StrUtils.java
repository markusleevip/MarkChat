package com.mark.chat.common.utils;

import lombok.experimental.UtilityClass;

import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author Mark
 * @date 2023/4/28
 */
@UtilityClass
public class StrUtils {


    /**
     * 生成聊天消息ID
     * @return
     */
    public String generateChatMsgId() {
        int randomNumber = ThreadLocalRandom.current().nextInt(1000, 9999);
        String uuid = UUID.randomUUID().toString().replace("-", "");        ;
        return uuid + randomNumber;
    }
}
