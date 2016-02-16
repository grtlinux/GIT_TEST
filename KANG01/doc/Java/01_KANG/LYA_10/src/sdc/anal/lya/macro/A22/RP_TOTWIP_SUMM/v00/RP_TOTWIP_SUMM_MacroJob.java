package sdc.anal.lya.macro.A22.RP_TOTWIP_SUMM.v00;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import kiea.kr.co.tain.base.Flag;
import kiea.kr.co.tain.util.Print;
import kiea.proj.sdc.anal.macro.impl.job.AbstractMacroJob;
import kiea.proj.sdc.anal.util.BasePath;
import kiea.proj.sdc.anal.util.FileUtil;
import kiea.proj.sdc.anal.util.StrUtil;
import kiea.proj.sdc.anal.util.log.v02.Logger;

public class RP_TOTWIP_SUMM_MacroJob extends AbstractMacroJob
{
	private SO_SYS_CELLID_LIST2_CsvIo reader1 = null;
	private WIP_EQP_SMMRY_CsvIo reader2 = null;
	private RP_TOTWIP_SUMM_CsvIo writer1 = null;
	
	private List<SO_SYS_CELLID_LIST2_Bean> inList1  = null;
	private List<WIP_EQP_SMMRY_Bean> inList2  = null;
	private List<RP_TOTWIP_SUMM_Bean> outList1 = null;
	
	private String filePath = null;

	private String jobId = null;

	public class ClusterCnt
	{
		private String clusterId;
		private int totBadCnt;
		private int totGoodCnt;
		public void addTotBadCnt(int badCnt)
		{
			totBadCnt += badCnt;
		}
		public void addTotGoodCnt(int goodCnt)
		{
			totGoodCnt += goodCnt;
		}
		public String getClusterId()
		{
			return clusterId;
		}
		public void setClusterId(String clusterId)
		{
			this.clusterId = clusterId;
		}
		public int getTotBadCnt()
		{
			return totBadCnt;
		}
		public int getTotGoodCnt()
		{
			return totGoodCnt;
		}
		public void setTotBadCnt(int totBadCnt)
		{
			this.totBadCnt = totBadCnt;
		}
		public void setTotGoodCnt(int totGoodCnt)
		{
			this.totGoodCnt = totGoodCnt;
		}
	}
	
	public RP_TOTWIP_SUMM_MacroJob(String jobId)
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
				reader1 = new SO_SYS_CELLID_LIST2_CsvIo(this.filePath);
				reader2 = new WIP_EQP_SMMRY_CsvIo(this.filePath);
				writer1 = new RP_TOTWIP_SUMM_CsvIo(this.filePath);
				
				inList1  = reader1.getList(true);
				inList2  = reader2.getList(true);
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
				Map<String, List<SO_SYS_CELLID_LIST2_Bean>> mapGlassList = new LinkedHashMap<String, List<SO_SYS_CELLID_LIST2_Bean>>(); // DISTINCT
				Map<String, RP_TOTWIP_SUMM_Bean> mapWip = new LinkedHashMap<String, RP_TOTWIP_SUMM_Bean>();
				Map<String, ClusterCnt> mapCluster = new LinkedHashMap<String, ClusterCnt>();
				
