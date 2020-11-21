package cn.tianyu20.generator;
import cn.tianyu20.good.dao.UserDaoImpl;
import cn.tianyu20.good.dao.UserDaoLevel;
import cn.tianyu20.good.dao.userDao;
import cn.tianyu20.good.server.ProxyUtils;
import cn.tianyu20.good.dao.goodDaoImpl;

public class Test2 {

    public static void main(String[] args) {
        userDao userDaoImpl = new UserDaoImpl();
        System.out.println(ProxyUtils.getInstance(userDaoImpl));
    }

}
