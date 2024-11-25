package net.hwyz.iov.cloud.mpt.system.service;

import net.hwyz.iov.cloud.mpt.common.core.domain.entity.TspToken;

import java.util.List;

/**
 * TSP令牌 业务层
 *
 * @author hwyz_leo
 */
public interface ITspTokenService {
    /**
     * 根据条件分页查询令牌列表
     *
     * @param token 令牌信息
     * @return 令牌信息集合信息
     */
    List<TspToken> selectTokenList(TspToken token);

    /**
     * 通过主键ID查询令牌
     *
     * @param id 用户ID
     * @return 令牌对象信息
     */
    TspToken selectTokenById(Long id);

    /**
     * 通过主键ID删除令牌
     *
     * @param id 主键ID
     * @return 结果
     */
    int deleteTokenById(Long id);

    /**
     * 批量删除令牌信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     * @throws Exception 异常
     */
    int deleteTokenByIds(String ids);

    /**
     * 保存令牌信息
     *
     * @param token 令牌信息
     * @return 结果
     */
    int updateToken(TspToken token);

}
