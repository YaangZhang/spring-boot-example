package com.sto.quartz.mapper;

import com.sto.quartz.model.TaskDO;
import com.sto.quartz.model.vo.TaskVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 任务类mapper
 * @author 小卖铺的老爷爷
 * @date 2018年6月29日
 * @website www.laoyeye.net
 */
@Component
public interface TaskMapper {

    TaskDO get(Long id);
    
    List<TaskDO> list();
    
    List<TaskVO> listTaskVoByDesc(@Param("desc") String desc);
    
    int save(TaskDO task);
    
    int update(TaskDO task);
    
    int remove(Long id);
    
    int removeBatch(Long[] ids);
}
