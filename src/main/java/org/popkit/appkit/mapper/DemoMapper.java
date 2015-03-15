package org.popkit.appkit.mapper;

import org.popkit.appkit.entity.BasicDo;

import java.util.List;

/**
 * Like ibatis's DAO
 * @author guobao.jiang
 * @date 3/15/15
 * @time 4:00 PM
 */
public interface DemoMapper {
    public void insert(BasicDo basicDo);
    List<BasicDo> listAllUsersInfo();
}
