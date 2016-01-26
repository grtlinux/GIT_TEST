package tain.kr.com.test.object2byte.v01;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

class ObjectByte implements Serializable {

	/**
	 * Object <-> byte[] 변환은 Object가 반드시 serialize 된 것이어야 변환에 문제 없다.
	 */
	private static final long serialVersionUID = -1227482356251180435L;
	
	public String toString() {
		return String.valueOf(serialVersionUID);
	}
}

public class ObjectByteTestMain {

	private static boolean flag = true;
	
	private static byte[] toByte(Object obj) {
		
		byte[] bytes = null;
		
		if (flag) {
			ByteArrayOutputStream bos = null;
			ObjectOutputStream oos = null;
			
			try {
				bos = new ByteArrayOutputStream();
				oos = new ObjectOutputStream(bos);
				oos.writeObject(obj);
				oos.flush();
				bytes = bos.toByteArray();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					if (oos != null)
						oos.close();
					if (bos != null)
						bos.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
		return bytes;
	}
	
	private static Object toObject(byte[] bytes) {
		
		Object obj = null;
		
		if (flag) {
			try {
				ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
				ObjectInputStream ois = new ObjectInputStream(bis);
				obj = ois.readObject();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		
		return obj;
	}
	
	private static void test01(String[] args) throws Exception {
		
		if (flag) {
			ObjectByte src = new ObjectByte();
			
			byte[] bytes = toByte(src);
			if (flag) {
				System.out.println("> src");
				System.out.println(">" + src);
				System.out.println(">" + bytes.length);
			}
			
			ObjectByte tgt = (ObjectByte) toObject(bytes);
			if (flag) {
				System.out.println("> tgt");
				System.out.println(">" + tgt);
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		
		if (flag) test01(args);
	}
}
