package com.mercy.entity;

import com.baomidou.mybatisplus.annotation.Version;
import lombok.Data;

/**
 * @Description:
 * @Author: Huwl
 * @Date: 2020-12-31 11:05
 **/


@Data
public class PatrolTask  extends  BaseEntity{
    private static final long serialVersionUID = 1L;

    /**
     * 任务单号
     **/
    private java.lang.String taskNo;

    /**
     * 公司id
     **/
    private java.lang.Long companyId;

    /**
     * 巡检路线id
     **/
    private java.lang.Long patrolPathId;

    /**
     * 计划开始日期
     **/
    private java.util.Date planStartTime;

    /**
     * 计划结束日期
     **/
    private java.util.Date planEndTime;

    /**
     * 状态：【0-编辑、1-未下发、2-待巡检、3-超期未巡、4-巡检中、5-已取消】
     **/
    private java.lang.Integer state;

    /**
     * 巡检计划id
     **/
    private java.lang.Long planId;

    /**
     * 来源 1:临时 0：巡检计划
     **/
    private java.lang.Integer source;

    /**
     * 实际开始日期
     **/
    private java.util.Date actualStartTime;

    /**
     * 实际结束日期
     **/
    private java.util.Date actualEndTime;

    /**
     * 负责人
     **/
    private java.lang.Long chargeUserId;

    /**
     * 取消原因
     **/
    private java.lang.String cancelReason;

    /**
     * 备注
     **/
    private java.lang.String remark;

    /**
     * 终止原因
     **/
    private java.lang.String abortReason;

    /**
     * 班组id
     **/
    private java.lang.Long teamGroupId;

    /**
     * 乐观锁版本号
     **/
    @Version
    private java.lang.Integer version;

    /**
     * 班组名称
     **/
    private java.lang.String teamGroupName;

    /**
     * 计划名称
     **/
    private java.lang.String planName;

    /**
     * 消息code
     **/
    private java.lang.String mqCode;
}
