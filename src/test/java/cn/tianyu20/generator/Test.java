package cn.tianyu20.generator;

import sun.misc.ProxyGenerator;
import cn.tianyu20.good.dao.goodDao;

import java.io.FileOutputStream;
import java.io.IOException;

public class Test {


    public void test001(){

    }

    public static void main(String[] args) throws IOException {
        byte[] bytes = ProxyGenerator.generateProxyClass("myGeneratorResult", new Class[]{goodDao.class});
        FileOutputStream fos = new FileOutputStream("G:\\DiskTop\\2020Stydy\\mybatis\\pro\\myGeneratorResult.class");
        fos.write(bytes);
        fos.flush();
        fos.close();
    }
}
