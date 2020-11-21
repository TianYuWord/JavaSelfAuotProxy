package cn.tianyu20.generator;

import cn.tianyu20.good.dao.UserDaoImpl;
import cn.tianyu20.good.dao.UserDaoLevel;
import cn.tianyu20.good.dao.UserDaoLog;
import cn.tianyu20.good.dao.userDao;

public class Test1 {
    //使用集成的方式进行代理
    public static void main(String[] args) {
        userDao target = new UserDaoImpl();


        userDao log = new UserDaoLog(target);
        userDao level = new UserDaoLevel(log);
        level.query();

    }
}
