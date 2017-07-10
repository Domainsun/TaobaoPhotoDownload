package com.example.administrator.taobaophotodownload;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.administrator.taobaophotodownload.HttpUntils.URLThread;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class MainActivity extends AppCompatActivity {
    String html;

    String filePath = "/sdcard/Test/";
    String fileName = "html.txt";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


            getHtml("https://detail.tmall.com/item.htm?spm=a230r.1.14.165.ebb2eb25i5qBy&id=544756767588&ns=1&abbucket=3");
        try {
            writevFile(fileName,html);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("写入执行完毕");

    }



    public  void getHtml(String url) {
        URLThread t1 = new URLThread(url);
        FutureTask<String> ft = new FutureTask<>(t1);
        Thread oneThread = new Thread(ft);
        oneThread.start();


        try {
            html = ft.get();
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        } catch (ExecutionException e1) {
            e1.printStackTrace();
        }

        System.out.println("html-------:\n" + html);

    }


//    private void writeTxtToFile(String strcontent, String filePath, String fileName) {
////        生成文件夹之后，再生成文件，不然会出错
//        makeFilePath(filePath, fileName);
//
//        String strFilePath = filePath + fileName;
//        // 每次写入时，都换行写
//        String strContent = strcontent + "\r\n";
//        try {
//            File file = new File(strFilePath);
//            if (!file.exists()) {
//                Log.d("TestFile", "Create the file:" + strFilePath);
//                file.getParentFile().mkdirs();
//                file.createNewFile();
//            }
//            RandomAccessFile raf = new RandomAccessFile(file, "rwd");
//            raf.seek(file.length());
//            raf.write(strContent.getBytes());
//            raf.close();
//
//            System.out.println("写入完成--------------:");
//        } catch (Exception e) {
//            System.out.println("写入异常:" + e.toString());
//        }
//    }
//
//    private File makeFilePath(String filePath, String fileName) {
//        File file = null;
//        makeRootDirectory(filePath);
//        try {
//            file = new File(filePath + fileName);
//            if (!file.exists()) {
//                file.createNewFile();
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return file;
//    }
//
//    private void makeRootDirectory(String filePath) {
//        File file = null;
//        try {
//            file = new File(filePath);
//            if (!file.exists()) {
//                file.mkdir();
//            }
//        } catch (Exception e) {
//            Log.i("error:", e + "");
//        }
//    }


    //写数据
    public void writevFile(String fileName,String writestr) throws IOException {
        try{

            FileOutputStream fout =openFileOutput(fileName, MODE_PRIVATE);

            byte [] bytes = writestr.getBytes();

            fout.write(bytes);

            fout.close();
        }

        catch(Exception e){
            e.printStackTrace();
        }
    }
}
