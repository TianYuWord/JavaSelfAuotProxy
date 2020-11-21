package cn.tianyu20.good.dao;

public class goodDaoImpl implements goodDao {
    /**
     * 需求1，
     * 需要在query方法之前记录一下时间
     * 此时需要考虑已下两个问题
     * 001，如果代码写到query方法里面就会导致影响到这个代码的单一性
     * 002，如果代码写到这个代码的外面，那么会有很多的地方要改
     */
    public void query() {
        System.out.println("query方法");
    }

    public void myInterface001() {
        System.out.println("interface001");
    }

    public void muInterface002() {
        System.out.println("interface002");
    }

    public userDao getUserDao (UserDaoLevel level){
        return null;
    };
}
