package com.emoticon.tb.mapper;

import com.emoticon.tb.domain.TbSticker;

import java.util.List;

/**
 * 表情包Mapper接口
 *
 * @author feiyu
 * @date 2025-03-22
 */
public interface TbStickerMapper
{
    /**
     * 查询表情包
     *
     * @param id 表情包主键
     * @return 表情包
     */
    public TbSticker selectTbStickerById(String id);

    /**
     * 查询表情包列表
     *
     * @param tbSticker 表情包
     * @return 表情包集合
     */
    public List<TbSticker> selectTbStickerList(TbSticker tbSticker);

    /**
     * 新增表情包
     *
     * @param tbSticker 表情包
     * @return 结果
     */
    public int insertTbSticker(TbSticker tbSticker);

    /**
     * 修改表情包
     *
     * @param tbSticker 表情包
     * @return 结果
     */
    public int updateTbSticker(TbSticker tbSticker);

    /**
     * 删除表情包
     *
     * @param id 表情包主键
     * @return 结果
     */
    public int deleteTbStickerById(String id);

    /**
     * 批量删除表情包
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTbStickerByIds(String[] ids);

    /**
     * 查询 app 表情包
     * @param tbSticker
     * @return
     */
    List<TbSticker> selectAppTbStickerList(TbSticker tbSticker);
}
