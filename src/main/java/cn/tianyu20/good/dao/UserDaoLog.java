package cn.tianyu20.good.dao;

public class UserDaoLog implements userDao {
    private userDao target;

    public UserDaoLog(userDao userdao) {
        this.target = userdao;
    }

    public void query() {
        System.out.println("log---------执行了内部的log信息");
        this.target.query();
    }
}
