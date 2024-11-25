package net.hwyz.iov.cloud.mpt.system.mapper;

import net.hwyz.iov.cloud.mpt.common.core.domain.entity.TspToken;

import java.util.List;

/**
 * TSP令牌表 数据层
 *
 * @author hwyz_leo
 */
public interface TspTokenMapper {
    /**
     * 根据条件分页查询令牌列表
     *
     * @param tspToken 令牌信息
     * @return 令牌信息集合信息
     */
    List<TspToken> selectTokenList(TspToken tspToken);

    /**
     * 通过主键ID查询令牌
     *
     * @param id 主键ID
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
     */
    int deleteTokenByIds(Long[] ids);

    /**
     * 修改令牌信息
     *
     * @param token 令牌信息
     * @return 结果
     */
    int updateToken(TspToken token);

}
