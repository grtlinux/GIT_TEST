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

package sdc.anal.mura.macro.A24.RP_MUR_EQP_DETAILS2.v04;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import kiea.kr.co.tain.base.Flag;
import kiea.kr.co.tain.util.SystemProperties;
import kiea.proj.sdc.anal.common.Connection04;
import kiea.proj.sdc.anal.common.Parameter;
import kiea.proj.sdc.anal.macro.impl.io.AbstractOracleReader;
import kiea.proj.sdc.anal.util.BasePath;
import kiea.proj.sdc.anal.util.StrUtil;
import kiea.proj.sdc.anal.util.log.v02.Logger;
import oracle.jdbc.OracleConnection;

/**
 * @author KangSeok
 * @date 2014. 8. 8.
 * @file_name ANAL_INDEX_OracleReader.java
 *
 */
public class RP_MUR_EQP_DETAILS2_OracleReader extends AbstractOracleReader
{
	private List<RP_MUR_EQP_DETAILS2_ReadBean> list = new ArrayList<RP_MUR_EQP_DETAILS2_ReadBean>();
	
	/**
	 * Sample02OracleReader
	 */
	public RP_MUR_EQP_DETAILS2_OracleReader()
	{
		if (Flag.TRUE) {
		}
	}

	/**
	 * 
	 * getLine56
	 *
	 * @return
	 */
	private String getLine56()
	{
		String query = null;
		
		query = String.format(""
				+ "/* L5, L6 : 얼룩 EQP_DETAILS2 */                                  \n"
				+ "SELECT                                                            \n"
				+ "    *                                                             \n"
				+ "FROM                                                              \n"
				+ "    yms_dm.h_meas_rundata_cell                                    \n"
				+ "WHERE                                                             \n"
				+ "    siteid = %s                                                   \n"  // LINE
				+ "    AND TO_DATE (%s, 'yyyymmddhh24miss') <= dcoldate              \n"  // FROM_ETL_DT
				+ "    AND TO_DATE (%s, 'yyyymmddhh24miss') > dcoldate               \n"  // TO_ETL_DT
				+ "    AND orgstepid like 'L__080'                                   \n"
				+ "    AND itemid = %s                                               \n"  // DEFECT_GROUP_CODE
				+ "    AND PROCID = %s                                               \n"  // USER_GROUP_CODE
				+ ""
				, StrUtil.quote(Parameter.getInstance().getStrLine())
				, StrUtil.quote(Parameter.getInstance().getStrFromDateTime())
				, StrUtil.quote(Parameter.getInstance().getStrToDateTime())
				, StrUtil.quote(Parameter.getInstance().getStrDefectGroupCode())
				, StrUtil.quote(Parameter.getInstance().getStrUserGroupCode())
				);

		return query;
	}
	
	/**
	 * 
	 * getLine78
	 *
	 * @return
	 */
	private String getLine78()
	{
		String query = null;
		
		query = String.format(""
				+ "/* L7, L8 : 얼룩 EQP_DETAILS2 */                                  \n"
				+ "SELECT                                                            \n"
				+ "    *                                                             \n"
				+ "FROM                                                              \n"
				+ "    yms_dm.h_meas_rundata_cell                                    \n"
				+ "WHERE                                                             \n"
				+ "    siteid = %s                                                   \n"  // LINE
				+ "    AND TO_DATE (%s, 'yyyymmddhh24miss') <= dcoldate              \n"  // FROM_ETL_DT
				+ "    AND TO_DATE (%s, 'yyyymmddhh24miss') > dcoldate               \n"  // TO_ETL_DT
				+ "    AND measstepgrpid LIKE 'L%%'                                  \n"
				+ "    AND itemid = %s                                               \n"  // DEFECT_GROUP_CODE
				+ "    AND PROCID = %s                                               \n"  // USER_GROUP_CODE
				+ ""
				, StrUtil.quote(Parameter.getInstance().getStrLine())
				, StrUtil.quote(Parameter.getInstance().getStrFromDateTime())
				, StrUtil.quote(Parameter.getInstance().getStrToDateTime())
				, StrUtil.quote(Parameter.getInstance().getStrDefectGroupCode())
				, StrUtil.quote(Parameter.getInstance().getStrUserGroupCode())
				);

		return query;
	}
	
