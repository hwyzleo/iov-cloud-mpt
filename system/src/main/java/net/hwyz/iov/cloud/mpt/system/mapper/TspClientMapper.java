package net.hwyz.iov.cloud.mpt.system.mapper;

import net.hwyz.iov.cloud.mpt.common.core.domain.entity.TspClient;

import java.util.List;

/**
 * TSP客户端表 数据层
 *
 * @author hwyz_leo
 */
public interface TspClientMapper {
    /**
     * 根据条件分页查询客户端列表
     *
     * @param tspClient 客户端信息
     * @return 客户端信息集合信息
     */
    List<TspClient> selectClientList(TspClient tspClient);

    /**
     * 通过主键ID查询客户端
     *
     * @param id 主键ID
     * @return 客户端对象信息
     */
    TspClient selectClientById(Long id);

    /**
     * 通过主键ID删除客户端
     *
     * @param id 主键ID
     * @return 结果
     */
    int deleteClientById(Long id);

    /**
     * 批量删除客户端信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteClientByIds(Long[] ids);

    /**
     * 修改客户端信息
     *
     * @param client 客户端信息
     * @return 结果
     */
    int updateClient(TspClient client);

}
