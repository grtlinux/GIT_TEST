package kiea.proj.sdc.anal.macro.sample.a11.img.macro.CONTACT_MUR_DIST_4SF_RESULT;

import java.util.List;

import kiea.kr.co.tain.base.Flag;
import kiea.kr.co.tain.util.DateTime;
import kiea.proj.sdc.anal.macro.impl.job.AbstractMacroJob;
import kiea.proj.sdc.anal.util.BasePath;
import kiea.proj.sdc.anal.util.FileUtil;
import kiea.proj.sdc.anal.util.log.v02.Logger;

@SuppressWarnings("unused")
public class CONTACT_MUR_DIST_4SF_RESULT_MacroJob extends AbstractMacroJob
{
	private CONTACT_MURA_DIST_RESULT_CsvIo reader1 = null;
	private CONTACT_MUR_DIST_4SF_RESULT_CsvIo writer1 = null;
	
	private List<CONTACT_MURA_DIST_RESULT_Bean> inList1  = null;
	private List<CONTACT_MUR_DIST_4SF_RESULT_Bean> outList1 = null;
	
	private String strFromDateTime    = null;
	private String strToDateTime      = null;
	private String strLine            = null;
	private String strAreaCode        = null;
	private String strUserGroupCode   = null;
	private String strDefectGroupCode = null;
	
	private String filePath = null;

	private String jobKeyPath = null;
	private String dsName = null;

