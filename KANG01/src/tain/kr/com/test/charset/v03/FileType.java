package tain.kr.com.test.charset.v03;

import org.apache.log4j.Logger;

public enum FileType {

	// type, num, name, flg
	
	EUC_KR ( 1, "EUC-KR", false ),
	UTF_8  ( 2, "UTF-8" , true  ),
	;
	
	private final int num;
	private final String name;
	private final boolean flg;
	
	FileType(int num, String name, boolean flg) {
		this.num = num;
		this.name = name;
		this.flg = flg;
	}

	public int getNum() {
		return num;
	}

	public String getName() {
		return name;
	}

	public boolean isFlg() {
		return flg;
	}

	///////////////////////////////////////////////////////////////////
	
	final static Logger log = Logger.getLogger(FileType.class);
	
	private static boolean flag = true;
	
	public static void main(String[] args) {
		
		if (flag) {
			int num = FileType.EUC_KR.getNum();
			String name = FileType.EUC_KR.getName();
			boolean flg = FileType.EUC_KR.isFlg();
			
			if (flag) log.debug(String.format("%d %s %s", num, name, flg));
		}
		
		if (flag) {
			for (FileType fileType : FileType.values()) {
				if (flag) log.debug(String.format("> %s %d %s %s", fileType, fileType.getNum(), fileType.getName(), fileType.isFlg()));
			}
		}
	}
}
