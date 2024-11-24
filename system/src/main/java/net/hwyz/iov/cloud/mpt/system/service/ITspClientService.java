package net.hwyz.iov.cloud.mpt.system.service;

import net.hwyz.iov.cloud.mpt.common.core.domain.entity.TspClient;

import java.util.List;

/**
 * TSP客户端 业务层
 *
 * @author hwyz_leo
 */
public interface ITspClientService {
    /**
     * 根据条件分页查询客户端列表
     *
     * @param client 客户端信息
     * @return 客户端信息集合信息
     */
    List<TspClient> selectClientList(TspClient client);

    /**
     * 通过主键ID查询客户端
     *
     * @param id 用户ID
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
     * @throws Exception 异常
     */
    int deleteClientByIds(String ids);

    /**
     * 保存客户端信息
     *
     * @param client 客户端信息
     * @return 结果
     */
    int updateClient(TspClient client);

}
