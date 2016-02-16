/* Copyright (c) 2008-2014, KangSeok
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this
 * list of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice,
 * this list of conditions and the following disclaimer in the documentation
 * and/or other materials provided with the distribution.
 *
 * Neither the name of the HSQL Development Group nor the names of its
 * contributors may be used to endorse or promote products derived from this
 * software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL HSQL DEVELOPMENT GROUP, HSQLDB.ORG,
 * OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package sdc.anal.lya.macro.A22.RP_SYS_WIP_DETAILS2.v00;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kiea.kr.co.tain.base.Flag;
import kiea.kr.co.tain.util.SystemProperties;
import kiea.proj.sdc.anal.common.FileValue;
import kiea.proj.sdc.anal.macro.impl.io.AbstractCSVReader;
import kiea.proj.sdc.anal.util.BasePath;
import kiea.proj.sdc.anal.util.StrUtil;
import kiea.proj.sdc.anal.util.log.v02.Logger;

import org.apache.commons.io.output.FileWriterWithEncoding;

import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.CSVWriter;
import au.com.bytecode.opencsv.bean.CsvToBean;
import au.com.bytecode.opencsv.bean.HeaderColumnNameTranslateMappingStrategy;

/**
 * @author KangSeok
 * @date 2014. 8. 5.
 * @file_name SO_SYS_CELLID_LIST2_CsvIo.java
 *
 */
public class SO_SYS_CELLID_LIST2_CsvIo extends AbstractCSVReader
{
	private String fileName = null;
	private List<SO_SYS_CELLID_LIST2_Bean> list = null;
	
	public SO_SYS_CELLID_LIST2_CsvIo(String filePath)
	{
		this.fileName = filePath + FileValue.SEP + "A21_" + "SO_SYS_CELLID_LIST2.csv";
	}

	/**
	 * getList
	 */
	@Override
	public List<SO_SYS_CELLID_LIST2_Bean> getList()
	{
		return getList(false);
	}
	
	public List<SO_SYS_CELLID_LIST2_Bean> getList(boolean flgRead)
	{
		if (Flag.TRUE) {
			
			if (!flgRead) {
				/*
				 * if flgRead = true, 초기에 읽지않는다.
				 */
				list = new ArrayList<SO_SYS_CELLID_LIST2_Bean>();
				return list;
			}
			
			try {
				/*
				 * Field 명과 Bean 항목명과 맵핑한다.
				 */
				Map<String, String> map = new HashMap<String, String>();
				map.put("CLUSTER_ID", "clusterId");
				map.put("GLASS_ID"  , "glassId"  );
				map.put("CELL_ID"   , "cellId"   );
				map.put("DEFECT_SEQ", "defectSeq");
				map.put("X_VALUE"   , "XValue"   );
				map.put("Y_VALUE"   , "YValue"   );
				map.put("GROUP"     , "group"    );
				
				HeaderColumnNameTranslateMappingStrategy<SO_SYS_CELLID_LIST2_Bean> mapping = new HeaderColumnNameTranslateMappingStrategy<SO_SYS_CELLID_LIST2_Bean>();
				mapping.setType(SO_SYS_CELLID_LIST2_Bean.class);
				mapping.setColumnMapping(map);
				
				/*
				 * CSV 파일을 읽어 Bean 리스트를 생성한다.
				 */
				CsvToBean<SO_SYS_CELLID_LIST2_Bean> bean = new CsvToBean<SO_SYS_CELLID_LIST2_Bean>();
				//CSVReader reader = new CSVReader(new FileReader(fileName));
				CSVReader reader = new CSVReader(new InputStreamReader(new FileInputStream(fileName), "EUC-KR"));
				list = bean.parse(mapping, reader);
				reader.close();
			
			} catch (FileNotFoundException e) {
				list = new ArrayList<SO_SYS_CELLID_LIST2_Bean>();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return list;
	}

	/**
	 * writeList
	 */
	public void writeList()
	{
		if (Flag.TRUE) {
			try {
				/*
				 * 기존 CSV 파일을 삭제하고 신규로 CSV를 생성하여 list를 기록한다.
				 */

				if (Flag.TRUE) new File(fileName).delete();
				
				//CSVWriter writer = new CSVWriter(new FileWriter(fileName));  // default seperator
				//CSVWriter writer = new CSVWriter(new FileWriterWithEncoding(fileName, "US-ASCII"));  // default seperator
				CSVWriter writer = new CSVWriter(new FileWriterWithEncoding(fileName, "EUC-KR"));  // default seperator

				// Header 출력
				writer.writeNext(SO_SYS_CELLID_LIST2_Bean.getHeader());
				
				for (SO_SYS_CELLID_LIST2_Bean line : list) {
					// Data 출력
					writer.writeNext(line.getStringArray());
				}
				
				writer.close();
			
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	///////////////////////////////////////////////////////////////////////////
	
	private static void test01()
	{
		if (Flag.TRUE) {
			SystemProperties.setTesting("010");
			BasePath.getInstance();
			try {
				Logger.getInstance(RP_SYS_WIP_DETAILS2_Main.jobId, RP_SYS_WIP_DETAILS2_Main.dsName + ".log");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		if (Flag.TRUE) {
			String path = BasePath.getInstance().getDataPath() + "/" + StrUtil.getDateFromJobId(RP_SYS_WIP_DETAILS2_Main.jobId) + "/" + RP_SYS_WIP_DETAILS2_Main.jobId;
			SO_SYS_CELLID_LIST2_CsvIo ioCsv = new SO_SYS_CELLID_LIST2_CsvIo(path);
			
			if (Flag.TRUE) {
				/*
				 * read
				 */
				List<SO_SYS_CELLID_LIST2_Bean> list = ioCsv.getList(true);
				
				for (SO_SYS_CELLID_LIST2_Bean bean : list) {
					System.out.println(bean);
				}
				
				System.out.println("the read ok !!!");
			}
		}
		
		Logger.exit();
	}

	public static void main(String[] args)
	{
		if (Flag.TRUE) test01();
	}
}