				if (Flag.TRUE) {
					Map<String, SO_SYS_CELLID_LIST2_Bean> map = new LinkedHashMap<String, SO_SYS_CELLID_LIST2_Bean>();
					
					for (SO_SYS_CELLID_LIST2_Bean bean : inList1) {
						/*
						 * DISTINCT
						 */
						String key = bean.getGlassId() + ":" + bean.getClusterId();
						map.put(key, bean);
					}
					
					for (Map.Entry<String, SO_SYS_CELLID_LIST2_Bean> entry : map.entrySet()) {
						/*
						 * SET GROUP
						 */
						SO_SYS_CELLID_LIST2_Bean bean = entry.getValue();
						
						List<SO_SYS_CELLID_LIST2_Bean> list = mapGlassList.get(bean.getGlassId());
						if (list == null) {
							list = new ArrayList<SO_SYS_CELLID_LIST2_Bean>();
							mapGlassList.put(bean.getGlassId(), list);
						}
						
						bean.setCellId("");
						bean.setDefectSeq("");
						
						list.add(bean);
					}
					
					if (!Flag.TRUE) {
						/*
						 * 확인출력
						 */
						for (Map.Entry<String, List<SO_SYS_CELLID_LIST2_Bean>> entry : mapGlassList.entrySet()) {
							String glassId = entry.getKey();
							List<SO_SYS_CELLID_LIST2_Bean> list = entry.getValue();
							
							Print.println("[%s]", glassId);
							for (SO_SYS_CELLID_LIST2_Bean bean : list) {
								Print.println("\t%s", bean);
							}
						}
					}
				}
				
				if (Flag.TRUE) {
					/*
					 * 
					 */
					for (WIP_EQP_SMMRY_Bean inBean2 : inList2) {
						
						List<SO_SYS_CELLID_LIST2_Bean> list = mapGlassList.get(inBean2.getGlassId());
						if (list == null)
							continue;
						
						for (SO_SYS_CELLID_LIST2_Bean bean : list) {
							String key = inBean2.getStepId() + ":" + inBean2.getEqpId() + ":" + bean.getClusterId();
							
							RP_TOTWIP_SUMM_Bean outBean1 = mapWip.get(key);
							if (outBean1 == null) {
								outBean1 = new RP_TOTWIP_SUMM_Bean();
								
								outBean1.setStepId   (inBean2.getStepId());
								outBean1.setEqpId    (inBean2.getEqpId ());
								outBean1.setClusterId(bean.getClusterId());
								outBean1.setBadCnt   ("0");
								outBean1.setGoodCnt  ("0");
								
								mapWip.put(key, outBean1);
							}
							
							if ("BAD".equals(bean.getGroup())) {
								outBean1.addBadCnt();
							} else {
								outBean1.addGoodCnt();
							}
						}
					}
					
					if (!Flag.TRUE) {
						/*
						 * 확인출력
						 */
						for (Map.Entry<String, RP_TOTWIP_SUMM_Bean> entry : mapWip.entrySet()) {
							System.out.println(entry.getValue());
						}
					}
				}
				
				if (Flag.TRUE) {
					/*
					 * ClusterID별 TOT_BAD_CNT, TOT_GOOD_CNT를 구한다.
					 */
					for (Map.Entry<String, RP_TOTWIP_SUMM_Bean> entry : mapWip.entrySet()) {
						RP_TOTWIP_SUMM_Bean outBean1 = entry.getValue();
						
						ClusterCnt clusterCnt = mapCluster.get(outBean1.getClusterId());
						if (clusterCnt == null) {
							clusterCnt = new ClusterCnt();
							
							clusterCnt.setTotBadCnt (0);
							clusterCnt.setTotGoodCnt(0);
							
							mapCluster.put(outBean1.getClusterId(), clusterCnt);
						}
						
						clusterCnt.addTotBadCnt (Integer.parseInt(outBean1.getBadCnt ()));
						clusterCnt.addTotGoodCnt(Integer.parseInt(outBean1.getGoodCnt()));
					}
					
					/*
					 * UPDATE
					 */
					for (Map.Entry<String, RP_TOTWIP_SUMM_Bean> entry : mapWip.entrySet()) {
						RP_TOTWIP_SUMM_Bean outBean1 = entry.getValue();
						
						ClusterCnt clusterCnt = mapCluster.get(outBean1.getClusterId());
						double totBadCnt = (double) clusterCnt.getTotBadCnt();
						double totGoodCnt = (double) clusterCnt.getTotGoodCnt();
						
						double badRatio = Double.parseDouble(outBean1.getBadCnt()) / totBadCnt;
						double goodRatio = Double.parseDouble(outBean1.getGoodCnt()) / totGoodCnt;
						
						double bgRatio = badRatio - goodRatio;
						
						outBean1.setBGRatio("" + bgRatio);
						outBean1.setBadRatio("" + badRatio);
						outBean1.setGoodRatio("" + goodRatio);
						outBean1.setTotBadCnt("" + clusterCnt.getTotBadCnt());
						outBean1.setTotGoodCnt("" + clusterCnt.getTotGoodCnt());
						
						if (bgRatio > 0)
							outList1.add(outBean1);
					}
					
					if (!Flag.TRUE) {
						/*
						 * 확인
						 */
						for (Map.Entry<String, RP_TOTWIP_SUMM_Bean> entry : mapWip.entrySet()) {
							System.out.println(entry.getValue());
						}
					}
				}
				
