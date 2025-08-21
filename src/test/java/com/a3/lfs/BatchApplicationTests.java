package com.a3.lfs;

import org.joda.time.DateTime;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//@RunWith(SpringRunner.class)
@SpringBootTest
public class BatchApplicationTests{

	private static final Logger logger = LoggerFactory.getLogger(BatchApplicationTests.class);

	@Test
	public void test(){
/*
		DateTime dt = new DateTime().withMonthOfYear(4);

		logger.info(dt.dayOfMonth().withMaximumValue().toString("yyyy-MM-dd"));

		logger.info(dt.dayOfMonth().withMinimumValue().toString("yyyy-MM-dd"));
*/
//		logger.info(BatchUtil.longToIp(170994844));

		StringBuffer sb = new StringBuffer();

		sb.append("(");
		sb.append(" '1) PrivacyDb-I\n'+");
		sb.append(" '비 암호화된 개인정보 파일이 검출되었습니다.\n'+");
		sb.append(" '삭제 조치 바랍니다.\n\n'+");

		String myPcStr = String.format("\"%s\" 외 %d건", "패스워드 점검 결과", 4);

		sb.append(" '2) 내PC지킴이\n'+");
		sb.append(" '" + myPcStr + "\n'+");
		sb.append(" 'http://help.ahnlab.com/APC/MyPCInspector_charge/agent/4.6.5/ko_kr/start.htm#pc_che_sub1.htm\n'+");
		sb.append(" '위 URL을 참고하여 조치 하시길 바랍니다.'");

		sb.append(")");

		String contents = sb.toString();

		contents = contents.replaceAll("\n\n'", "'+CHAR(13)+CHAR(10)+CHAR(13)+CHAR(10)");
		contents = contents.replaceAll("\n'", "'+CHAR(13)+CHAR(10)");

		logger.info(contents);
	}

	@Test
	public void funcTest(){

		//		System.out.println(new DateTime().minusDays(1).getDayOfWeek());

		//		String ip = StringUtils.trimAllWhitespace("10.4 9 . 10.*");
		String ip = StringUtils.trimAllWhitespace("10");

		if(ip.endsWith(".")){

			ip = ip.substring(0, ip.length() - 1);
		}

		List<String> ipArr = new ArrayList<>(Arrays.asList(StringUtils.delimitedListToStringArray(ip, ".")));

		int size = ipArr.size();
		if(size != 4){

			for(int i=size; i<4; i++){

				ipArr.add("*");
			}
		}


		String rangeIpStart = "";
		String rangeIpEnd = "";

		for(String unit : ipArr){

			if(unit.equals("*")){

				rangeIpStart += ("0.");
				rangeIpEnd += ("255.");
			}
			else{

				rangeIpStart += (unit + ".");
				rangeIpEnd += (unit + ".");
			}
		}

		rangeIpStart = rangeIpStart.substring(0, (rangeIpStart.length() - 1));
		rangeIpEnd = rangeIpEnd.substring(0, (rangeIpEnd.length() - 1));

		System.out.println(rangeIpStart);
		System.out.println(rangeIpEnd);


		/*
		try{
			long ipLo = ipToLong(InetAddress.getByName("192.200.0.0"));
			long ipHi = ipToLong(InetAddress.getByName("192.255.0.0"));
			long ipToTest = ipToLong(InetAddress.getByName("192.200.3.0"));

			System.out.println(ipToTest >= ipLo && ipToTest <= ipHi);
		}
		catch(UnknownHostException e){
			e.printStackTrace();
		}
		*/
	}

	@Test
	public void recursiveTest(){

		rt(0);
	}

	private void rt(int cnt){

		try{

			if(cnt != 5){

				logger.info("in cnt : " + cnt);
				 rt(++cnt);
			}
			else{

				logger.info("ok cnt : " + cnt);
			}
		}
		catch(Exception e){

			logger.error("load inspect data error", e);
		}
	}
}
