package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.ManageUser;




public class ManageUserDao extends Dao{

	public ManageUser getManageUserInfo(String user_id, String facility_id) throws Exception {
		//先生インスタンス初期化
		ManageUser mu = new ManageUser();

		//データベースへのコネクション
		Connection connection =getConnection();

		//プリペアードステートメント
		PreparedStatement statement=null;

		try{
			//prepareにsql文セット
			statement=connection.prepareStatement("select * from manageuser where user_id=? and facility_id=? ");
			//バインド
			statement.setString(1, user_id);
			statement.setString(2, facility_id);
			//プリペアードステートメント実行
			ResultSet rSet=statement.executeQuery();

			if(rSet.next()){
				mu.setUser_id(rSet.getString("user_id"));
				mu.setUser_pass(rSet.getString("user_pass"));
				mu.setUser_name(rSet.getString("user_name"));
				mu.setFacility_id(rSet.getString("facility_id"));
			}else{
				//リザルトセットが存在しない場合
				//学生インスタンスにnullをセット
				mu=null;
			}
		}catch(Exception e){
			throw e;
		}finally{
			//プリペア閉じる
			if(statement !=null){
				try{
					statement.close();
				}catch(SQLException sqle){
					throw sqle;
				}
			}
			if(connection !=null){
				try{
					connection.close();
				}catch(SQLException sqle){
					throw sqle;
				}
			}
		}
		return mu;
	}

	private List<ManageUser> postFilter(ResultSet rSet) throws Exception {
		//空のリスト作成
		List<ManageUser>list=new ArrayList<>();
		try{
			//リザルトセットを全件走査

			while(rSet.next()){

				ManageUser mu=new ManageUser();

				mu.setUser_id(rSet.getString("user_id"));
				mu.setUser_name(rSet.getString("user_name"));
				mu.setUser_pass(rSet.getString("user_pass"));
				mu.setFacility_id(rSet.getString("facility_id"));


				//リストにセットしていく
				list.add(mu);

			}
		}catch(SQLException |NullPointerException e){
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * filterメソッド

	 */
	public List<ManageUser> filter(String facility_id) throws Exception {
		//戻り値用のリストを作成
		//new演算子とArrayListで空のListを用意
		List<ManageUser> list = new ArrayList<>();

		//データベースへのコネクション
		Connection connection=getConnection();
		//プリペアードステートメント
		PreparedStatement statement=null;
		//リザルトセット
		ResultSet rSet=null;
		//SQL文の条件追加
		String sql="select * from manageuser where facility_id=? ";

		try{
			//プリペアードステートメントにSQLセット
			statement=connection.prepareStatement(sql);
			//プリペアードステートメントに学校コードをバインド
			statement.setString(1, facility_id);

			rSet=statement.executeQuery();
			list=postFilter(rSet);
		}catch(Exception e){
			throw e;
		}finally{
			if(statement !=null){
				try{
					statement.close();
				}catch(SQLException sqle){
					throw sqle;
				}
			}
			if(connection !=null){
				try{
					connection.close();
				}catch(SQLException sqle){
					throw sqle;
				}
			}
		}
		return list;
	}

	public boolean saveManageUserInfo(ManageUser mu) throws Exception {
		//コネクションを確立
		Connection connection = getConnection();
		//プリペアードステートメント
		PreparedStatement statement = null;
		//実行件数
		int count = 0;

		try{
			//データベースから保護者を取得
			ManageUser old = getManageUserInfo(mu.getUser_id(), mu.getFacility_id());
			if (old == null) {
				//保護者が存在しなかった場合
				//プリペアードステートメンにINSERT文をセット
				statement = connection.prepareStatement(
						"insert into ManageUser (user_id, user_name, user_pass, facility_id) values(?, ?, ?, ?) ");
				//プリペアードステートメントに値をバインド
				statement.setString(1, mu.getUser_id());
				statement.setString(2, mu.getUser_name());
				statement.setString(3, mu.getUser_pass());
				statement.setString(4, mu.getFacility_id());
			} else {
				//保護者が存在した場合
				//プリペアードステートメントにUPDATE文をセット
				statement = connection
						.prepareStatement("update ManageUser set user_id=?, user_name=?, user_pass=?, facility_id=? where parentsuser_id=? and facility_id=?");
				//プリペアードステートメントに値をバインド
				statement.setString(1, mu.getUser_id());
				statement.setString(2, mu.getUser_name());
				statement.setString(3, mu.getUser_pass());
				statement.setString(4, mu.getFacility_id());
				statement.setString(5, mu.getUser_id());
				statement.setString(6, mu.getFacility_id());
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

	public boolean newSaveManageUserInfo(String user_id,String user_pass,String facility_id) throws Exception {
		//コネクションを確立
		Connection connection = getConnection();
		//プリペアードステートメント
		PreparedStatement statement = null;
		//実行件数
		int count = 0;

		try{
			//プリペアードステートメンにINSERT文をセット
			statement = connection.prepareStatement(
					"insert into ManageUser (user_id, user_name, user_pass, facility_id) values(?, null, ?, ?) ");
			//プリペアードステートメントに値をバインド
			statement.setString(1, user_id);
			statement.setString(2, user_pass);
			statement.setString(3, facility_id);


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

	public ManageUser login(String user_id, String user_pass,String facility_id) throws Exception {
		ManageUser mu = new ManageUser();
		//データベースへのコネクション
		Connection connection =getConnection();

		//プリペアードステートメント
		PreparedStatement statement=null;

		try{
			//prepareにsql文セット
			statement=connection.prepareStatement("select * from manageuser where user_id=? and user_pass=? and facility_id=? ");
			//バインド
			statement.setString(1, user_id);
			statement.setString(2, user_pass);
			statement.setString(3, facility_id);
			//プリペアードステートメント実行
			ResultSet rSet=statement.executeQuery();

			if(rSet.next()){
				//リザルトセットが存在する場合
				//学生インスタンスに検索結果をセット
				mu.setUser_id(rSet.getString("user_id"));
				mu.setUser_pass(rSet.getString("user_pass"));
				mu.setUser_name(rSet.getString("user_name"));
				mu.setFacility_id(rSet.getString("facility_id"));
			}else{
				//リザルトセットが存在しない場合
				//学生インスタンスにnullをセット
				mu=null;
			}
		}catch(Exception e){
			throw e;
		}finally{
			//プリペア閉じる
			if(statement !=null){
				try{
					statement.close();
				}catch(SQLException sqle){
					throw sqle;
				}
			}
			if(connection !=null){
				try{
					connection.close();
				}catch(SQLException sqle){
					throw sqle;
				}
			}
		}
		return mu;
	}

}
