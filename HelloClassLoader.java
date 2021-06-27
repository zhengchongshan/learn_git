import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Base64;

public class HelloClassLoader extends ClassLoader{
    public static void main(String[] args){
        try{
          Class clazz =  new HelloClassLoader().findClass("Hello").newInstance().getClass();
          Method method = clazz.getMethod("hello");
          Constructor constructor=clazz.getConstructor();
          Object obj=constructor.newInstance();
          method.invoke(obj, null);
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }catch (IllegalAccessException e){
            e.printStackTrace();
        }catch (InstantiationException e){
            e.printStackTrace();
        }catch (NoSuchMethodException e){
            e.printStackTrace();
        }catch (InvocationTargetException e){
            e.printStackTrace();
        }
    }
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException{
        try {

                byte[] inputBytes = Stream2Byte("C:\\Users\\mountainZheng\\IdeaProjects\\demo\\src\\Hello\\Hello.xlass");
                byte[] inputByteResults = new byte[inputBytes.length];
                for(int i=0;i<inputBytes.length;i++){
                    inputByteResults[i] = (byte)(255 - inputBytes[i]);
                }
            return defineClass(name,inputByteResults,0,inputByteResults.length);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    public byte[] Stream2Byte(String infile) {
        BufferedInputStream in = null;
        ByteArrayOutputStream out = null;
        try {
            in = new BufferedInputStream(new FileInputStream(infile));
            out = new ByteArrayOutputStream(1024);

            byte[] temp = new byte[1024];
            int size = 0;
            while ((size = in.read(temp)) != -1) {
                out.write(temp, 0, size);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        byte[] content = out.toByteArray();
        return content;

    }
}
