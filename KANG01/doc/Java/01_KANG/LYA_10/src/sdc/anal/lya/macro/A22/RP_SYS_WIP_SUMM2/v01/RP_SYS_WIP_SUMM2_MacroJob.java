package sdc.anal.lya.macro.A22.RP_SYS_WIP_SUMM2.v01;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import kiea.kr.co.tain.base.Flag;
import kiea.proj.sdc.anal.macro.impl.job.AbstractMacroJob;
import kiea.proj.sdc.anal.util.BasePath;
import kiea.proj.sdc.anal.util.FileUtil;
import kiea.proj.sdc.anal.util.StrUtil;
import kiea.proj.sdc.anal.util.log.v02.Logger;

public class RP_SYS_WIP_SUMM2_MacroJob extends AbstractMacroJob
{
	private SO_SYS_CELLID_LIST2_CsvIo reader1 = null;
	private WIP_EQP_SMMRY_CsvIo reader2 = null;
	private STEP_DESC_CsvIo reader3 = null;
	private RP_SYS_WIP_SUMM2_CsvIo writer1 = null;
	
	private List<SO_SYS_CELLID_LIST2_Bean> inList1  = null;
	private List<WIP_EQP_SMMRY_Bean> inList2  = null;
	private List<STEP_DESC_Bean> inList3  = null;
	private List<RP_SYS_WIP_SUMM2_Bean> outList1 = null;
	
	private String filePath = null;

	private String jobId = null;

	public RP_SYS_WIP_SUMM2_MacroJob(String jobId)
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
				reader3 = new STEP_DESC_CsvIo(this.filePath);
				writer1 = new RP_SYS_WIP_SUMM2_CsvIo(this.filePath);
				
				inList1  = reader1.getList(true);
				inList2  = reader2.getList(true);
				inList3  = reader3.getList(true);
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
				
				Map<String, STEP_DESC_Bean> mapDesc = new LinkedHashMap<String, STEP_DESC_Bean>();
				Map<String, List<SO_SYS_CELLID_LIST2_Bean>> mapListGlass = new LinkedHashMap<String, List<SO_SYS_CELLID_LIST2_Bean>>();
				Map<String, RP_SYS_WIP_SUMM2_Bean> mapWip = new LinkedHashMap<String, RP_SYS_WIP_SUMM2_Bean>();
				
				if (Flag.TRUE) {
					/*
					 * STEP_DESC
					 */
					for (STEP_DESC_Bean inBean3 : inList3) {
						mapDesc.put(inBean3.getImptStepGrpId(), inBean3);
					}
					
					if (!Flag.TRUE) {
						/*
						 * 확인출력
						 */
						for (Map.Entry<String, STEP_DESC_Bean> entry : mapDesc.entrySet()) {
							System.out.println(entry.getValue());
						}
					}
				}
				
				if (Flag.TRUE) {
					/*
					 * SO_SYS_CELLID_LIST2
					 */
					Map<String, SO_SYS_CELLID_LIST2_Bean> mapGlass = new LinkedHashMap<String, SO_SYS_CELLID_LIST2_Bean>();

					for (SO_SYS_CELLID_LIST2_Bean inBean1 : inList1) {
						String key = inBean1.getClusterId() + ":" + inBean1.getGlassId();
						mapGlass.put(key, inBean1);
					}
					
					for (Map.Entry<String, SO_SYS_CELLID_LIST2_Bean> entry : mapGlass.entrySet()) {
						SO_SYS_CELLID_LIST2_Bean inBean1 = entry.getValue();
						if (!Flag.TRUE) System.out.println(inBean1);
						
						List<SO_SYS_CELLID_LIST2_Bean> listGlass = mapListGlass.get(inBean1.getGlassId());
						if (listGlass == null) {
							listGlass = new ArrayList<SO_SYS_CELLID_LIST2_Bean>();
							mapListGlass.put(inBean1.getGlassId(), listGlass);
						}
						
						listGlass.add(inBean1);
					}

					if (!Flag.TRUE) {
						/*
						 * 확인출력
						 */
						for (Map.Entry<String, List<SO_SYS_CELLID_LIST2_Bean>> listEntry : mapListGlass.entrySet()) {
							System.out.println(listEntry.getKey());
							List<SO_SYS_CELLID_LIST2_Bean> listBean = listEntry.getValue();
							for (SO_SYS_CELLID_LIST2_Bean bean : listBean) {
								System.out.println("\t" + bean);
							}
						}
					}
				}
				
