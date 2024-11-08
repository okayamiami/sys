/* 継承クラス */
//テキストｐ235・236
//「dao」の「DAO.java」を継承

//「chapter14」の「Seach.java」と同じことをしている(やり方はこっちのほうが簡略化されてる)

package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.Bus;

public class BusDao extends Dao {

	private String baseSql = "select * from Bus WHERE facility_id = ? ";

	public List<Bus> getBus(String facility_id) throws Exception {
		//戻り値用のリスト
		List<Bus> list = new ArrayList<>();
		Connection connection = getConnection();
		PreparedStatement st = null;

		try{
			st = connection.prepareStatement(baseSql);
			st.setString(1,facility_id);
			ResultSet rSet = st.executeQuery();

			while(rSet.next()){
				Bus bus = new Bus();
				bus.setBus_id(rSet.getString("bus_id"));
				bus.setBus_name(rSet.getString("bus_name"));
				bus.setFacility_id(rSet.getString("facility_id)"));
				list.add(bus);
			}
		}catch(Exception e){
			throw e;
		} finally {
			//
			if(st != null) {
				try {
					st.close();
				} catch (SQLException sqle) {
					throw sqle;
				}
			}

			if(connection != null) {
				try {
					connection.close();
				} catch (SQLException sqle) {
					throw sqle;
				}
			}


		}
		return list;
	}


//	/
//	public boolean saveBus() throws Exception{
//
//
//	}

}
