package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.Information;

public class InformationDao extends Dao{

	private String baseSql = "select * from Infomation where facility_id=? ";

	// お知らせ全件表示
	public List<Information> getInfo(String facility_id) throws Exception {
		// 戻り値用のリストを作成
		// new演算子とArrayListで空のListを用意
		List<Information> list = new ArrayList<>();

		// データベースへのコネクションを確立
		// データベースに接続された！！
		Connection connection = getConnection();

		// プリペアードステートメント
		PreparedStatement statement = null;

		try {

			// プリペアードステートメントにSQL文をセット
			statement = connection.prepareStatement(baseSql);

			//プレースホルダー（？の部分）に値を設定
			statement.setString(1,facility_id);

			// プリペアードステートメントを実行
			// SQL文を実行する
			// 結果はリザルトセット型となる
			ResultSet rSet = statement.executeQuery();

			// リザルトセット（結果）を全件走査
			while (rSet.next()){
				Information info = new Information();
				// 戻り値用のlistにクラスIDを追加
				info.setInfo_id(rSet.getString("info_id"));
				info.setInfo_title(rSet.getString("info_title"));
				info.setInfo_main(rSet.getString("info_main"));
				info.setFacility_id(rSet.getString("facility_id"));
				info.setInfo_genre(rSet.getString("info_genre"));
				info.setInfo_date(rSet.getString("info_date"));
				list.add(info);

			}
		} catch (Exception e) {
			throw e;
		} finally {
			// プリペアードステートメントを閉じる
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException sqle) {
					throw sqle;
				}
			}
			// コネクションを閉じる
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException sqle) {
					throw sqle;
				}
			}
		}

		return list;

	}


	//お知らせ1件詳細情報取得
	public Information getInfoMain(String facility_id,String info_id)throws Exception{
		Information infomain = new Information();
		Connection connection = getConnection();
		PreparedStatement st = null;

		String sql2 = "and info_id = ? ";
		try{
			st = connection.prepareStatement(baseSql+sql2);
			st.setString(1,facility_id);
			st.setString(2,info_id);
			ResultSet rSet = st.executeQuery();

			if(rSet.next()){
				infomain.setInfo_id(rSet.getString("info_id"));
				infomain.setInfo_title(rSet.getString("info_title"));
				infomain.setInfo_main(rSet.getString("info_main"));
				infomain.setFacility_id(rSet.getString("facility_id"));
				infomain.setInfo_genre(rSet.getString("info_genre"));
				infomain.setInfo_date(rSet.getString("info_date"));
			}else{
				infomain=null;
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
		return infomain;
	}


	public boolean saveInfo(Information information) throws Exception {

		//コネクションを確立
		Connection connection = getConnection();
		//プリペアードステートメント
		PreparedStatement statement = null;
		//実行件数
		int count = 0;

		try{
			//データベースからお知らせ情報を取得
			Information info = getInfoMain(information.getFacility_id(), information.getInfo_id());
			if (info == null) {
				//お知らせ情報が存在しなかった場合
				//プリペアードステートメンにINSERT文をセット
				statement = connection.prepareStatement(
						"insert into Information (info_id, info_title, info_main, facility_id, info_genre, info_date) values(?, ?, ?, ?, ?, ?) ");
				//プリペアードステートメントに値をバインド
				statement.setString(1, info.getInfo_id());
				statement.setString(2, info.getInfo_title());
				statement.setString(3, info.getInfo_main());
				statement.setString(4, info.getFacility_id());
				statement.setString(5, info.getInfo_genre());
				statement.setString(6, info.getInfo_date());
			} else {
				//お知らせ情報が存在した場合
				//プリペアードステートメントにUPDATE文をセット
				statement = connection
						.prepareStatement("update Information set info_title=? info_main=? info_genre=? info_date=? where info_id=? and facility_id=?");
				//プリペアードステートメントに値をバインド
				statement.setString(1, info.getInfo_title());
				statement.setString(2, info.getInfo_main());
				statement.setString(3, info.getInfo_genre());
				statement.setString(4, info.getInfo_date());
				statement.setString(5, info.getInfo_id());
				statement.setString(6, info.getFacility_id());
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