				if (Flag.TRUE) {
					/*
					 * WIP_EQP_SMMRY 중복제거
					 */
				}

				if (Flag.TRUE) {
					/*
					 * WIP_EQP_SMMRY 누적처리
					 */
					for (WIP_EQP_SMMRY_Bean inBean2 : inList2) {
						
						List<SO_SYS_CELLID_LIST2_Bean> listGlass = mapListGlass.get(inBean2.getGlassId());
						if (listGlass == null)
							continue;

						for (SO_SYS_CELLID_LIST2_Bean inBean1 : listGlass) {
							
							if (Flag.TRUE) {
								/*
								 * TOTAL CNT
								 */
								//String keyTotal = inBean1.getClusterId() + ":" + inBean2.getStepId();
								String keyTotal = inBean1.getClusterId();
								
								RP_SYS_WIP_SUMM2_Bean bean = mapWip.get(keyTotal);
								if (bean == null) {
									bean = new RP_SYS_WIP_SUMM2_Bean();
									
									bean.setTotBadCnt("0");
									bean.setTotGoodCnt("0");
									
									mapWip.put(keyTotal, bean);
								}
								
								if ("BAD".equals(inBean1.getGroup())) {
									bean.addTotBadCnt();
								} else {
									bean.addTotGoodCnt();
								}
							}
							
							if (Flag.TRUE) {
								/*
								 * CNT
								 */
								String keyCnt = inBean1.getClusterId() + ":" + inBean2.getStepId() + ":" + inBean2.getEqpId();
								
								RP_SYS_WIP_SUMM2_Bean bean = mapWip.get(keyCnt);
								if (bean == null) {
									bean = new RP_SYS_WIP_SUMM2_Bean();
									
									bean.setDivCode("EQP_ID");
									bean.setClusterId(inBean1.getClusterId());
									bean.setStepId(inBean2.getStepId());
									STEP_DESC_Bean beanDesc = mapDesc.get(inBean2.getStepId());
									if (beanDesc == null) {
										bean.setStepDesc("");
									} else {
										bean.setStepDesc(beanDesc.getStepDesc());
									}
									bean.setSuspiciousEqp(inBean2.getEqpId());
									bean.setBadCnt ("0");
									bean.setGoodCnt("0");
									
									mapWip.put(keyCnt, bean);
								}
								
								if ("BAD".equals(inBean1.getGroup())) {
									bean.addBadCnt();
								} else {
									bean.addGoodCnt();
								}
							}
						}
					}
					
					if (!Flag.TRUE) {
						/*
						 * 확인출력
						 */
						for (Map.Entry<String, RP_SYS_WIP_SUMM2_Bean> entry : mapWip.entrySet()) {
							RP_SYS_WIP_SUMM2_Bean bean = entry.getValue();
							if (bean.getDivCode() == null) {
								if (!Flag.TRUE) System.out.println("TOTAL : " + bean);
							} else {
								if (Flag.TRUE) System.out.println("\tCNT : " + bean);
							}
						}
					}
				}
				
				if (Flag.TRUE) {
					/*
					 * RP_SYS_WIP_SUMM2 CNT만 처리
					 */
					for (Map.Entry<String, RP_SYS_WIP_SUMM2_Bean> entry : mapWip.entrySet()) {
						
						RP_SYS_WIP_SUMM2_Bean bean = entry.getValue();
						
						if (bean.getDivCode() != null) {
							if (!Flag.TRUE) System.out.println("\tCNT : " + bean);
							
							outList1.add(bean);
						}
					}
					
					/*
					 * RP_SYS_WIP_SUMM2 TOTAL 값 처리
					 */
					for (RP_SYS_WIP_SUMM2_Bean outBean1 : outList1) {
						
						//RP_SYS_WIP_SUMM2_Bean bean = mapWip.get(outBean1.getClusterId() + ":" + outBean1.getStepId());
						RP_SYS_WIP_SUMM2_Bean bean = mapWip.get(outBean1.getClusterId());
						
						double ratioBad = Double.parseDouble(outBean1.getBadCnt()) * 100 / Double.parseDouble(bean.getTotBadCnt());
						double ratioGood = Double.parseDouble(outBean1.getGoodCnt()) * 100 / Double.parseDouble(bean.getTotGoodCnt());
						
						outBean1.setBadRatio  ("" + ratioBad);
						outBean1.setGoodRatio ("" + ratioGood);
						outBean1.setTotBadCnt (bean.getTotBadCnt());
						outBean1.setTotGoodCnt(bean.getTotGoodCnt());
					}
					
					if (!Flag.TRUE) {
						/*
						 * 확인출력
						 */
						for (RP_SYS_WIP_SUMM2_Bean bean : outList1) {
							System.out.println(bean);
						}
					}
				}
				
