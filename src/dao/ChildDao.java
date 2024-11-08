package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.Child;

public class ChildDao extends Dao{

	private String baseSql = "select * from Child WHERE facility_id = ? ";

	//子供個人の情報取得用
	public Child getChildinfo(String facility_id,String child_id)throws Exception{
		Child child = new Child();
		Connection connection = getConnection();
		PreparedStatement st = null;

		String sql2 = "and child_id = ? ";
		try{
			st = connection.prepareStatement(baseSql+sql2);
			st.setString(1,facility_id);
			st.setString(2,child_id);
			ResultSet rSet = st.executeQuery();

			if(rSet.next()){
				child.setChild_id(rSet.getString("child_id"));
				child.setChild_name(rSet.getString("child_name"));
				child.setParents_id(rSet.getString("parents_id"));
				child.setClass_id(rSet.getString("class_id"));
				child.setIs_attend(rSet.getBoolean("is_attend"));
				child.setFacility_id(rSet.getString("facility_id"));
			}else{
				child=null;
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
		return child;
	}

	//子供全員表示
	public List<Child> getChildListinfo(String facility_id)throws Exception{
		//戻り値用のリスト
		List<Child> list = new ArrayList<>();
		Connection connection = getConnection();
		PreparedStatement st = null;

		try{
			st = connection.prepareStatement(baseSql);
			st.setString(1,facility_id);
			ResultSet rSet = st.executeQuery();

			while(rSet.next()){
				Child child = new Child();
				child.setChild_id(rSet.getString("child_id"));
				child.setChild_name(rSet.getString("child_name"));
				child.setParents_id(rSet.getString("parents_id"));
				child.setClass_id(rSet.getString("class_id"));
				child.setIs_attend(rSet.getBoolean("is_attend"));
				child.setFacility_id(rSet.getString("facility_id"));
				list.add(child);
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


	//子供情報のデータベース登録
	public boolean saveChildinfo(Child child) throws Exception{
				//コネクションを確立
				Connection connection = getConnection();
				//プリペアードステートメント
				PreparedStatement statement = null;
				//実行件数
				int count = 0;

				try{
					//データベースから学生を取得
					Child old = getChildinfo(child.getFacility_id(), child.getChild_id());
					if (old == null) {
						//学生が存在しなかった場合
						//プリペアードステートメンにINSERT文をセット
						statement = connection.prepareStatement(
								"insert into Child (child_id, child_name, parents_id, class_id, is_attend, facility_id) values(?, ?, ?, ?, ?, ?) ");
						//プリペアードステートメントに値をバインド
						statement.setString(1, child.getChild_id());
						statement.setString(2, child.getChild_name());
						statement.setString(3, child.getParents_id());
						statement.setString(4, child.getClass_id());
						statement.setBoolean(5, child.is_attend());
						statement.setString(6, child.getFacility_id());
					} else {
						//学生が存在した場合
						//プリペアードステートメントにUPDATE文をセット
						statement = connection
								.prepareStatement("update Child set child_name=?, parents_id=?, class_id=?, is_attend=?, facility_id=? where child_id=? and facility_id=?");
						//プリペアードステートメントに値をバインド
						statement.setString(1, child.getChild_name());
						statement.setString(2, child.getParents_id());
						statement.setString(3, child.getClass_id());
						statement.setBoolean(4, child.is_attend());
						statement.setString(5, child.getFacility_id());
						statement.setString(6, child.getChild_id());
						statement.setString(7, child.getFacility_id());
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