	/**
	 * getList
	 */
	@Override
	public List<RP_MUR_EQP_DETAILS2_ReadBean> getList()
	{
		return getList(false);
	}
	
	public List<RP_MUR_EQP_DETAILS2_ReadBean> getList(boolean flgRead)
	{
		if (Flag.TRUE) {
			if (!flgRead) {
				/*
				 * if flgRead = true, 초기에 읽지않는다.
				 */
				list = new ArrayList<RP_MUR_EQP_DETAILS2_ReadBean>();
				return list;
			}
			
			try {
				String query = null;
				
				if ("L5FAB".equals(Parameter.getInstance().getStrLine()) || "L6FAB".equals(Parameter.getInstance().getStrLine())) {
					query = getLine56();
				} else if ("L7AFAB".equals(Parameter.getInstance().getStrLine()) || "L7BFAB".equals(Parameter.getInstance().getStrLine()) || "L8AFAB".equals(Parameter.getInstance().getStrLine()) || "L8ZFAB".equals(Parameter.getInstance().getStrLine())) {
					query = getLine78();
				} else {
					return list;
				}
				
				if (!Flag.TRUE) System.out.println("[" + query + "]");
				if (Flag.TRUE) Logger.info("[" + query + "]");
				
				OracleConnection conn = Connection04.getInstance().getConn();

				if (Flag.TRUE) {
					Statement stmt = conn.createStatement();
					ResultSet rs = stmt.executeQuery(query);
					for (int i=0; i >= 0 && rs.next(); i++) {
						if (!Flag.TRUE) {
							System.out.println(String.format("%3d) --------------------------------------", i));
							System.out.println(String.format("    SITEID         = [%s]", rs.getString("SITEID"        )));
							System.out.println(String.format("    SITEID_EQPTYPE = [%s]", rs.getString("SITEID_EQPTYPE")));
							System.out.println(String.format("    COMPMASTERKEY  = [%s]", rs.getString("COMPMASTERKEY" )));
							System.out.println(String.format("    GLASSID        = [%s]", rs.getString("GLASSID"       )));
							System.out.println(String.format("    PRODID         = [%s]", rs.getString("PRODID"        )));
							System.out.println(String.format("    PROCID         = [%s]", rs.getString("PROCID"        )));
							System.out.println(String.format("    ORGSTEPID      = [%s]", rs.getString("ORGSTEPID"     )));
							System.out.println(String.format("    MEASSTEPGRPID  = [%s]", rs.getString("MEASSTEPGRPID" )));
							System.out.println(String.format("    MEASEQPUNITID  = [%s]", rs.getString("MEASEQPUNITID" )));
							System.out.println(String.format("    DCOLDATE       = [%s]", rs.getString("DCOLDATE"      )));
							System.out.println(String.format("    ITEMID         = [%s]", rs.getString("ITEMID"        )));
							System.out.println(String.format("    SUBITEMID      = [%s]", rs.getString("SUBITEMID"     )));
							System.out.println(String.format("    DATAVALUE      = [%s]", rs.getString("DATAVALUE"     )));
							System.out.println(String.format("    CELLLOCID      = [%s]", rs.getString("CELLLOCID"     )));
							System.out.println(String.format("    USL            = [%s]", rs.getString("USL"           )));
							System.out.println(String.format("    LSL            = [%s]", rs.getString("LSL"           )));
							System.out.println(String.format("    TARGET         = [%s]", rs.getString("TARGET"        )));
							System.out.println(String.format("    CODE_X         = [%s]", rs.getString("CODE_X"        )));
							System.out.println(String.format("    CODE_Y         = [%s]", rs.getString("CODE_Y"        )));
							System.out.println(String.format("    PRODTYPE       = [%s]", rs.getString("PRODTYPE"      )));
							System.out.println(String.format("    CODE_X2        = [%s]", rs.getString("CODE_X2"       )));
							System.out.println(String.format("    CODE_Y2        = [%s]", rs.getString("CODE_Y2"       )));
							System.out.println(String.format("    ETLMODIFYDATE  = [%s]", rs.getString("ETLMODIFYDATE" )));
							System.out.println(String.format("    SHOTNO         = [%s]", rs.getString("SHOTNO"        )));
							System.out.println(String.format("    IMGFILE        = [%s]", rs.getString("IMGFILE"       )));
						}
						
						if (Flag.TRUE) {
							RP_MUR_EQP_DETAILS2_ReadBean bean = new RP_MUR_EQP_DETAILS2_ReadBean();

							bean.setSiteId       (rs.getString("SITEID"        ));
							bean.setSiteIdEqpType(rs.getString("SITEID_EQPTYPE"));
							bean.setCompMasterKey(rs.getString("COMPMASTERKEY" ));
							bean.setGlassId      (rs.getString("GLASSID"       ));
							bean.setProdId       (rs.getString("PRODID"        ));
							bean.setProcId       (rs.getString("PROCID"        ));
							bean.setOrgStepId    (rs.getString("ORGSTEPID"     ));
							bean.setMeasStepGrpId(rs.getString("MEASSTEPGRPID" ));
							bean.setMeasEqpUnitId(rs.getString("MEASEQPUNITID" ));
							bean.setDcolDate     (rs.getString("DCOLDATE"      ));
							bean.setItemId       (rs.getString("ITEMID"        ));
							bean.setSubItemId    (rs.getString("SUBITEMID"     ));
							bean.setDataValue    (rs.getString("DATAVALUE"     ));
							bean.setCellLocId    (rs.getString("CELLLOCID"     ));
							bean.setUsl          (rs.getString("USL"           ));
							bean.setLsl          (rs.getString("LSL"           ));
							bean.setTarget       (rs.getString("TARGET"        ));
							bean.setCodeX        (rs.getString("CODE_X"        ));
							bean.setCodeY        (rs.getString("CODE_Y"        ));
							bean.setProdType     (rs.getString("PRODTYPE"      ));
							bean.setCodeX2       (rs.getString("CODE_X2"       ));
							bean.setCodeY2       (rs.getString("CODE_Y2"       ));
							bean.setEtlModifyDate(rs.getString("ETLMODIFYDATE" ));
							bean.setShotNo       (rs.getString("SHOTNO"        ));
							bean.setImgFile      (rs.getString("IMGFILE"       ));
							
							list.add(bean);
						}
					}
					
					if (!Flag.TRUE) {
						System.out.println();
						ResultSetMetaData md = rs.getMetaData();
						for (int i=1; i <= md.getColumnCount(); i++) {
							System.out.println(String.format("%d) [%s] [%s] [%s] [%s]", i, md.getColumnName(i), md.getColumnLabel(i), md.getColumnClassName(i), md.getCatalogName(i)));
						}
					}
				}
				
				Connection04.getInstance().close();
				if (!Flag.TRUE) System.out.println("OK!");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return list;
	}
	
	///////////////////////////////////////////////////////////////////////////
	
	private static void test01()
	{
		if (Flag.TRUE) {
			SystemProperties.setTesting("020");
			BasePath.getInstance();
			try {
				Logger.getInstance(RP_MUR_EQP_DETAILS2_Main.jobId, RP_MUR_EQP_DETAILS2_Main.dsName + ".log");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		if (Flag.TRUE) {
			Parameter.getInstance(RP_MUR_EQP_DETAILS2_Main.jobId);
			
			RP_MUR_EQP_DETAILS2_OracleReader reader = new RP_MUR_EQP_DETAILS2_OracleReader();
			
			for (RP_MUR_EQP_DETAILS2_ReadBean bean : reader.getList(true)) {
				System.out.println(bean);
			}
			
			System.out.println("the read ok !!!");
		}
		
		Logger.exit();
	}
	
	public static void main(String[] args)
	{
		if (Flag.TRUE) test01();
	}
}
