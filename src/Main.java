import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;


public class Main {
    public static void main(String[] args) {
        long start = 0;
        long end = 0;



        CreateString createString = new CreateString(27);

        File file = new File("D:\\0\\byte.txt");
        File file2 = new File("D:\\0\\char.txt");
        boolean isMakeDir = true;
        boolean isMakeDir2 = true;
        if (file.exists()) file.delete();
        if (file2.exists()) file2.delete();
        try{
            isMakeDir = file.createNewFile();
            isMakeDir2 = file2.createNewFile();
        }catch (IOException e){
            e.printStackTrace();
        }

        //写入硬盘
        try {

            if (isMakeDir&&isMakeDir2){
                System.out.println("创建文件成功");

                //字节流
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
                start = System.currentTimeMillis();
                objectOutputStream.writeObject(createString.getS());
                objectOutputStream.flush();
                end = System.currentTimeMillis();
                System.out.print("使用字节流把对象放入内存时间(ms)：");
                System.out.println(end-start);

                BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
                ObjectOutputStream objectOutputStream1 = new ObjectOutputStream(bufferedOutputStream);
                start = System.currentTimeMillis();
                objectOutputStream1.writeObject(createString.getS());
                objectOutputStream1.flush();
                objectOutputStream1.close();
                end = System.currentTimeMillis();
                System.out.print("使用缓存字节流把对象放入内存时间(ms)：");
                System.out.println(end-start);



                //字符流
                start = System.currentTimeMillis();
                FileWriter fileWriter = new FileWriter(file2);
                fileWriter.write(createString.getS());
                fileWriter.flush();
                end = System.currentTimeMillis();
                System.out.print("使用字符流把对象放入内存时间(ms)：");
                System.out.println(end-start);

                start = System.currentTimeMillis();
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                bufferedWriter.write(createString.getS());
                bufferedWriter.flush();
                bufferedWriter.close();
                end = System.currentTimeMillis();
                System.out.print("使用缓存字符流把对象放入内存时间(ms)：");
                System.out.println(end-start);

            }else{
                System.out.println("创建文件失败");
            }
        }catch (IOException e){
            e.printStackTrace();
        }

        //读入内存
        try{
            if (isMakeDir&&isMakeDir2){
                //字节流
                FileInputStream fileInputStream = new FileInputStream(file);
                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
                start = System.currentTimeMillis();
                String s = (String)objectInputStream.readObject();
                end = System.currentTimeMillis();
                System.out.print("\n使用字节流读取文件的时间(ms): ");
                System.out.println(end-start);

                BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
                objectInputStream = new ObjectInputStream(bufferedInputStream);
                start = System.currentTimeMillis();
                s = (String)objectInputStream.readObject();
                objectInputStream.close();
                end = System.currentTimeMillis();
                System.out.print("使用缓存字节流读取文件的时间(ms): ");
                System.out.println(end-start);


                //字符流
                FileReader fileReader = new FileReader(file);
                char[] array = new char[140217728];
                start = System.currentTimeMillis();
                fileReader.read(array);
                end = System.currentTimeMillis();
                System.out.print("使用字符流读取文件的时间(ms): ");
                System.out.println(end-start);

                BufferedReader bufferedReader = new BufferedReader(fileReader);
                start = System.currentTimeMillis();
                fileReader.read(array);
                fileReader.close();
                end = System.currentTimeMillis();
                System.out.print("使用缓存字符流读取文件的时间(ms): ");
                System.out.println(end-start);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public static String bytetoString(byte[] b){
        StringBuilder stringBuilder = new StringBuilder();
        for (byte e:b){
            stringBuilder.append(e);
        }
        return stringBuilder.toString();
    }

    public static byte[] objectToByteArray(Object obj) {
        byte[] bytes = null;
        ByteArrayOutputStream byteArrayOutputStream = null;
        ObjectOutputStream objectOutputStream = null;
        try {
            byteArrayOutputStream = new ByteArrayOutputStream();
            objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(obj);
            objectOutputStream.flush();
            bytes = byteArrayOutputStream.toByteArray();

        } catch (IOException e) {
            System.err.println("objectToByteArray failed, " + e);
        } finally {
            if (objectOutputStream != null) {
                try {
                    objectOutputStream.close();
                } catch (IOException e) {
                    System.err.println("close objectOutputStream failed, " + e);
                }
            }
            if (byteArrayOutputStream != null) {
                try {
                    byteArrayOutputStream.close();
                } catch (IOException e) {
                    System.err.println("close byteArrayOutputStream failed, " + e);
                }
            }

        }
        return bytes;
    }

}
