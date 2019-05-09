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

import com.addr.db.impl.AddrDAOImpl;

public class Test {
	private static AddrDAO adao = new AddrDAOImpl();
	private static final String FILE_PATH = "D:\\study\\addr\\jibun_sejong.txt";

	public static void main(String[] args) {
		long Time = System.currentTimeMillis();

		File f = new File(FILE_PATH);
		List<String> list = new ArrayList<>();
		list.add("ad_code");
		list.add("ad_sido");
		list.add("ad_gugun");
		list.add("ad_dong");
		list.add("ad_lee");
		list.add("ad_san");
		list.add("ad_bunji");
		list.add("ad_ho");
		list.add("ad_roadcode");
		list.add("ad_isbase");
		list.add("ad_orgnum");
		list.add("ad_subnum");
		list.add("ad_jinum");
		list.add("ad_etc");
		try {
			FileReader fr = new FileReader(f); 
			BufferedReader br = new BufferedReader(fr);
			String line = "";
			List<Map<String, String>> addrList = new ArrayList<>();
			int cnt = 1;
			while ((line = br.readLine()) != null) {
				String[] lines = line.split("\\|");
				Map<String, String> addrMap = new HashMap<>();
				for (int i = 0; i < lines.length; i++) {
					addrMap.put(list.get(i), lines[i]);
				}
				addrList.add(addrMap);

				if (addrList.size() == 1000) {
					long stime = System.currentTimeMillis();
					int result = adao.insertAddressList(addrList);
					addrList.clear();
					System.out.println("반영된 건 수 : " + result);
					System.out.println("1000개 insert 완료 시간 : " + (System.currentTimeMillis() - stime));

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
		System.out.println("전체 수행 시간 : " + (System.currentTimeMillis() - Time));

	}
}
