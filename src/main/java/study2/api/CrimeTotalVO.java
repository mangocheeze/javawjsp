package study2.api;

public class CrimeTotalVO {
	private String part;
	private int year;
	private int totBurglar;
	private int totMurder;
	private int totTheft;
	private int totViolence;
	
	public String getPart() {
		return part;
	}
	public void setPart(String part) {
		this.part = part;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public int getTotBurglar() {
		return totBurglar;
	}
	public void setTotBurglar(int totBurglar) {
		this.totBurglar = totBurglar;
	}
	public int getTotMurder() {
		return totMurder;
	}
	public void setTotMurder(int totMurder) {
		this.totMurder = totMurder;
	}
	public int getTotTheft() {
		return totTheft;
	}
	public void setTotTheft(int totTheft) {
		this.totTheft = totTheft;
	}
	public int getTotViolence() {
		return totViolence;
	}
	public void setTotViolence(int totViolence) {
		this.totViolence = totViolence;
	}
	@Override
	public String toString() {
		return "CrimeTotalVO [part=" + part + ", year=" + year + ", totBurglar=" + totBurglar + ", totMurder=" + totMurder
				+ ", totTheft=" + totTheft + ", totViolence=" + totViolence + "]";
	}
}
