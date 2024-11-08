package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.ChildAbs;

public class ChildAbsDao extends Dao{

	/**
	 * baseSql:String 共通SQL文 プライベート
	 */

	private String baseSql =
			"SELECT child.child_id, child.child_name, child.parents_id, child.class_id, child.is_attend, child.facility_id,absence.abs_is_attend FROM child left outer join absence ON child.child_id = absence.child_id and child.facility_id = absence.facility_id  where child.facility_id=? ";


	//子供情報一覧表示のみ使用（欠席情報あり）
	public List<ChildAbs> getChildListAbsinfo(String facility_id)throws Exception{
		//戻り値用のリスト
		List<ChildAbs> list = new ArrayList<>();
		Connection connection = getConnection();
		PreparedStatement st = null;

		try{
			st = connection.prepareStatement(baseSql);
			st.setString(1,facility_id);
			ResultSet rSet = st.executeQuery();
			while(rSet.next()){
				ChildAbs childabs = new ChildAbs();
				childabs.setChild_id(rSet.getString("child_id"));
				childabs.setChild_name(rSet.getString("child_name"));
				childabs.setParents_id(rSet.getString("parents_id"));
				childabs.setClass_id(rSet.getString("class_id"));
				childabs.setIs_attend(rSet.getBoolean("is_attend"));
				childabs.setFacility_id(rSet.getString("facility_id"));
				childabs.setAbs_is_attend(rSet.getBoolean("abs_is_attend"));
				list.add(childabs);
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


}
