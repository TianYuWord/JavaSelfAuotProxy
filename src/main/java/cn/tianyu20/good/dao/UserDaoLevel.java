package cn.tianyu20.good.dao;

public class UserDaoLevel implements userDao{

    private userDao dao;

    public UserDaoLevel(userDao dao) {
        this.dao = dao;
    }

    public void query() {
        System.out.println("权限");
        this.dao.query();
    }
}
