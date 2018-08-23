package kemu2daochu;

public class Tongji {
	private String jx;
	private int zongji,hege,buhege,chukao,bukao;
	private double hegelv;
	public Tongji() {}
	public Tongji(int zongji, int hege, int buhege, double hegelv,String jx) {
		super();
		this.zongji = zongji;
		this.hege = hege;
		this.buhege = buhege;
		this.hegelv = hegelv;
		this.jx=jx;
	}
	
	public int getChukao() {
		return chukao;
	}
	public void setChukao(int chukao) {
		this.chukao = chukao;
	}
	public int getBukao() {
		return bukao;
	}
	public void setBukao(int bukao) {
		this.bukao = bukao;
	}
	public String getJx() {
		return jx;
	}
	public void setJx(String jx) {
		this.jx = jx;
	}
	public int getzongji() {
		return zongji;
	}
	public void setzongji(int zongji) {
		this.zongji = zongji;
	}
	public int getHege() {
		return hege;
	}
	public void setHege(int hege) {
		this.hege = hege;
	}
	public int getBuhege() {
		return buhege;
	}
	public void setBuhege(int buhege) {
		this.buhege = buhege;
	}
	public double getHegelv() {
		return hegelv;
	}
	public void setHegelv(double hegelv) {
		this.hegelv = hegelv;
	}
	@Override
	public String toString() {
		return "Tongji [jx=" + jx + ", zongji=" + zongji + ", hege=" + hege + ", buhege=" + buhege + ", hegelv="
				+ hegelv + "]";
	}
	
}
