package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.ClassCd;

public class ClassCdDao extends Dao{

	private String baseSql = "select * from ClassCd WHERE facility_id = ? ";

	public List<ClassCd>  getClassCdinfo(String facility_id) throws Exception {
			// 戻り値用のリストを作成
			// new演算子とArrayListで空のListを用意
			List<ClassCd> list = new ArrayList<>();

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
					ClassCd classcd = new ClassCd();
					// 戻り値用のlistにクラスIDを追加
					classcd.setClass_id(rSet.getString("class_id"));
					classcd.setClass_name(rSet.getString("class_name"));
					classcd.setFacility_id(rSet.getString("facility_id"));
					list.add(classcd);
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

//	public void saveClassCdinfo(){
//
//	}
}
