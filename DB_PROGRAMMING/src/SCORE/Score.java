package SCORE;
public class Score {
	private String name;
	private int rank,kor, eng, math, total, average;

	public int getRank()
	{
		return rank;
	}
	public String getName() {
		return name;
	}
	public void setName(String name)
	{
		this.name=name;
	}
	
	public int getKor() {
		return kor;
	}
	public void setKor(int kor)
	{
		this.kor=kor;
	}
	
	public int getEng() {
		return eng;
	}
	public void setEng(int eng)
	{
		this.eng=eng;
	}
	
	public int getMath() {
		return math;
	}
	public void setMath(int math)
	{
		this.math=math;
	}
	
	public int getTotal() {
		return total;
	}
	public void setTotal(int total)
	{
		this.total=total;
	}
	
	public int getAverage() {
		return average;
	}
	public void setAverage(int average)
	{
		this.average=average;
	}
}
