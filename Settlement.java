package com.settlement;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.connections.MyConnection;
import com.pojo.Trade;

public class Settlement {
	public static void securitySettlement() {
		List<String> securities = getAllSecurities(); //To get the list of all securities present
		List<String> members = getAllMembers(); //To get all members participating in the trade 
		String FIND_BY_SECURITYNAME = "select * from tradelist where securityname = ?";
		for(String security:securities) {
			List<Trade> tradesPerSecurity = new ArrayList<Trade>();
			PreparedStatement ps = MyConnection.getMyConnection().prepareStatement(FIND_BY_SECURITYNAME);
			ps.setString(0, security);
			ResultSet set = ps.executeQuery();
			while(set.next()) {
				tradesPerSecurity.add(new Trade(set.getInt("tradeId"),set.getString("scurityName"),set.getInt("quantity"),set.getDouble("price"),set.getString("buyingClearingMember"),set.getString("sellerClearingMember")));
				
			}
		}
	}
}