	public CONTACT_MUR_DIST_4SF_RESULT_MacroJob(String jobKeyPath, String dsName)
	{
		this.jobKeyPath = jobKeyPath;
		this.dsName = dsName;
		
		this.filePath = FileUtil.makeDataDir(BasePath.getInstance().getDataPath(), DateTime.getDate(2), this.jobKeyPath);
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
				reader1 = new CONTACT_MURA_DIST_RESULT_CsvIo(this.filePath);
				writer1 = new CONTACT_MUR_DIST_4SF_RESULT_CsvIo(this.filePath);
				
				inList1  = reader1.getList();
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
					for (CONTACT_MURA_DIST_RESULT_Bean inBean1 : inList1) {

						if (Flag.TRUE) {
							CONTACT_MUR_DIST_4SF_RESULT_Bean outBean1 = new CONTACT_MUR_DIST_4SF_RESULT_Bean();

							//outBean1.setDataNum    (inBean1.getDataNum    ());
							outBean1.setDataNum    ("DATA_NUM");
							outBean1.setSeq        ("1");
							outBean1.setClusterNo  (inBean1.getClusterNo  ());
							outBean1.setSiteId     (inBean1.getSiteId     ());
							outBean1.setProdGrpName(inBean1.getProdGrpName());
							outBean1.setProcId     (inBean1.getProcId     ());
							outBean1.setItemId     (inBean1.getItemId     ());
							outBean1.setItemName   (inBean1.getItemName   ());
							outBean1.setCellNo     (inBean1.getCellNo     ());
							outBean1.setCellLocId  (inBean1.getCellLocId  ());
							outBean1.setEqpName    (inBean1.getEqpName    ());
							outBean1.setPart       (inBean1.getPart       ());
							outBean1.setMaker      (inBean1.getMaker      ());
							outBean1.setUnitName   (inBean1.getUnitName   ());
							outBean1.setContactMap (inBean1.getContactMap ());
							outBean1.setContactX1  (inBean1.getContactX1  ());
							outBean1.setContactY1  (inBean1.getContactY1  ());
							outBean1.setWidth      (inBean1.getWidth      ());
							outBean1.setHeight     (inBean1.getHeight     ());

							outList1.add(outBean1);
							
						}

						if (Flag.TRUE) {
							CONTACT_MUR_DIST_4SF_RESULT_Bean outBean1 = new CONTACT_MUR_DIST_4SF_RESULT_Bean();

							//outBean1.setDataNum    (inBean1.getDataNum    ());
							outBean1.setDataNum    ("DATA_NUM");
							outBean1.setSeq        ("2");
							outBean1.setClusterNo  (inBean1.getClusterNo  ());
							outBean1.setSiteId     (inBean1.getSiteId     ());
							outBean1.setProdGrpName(inBean1.getProdGrpName());
							outBean1.setProcId     (inBean1.getProcId     ());
							outBean1.setItemId     (inBean1.getItemId     ());
							outBean1.setItemName   (inBean1.getItemName   ());
							outBean1.setCellNo     (inBean1.getCellNo     ());
							outBean1.setCellLocId  (inBean1.getCellLocId  ());
							outBean1.setEqpName    (inBean1.getEqpName    ());
							outBean1.setPart       (inBean1.getPart       ());
							outBean1.setMaker      (inBean1.getMaker      ());
							outBean1.setUnitName   (inBean1.getUnitName   ());
							outBean1.setContactMap (inBean1.getContactMap ());
							outBean1.setContactX1  ("" + (Double.parseDouble(inBean1.getContactX1()) + Double.parseDouble(inBean1.getWidth ())));
							outBean1.setContactY1  (inBean1.getContactY1  ());
							outBean1.setWidth      (inBean1.getWidth      ());
							outBean1.setHeight     (inBean1.getHeight     ());

							outList1.add(outBean1);
							
						}

						if (Flag.TRUE) {
							CONTACT_MUR_DIST_4SF_RESULT_Bean outBean1 = new CONTACT_MUR_DIST_4SF_RESULT_Bean();

							//outBean1.setDataNum    (inBean1.getDataNum    ());
							outBean1.setDataNum    ("DATA_NUM");
							outBean1.setSeq        ("3");
							outBean1.setClusterNo  (inBean1.getClusterNo  ());
							outBean1.setSiteId     (inBean1.getSiteId     ());
							outBean1.setProdGrpName(inBean1.getProdGrpName());
							outBean1.setProcId     (inBean1.getProcId     ());
							outBean1.setItemId     (inBean1.getItemId     ());
							outBean1.setItemName   (inBean1.getItemName   ());
							outBean1.setCellNo     (inBean1.getCellNo     ());
							outBean1.setCellLocId  (inBean1.getCellLocId  ());
							outBean1.setEqpName    (inBean1.getEqpName    ());
							outBean1.setPart       (inBean1.getPart       ());
							outBean1.setMaker      (inBean1.getMaker      ());
							outBean1.setUnitName   (inBean1.getUnitName   ());
							outBean1.setContactMap (inBean1.getContactMap ());
							outBean1.setContactX1  ("" + (Double.parseDouble(inBean1.getContactX1()) + Double.parseDouble(inBean1.getWidth ())));
							outBean1.setContactY1  ("" + (Double.parseDouble(inBean1.getContactY1()) + Double.parseDouble(inBean1.getHeight())));
							outBean1.setWidth      (inBean1.getWidth      ());
							outBean1.setHeight     (inBean1.getHeight     ());

							outList1.add(outBean1);
							
						}

						if (Flag.TRUE) {
							CONTACT_MUR_DIST_4SF_RESULT_Bean outBean1 = new CONTACT_MUR_DIST_4SF_RESULT_Bean();

							//outBean1.setDataNum    (inBean1.getDataNum    ());
							outBean1.setDataNum    ("DATA_NUM");
							outBean1.setSeq        ("4");
							outBean1.setClusterNo  (inBean1.getClusterNo  ());
							outBean1.setSiteId     (inBean1.getSiteId     ());
							outBean1.setProdGrpName(inBean1.getProdGrpName());
							outBean1.setProcId     (inBean1.getProcId     ());
							outBean1.setItemId     (inBean1.getItemId     ());
							outBean1.setItemName   (inBean1.getItemName   ());
							outBean1.setCellNo     (inBean1.getCellNo     ());
							outBean1.setCellLocId  (inBean1.getCellLocId  ());
							outBean1.setEqpName    (inBean1.getEqpName    ());
							outBean1.setPart       (inBean1.getPart       ());
							outBean1.setMaker      (inBean1.getMaker      ());
							outBean1.setUnitName   (inBean1.getUnitName   ());
							outBean1.setContactMap (inBean1.getContactMap ());
							outBean1.setContactX1  (inBean1.getContactX1  ());
							outBean1.setContactY1  ("" + (Double.parseDouble(inBean1.getContactY1()) + Double.parseDouble(inBean1.getHeight())));
							outBean1.setWidth      (inBean1.getWidth      ());
							outBean1.setHeight     (inBean1.getHeight     ());

							outList1.add(outBean1);
							
						}

						if (Flag.TRUE) {
							CONTACT_MUR_DIST_4SF_RESULT_Bean outBean1 = new CONTACT_MUR_DIST_4SF_RESULT_Bean();

							//outBean1.setDataNum    (inBean1.getDataNum    ());
							outBean1.setDataNum    ("DATA_NUM");
							outBean1.setSeq        ("5");
							outBean1.setClusterNo  (inBean1.getClusterNo  ());
							outBean1.setSiteId     (inBean1.getSiteId     ());
							outBean1.setProdGrpName(inBean1.getProdGrpName());
							outBean1.setProcId     (inBean1.getProcId     ());
							outBean1.setItemId     (inBean1.getItemId     ());
							outBean1.setItemName   (inBean1.getItemName   ());
							outBean1.setCellNo     (inBean1.getCellNo     ());
							outBean1.setCellLocId  (inBean1.getCellLocId  ());
							outBean1.setEqpName    (inBean1.getEqpName    ());
							outBean1.setPart       (inBean1.getPart       ());
							outBean1.setMaker      (inBean1.getMaker      ());
							outBean1.setUnitName   (inBean1.getUnitName   ());
							outBean1.setContactMap (inBean1.getContactMap ());
							outBean1.setContactX1  (inBean1.getContactX1  ());
							outBean1.setContactY1  (inBean1.getContactY1  ());
							outBean1.setWidth      (inBean1.getWidth      ());
							outBean1.setHeight     (inBean1.getHeight     ());

							outList1.add(outBean1);
							
						}

						if (Flag.TRUE) {
							CONTACT_MUR_DIST_4SF_RESULT_Bean outBean1 = new CONTACT_MUR_DIST_4SF_RESULT_Bean();

							//outBean1.setDataNum    (inBean1.getDataNum    ());
							outBean1.setDataNum    ("DATA_NUM");
							outBean1.setSeq        ("6");
							outBean1.setClusterNo  (inBean1.getClusterNo  ());
							outBean1.setSiteId     (inBean1.getSiteId     ());
							outBean1.setProdGrpName(inBean1.getProdGrpName());
							outBean1.setProcId     (inBean1.getProcId     ());
							outBean1.setItemId     (inBean1.getItemId     ());
							outBean1.setItemName   (inBean1.getItemName   ());
							outBean1.setCellNo     (inBean1.getCellNo     ());
							outBean1.setCellLocId  (inBean1.getCellLocId  ());
							outBean1.setEqpName    (inBean1.getEqpName    ());
							outBean1.setPart       (inBean1.getPart       ());
							outBean1.setMaker      (inBean1.getMaker      ());
							outBean1.setUnitName   (inBean1.getUnitName   ());
							outBean1.setContactMap (inBean1.getContactMap ());
							outBean1.setContactX1  ("");
							outBean1.setContactY1  ("");
							outBean1.setWidth      (inBean1.getWidth      ());
							outBean1.setHeight     (inBean1.getHeight     ());

							outList1.add(outBean1);
							
						}
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