				if (Flag.TRUE) {
					/*
					 * BGRATIO 내림차순 RANK를 구한다.
					 */
					Collections.sort(outList1, new Comparator<RP_TOTWIP_SUMM_Bean>() {
						@Override
						public int compare(RP_TOTWIP_SUMM_Bean bean1, RP_TOTWIP_SUMM_Bean bean2) {
							double d1 = Double.parseDouble(bean1.getBGRatio());
							double d2 = Double.parseDouble(bean2.getBGRatio());
							
							if (d1 < d2) return 1;
							if (d1 > d2) return -1;
							return 0;
						}
					});
					
					Map<String, String> mapRank = new LinkedHashMap<String, String>();

					for (RP_TOTWIP_SUMM_Bean outBean1 : outList1) {
						
						String key = outBean1.getClusterId();
						String rank = mapRank.get(key);
						if (rank == null) {
							rank = new String("0");
						}
						
						rank = "" + (Integer.parseInt(rank) + 1);
						mapRank.put(key, rank);
						
						outBean1.setRank(rank);
					}
					
					if (!Flag.TRUE) {
						/*
						 * 확인
						 */
						for (RP_TOTWIP_SUMM_Bean outBean1 : outList1) {
							System.out.println(outBean1);
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void macroJob_OLD_20141028()
	{
		if (Flag.TRUE) Logger.info("macroJob : " + this.getClass());

		if (Flag.TRUE) {
			try {
				/*
				 * Job
				 */
				Map<String, SO_SYS_CELLID_LIST2_Bean> map1 = new LinkedHashMap<String, SO_SYS_CELLID_LIST2_Bean>();
				Map<String, RP_TOTWIP_SUMM_Bean> map2 = new LinkedHashMap<String, RP_TOTWIP_SUMM_Bean>();
				Map<String, ClusterCnt> map3 = new LinkedHashMap<String, ClusterCnt>();

				if (Flag.TRUE) {
					/*
					 * SO_SYS_CELLID_LIST2_Bean map구성
					 */
					for (SO_SYS_CELLID_LIST2_Bean inBean1 : inList1) {
						map1.put(inBean1.getGlassId(), inBean1);
					}
				}
				
				if (Flag.TRUE) {
					/*
					 * RP_TOTWIP_SUMM_Bean map의 BadCnt, GoodCnt 값을 세팅한다.
					 */
					for (WIP_EQP_SMMRY_Bean inBean2 : inList2) {
						SO_SYS_CELLID_LIST2_Bean inBean1 = map1.get(inBean2.getGlassId());
						String key = inBean1.getClusterId() + ":" + inBean2.getEqpId();
						
						RP_TOTWIP_SUMM_Bean outBean1 = map2.get(key);
						if (outBean1 == null) {
							outBean1 = new RP_TOTWIP_SUMM_Bean();
							
							outBean1.setStepId   (inBean2.getStepId   ());
							outBean1.setEqpId    (inBean2.getEqpId    ());
							outBean1.setClusterId(inBean1.getClusterId());
							outBean1.setBadCnt   ("0");
							outBean1.setGoodCnt  ("0");
							
							map2.put(key, outBean1);
						}
						
						if ("BAD".equals(inBean1.getGroup())) {
							outBean1.addBadCnt();
						} else {
							outBean1.addGoodCnt();
						}
					}
					
					if (!Flag.TRUE) {
						/*
						 * 확인
						 */
						for (Map.Entry<String, RP_TOTWIP_SUMM_Bean> entry : map2.entrySet()) {
							System.out.println(entry.getValue());
						}
					}
				}
				
				if (Flag.TRUE) {
					/*
					 * ClusterID별 TOT_BAD_CNT, TOT_GOOD_CNT를 구한다.
					 */
					for (Map.Entry<String, RP_TOTWIP_SUMM_Bean> entry : map2.entrySet()) {
						RP_TOTWIP_SUMM_Bean outBean1 = entry.getValue();
						
						ClusterCnt clusterCnt = map3.get(outBean1.getClusterId());
						if (clusterCnt == null) {
							clusterCnt = new ClusterCnt();
							
							clusterCnt.setTotBadCnt (0);
							clusterCnt.setTotGoodCnt(0);
							
							map3.put(outBean1.getClusterId(), clusterCnt);
						}
						
						clusterCnt.addTotBadCnt (Integer.parseInt(outBean1.getBadCnt ()));
						clusterCnt.addTotGoodCnt(Integer.parseInt(outBean1.getGoodCnt()));
					}
					
					/*
					 * UPDATE
					 */
					for (Map.Entry<String, RP_TOTWIP_SUMM_Bean> entry : map2.entrySet()) {
						RP_TOTWIP_SUMM_Bean outBean1 = entry.getValue();
						
						ClusterCnt clusterCnt = map3.get(outBean1.getClusterId());
						double totBadCnt = (double) clusterCnt.getTotBadCnt();
						double totGoodCnt = (double) clusterCnt.getTotGoodCnt();
						
						double badRatio = Double.parseDouble(outBean1.getBadCnt()) / totBadCnt;
						double goodRatio = Double.parseDouble(outBean1.getGoodCnt()) / totGoodCnt;
						
						double bgRatio = badRatio - goodRatio;
						
						outBean1.setBGRatio("" + bgRatio);
						outBean1.setBadRatio("" + badRatio);
						outBean1.setGoodRatio("" + goodRatio);
						outBean1.setTotBadCnt("" + clusterCnt.getTotBadCnt());
						outBean1.setTotGoodCnt("" + clusterCnt.getTotGoodCnt());
						
						outList1.add(outBean1);
					}
					
					if (!Flag.TRUE) {
						/*
						 * 확인출력
						 */
						for (Map.Entry<String, RP_TOTWIP_SUMM_Bean> entry : map2.entrySet()) {
							System.out.println(entry.getValue());
						}
					}
				}
				
				if (Flag.TRUE) {
					/*
					 * BGRATIO 내림차순 RANK를 구한다.
					 */
					Collections.sort(outList1, new Comparator<RP_TOTWIP_SUMM_Bean>() {
						@Override
						public int compare(RP_TOTWIP_SUMM_Bean bean1, RP_TOTWIP_SUMM_Bean bean2) {
							double d1 = Double.parseDouble(bean1.getBGRatio());
							double d2 = Double.parseDouble(bean2.getBGRatio());
							
							if (d1 < d2) return 1;
							if (d1 > d2) return -1;
							return 0;
						}
					});
					
					int rank = 0;
					for (RP_TOTWIP_SUMM_Bean outBean1 : outList1) {
						rank++;
						outBean1.setRank("" + rank);
					}
					
					if (!Flag.TRUE) {
						/*
						 * 확인출력
						 */
						for (RP_TOTWIP_SUMM_Bean outBean1 : outList1) {
							System.out.println(outBean1);
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
