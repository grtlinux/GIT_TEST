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

package sdc.anal.lya.macro.A11.INSPCT_HIST.v01;

import java.util.List;

import kiea.kr.co.tain.base.Flag;
import kiea.proj.sdc.anal.macro.impl.job.AbstractMacroJob;
import kiea.proj.sdc.anal.util.BasePath;
import kiea.proj.sdc.anal.util.FileUtil;
import kiea.proj.sdc.anal.util.StrUtil;
import kiea.proj.sdc.anal.util.log.v02.Logger;

/**
 * @author KangSeok
 * @date 2014. 8. 8.
 * @file_name AIB_INSPCT_HIST_MacroJob.java
 *
 */
public class INSPCT_HIST_MacroJob extends AbstractMacroJob
{
	private INSPCT_HIST_OracleReader reader = null;
	private INSPCT_HIST_CsvWriter writer = null;
	
	private List<INSPCT_HIST_ReadBean> inList = null;
	private List<INSPCT_HIST_WriteBean> outList = null;
	
	private String filePath = null;

	private String jobId = null;

	public INSPCT_HIST_MacroJob(String jobId)
	{
		this.jobId = jobId;
		
		this.filePath = FileUtil.makeDataDir(BasePath.getInstance().getDataPath(), StrUtil.getDateFromJobId(this.jobId), this.jobId);
	}
	
	/**
	 * generateDataSet
	 */
	public void generateDataSet()
	{
		if (Flag.TRUE) {
		}
	}

	/**
	 * beforeMacroJob
	 */
	public void beforeMacroJob()
	{
		if (Flag.TRUE) Logger.info("beforeMacroJob : " + this.getClass());
		
		if (Flag.TRUE) {
			try {
				reader = new INSPCT_HIST_OracleReader();
				writer = new INSPCT_HIST_CsvWriter(this.filePath);
				
				inList = reader.getList(true);
				outList = writer.getList();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * macroJob
	 */
	public void macroJob()
	{
		if (Flag.TRUE) Logger.info("macroJob : " + this.getClass());

		if (Flag.TRUE) {
			try {
				/*
				 * Job
				 * 1. InBean -> OutBean
				 */
				
				if (Flag.TRUE) {
					for (INSPCT_HIST_ReadBean readBean : inList) {
						INSPCT_HIST_WriteBean writeBean = new INSPCT_HIST_WriteBean();

						writeBean.setLineCode       (readBean.getLineCode       ());
						writeBean.setUserGroupCode  (readBean.getUserGroupCode  ());
						writeBean.setProcessId      (readBean.getProcessId      ());
						writeBean.setProductId      (readBean.getProductId      ());
						writeBean.setProductType    (readBean.getProductType    ());
						writeBean.setAreaCode       (readBean.getAreaCode       ());
						writeBean.setSubAreaCode    (readBean.getSubAreaCode    ());
						writeBean.setStepId         (readBean.getStepId         ());
						writeBean.setEqpId          (readBean.getEqpId          ());
						writeBean.setGlassId        (readBean.getGlassId        ());
						writeBean.setCellId         (readBean.getCellId         ());
						writeBean.setInspectDt      (readBean.getInspectDt      ());
						writeBean.setCellLocId      (readBean.getCellLocId      ());
						writeBean.setDefectGroupCode(readBean.getDefectGroupCode());
						writeBean.setDecisionCode   (readBean.getDecisionCode   ());
						writeBean.setInspectorId    (readBean.getInspectorId    ());

						outList.add(writeBean);
					}
				}

				if (!Flag.TRUE) {
					for (int i=0; inList != null && i < inList.size(); i++) {
						INSPCT_HIST_WriteBean writeBean = new INSPCT_HIST_WriteBean();

						writeBean.setLineCode       (inList.get(i).getLineCode       ());
						writeBean.setUserGroupCode  (inList.get(i).getUserGroupCode  ());
						writeBean.setProcessId      (inList.get(i).getProcessId      ());
						writeBean.setProductId      (inList.get(i).getProductId      ());
						writeBean.setProductType    (inList.get(i).getProductType    ());
						writeBean.setAreaCode       (inList.get(i).getAreaCode       ());
						writeBean.setSubAreaCode    (inList.get(i).getSubAreaCode    ());
						writeBean.setStepId         (inList.get(i).getStepId         ());
						writeBean.setEqpId          (inList.get(i).getEqpId          ());
						writeBean.setGlassId        (inList.get(i).getGlassId        ());
						writeBean.setCellId         (inList.get(i).getCellId         ());
						writeBean.setInspectDt      (inList.get(i).getInspectDt      ());
						writeBean.setCellLocId      (inList.get(i).getCellLocId      ());
						writeBean.setDefectGroupCode(inList.get(i).getDefectGroupCode());
						writeBean.setDecisionCode   (inList.get(i).getDecisionCode   ());
						writeBean.setInspectorId    (inList.get(i).getInspectorId    ());

						outList.add(writeBean);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * afterMacroJob
	 */
	public void afterMacroJob()
	{
		if (Flag.TRUE) Logger.info("afterMacroJob : " + this.getClass());
		
		if (Flag.TRUE) {
			try {
				
				INSPCT_HIST_Main.cntWList = outList.size();

				writer.writeList();

				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
