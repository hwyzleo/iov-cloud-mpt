package net.hwyz.iov.cloud.mpt.system.service.impl;

import net.hwyz.iov.cloud.mpt.common.annotation.DataScope;
import net.hwyz.iov.cloud.mpt.common.core.domain.entity.TspClient;
import net.hwyz.iov.cloud.mpt.common.core.text.Convert;
import net.hwyz.iov.cloud.mpt.system.mapper.TspClientMapper;
import net.hwyz.iov.cloud.mpt.system.service.ITspClientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Validator;
import java.util.List;

/**
 * TSP客户端 业务层处理
 *
 * @author hwyz_leo
 */
@Service
public class TspClientServiceImpl implements ITspClientService {
    private static final Logger log = LoggerFactory.getLogger(TspClientServiceImpl.class);

    @Autowired
    private TspClientMapper clientMapper;

    @Autowired
    protected Validator validator;

    /**
     * 根据条件分页查询客户端列表
     *
     * @param client 客户端信息
     * @return 客户端信息集合信息
     */
    @Override
    @DataScope(deptAlias = "d", userAlias = "u")
    public List<TspClient> selectClientList(TspClient client) {
        return clientMapper.selectClientList(client);
    }

    /**
     * 通过主键ID查询客户端
     *
     * @param id 主键ID
     * @return 客户端对象信息
     */
    @Override
    public TspClient selectClientById(Long id) {
        return clientMapper.selectClientById(id);
    }

    /**
     * 通过主键ID删除客户端
     *
     * @param id 主键ID
     * @return 结果
     */
    @Override
    @Transactional
    public int deleteClientById(Long id) {
        return clientMapper.deleteClientById(id);
    }

    /**
     * 批量删除客户端信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    @Transactional
    public int deleteClientByIds(String ids) {
        return clientMapper.deleteClientByIds(Convert.toLongArray(ids));
    }

    /**
     * 修改保存客户端信息
     *
     * @param client 客户端信息
     * @return 结果
     */
    @Override
    @Transactional
    public int updateClient(TspClient client) {
        return clientMapper.updateClient(client);
    }

}
