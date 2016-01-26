package tain.kr.com.test.enumtest.v01;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public enum EnumField {

	// type, lead, off, len, name, desc, defVal
	
	MSG_LEN           ('N', '0',   0,   4, "MSG_LEN"            , "전문총길이"                     , "0879"),
	MSG_TEXT          ('C', ' ',   4,  10, "MSG_TEXT"           , "전문구분"                       , "INPIRT0210"),
	TRADE_GB_CD       ('N', ' ',  14,   6, "TRADE_GB_CD"        , "거래구분코드"                   , "100010"),
	BUSI_DT           ('N', ' ',  20,   8, "BUSI_DT"            , "영업일자"                       , ""),
	TRADE_GENTD_DT    ('N', ' ',  28,   4, "TRADE_GENTD_DT"     , "거래발생일자"                   , ""),
	TRADE_GENTD_TM    ('N', ' ',  32,   6, "TRADE_GENTD_TM"     , "거래발생시간"                   , ""),
	TRADE_GENTD_STCD  ('N', ' ',  38,   4, "TRADE_GENTD_STCD"   , "거래발생점번호"                 , "9900"),
	TRADE_GENTD_POSNO ('N', ' ',  42,   4, "TRADE_GENTD_POSNO"  , "거래발생POS번호"                , "7946"),
	TRADE_NO          ('N', ' ',  46,   4, "TRADE_NO"           , "거래번호"                       , "2839"),
	DO_BYID           ('N', ' ',  50,   6, "DO_BYID"            , "담당자사번"                     , ""),
	CARD_NO           ('N', ' ',  56,  60, "CARD_NO"            , "카드번호"                       , ""),
	CUST_ID           ('N', ' ', 116,   9, "CUST_ID"            , "고객고유번호"                   , ""),
	INPUT_FLG         ('N', ' ', 125,   1, "INPUT_FLG"          , "포인트카드인식구분"             , "M"),
	BRCH_ID           ('N', ' ', 126,  10, "BRCH_ID"            , "가맹점ID"                       , "E999999999"),
	RECPT_NO          ('N', ' ', 136,  20, "RECPT_NO"           , "영수증번호"                     , ""),
	PASSWD            ('N', ' ', 156,   4, "PASSWD"             , "비밀번호"                       , ""),
	GIFTCARD_AMT      ('N', ' ', 160,  10, "GIFTCARD_AMT"       , "상품권금액"                     , ""),
	CASH_AMT          ('N', ' ', 170,  10, "CASH_AMT"           , "수단별 결제금액:현금"           , ""),
	GIFTCARD_PAY_AMT  ('N', ' ', 180,  10, "GIFTCARD_PAY_AMT"   , "수단별 결제금액:상품권"         , ""),
	M_GIFTCARD_PAY_AMT('N', ' ', 190,  10, "M_GIFTCARD_PAY_AMT" , "수단별 결제금액:상품권(모바일)" , ""),
	SHIN_CARD_AMT     ('N', ' ', 200,  10, "SHIN_CARD_AMT"      , "수단별 결제금액:신세계시티"     , ""),
	ALLIANCE_CARD_AMT1('N', ' ', 210,  10, "ALLIANCE_CARD_AMT1" , "수단별 결제금액:제휴카드1"      , ""),
	ALLIANCE_CARD_AMT2('N', ' ', 220,  10, "ALLIANCE_CARD_AMT2" , "수단별 결제금액:제휴카드2"      , ""),
	ALLIANCE_CARD_AMT3('N', ' ', 230,  10, "ALLIANCE_CARD_AMT3" , "수단별 결제금액:제휴카드3"      , ""),
	OTHER_CARD_AMT    ('N', ' ', 240,  10, "OTHER_CARD_AMT"     , "수단별 결제금액:타사카드"       , ""),
	SHIN_DEBIT_AMT    ('N', ' ', 250,  10, "SHIN_DEBIT_AMT"     , "수단별 결제금액:제휴직불카드"   , ""),
	DEBIT_AMT         ('N', ' ', 260,  10, "DEBIT_AMT"          , "수단별 결제금액:직불카드"       , ""),
	OTHER_ETC_AMT     ('N', ' ', 270,  10, "OTHER_ETC_AMT"      , "수단별 결제금액:기타매출"       , ""),
	NOADD_AMT         ('N', ' ', 280,  10, "NOADD_AMT"          , "수단별 결제금액:미적립결제액"   , ""),
	SPOINT_AMT        ('N', ' ', 290,  10, "SPOINT_AMT"         , "수단별 결제금액:S-POINT사용"    , ""),
	FILLER_AMT1       ('N', ' ', 300,  10, "FILLER_AMT1"        , "수단별 결제금액:여분1"          , ""),
	FILLER_AMT2       ('N', ' ', 310,  10, "FILLER_AMT2"        , "수단별 결제금액:여분2"          , ""),
	NOPOINT_PROD_AMT  ('N', ' ', 320,  10, "NOPOINT_PROD_AMT"   , "포인트미적립상품금액"           , ""),
	TOT_TRADE_AMT     ('N', ' ', 330,  10, "TOT_TRADE_AMT"      , "총거래금액"                     , ""),
	TPOINT            ('N', ' ', 340,   8, "TPOINT"             , "누적포인트"                     , ""),
	UBPOINT           ('N', ' ', 348,   8, "UBPOINT"            , "가용포인트"                     , ""),
	GPOINT            ('N', ' ', 356,   8, "GPOINT"             , "금회포인트"                     , ""),
	CAMPAIGN_CLUB1    ('N', ' ', 364,   5, "CAMPAIGN_CLUB1"     , "캠페인:클럽ID1"                 , ""),
	CAMPAIGN_AMT1     ('N', ' ', 369,  10, "CAMPAIGN_AMT1"      , "캠페인:기준금액1"               , ""),
	CAMPAIGN_CLUB2    ('N', ' ', 379,   5, "CAMPAIGN_CLUB2"     , "캠페인:클럽ID2"                 , ""),
	CAMPAIGN_AMT2     ('N', ' ', 384,  10, "CAMPAIGN_AMT2"      , "캠페인:기준금액2"               , ""),
	CAMPAIGN_CLUB3    ('N', ' ', 394,   5, "CAMPAIGN_CLUB3"     , "캠페인:클럽ID3"                 , ""),
	CAMPAIGN_AMT3     ('N', ' ', 399,  10, "CAMPAIGN_AMT3"      , "캠페인:기준금액3"               , ""),
	EXTINCT_POINT     ('N', ' ', 409,   8, "EXTINCT_POINT"      , "소멸예정포인트"                 , ""),
	BASE_APOINT       ('N', ' ', 417,   8, "BASE_APOINT"        , "발생포인트:기본적립"            , ""),
	PROD_APOINT       ('N', ' ', 425,   8, "PROD_APOINT"        , "발생포인트:상품적립"            , ""),
	CLUB_APOINT       ('N', ' ', 433,   8, "CLUB_APOINT"        , "발생포인트:클럽적립"            , ""),
	EVENT_APOINT      ('N', ' ', 441,   8, "EVENT_APOINT"       , "발생포인트:이벤트적립"          , ""),
	ETC_APOINT        ('N', ' ', 449,   8, "ETC_APOINT"         , "발생포인트:기타적립"            , ""),
	CLUB01            ('N', ' ', 457,   5, "CLUB01"             , "클럽속성:클럽ID01"              , ""),
	CLUB02            ('N', ' ', 462,   5, "CLUB02"             , "클럽속성:클럽ID02"              , ""),
	CLUB03            ('N', ' ', 467,   5, "CLUB03"             , "클럽속성:클럽ID03"              , ""),
	CLUB04            ('N', ' ', 472,   5, "CLUB04"             , "클럽속성:클럽ID04"              , ""),
	CLUB05            ('N', ' ', 477,   5, "CLUB05"             , "클럽속성:클럽ID05"              , ""),
	CLUB06            ('N', ' ', 482,   5, "CLUB06"             , "클럽속성:클럽ID06"              , ""),
	CLUB07            ('N', ' ', 487,   5, "CLUB07"             , "클럽속성:클럽ID07"              , ""),
	CLUB08            ('N', ' ', 492,   5, "CLUB08"             , "클럽속성:클럽ID08"              , ""),
	CLUB09            ('N', ' ', 497,   5, "CLUB09"             , "클럽속성:클럽ID09"              , ""),
	CLUB10            ('N', ' ', 502,   5, "CLUB10"             , "클럽속성:클럽ID10"              , ""),
	CLUB11            ('N', ' ', 507,   5, "CLUB11"             , "클럽속성:클럽ID11"              , ""),
	CLUB12            ('N', ' ', 512,   5, "CLUB12"             , "클럽속성:클럽ID12"              , ""),
	CLUB13            ('N', ' ', 517,   5, "CLUB13"             , "클럽속성:클럽ID13"              , ""),
	CLUB14            ('N', ' ', 522,   5, "CLUB14"             , "클럽속성:클럽ID14"              , ""),
	CLUB15            ('N', ' ', 527,   5, "CLUB15"             , "클럽속성:클럽ID15"              , ""),
	CLUB16            ('N', ' ', 532,   5, "CLUB16"             , "클럽속성:클럽ID16"              , ""),
	CLUB17            ('N', ' ', 537,   5, "CLUB17"             , "클럽속성:클럽ID17"              , ""),
	CLUB18            ('N', ' ', 542,   5, "CLUB18"             , "클럽속성:클럽ID18"              , ""),
	CLUB19            ('N', ' ', 547,   5, "CLUB19"             , "클럽속성:클럽ID19"              , ""),
	CLUB20            ('N', ' ', 552,   5, "CLUB20"             , "클럽속성:클럽ID20"              , ""),
	CONV_CUST_FLG     ('C', ' ', 557,   1, "CONV_CUST_FLG"      , "전환고객여부"                   , ""),
	CUST_NAME_FLG     ('C', ' ', 558,   1, "CUST_NAME_FLG"      , "고객명존재여부"                 , ""),
	CASH_RECPT_FLG    ('C', ' ', 559,   1, "CASH_RECPT_FLG"     , "핸드폰번호여부"                 , ""),
	MOBILE_NO         ('N', ' ', 560,  30, "MOBILE_NO"          , "핸드폰번호"                     , ""),
	REJCT_GB          ('C', ' ', 590,   1, "REJCT_GB"           , "취소구분(적립취소/사용취소)"    , ""),
	CASH_SAVING_GB    ('C', ' ', 591,   1, "CASH_SAVING_GB"     , "잔돈적립카드여부"               , ""),
	ASIANA_GB         ('C', ' ', 592,   1, "ASIANA_GB"          , "아시아니카드여부"               , ""),
	BASIS_CARNO1      ('C', ' ', 593,  12, "BASIS_CARNO1"       , "주차정산:고정차량1"             , ""),
	BASIS_CARNO2      ('C', ' ', 605,  12, "BASIS_CARNO2"       , "주차정산:고정차량2"             , ""),
	TODAY_CARNO       ('N', ' ', 617,   6, "TODAY_CARNO"        , "주차정산:당일차량"              , ""),
	OTRADE_BUSI_DT    ('N', ' ', 623,   8, "OTRADE_BUSI_DT"     , "원거래영업일자"                 , ""),
	OTRADE_STCD       ('N', ' ', 631,   4, "OTRADE_STCD"        , "원거래점번호"                   , ""),
	OTRADE_POSNO      ('N', ' ', 635,   4, "OTRADE_POSNO"       , "원거래POS번호"                  , ""),
	OTRADE_RECPT_NO   ('N', ' ', 639,  20, "OTRADE_RECPT_NO"    , "원거래영수증번호"               , ""),
	OTRADE_AMT        ('N', ' ', 659,  10, "OTRADE_AMT"         , "원거래금액"                     , ""),
	REPLY_CD          ('N', ' ', 669,   4, "REPLY_CD"           , "응답코드"                       , ""),
	SYSTEM_CRE_DT     ('N', ' ', 673,   8, "SYSTEM_CRE_DT"      , "시스템일자"                     , ""),
	SYSTEM_CRE_TM     ('N', ' ', 681,   6, "SYSTEM_CRE_TM"      , "시스템시간"                     , ""),
	CUST_NAME         ('C', ' ', 687,  40, "CUST_NAME"          , "고객명"                         , ""),
	REPLY_MESG        ('C', ' ', 727,  40, "REPLY_MESG"         , "영수증출력메시지"               , ""),
	APP_POINT_GB      ('C', ' ', 767,   2, "APP_POINT_GB"       , "APP포인트카드구분"              , ""),
	APP_POINT_FLG     ('C', ' ', 769,  10, "APP_POINT_FLG"      , "APP포인트카드구분자"            , ""),
	FILLER            ('N', ' ', 779, 100, "FILLER"             , "여분"                           , ""),
	;

	private final char type;
	private final char lead;
	private final int off;
	private final int len;
	private final String name;
	private final String desc;
	private final String defVal; 
	
	public char getType() {
		return this.type;
	}
	
	public char getLead() {
		return this.lead;
	}
	
	public int getOff() {
		return this.off;
	}
	
	public int getLen() {
		return this.len;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getDesc() {
		return this.desc;
	}
	
	public String getDefVal() {
		return this.defVal;
	}
	
	EnumField(char type, char lead, int off, int len, String name, String desc, String defVal) {
		this.type = type;
		this.lead = lead;
		this.off = off;
		this.len = len;
		this.name = name;
		this.desc = desc;
		this.defVal = defVal;
	}
	
	////////////////////////////////////////////////////////////////////
	
	public void fill(byte[] bytes, byte by) {
		
		for (int i=0; i < this.len; i++) {
			bytes[this.off + i] = by;
		}
	}
	
	public void setVal(byte[] bytes, String val) {
		
		if (val == null) {
			// clear the exist string
			for (int i=0; i < this.len; i++) {
				bytes[this.off + i] = (byte) ' ';
			}
			
			return;
		}
		
		byte[] byVal = val.getBytes();
		int byLen = byVal.length;
		
		if (this.type == 'C') {
			// left arrange
			
			for (int i=0, j=0; i < this.len; i++, j++) {
				if (j < byLen) {
					bytes[this.off + i] = byVal[j];
				} else {
					bytes[this.off + i] = (byte) this.lead;
				}
			}
			
		} else if (this.type == 'N') {
			// right arrange
			
			for (int i=0, j=0; i < this.len; i++, j++) {
				if (j < byLen) {
					bytes[this.off + this.len - 1 - i] = byVal[byLen - 1 - j];
				} else {
					bytes[this.off + this.len - 1 - i] = (byte) this.lead;
				}
			}
		}
	}

	public String getVal(byte[] bytes) {
		return new String(bytes, this.off, this.len);
	}

	///////////////////////////////////////////////////////////////////
	
	private static boolean flag = true;
	
	public static final int lenTotal = 879;

	public static byte[] makeBytes() {
		
		byte[] bytes = new byte[lenTotal];
		
		if (!flag) {
			for (int i=0; i < lenTotal; i++) {
				bytes[i] = (byte) ' ';
			}
		}
		
		if (flag) {
			for (EnumField fld : EnumField.values()) {
				fld.setVal(bytes, fld.getDefVal());
			}
		}
		
		return bytes;
	}
	
	public static byte[] clearBytes(byte[] bytes) {
		
		for (int i=0; i < lenTotal; i++) {
			bytes[i] = (byte) ' ';
		}
		
		return bytes;
	}
	
	///////////////////////////////////////////////////////////////////

	// socket recv
	public static byte[] recv(Socket socket, DataInputStream dis, final int size) throws Exception {
		int ret = 0;
		int readed = 0;
		byte[] buf = new byte[size];
		
		socket.setSoTimeout(0);
		while (readed < size) {
			ret = dis.read(buf, readed, size - readed);
			//System.out.println("    size:" + size + "    readed:" + readed + "     ret:" + ret);
			
			if (ret <= 0) {
				try {
					Thread.sleep(1000);
				} catch (Exception e) {}

				continue;
			} else {
				socket.setSoTimeout(1000);
			}
			
			readed += ret;
		}
		
		return buf;
	}

	public static void main(String[] args) throws Exception {
		
		if (!flag) {
			int off = 0;
			for (EnumField fld : EnumField.values()) {
				System.out.printf("[%s] [%3d][%3d] [%3d] [%20s] [%s] [%s]\n"
						, fld.getType(), off, fld.getOff(), fld.getLen(), fld.getName(), fld.getDesc(), fld.getDefVal());
				off += fld.getLen();
			}
		}
		
		if (!flag) {
			//byte[] byt = new byte[EnumField.lenTotal];
			byte[] bytes = new byte[20];
			
			MSG_LEN     .setVal(bytes, String.valueOf(12));
			MSG_TEXT    .setVal(bytes, "TR0210");
			TRADE_GB_CD .setVal(bytes, "0210"  );
			System.out.println("[" + new String(bytes) + "]");
			
			System.out.println("[" + MSG_LEN      .getVal(bytes) + "]");
			System.out.println("[" + MSG_TEXT     .getVal(bytes) + "]");
			System.out.println("[" + TRADE_GB_CD  .getVal(bytes) + "]");
		}
		
		if (!flag) {
			byte[] bytes = EnumField.makeBytes();
			if (flag) System.out.println("[" + new String(bytes) + "]");
			EnumField.clearBytes(bytes);
			if (flag) System.out.println("[" + new String(bytes) + "]");
		}
		
		if (flag) {
			/*
			 * socket test
			 */
			Socket socket = new Socket("174.100.67.129", 64000);
			if (flag) System.out.println("STATUS : connection is OK!!!");
			
			DataInputStream dis = new DataInputStream(socket.getInputStream());
			DataOutputStream dos = new DataOutputStream(socket.getOutputStream());

			byte[] bytes = null;
			
			if (flag) {
				/*
				 * make send packet
				 */
				bytes = EnumField.makeBytes();
				
				BUSI_DT       .setVal(bytes, "20151209");
				TRADE_GENTD_DT.setVal(bytes, "1209");
				TRADE_GENTD_TM.setVal(bytes, "121212");
				
				if (!flag) System.out.println("[" + new String(bytes) + "]");
			}
			
			if (flag) {
				/*
				 * send request
				 */
				dos.write(bytes, 0, EnumField.lenTotal); dos.flush();
				if (flag) System.out.printf("-> SEND DATA [%s]\n", new String(bytes));
			}
			
			if (flag) {
				/*
				 * recv response
				 */
				byte[] len = recv(socket, dis, 4);
				int size = Integer.parseInt(new String(len));
				if (flag) System.out.printf("<- RECV LEN [%s][%d]\n", new String(len), size);
				
				byte[] data = recv(socket, dis, size - 4);
				if (flag) System.out.printf("<- RECV DATA [%s]\n", new String(data));
			}
			
			dis.close();
			dos.close();
			socket.close();
		}
	}
}
