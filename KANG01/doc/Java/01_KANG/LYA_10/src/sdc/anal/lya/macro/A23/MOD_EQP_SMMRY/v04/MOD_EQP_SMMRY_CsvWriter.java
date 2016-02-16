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

package sdc.anal.lya.macro.A23.MOD_EQP_SMMRY.v04;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kiea.kr.co.tain.base.Flag;
import kiea.kr.co.tain.util.SystemProperties;
import kiea.proj.sdc.anal.common.FileValue;
import kiea.proj.sdc.anal.macro.impl.io.AbstractCSVWriter;
import kiea.proj.sdc.anal.util.BasePath;
import kiea.proj.sdc.anal.util.StrUtil;

import org.apache.commons.io.output.FileWriterWithEncoding;

import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.CSVWriter;
import au.com.bytecode.opencsv.bean.CsvToBean;
import au.com.bytecode.opencsv.bean.HeaderColumnNameTranslateMappingStrategy;

/**
 * @author KangSeok
 * @date 2014. 8. 8.
 * @file_name MOD_EQP_SMMRY_CsvWriter.java
 *
 */
public class MOD_EQP_SMMRY_CsvWriter extends AbstractCSVWriter
{
	private String fileName = null;
	private List<MOD_EQP_SMMRY_WriteBean> list = null;
	
	public MOD_EQP_SMMRY_CsvWriter(String filePath)
	{
		this.fileName = filePath + FileValue.SEP + MOD_EQP_SMMRY_Main.dsName + ".csv";
	}

	/**
	 * getList
	 */
	@Override
	public List<MOD_EQP_SMMRY_WriteBean> getList()
	{
		return getList(false);
	}
	
	public List<MOD_EQP_SMMRY_WriteBean> getList(boolean flgRead)
	{
		if (Flag.TRUE) {
			
			if (!flgRead) {
				/*
				 * if flgRead = true, 초기에 읽지않는다.
				 */
				list = new ArrayList<MOD_EQP_SMMRY_WriteBean>();
				return list;
			}
			
			try {
				/*
				 * Field 명과 Bean 항목명과 맵핑한다.
				 */
				Map<String, String> map = new HashMap<String, String>();
				map.put("LINE_CODE"        , "lineCode"       );
				map.put("USER_GROUP_CODE"  , "userGroupCode"  );
				map.put("PROCESS_ID"       , "processId"      );
				map.put("PRODUCT_ID"       , "productId"      );
				map.put("PRODUCT_TYPE"     , "productType"    );
				map.put("AREA_CODE"        , "areaAode"       );
				map.put("SUB_AREA_CODE"    , "subAreaCode"    );
				map.put("STEP_ID"          , "stepId"         );
				map.put("EQP_ID"           , "eqpId"          );
				map.put("GLASS_ID"         , "glassId"        );
				map.put("CELL_ID"          , "cellId"         );
				map.put("CELL_LOC_ID"      , "cellLocId"      );
				map.put("DEFECT_GROUP_CODE", "defectGroupCode");
				map.put("DECISION_CODE"    , "decisionCode"   );
				map.put("INSPECTOR_ID"     , "inspectorId"    );
				map.put("INSPECT_DT"       , "inspectDt"      );
				map.put("INSPECT_HOUR"     , "inspectHour"    );
				map.put("INSPECT_HOUR2"    , "inspectHour2"   );
				
				HeaderColumnNameTranslateMappingStrategy<MOD_EQP_SMMRY_WriteBean> mapping = new HeaderColumnNameTranslateMappingStrategy<MOD_EQP_SMMRY_WriteBean>();
				mapping.setType(MOD_EQP_SMMRY_WriteBean.class);
				mapping.setColumnMapping(map);
				
				/*
				 * CSV 파일을 읽어 Bean 리스트를 생성한다.
				 */
				CsvToBean<MOD_EQP_SMMRY_WriteBean> bean = new CsvToBean<MOD_EQP_SMMRY_WriteBean>();
				CSVReader reader = new CSVReader(new FileReader(fileName));
				list = bean.parse(mapping, reader);
				reader.close();
			
			} catch (FileNotFoundException e) {
				list = new ArrayList<MOD_EQP_SMMRY_WriteBean>();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return list;
	}

	/**
	 * writeList
	 */
	@Override
	public void writeList()
	{
		if (Flag.TRUE) {
			try {
				/*
				 * 
				 */

				//CSVWriter writer = new CSVWriter(new FileWriter(fileName));  // default seperator
				CSVWriter writer = new CSVWriter(new FileWriterWithEncoding(fileName, "EUC-KR"));  // default seperator
				//CSVWriter writer = new CSVWriter(new FileWriterWithEncoding(fileName, "US-ASCII"));  // default seperator

				// Header 출력
				writer.writeNext(MOD_EQP_SMMRY_WriteBean.getHeader());
				
				for (MOD_EQP_SMMRY_WriteBean line : list) {
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
		}

		if (Flag.TRUE) {
			String path = BasePath.getInstance().getDataPath() + "/" + StrUtil.getDateFromJobId(MOD_EQP_SMMRY_Main.jobId) + "/" + MOD_EQP_SMMRY_Main.jobId;
			MOD_EQP_SMMRY_CsvWriter csv = new MOD_EQP_SMMRY_CsvWriter(path);
			
			if (Flag.TRUE) {
				/*
				 * read
				 */
				List<MOD_EQP_SMMRY_WriteBean> list = csv.getList(true);
				
				for (MOD_EQP_SMMRY_WriteBean bean : list) {
					System.out.println(bean);
				}
				
				System.out.println("the read ok !!!");
			}
		}
	}

	public static void main(String[] args)
	{
		if (Flag.TRUE) test01();
	}
}
