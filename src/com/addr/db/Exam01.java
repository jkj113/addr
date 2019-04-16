package com.addr.db;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.addr.db.impl.AddressDAOImpl;

public class Exam01 {
	private static AddressDAO adao = new AddressDAOImpl();
	private static final String ADDR_FILE_PATH = "D:\\study\\addr\\jibun_chungnam.txt";

	public static void main(String[] args) {
		long sTime = System.currentTimeMillis();

		File f = new File(ADDR_FILE_PATH);
		List<String> colList = new ArrayList<>();
		colList.add("ad_code");
		colList.add("ad_sido");
		colList.add("ad_gugun");
		colList.add("ad_dong");
		colList.add("ad_lee");
		colList.add("ad_san");
		colList.add("ad_bunji");
		colList.add("ad_ho");
		colList.add("ad_roadcode");
		colList.add("ad_isbase");
		colList.add("ad_orgnum");
		colList.add("ad_subnum");
		colList.add("ad_jinum");
		colList.add("ad_etc");
		try {
			FileReader fr = new FileReader(f); //어떤 파일이든 읽어들일 수는 있다. 파일 전체를 그냥 읽어들임(줄별로 나눠주지 않는다) 
			                                   //  --> BufferedReader가 줄별로 나눠준다. InputStream(읽을 대상)을 읽는데 FileReader가 InputStream으로 준다???? Stream --> 중간중간 적어두는 단계의 단위??
			BufferedReader br = new BufferedReader(fr); // 원래는 close해줘야 하는데 안 해줘서 노란색. 근데 얘는 안 해줘도 된다. 한번 돌면 끝
			String line = "";
			List<Map<String, String>> addrList = new ArrayList<>();
			int cnt = 1;
			while ((line = br.readLine()) != null) {
				String[] lines = line.split("\\|");
				Map<String, String> addrMap = new HashMap<>();
				for (int i = 0; i < lines.length; i++) {
					addrMap.put(colList.get(i), lines[i]);
				}
				addrList.add(addrMap);

				if (addrList.size() == 1000) {
					long subStime = System.currentTimeMillis();
					int result = adao.insertAddressList(addrList);
					addrList.clear();
					System.out.println("반영된 건 수 : " + result);
					System.out.println("1000개 insert 완료 시간 : " + (System.currentTimeMillis() - subStime));

				}

			}
			int result = adao.insertAddressList(addrList);
			addrList.clear();
			System.out.println("반영된 건 수 : " + result);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("전체 수행 시간 : " + (System.currentTimeMillis() - sTime));

//		String addr = "4413111600|충청남도|천안시 동남구|신방동||0|95|5|441312249001|0|84|0|43457|";
//		String[] addrs = addr.split("\\|");
//		
//		System.out.println(addrs.length);
	}
}
