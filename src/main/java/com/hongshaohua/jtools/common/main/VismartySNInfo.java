package com.hongshaohua.jtools.common.main;

public class VismartySNInfo {

	/**
	 * 对应的sn
	 * */
	private String sn;
	/**
	 * 0-9999
	 * */
	private int code;
	/**
	 * time seconds， 10 length
	 * */
	private int time;
	/**
	 * 秒内生成的第几个
	 * */
	private int num;

	public VismartySNInfo(String sn) {
		this.sn = sn;
	}

	public VismartySNInfo(int code, int time, int num) {
		this.code = code;
		this.time = time;
		this.num = num;
	}

	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	@Override
	public String toString() {
		return "VismartySNInfo{" +
				"sn='" + sn + '\'' +
				", code=" + code +
				", time=" + time +
				", num=" + num +
				'}';
	}
}
