package sdc.anal.mura.macro.A80.ANAL_PARAM80.v01;

import java.util.List;

import kiea.kr.co.tain.base.Flag;
import kiea.proj.sdc.anal.macro.impl.job.AbstractMacroJob;
import kiea.proj.sdc.anal.util.BasePath;
import kiea.proj.sdc.anal.util.FileUtil;
import kiea.proj.sdc.anal.util.StrUtil;
import kiea.proj.sdc.anal.util.log.v02.Logger;

public class ANAL_PARAM80_MacroJob extends AbstractMacroJob
{
	private ANAL_PARAM20_CsvIo reader1 = null;
	private ANAL_PARAM80_CsvIo writer1 = null;
	
	private List<ANAL_PARAM20_Bean> inList1  = null;
	private List<ANAL_PARAM80_Bean> outList1 = null;
	
	private String filePath = null;

	private String jobId = null;

	public ANAL_PARAM80_MacroJob(String jobId)
	{
		this.jobId = jobId;
		
		this.filePath = FileUtil.makeDataDir(BasePath.getInstance().getDataPath(), StrUtil.getDateFromJobId(this.jobId), this.jobId);
	}
	
	/**
	 * generateDataSet
	 */
	public void generateDataSet()
	{
	}

	/**
	 * beforeMacroJob
	 */
	public void beforeMacroJob()
	{
		if (Flag.TRUE) Logger.info("beforeMacroJob : " + this.getClass());
		
		if (Flag.TRUE) {
			try {
				reader1 = new ANAL_PARAM20_CsvIo(this.filePath);
				writer1 = new ANAL_PARAM80_CsvIo(this.filePath);
				
				inList1  = reader1.getList(true);
				outList1 = writer1.getList();
				
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
				 */
				if (Flag.TRUE) {
					/*
					 * 해당자료만 추출한다.
					 */
					for (ANAL_PARAM20_Bean inBean1 : inList1) {
						
						ANAL_PARAM80_Bean outBean1 = new ANAL_PARAM80_Bean();

						outBean1.setJobId   (inBean1.getJobId   ());
						outBean1.setSeq     (inBean1.getSeq     ());
						outBean1.setParamNm (inBean1.getParamNm ());
						outBean1.setParamVal(inBean1.getParamVal());
						outBean1.setRegDt   (inBean1.getRegDt   ());

						outList1.add(outBean1);
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
				
				writer1.writeList();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
