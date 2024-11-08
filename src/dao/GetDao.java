package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.Get;

public class GetDao extends Dao{

	private String baseSql = "select * from Get WHERE facility_id=? ";

	public Get getGetinfo(String bus_id, String child_id, String facility_id) throws Exception{
		Get get = new Get();
		Connection connection = getConnection();
		PreparedStatement st = null;
		String sql2 = "and bus_id=? ";
		String sql3 = "and child_id=? ";

		try{
			st = connection.prepareStatement(baseSql+sql2+sql3);
			st.setString(1,facility_id);
			st.setString(2,bus_id);
			st.setString(3,child_id);
			ResultSet rSet = st.executeQuery();

			if(rSet.next()){
				get.setBus_id(rSet.getString("bus_id"));
				get.setChild_id(rSet.getString("child_id"));
				get.setGet_is_attend(rSet.getBoolean("get_is_attend"));
				get.setFacility_id(rSet.getString("facility_id"));
			}else{
				get=null;
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
		return get;

	}

	public List<Get> getGetListinfo(String bus_id, String facility_id)throws Exception{
		//戻り値用のリスト
		List<Get> list = new ArrayList<>();
		Connection connection = getConnection();
		PreparedStatement st = null;

		try{
			st = connection.prepareStatement(baseSql);
			st.setString(1,facility_id);
			ResultSet rSet = st.executeQuery();


			//SQLで取得したGet情報を全てリストに詰め込む
			while(rSet.next()){
				Get get = new Get();
				get.setBus_id(rSet.getString("bus_id"));
				get.setChild_id(rSet.getString("child_id"));
				get.setGet_is_attend(rSet.getBoolean("get_is_attend"));
				get.setFacility_id(rSet.getString("facility_id"));
				list.add(get);
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

	public boolean changeGet(String bus_id, String child_id, String facility_id) throws Exception{
		//コネクションを確立
		Connection connection = getConnection();
		//プリペアードステートメント
		PreparedStatement statement = null;
		//実行件数
		int count = 0;

		GetDao gDao = new GetDao();

		try{
			//データベースから学生を取得
			Get get = gDao.getGetinfo(bus_id, child_id,facility_id);
			if (get.isGet_is_attend() == false) {
				//プリペアードステートメンにUPDATE文をセット
				statement = connection.prepareStatement(
						"update Get set is_attend=? where bus_id=?, child_id=?, facility_id=? ");
				//プリペアードステートメントに値をバインド
				statement.setBoolean(1, true);
				statement.setString(2, get.getBus_id());
				statement.setString(3, get.getChild_id());
				statement.setString(4, get.getFacility_id());

			} else {
				//プリペアードステートメンにUPDATE文をセット
				statement = connection.prepareStatement(
						"update Get set is_attend=? where bus_id=?, child_id=?, facility_id=? ");
				//プリペアードステートメントに値をバインド
				statement.setBoolean(1, false);
				statement.setString(2, get.getBus_id());
				statement.setString(3, get.getChild_id());
				statement.setString(4, get.getFacility_id());
			}

			//プリペアードステートメントを実行
			count = statement.executeUpdate();

		} catch (Exception e) {
			throw e;
		} finally {
			//
			if(statement != null) {
				try {
					statement.close();
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

		if (count > 0) {
			//実行件数が1以上ある場合
			return true;
		} else {
			//実行件数が0件の場合
			return false;
		}

	}
}
