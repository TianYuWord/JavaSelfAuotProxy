package cn.tianyu20.good.server;

import javax.tools.JavaCompiler;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.FileWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

public class ProxyUtils {

    //Java
    private Class target;

    /**
     *
     * String Content
     * Java IO
     * class文件
     * Load JVM
     * @return
     */
    public static Object getInstance(Object targetObj){
        Object proxy = null;
        Class target = targetObj.getClass().getInterfaces()[0];
        // String Content
        String tab = "\t";
        String end = "\n";

        Method[] methods = target.getDeclaredMethods();

        String packageContent = "package cn.tianyu20.good.server;"+end;
        String targetImportContent = "import "+target.getName() + ";"+end;
        String classNameImpl = "public class TYProxy implements " + target.getSimpleName() + "{" +end;

        String targetObjectFieldContent = tab+tab+"private "+target.getSimpleName() + " target;"+end;

        String constructionContent = tab + tab + "public TYProxy("+target.getSimpleName() +" target){"+end;
        constructionContent+=tab+tab+tab+"this.target=target;"+end;
        constructionContent+=tab+tab+"}"+end;

        String MethodsContent = "";
        for(Integer i = 0; i<methods.length; i++){
            String mdLine = tab+tab+"public ";
            String mdName = methods[i].getName();
            String returnType = methods[i].getReturnType().getSimpleName();
            String argsContent = "";
            Object[] args = methods[i].getParameterTypes();
            String paramsContent = "";
            Integer index = 0;
            for (Object arg : args) {
                argsContent += arg.getClass().getSimpleName() + " arg"+index+",";
                paramsContent += "arg"+index+",";
                index++;
                targetImportContent += "import " + arg.getClass().getName()+ ";"+end;
            }
            if(argsContent.length()>0){
                argsContent = argsContent.substring(0,argsContent.length()-1);
                paramsContent = paramsContent.substring(0,paramsContent.length()-1);
            }
            mdLine += returnType +" "+ mdName + "("+argsContent+"){"+end;
            mdLine += tab+tab+tab+"this.target."+mdName + "("+paramsContent+");"+end;
            mdLine += tab+tab+"}"+end;
            MethodsContent += mdLine;
            try {
                //Java IO
                File file = new File("G:\\DiskTop\\2020Stydy\\mybatis\\pro\\src\\main\\java\\cn\\tianyu20\\good\\server\\TYProxy.java");
                FileWriter fw = new FileWriter(new File("G:\\DiskTop\\2020Stydy\\mybatis\\pro\\src\\main\\java\\cn\\tianyu20\\good\\server\\TYProxy.java"));
                fw.write(packageContent + targetImportContent + classNameImpl + targetObjectFieldContent + constructionContent + MethodsContent + "}");
                fw.flush();
                //.java 文件
                fw.close();

                //.class 文件
                JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
                StandardJavaFileManager fileManage = compiler.getStandardFileManager(null,null,null);
                Iterable units = fileManage.getJavaFileObjects(file);
                JavaCompiler.CompilationTask t = compiler.getTask(null,fileManage,null,null,null,units);
                t.call();
                fileManage.close();
                URL[] urls = new URL[]{new URL("file:G:\\DiskTop\\2020Stydy\\mybatis\\pro\\src\\main\\java\\")};
                URLClassLoader urlClassLoader = new URLClassLoader(urls);
                Class clazz = urlClassLoader.loadClass("cn.tianyu20.good.server.TYProxy");
                Constructor constructor = clazz.getConstructor(target);
                proxy = constructor.newInstance(targetObj);
                //Class Loader JVM
                //Class.forName()
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        return proxy;
    }

}
