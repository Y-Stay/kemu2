package kemu2daochu;

public class CJ {
	private int xuhao,kscs,cs;
	private String name,zjhm,cj,bukao,jx;
	public CJ() {}
	public CJ(int xuhao, int kscs, String name, String zjhm, String cj, int cs, String bukao, String jx) {
		super();
		this.xuhao = xuhao;
		this.kscs = kscs;
		this.name = name;
		this.zjhm = zjhm;
		this.cj = cj;
		this.cs = cs;
		this.bukao = bukao;
		this.jx = jx;
	}
	
	@Override
	public String toString() {
		return name+","+cj+","+cs+","+bukao;
	}
	public int getXuhao() {
		return xuhao;
	}
	public void setXuhao(int xuhao) {
		this.xuhao = xuhao;
	}
	public int getKscs() {
		return kscs;
	}
	public void setKscs(int kscs) {
		this.kscs = kscs;
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
	public String getCj() {
		return cj;
	}
	public void setCj(String cj) {
		this.cj = cj;
	}
	public int getCs() {
		return cs;
	}
	public void setCs(int cs) {
		this.cs = cs;
	}
	public String getBukao() {
		return bukao;
	}
	public void setBukao(String bukao) {
		this.bukao = bukao;
	}
	public String getJx() {
		return jx;
	}
	public void setJx(String jx) {
		this.jx = jx;
	};
	
}
