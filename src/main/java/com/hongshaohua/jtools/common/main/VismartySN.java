package com.hongshaohua.jtools.common.main;

import com.hongshaohua.jtools.common.utils.time.TimeUtilsMillisecond;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class VismartySN {

	public static final long password = 3846693768018346L;

	private String genSN(int code, int time, int num) throws Exception {
		if(code < 0 || code > 9999) {
			throw new Exception("code only 0 - 9999");
		}

		String timeStr = "" + time;
		if(timeStr.length() != 10) {
			throw new Exception("time must be 10 length");
		}

		if(num < 0 || num > 99) {
			throw new Exception("num only 0 - 99");
		}

		//拆分time
		String timeStr1 = timeStr.substring(0, 2);
		String timeStr2 = timeStr.substring(2, 4);
		String timeStr3 = timeStr.substring(4, 6);
		String timeStr4 = timeStr.substring(6, 8);
		String timeStr5 = timeStr.substring(8, 10);

		//拆分code
		String codeStr = String.format("%04d", code);
		String codeStr1 = codeStr.substring(0, 2);
		String codeStr2 = codeStr.substring(2, 4);

		//拆分num
		String numStr = String.format("%02d", num);
		String numStr1 = numStr.substring(0, 1);
		String numStr2 = numStr.substring(1, 2);

		//组合字串
		/**
		 * T1|C1|T2|N2|T3|C2|T4|N1|T5
		 * */
		return timeStr1 + codeStr1 + timeStr2 + numStr2 + timeStr3 + codeStr2 + timeStr4 + numStr1 + timeStr5;
	}

	private void parseSN(VismartySNInfo info, String sn) throws Exception {

		String timeStr1 = sn.substring(0, 2);
		String codeStr1 = sn.substring(2, 4);
		String timeStr2 = sn.substring(4, 6);
		String numStr2 = sn.substring(6, 7);
		String timeStr3 = sn.substring(7, 9);
		String codeStr2 = sn.substring(9, 11);
		String timeStr4 = sn.substring(11, 13);
		String numStr1 = sn.substring(13, 14);
		String timeStr5 = sn.substring(14, 16);

		String timeStr = timeStr1 + timeStr2 +timeStr3 +timeStr4 +timeStr5;
		String codeStr = codeStr1 + codeStr2;
		String numStr = numStr1 + numStr2;

		info.setCode(Integer.parseInt(codeStr));
		info.setTime(Integer.parseInt(timeStr));
		info.setNum(Integer.parseInt(numStr));
	}

	private String encrypt(String sn, long password) {
		long decode = Long.parseLong(sn);
		long encode = decode ^ password;
		return Long.toHexString(encode).toUpperCase();
	}

	private String decrypt(String esn, long password) {
		long encode = Long.parseLong(esn, 16);
		long decode = encode ^ password;
		return "" + decode;
	}

	public void gen(VismartySNInfo info, long password) throws Exception {
		String sn = this.genSN(info.getCode(), info.getTime(), info.getNum());
		info.setSn(this.encrypt(sn, password));
	}

	public void parse(VismartySNInfo info, long password) throws Exception {
		String sn = this.decrypt(info.getSn(), password);
		this.parseSN(info, sn);
	}
}
