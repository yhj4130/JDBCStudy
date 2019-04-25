package com.test;

public class ScoreDTO
{
	// 주요 속성 구성
	private String sid, name;
	private int kor, eng, mat, tot, rank;
	private double avg;
	
	public String getSid()
	{
		return sid;
	}
	public void setSid(String sid)
	{
		this.sid = sid;
	}
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public int getKor()
	{
		return kor;
	}
	public void setKor(int kor)
	{
		this.kor = kor;
	}
	public int getEng()
	{
		return eng;
	}
	public void setEng(int eng)
	{
		this.eng = eng;
	}
	public int getMat()
	{
		return mat;
	}
	public void setMat(int mat)
	{
		this.mat = mat;
	}
	public int getTot()
	{
		return kor+eng+mat;
	}
	public void setTot(int tot)
	{
		this.tot = kor+eng+mat;
	}
	public int getRank()
	{
		return rank;
	}
	public void setRank(int rank)
	{
		this.rank = rank;
	}
	public double getAvg()
	{
		return tot/3.0;
	}
	public void setAvg(double avg)
	{
		this.avg = tot/3.0;
	}
	
	
}
