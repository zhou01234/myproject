package test;

import java.util.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;

public class testdate {
	
	public static void main(String[] args){
		FileChannel channel = null;  
        FileLock lock = null;  
        try {  
            
            channel = new FileOutputStream("F:\\logfile.txt",true).getChannel();  
            RandomAccessFile raf = new RandomAccessFile("F:\\logfile.txt","rw");  
  
            //���ļ�ĩβ׷�����ݵĴ���  
            raf.seek(raf.length());  
            channel = raf.getChannel();  

            //�����������trylock()���������ķ��������ļ���������ʱ��tryLock()��õ�nullֵ  
            do {  
              lock = channel.tryLock();  
            } while (null == lock);  
  
            //�������  
            ByteBuffer sendBuffer=ByteBuffer.wrap((new Date()+" д��\n").getBytes());  
            channel.write(sendBuffer);  
            Thread.sleep(5000);  
        } catch (FileNotFoundException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        } catch (InterruptedException e) {  
            e.printStackTrace();  
        } finally {  
            if (lock != null) {  
                try {  
                    lock.release();  
                    lock = null;  
                } catch (IOException e) {  
                    e.printStackTrace();  
                }  
            }  
  
            if (channel != null) {  
                try {  
                    channel.close();  
                    channel = null;  
                } catch (IOException e) {  
                    e.printStackTrace();  
                }  
            }  
        }  
	}
	
	
}
