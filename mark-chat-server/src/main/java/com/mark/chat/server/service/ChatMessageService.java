package com.mark.chat.server.service;

import com.mark.chat.common.doman.dto.PrivateMessageDto;

/**
 * @Author: Mark
 * @Date: 2023/5/23
 */
public interface ChatMessageService {

    /**
     * 将消息放到缓存
     *
     * @param dto
     */
    void saveMessage(PrivateMessageDto dto);

    /**
     * 获取消息
     * @param uid
     */
    void getMessage(Long uid);
}
