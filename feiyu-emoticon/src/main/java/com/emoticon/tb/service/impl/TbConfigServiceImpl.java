package com.emoticon.tb.service.impl;

import com.emoticon.common.utils.DateUtils;
import com.emoticon.tb.domain.TbConfig;
import com.emoticon.tb.mapper.TbConfigMapper;
import com.emoticon.tb.service.ITbConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 全局配置Service业务层处理
 *
 * @author feiyu
 * @date 2025-03-22
 */
@Service
public class TbConfigServiceImpl implements ITbConfigService {
    @Autowired
    private TbConfigMapper tbConfigMapper;

    /**
     * 查询全局配置
     *
     * @param id 全局配置主键
     * @return 全局配置
     */
    @Override
    public TbConfig selectTbConfigById(Long id) {
        return tbConfigMapper.selectTbConfigById(id);
    }

    /**
     * 查询全局配置列表
     *
     * @param tbConfig 全局配置
     * @return 全局配置
     */
    @Override
    public List<TbConfig> selectTbConfigList(TbConfig tbConfig) {
        return tbConfigMapper.selectTbConfigList(tbConfig);
    }

    /**
     * 新增全局配置
     *
     * @param tbConfig 全局配置
     * @return 结果
     */
    @Override
    public int insertTbConfig(TbConfig tbConfig) {
        tbConfig.setCreateTime(DateUtils.getNowDate());
        return tbConfigMapper.insertTbConfig(tbConfig);
    }

    /**
     * 修改全局配置
     *
     * @param tbConfig 全局配置
     * @return 结果
     */
    @Override
    public int updateTbConfig(TbConfig tbConfig) {
        tbConfig.setUpdateTime(DateUtils.getNowDate());
        return tbConfigMapper.updateTbConfig(tbConfig);
    }

    /**
     * 批量删除全局配置
     *
     * @param ids 需要删除的全局配置主键
     * @return 结果
     */
    @Override
    public int deleteTbConfigByIds(Long[] ids) {
        return tbConfigMapper.deleteTbConfigByIds(ids);
    }

    /**
     * 删除全局配置信息
     *
     * @param id 全局配置主键
     * @return 结果
     */
    @Override
    public int deleteTbConfigById(Long id) {
        return tbConfigMapper.deleteTbConfigById(id);
    }
}
