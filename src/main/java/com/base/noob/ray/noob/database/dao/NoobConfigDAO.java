package com.base.noob.ray.noob.database.dao;

import com.base.noob.ray.noob.database.dto.NoobConfig;
import com.base.noob.ray.noob.database.mapper.NoobConfigMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NoobConfigDAO {

    @Autowired
    private NoobConfigMapper noobConfigMapper;

    public void addNoobConfig(NoobConfig noobConfig) {
        noobConfigMapper.insert(noobConfig);
    }

    public void updateNoobConfig(NoobConfig noobConfig) {
        noobConfigMapper.updateByPrimaryKey(noobConfig);
    }

    public NoobConfig query(Long id) {
        return noobConfigMapper.selectByPrimaryKey(id);
    }
}
