package kemu2daochu;

public class KS {
	private int xuhao,zw,kscs,zp;
	
	public int getZp() {
		return zp;
	}
	public void setZp(int zp) {
		this.zp = zp;
	}
	private String name,zjhm,jx,bukao;
	KS(){}
	public KS(int xuhao, int zw, String name, String zjhm, String jx, int kscs) {
		super();
		this.xuhao = xuhao;
		this.zw = zw;
		this.name = name;
		this.zjhm = zjhm;
		this.jx = jx;
		this.kscs = kscs;
	}
	
	public String getBukao() {
		return bukao;
	}
	public void setBukao(String bukao) {
		this.bukao = bukao;
	}
	public int getXuhao() {
		return xuhao;
	}
	public void setXuhao(int xuhao) {
		this.xuhao = xuhao;
	}
	public int getZw() {
		return zw;
	}
	public void setZw(int zw) {
		this.zw = zw;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getZjhm() {
		return zjhm;
	}
	public void setZjhm(String zjhm) {
		this.zjhm = zjhm;
	}
	public String getJx() {
		return jx;
	}
	public void setJx(String jx) {
		this.jx = jx;
	}
	public int getKscs() {
		return kscs;
	}
	public void setKscs(int kscs) {
		this.kscs = kscs;
	}
	@Override
	public String toString() {
		return xuhao+","+name;
	};
	
}
