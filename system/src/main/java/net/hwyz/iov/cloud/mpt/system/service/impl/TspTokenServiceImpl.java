package net.hwyz.iov.cloud.mpt.system.service.impl;

import net.hwyz.iov.cloud.mpt.common.annotation.DataScope;
import net.hwyz.iov.cloud.mpt.common.core.domain.entity.TspToken;
import net.hwyz.iov.cloud.mpt.common.core.text.Convert;
import net.hwyz.iov.cloud.mpt.system.mapper.TspTokenMapper;
import net.hwyz.iov.cloud.mpt.system.service.ITspTokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Validator;
import java.util.List;

/**
 * TSP令牌 业务层处理
 *
 * @author hwyz_leo
 */
@Service
public class TspTokenServiceImpl implements ITspTokenService {
    private static final Logger log = LoggerFactory.getLogger(TspTokenServiceImpl.class);

    @Autowired
    private TspTokenMapper tokenMapper;

    @Autowired
    protected Validator validator;

    /**
     * 根据条件分页查询令牌列表
     *
     * @param token 令牌信息
     * @return 令牌信息集合信息
     */
    @Override
    @DataScope(deptAlias = "d", userAlias = "u")
    public List<TspToken> selectTokenList(TspToken token) {
        return tokenMapper.selectTokenList(token);
    }

    /**
     * 通过主键ID查询令牌
     *
     * @param id 主键ID
     * @return 令牌对象信息
     */
    @Override
    public TspToken selectTokenById(Long id) {
        return tokenMapper.selectTokenById(id);
    }

    /**
     * 通过主键ID删除令牌
     *
     * @param id 主键ID
     * @return 结果
     */
    @Override
    @Transactional
    public int deleteTokenById(Long id) {
        return tokenMapper.deleteTokenById(id);
    }

    /**
     * 批量删除令牌信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    @Transactional
    public int deleteTokenByIds(String ids) {
        return tokenMapper.deleteTokenByIds(Convert.toLongArray(ids));
    }

    /**
     * 修改保存令牌信息
     *
     * @param token 令牌信息
     * @return 结果
     */
    @Override
    @Transactional
    public int updateToken(TspToken token) {
        return tokenMapper.updateToken(token);
    }

}
