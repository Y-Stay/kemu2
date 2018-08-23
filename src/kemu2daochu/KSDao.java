package kemu2daochu;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;



public class KSDao {
	public ArrayList<KS> getKSbyDate(String rq){
		ArrayList<KS> kss=new ArrayList<KS>();
		Connection conn=null;
		PreparedStatement pstat=null;
		PreparedStatement pstat1=null;
		ResultSet rs=null;
		ResultSet rs1=null;
		try {
			conn=jdbcUitl.getconn();
			String sql="Select k.id,k.xm,k.zjhm,j.jxjc,k.downkscs kscs,k.zwcjflag zw from t_ks k,ba_jx j where k.jxbm=j.jxdm(+) and k.ckykrq=? order by k.id";
			pstat=conn.prepareStatement(sql);
			pstat.setString(1, rq);
			rs=pstat.executeQuery();
			int i=1;
			
			while(rs.next()) {
				KS ks=new KS();
				ks.setXuhao(i);
				ks.setName(rs.getString("xm"));
				ks.setZjhm(rs.getString("zjhm"));
				ks.setJx(rs.getString("jxjc"));
				ks.setKscs(rs.getInt("kscs"));
				ks.setZw(rs.getInt("zw"));
				int ksid=rs.getInt("id");
				
				String sql1="Select zp from t_photo where ksid=?";
				pstat1=conn.prepareStatement(sql1);
				pstat1.setInt(1, ksid);
				rs1=pstat1.executeQuery();
				while(rs1.next()) {
					
					java.sql.Blob blob=(oracle.sql.BLOB)rs1.getBlob("zp");
					if(blob==null)
						ks.setZp(1);
					else ks.setZp(0);
				}
				kss.add(ks);
				i++;
			}
			return kss;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally {
			jdbcUitl.clos1(conn, pstat, rs);
			jdbcUitl.clos1(null, pstat1, rs1);
		}
		
	}
	public ArrayList<CJ> getCJbyDate(String rq){
		ArrayList<CJ> cjs=new ArrayList<CJ>();
		Connection conn=null;
		PreparedStatement pstat=null;
		ResultSet rs=null;
		
		PreparedStatement pstat1=null;
		ResultSet rs1=null;
		try {
			conn=jdbcUitl.getconn();
			String sql="select k.xm,k.zjhm,k.downkscs ykcs,x.jxjc,j.cj2 from t_ks k,t_ksjl j,ba_jx x where k.id=j.ksid and k.jxbm=x.jxdm(+) and j.rq1=? order by j.cj2 desc";
			pstat=conn.prepareStatement(sql);
			pstat.setString(1, rq);
			rs=pstat.executeQuery();
			int i=1;
			
			while(rs.next()) {
				CJ ks=new CJ();
				ks.setXuhao(i);
				ks.setName(rs.getString("xm"));
				ks.setZjhm(rs.getString("zjhm"));
				ks.setJx(rs.getString("jxjc"));
				
				String cj=null;
				int kscs=0;
				if(rs.getString("cj2")==null) {
					kscs=1;
					cj="合格";
				}else {
					kscs=2;
					cj=rs.getString("cj2");
				}
				ks.setCs(kscs);
				ks.setCj(cj);
				if(rs.getInt("ykcs")==0)
					ks.setBukao("");
				else ks.setBukao("B");
				cjs.add(ks);
				i++;
			}
			
			String sql1="select k.xm,k.zjhm,k.downkscs ykcs,b.jxjc from t_ks k ,ba_jx b where ckykrq=? and nvl(k.jx,'自主预约')=b.jxmc and not exists (select xm from t_ksjl where rq1=? and k.xm = t_ksjl.xm )";
			pstat1=conn.prepareStatement(sql1);
			pstat1.setString(1, rq);
			pstat1.setString(2, rq);
			rs1=pstat1.executeQuery();
			while(rs1.next()) {
				CJ ks=new CJ();
				ks.setXuhao(i);
				ks.setName(rs1.getString("xm"));
				ks.setZjhm(rs1.getString("zjhm"));
				ks.setJx(rs1.getString("jxjc"));
				if(rs1.getInt("ykcs")==0)
					ks.setBukao("");
				else ks.setBukao("B");
				ks.setCs(0);
				ks.setCj("缺考");
				cjs.add(ks);
				i++;
			}
			return cjs;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally {
			jdbcUitl.clos1(conn, pstat, rs);
			jdbcUitl.clos1(conn, pstat1, rs1);
		}
	}
	public Tongji getTongji(String jx ,String rq){
    	Connection conn=null;
    	PreparedStatement pstat=null;
    	ResultSet rs=null;
    	
    	Tongji tongji=new Tongji();
    	try {
			
				conn=jdbcUitl.getconn();
				
				String sql0="select count(j.ksid) zongji,sum(case when j.cj2='合格' or j.cj1='合格' then 1 else 0 end) hege,sum(case when j.cj2='不合格' then 1 else 0 end) buhege from t_ks k,t_ksjl j";
				StringBuilder sb= new StringBuilder(sql0);
				if(jx==null)
					sb.append(" where j.ksid=k.id and j.rq1=? and k.jxbm is null");
				else sb.append(",ba_jx b where j.ksid=k.id and k.jxbm=b.jxdm and j.rq1=? and b.jxjc=?");
				String sql=sb.toString();
				pstat=conn.prepareStatement(sql);				
				pstat.setString(1, rq);
				if(jx!=null)
				pstat.setString(2, jx);
				int i=0;
				rs=pstat.executeQuery();
				while(rs.next()) {
					double hegelv=0.0;
					tongji.setzongji(rs.getInt("hege")+rs.getInt("buhege"));
					tongji.setHege(rs.getInt("hege"));
					tongji.setBuhege(rs.getInt("buhege"));
					if(rs.getInt("zongji")==0||rs.getInt("hege")==0)
					hegelv=0.0;
					else
					hegelv=(double)rs.getInt("hege")/(double)rs.getInt("zongji");
					tongji.setHegelv(hegelv);
				}

			} catch (Exception e) {
			e.printStackTrace();
		}finally {
			jdbcUitl.clos1(conn, pstat, rs);
		}
    	return tongji;
    }
	public List<String> getjx(List<String> rqs){
    	Connection conn=null;
    	PreparedStatement pstat=null;
    	ResultSet rs=null;
    	ArrayList<String> jx = new ArrayList<String>();
    	try {
			conn=jdbcUitl.getconn();
			String sql="select b.jxjc from t_ks k,t_ksjl j,ba_jx b where j.ksid=k.id and k.jxbm=b.jxdm(+) and j.rq1 between ? and ? group by b.jxjc order by jxjc";
			pstat=conn.prepareStatement(sql);
			pstat.setString(1, rqs.get(0));
			pstat.setString(2, rqs.get(4));
			int i=0;
			rs=pstat.executeQuery();
			while(rs.next()) {
				jx.add(rs.getString("jxjc"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			jdbcUitl.clos1(conn, pstat, rs);
		}
    	return jx;
    }
	public Tongji getDayTongji(String rq){
    	Connection conn=null;
    	PreparedStatement pstat=null;
    	ResultSet rs=null;
    	Tongji tongji=new Tongji();
    	try {
			conn=jdbcUitl.getconn();
			String sql="select count(ksid) zongji,sum(case when cj1='合格'or cj2='合格' then 1 else 0 end) hege,sum (case when cj2='不合格' then 1 else 0 end) buhege from t_ksjl where rq1=?";
					
			pstat=conn.prepareStatement(sql);
			pstat.setString(1, rq);
			
//			int i=0;
			rs=pstat.executeQuery();
			while(rs.next()) {
				double hegelv=0.0;
				int zongji=rs.getInt("hege")+rs.getInt("buhege");
				tongji.setzongji(zongji);
				tongji.setHege(rs.getInt("hege"));
				tongji.setBuhege(rs.getInt("buhege"));
				if(rs.getInt("zongji")==0||rs.getInt("hege")==0)
				hegelv=0.0;
				else
				hegelv=(double)rs.getInt("hege")/(double)zongji;
				tongji.setHegelv(hegelv);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			jdbcUitl.clos1(conn, pstat, rs);
		}
    	return tongji;
    }
	public Tongji getzhouTongji(List<String> rqs){
    	Connection conn=null;
    	PreparedStatement pstat=null;
    	ResultSet rs=null;
    	Tongji tongji=new Tongji();
    	try {
			conn=jdbcUitl.getconn();
			String sql="select count(ksid) zongji,sum(case when cj1='合格'or cj2='合格' then 1 else 0 end) hege,sum (case when cj2='不合格' then 1 else 0 end) buhege from t_ksjl where rq1=? or rq1=? or rq1=? or rq1=? or rq1=? ";
					
			pstat=conn.prepareStatement(sql);
			pstat.setString(1, rqs.get(0));
			pstat.setString(2, rqs.get(1));
			pstat.setString(3, rqs.get(2));
			pstat.setString(4, rqs.get(3));
			pstat.setString(5, rqs.get(4));
//			int i=0;
			rs=pstat.executeQuery();
			while(rs.next()) {
				double hegelv=0.0;
				int zongji=rs.getInt("hege")+rs.getInt("buhege");
				tongji.setzongji(zongji);
				tongji.setHege(rs.getInt("hege"));
				tongji.setBuhege(rs.getInt("buhege"));
				if(rs.getInt("zongji")==0||rs.getInt("hege")==0)
				hegelv=0.0;
				else
				hegelv=(double)rs.getInt("hege")/(double)zongji;
				tongji.setHegelv(hegelv);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			jdbcUitl.clos1(conn, pstat, rs);
		}
    	return tongji;
    }
	public Tongji getmonthTongji(List<String> rqs){
    	Connection conn=null;
    	PreparedStatement pstat=null;
    	ResultSet rs=null;
    	Tongji tongji=new Tongji();
    	String rq5=rqs.get(4);
    	String rqx=rq5.substring(0, 7)+"%";
    	try {
			conn=jdbcUitl.getconn();
			String sql="select count(ksid) zongji,sum(case when cj1='合格'or cj2='合格' then 1 else 0 end) hege,sum (case when cj2='不合格' then 1 else 0 end) buhege from t_ksjl where rq1 like ?";
					
			pstat=conn.prepareStatement(sql);
			pstat.setString(1, rqx);
			
//			int i=0;
			rs=pstat.executeQuery();
			while(rs.next()) {
				double hegelv=0.0;
				int zongji=rs.getInt("hege")+rs.getInt("buhege");
				tongji.setzongji(zongji);
				tongji.setHege(rs.getInt("hege"));
				tongji.setBuhege(rs.getInt("buhege"));
				if(rs.getInt("zongji")==0||rs.getInt("hege")==0)
				hegelv=0.0;
				else
				hegelv=(double)rs.getInt("hege")/(double)zongji;
				tongji.setHegelv(hegelv);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			jdbcUitl.clos1(conn, pstat, rs);
		}
    	return tongji;
    }
	public Tongji getyearTongji(List<String> rqs){
    	Connection conn=null;
    	PreparedStatement pstat=null;
    	ResultSet rs=null;
    	Tongji tongji=new Tongji();
    	String rq5=rqs.get(4);
    	String rqx=rq5.substring(0, 4)+"%";
    	try {
			conn=jdbcUitl.getconn();
			String sql="select count(ksid) zongji,sum(case when cj1='合格'or cj2='合格' then 1 else 0 end) hege,sum (case when cj2='不合格' then 1 else 0 end) buhege from t_ksjl where rq1 like ?";
					
			pstat=conn.prepareStatement(sql);
			pstat.setString(1, rqx);
			
//			int i=0;
			rs=pstat.executeQuery();
			while(rs.next()) {
				double hegelv=0.0;
				int zongji=rs.getInt("hege")+rs.getInt("buhege");
				tongji.setzongji(zongji);
				tongji.setHege(rs.getInt("hege"));
				tongji.setBuhege(rs.getInt("buhege"));
				if(rs.getInt("zongji")==0||rs.getInt("hege")==0)
				hegelv=0.0;
				else
				hegelv=(double)rs.getInt("hege")/(double)zongji;
				tongji.setHegelv(hegelv);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			jdbcUitl.clos1(conn, pstat, rs);
		}
    	return tongji;
    }
	public List<Tongji> getzhouJxTongji(List<String> rqs){
    	Connection conn=null;
    	PreparedStatement pstat=null;
    	ResultSet rs=null;
    	
    	ArrayList<Tongji> tongji= new ArrayList<Tongji>();
    	try {
			conn=jdbcUitl.getconn();
			String sql="select b.jxjc ,count(j.ksid) zongji,sum(case when j.cj1='合格' or j.cj2='合格' then 1 else 0 end) hege,sum(case when j.cj2='不合格' then 1 else 0 end) buhege from t_ksjl j,ba_jx b ,t_ks k where k.id=j.ksid and k.jxbm=b.jxdm(+) and j.rq1 between ? and ? group by b.jxjc order by b.jxjc";
					
			pstat=conn.prepareStatement(sql);
			pstat.setString(1, rqs.get(0));
			pstat.setString(2, rqs.get(4));
			
//			int i=0;
			rs=pstat.executeQuery();
			while(rs.next()) {
				Tongji tj=new Tongji();
				double hegelv=0.0;
				tj.setJx(rs.getString("jxjc"));
				int zongji=rs.getInt("hege")+rs.getInt("buhege");
				tj.setzongji(zongji);
				tj.setHege(rs.getInt("hege"));
				tj.setBuhege(rs.getInt("buhege"));
				if(rs.getInt("zongji")==0||rs.getInt("hege")==0)
				hegelv=0.0;
				else
				hegelv=(double)rs.getInt("hege")/(double)zongji;
				tj.setHegelv(hegelv);
				tongji.add(tj);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			jdbcUitl.clos1(conn, pstat, rs);
		}
    	return tongji;
    }
	public Tongji getbukao(String rq){
    	Connection conn=null;
    	PreparedStatement pstat=null;
    	ResultSet rs=null;
    	Tongji tj=new Tongji();	
    	
    	try {
			conn=jdbcUitl.getconn();
			String sql="select sum(case when downkscs=0 then 1 else 0 end) chukao,sum(case when downkscs>0 then 1 else 0 end) bukao from T_KS where ckykrq=?";
				
			pstat=conn.prepareStatement(sql);
			pstat.setString(1, rq);
			rs=pstat.executeQuery();
			while(rs.next()) {
				
				tj.setChukao(rs.getInt("chukao"));
				tj.setBukao(rs.getInt("bukao"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			jdbcUitl.clos1(conn, pstat, rs);
		}
    	return tj;
    }

}
