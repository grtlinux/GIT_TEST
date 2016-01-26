package tain.kr.com.test.file.v02;

public class FileInfoBean {

	private static boolean flag = true;
	
	private String type;   // file type : RF(Remote File), LF(Local File), RD(Remote File Download), LU(Local File Upload)
	private String base;   // base path name
	private String name;   // file name except base path name
	private long time;    // last modified time by millisecond
	private long length;  // file size
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getBase() {
		return base;
	}
	public void setBase(String base) {
		this.base = base;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getTime() {
		return time;
	}
	public void setTime(long time) {
		this.time = time;
	}
	public long getLength() {
		return length;
	}
	public void setLength(long length) {
		this.length = length;
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////

	public String toString() {
		
		StringBuffer sb = new StringBuffer();
		
		if (flag) {
			sb.append(this.type).append("||");
			sb.append(this.base).append("||");
			sb.append(this.name).append("||");
			sb.append(this.time).append("||");
			sb.append(this.length);
		}
		
		return sb.toString();
	}
}
