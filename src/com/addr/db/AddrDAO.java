package com.addr.db;

import java.util.List;
import java.util.Map;

public interface AddrDAO {

	public int insertAddressList(List<Map<String,String>> addrList);
}