				if (Flag.TRUE) {
					/*
					 * SORT DESC BY BGRatio
					 */
					Collections.sort(outList1, new Comparator<RP_SYS_WIP_SUMM2_Bean>() {
						@Override
						public int compare(RP_SYS_WIP_SUMM2_Bean bean1, RP_SYS_WIP_SUMM2_Bean bean2) {
							int ret = 0;
							
							// 1. BGRatio
							double val1 = Double.parseDouble(bean1.getBadRatio());
							double val2 = Double.parseDouble(bean2.getBadRatio());
							
							double val = val2 - val1;
							if (val < 0)
								return -1;
							else if (val > 0)
								return 1;

							return ret;
						}
					});
					
					if (!Flag.TRUE) {
						/*
						 * 확인출력
						 */
						for (RP_SYS_WIP_SUMM2_Bean bean : outList1) {
							System.out.println(bean);
						}
					}
				}
				
				if (Flag.TRUE) {
					/*
					 * SET RANK BY CLUSTER
					 */
					Map<String, String> mapRank = new HashMap<String, String>(); // CLUSTER_ID, RANK
					
					for (RP_SYS_WIP_SUMM2_Bean bean : outList1) {
						
						String rank = mapRank.get(bean.getClusterId());
						if (rank == null) {
							rank = "0";
						}
						rank = "" + (Integer.parseInt(rank) + 1);
						
						bean.setRank(rank);
						
						mapRank.put(bean.getClusterId(), rank);
					}
					
					if (!Flag.TRUE) {
						/*
						 * 확인출력
						 */
						for (RP_SYS_WIP_SUMM2_Bean bean : outList1) {
							System.out.println(bean);
						}
					}
				}
				
				if (Flag.TRUE) {
					/*
					 * RANK 10 이상 삭제
					 */
					for (int i=outList1.size()-1; 0 <= i; i--) {
						int rank = Integer.parseInt(outList1.get(i).getRank());
						if (10 < rank) {
							outList1.remove(i);
						}
					}
					
					if (!Flag.TRUE) {
						/*
						 * 확인출력
						 */
						for (RP_SYS_WIP_SUMM2_Bean bean : outList1) {
							System.out.println(bean);
						}
					}
				}
				
				if (Flag.TRUE) {
					/*
					 * SORT
					 *     CLUSTER_ID
					 *     RANK
					 */
					Collections.sort(outList1, new Comparator<RP_SYS_WIP_SUMM2_Bean>() {
						@Override
						public int compare(RP_SYS_WIP_SUMM2_Bean bean1, RP_SYS_WIP_SUMM2_Bean bean2) {
							int ret = 0;
							
							int val;
							// 1. CLUSTER_ID
							val = Integer.parseInt(bean1.getClusterId()) - Integer.parseInt(bean2.getClusterId());
							if (val < 0)
								return -1;
							else if (val > 0)
								return 1;

							// 2. RANK
							val = Integer.parseInt(bean1.getRank()) - Integer.parseInt(bean2.getRank());
							if (val < 0)
								return -1;
							else if (val > 0)
								return 1;

							return ret;
						}
					});
					
					if (!Flag.TRUE) {
						/*
						 * 확인출력
						 */
						for (RP_SYS_WIP_SUMM2_Bean bean : outList1) {
							System.out.println(bean);
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
