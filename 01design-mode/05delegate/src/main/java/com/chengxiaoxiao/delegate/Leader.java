package com.chengxiaoxiao.delegate;

import java.util.HashMap;
import java.util.Map;

/**
 * 领导不负责干活，负责分配活
 *
 * @Description:
 * @Author: Cheng XiaoXiao  (🍊 ^_^ ^_^)
 * @Date: 2021-01-24 15:36
 */
public class Leader implements IEmployee {
    private Map<String, IEmployee> targets = new HashMap<String, IEmployee>();

    public Leader() {
        targets.put("加密", new EmployeeA());
    }

    public void doing(String command) {
        targets.get(command).doing(command);
    }
}
