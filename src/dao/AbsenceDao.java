package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.Absence;

public class AbsenceDao extends Dao{

	private String baseSql = "select * from Absence where facility_id=? ";

	// 欠席情報のリスト一覧
	public List<Absence> getAbsenceInfo(String absence_id, String facility_id) throws Exception {
		// 戻り値用のリストを作成
		// new演算子とArrayListで空のListを用意
		List<Absence> list = new ArrayList<>();

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
				Absence abs = new Absence();
				// 戻り値用
				abs.setAbsence_id(rSet.getString("absence_id"));
				abs.setAbsence_main(rSet.getString("absence_main"));
				abs.setChild_id(rSet.getString("child_id"));
				abs.setAbsence_date(rSet.getString("absence_date"));
				abs.setFacility_id(rSet.getString("facility_id"));
				abs.setAbs_is_attend(rSet.getBoolean("abs_is_attend"));
				list.add(abs);

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

	// 該当日の対象の子供の欠席情報取得  例）2024/11/6 A幼稚園　αくんの欠席情報の取得
	public Absence getAbschildinfo(String facility_id,String child_id,String absence_date)throws Exception{
		Absence abs = new Absence();
		Connection connection = getConnection();
		PreparedStatement st = null;


		String sql2 = "and child_id = ? and  absence_date=? ";

		try{
			st = connection.prepareStatement(baseSql+sql2);
			st.setString(1,facility_id);
			st.setString(2,child_id);
			st.setString(3, absence_date);
			ResultSet rSet = st.executeQuery();

			if(rSet.next()){
				abs.setAbsence_id(rSet.getString("absence_id"));
				abs.setAbsence_main(rSet.getString("absence_main"));
				abs.setChild_id(rSet.getString("child_id"));
				abs.setAbsence_date(rSet.getString("absence_date"));
				abs.setFacility_id(rSet.getString("facility_id"));
				abs.setAbs_is_attend(rSet.getBoolean("abs_is_attend"));
			}else{
				abs=null;
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
		return abs;
	}





	// 欠席情報登録
	public boolean saveAbsenceInfo(Absence absence) throws Exception {
		//コネクションを確立
		Connection connection = getConnection();
		//プリペアードステートメント
		PreparedStatement statement = null;
		//実行件数
		int count = 0;

		try{
			//データベースから欠席情報
			Absence abssave = getAbschildinfo (absence.getFacility_id(), absence.getChild_id(), absence.getAbsence_date());
			if (abssave == null) {
				//該当の日に対象の子の欠席情報が存在しなかった場合
				//プリペアードステートメンにINSERT文をセット
				statement = connection.prepareStatement(
						"insert into Absence (absence_id, absence_main, child_id, absence_date, facility_id,) values(?, ?, ?, ?, ?) ");
				//プリペアードステートメントに値をバインド
				statement.setString(1, absence.getAbsence_id());
				statement.setString(2, absence.getAbsence_main());
				statement.setString(3, absence.getChild_id());
				statement.setString(4, absence.getAbsence_id());
				statement.setString(5, absence.getFacility_id());

			} else {
				//該当の日に対象の子の欠席情報が存在した場合
				//プリペアードステートメントにUPDATE文をセット
				statement = connection
						.prepareStatement("update Absence set absence_main=? child_id=? absence_date=? where absence_id=? and facility_id=?  ");
				//プリペアードステートメントに値をバインド
				statement.setString(1, absence.getAbsence_main());
				statement.setString(2, absence.getChild_id());
				statement.setString(3, absence.getAbsence_date());
				statement.setString(4, absence.getAbsence_id());
				statement.setString(5, absence.getFacility_id());

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
