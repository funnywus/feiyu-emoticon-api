package com.emoticon.tb.service.impl;

import com.emoticon.tb.domain.TbSticker;
import com.emoticon.tb.mapper.TbStickerMapper;
import org.redisson.api.RAtomicLong;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class AsyncStickerServiceImpl {

    @Autowired
    private TbStickerMapper tbStickerMapper;

    @Autowired
    private RedissonClient redissonClient;

    private static final String STICKER_VIEW_KEY = "sticker:view:";
    private static final int BATCH_UPDATE_THRESHOLD = 1;

    /**
     * 异步增加浏览量
     */
    @Async
    public void incrementViewCount(String id) {
        String viewKey = STICKER_VIEW_KEY + id;
        RAtomicLong atomicLong = redissonClient.getAtomicLong(viewKey);
        long currentViews = atomicLong.incrementAndGet();

        // 当累积到一定量时，更新数据库
        if (currentViews % BATCH_UPDATE_THRESHOLD == 0) {
            TbSticker sticker = new TbSticker();
            sticker.setId(id);
            sticker.setViews(currentViews);
            tbStickerMapper.updateTbSticker(sticker);
        }
    }
}
