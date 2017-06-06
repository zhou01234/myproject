package test;

import java.util.*;
import java.io.*;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

public class Log {
	FileChannel channel = null;  
    FileLock lock = null;  
    
    public Log() {
    	try {
			channel = new FileOutputStream("F:/log.txt",true).getChannel();
			RandomAccessFile raf = new RandomAccessFile("F:/log.txt","rw");  
	        //在文件末尾追加内容的处理  
	        raf.seek(raf.length());  
	        channel = raf.getChannel(); 
		} catch (IOException e) {
			e.printStackTrace();
		}      
	}
    
	public Log(String file) {
		try {
			channel = new FileOutputStream(file,true).getChannel();
			RandomAccessFile raf = new RandomAccessFile(file,"rw");  
	        //在文件末尾追加内容的处理  
	        raf.seek(raf.length());  
	        channel = raf.getChannel(); 
		} catch (IOException e) {
			e.printStackTrace();
		}  
        
	}
	
	public void log_write(String content) {
		try {
			do {  
	              lock = channel.tryLock(); }
			while (null == lock);  
	  
	        //互斥操作  
	        ByteBuffer sendBuffer=ByteBuffer.wrap(("\n"+new Date()+"\n"+content+"\n").getBytes());  
	        channel.write(sendBuffer);  
	        Thread.sleep(5000);  
		} catch(IOException | InterruptedException e) {
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
