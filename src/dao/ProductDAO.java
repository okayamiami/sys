/* 継承クラス */
//テキストｐ235・236
//「dao」の「DAO.java」を継承

//「chapter14」の「Seach.java」と同じことをしている(やり方はこっちのほうが簡略化されてる)

package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.Product;

public class ProductDAO extends DAO {

	public List<Product> search(String keyword) throws Exception {

		//空っぽのリストをつくる
		List<Product> list=new ArrayList<>();

		Connection con=getConnection();

		//検索(★)
		PreparedStatement st=con.prepareStatement(
				"SELECT * FROM PRODUCT WHERE NAME LIKE ?");
		st.setString(1, "%" + keyword + "%");
		ResultSet rs=st.executeQuery();

		//★で検索したものをリストに追加
		//検索でヒットしたものの数だけwhileを回し、追加していく
		while (rs.next()) {
			Product p=new Product();
			p.setId(rs.getInt("id"));
			p.setName(rs.getString("name"));
			p.setPrice(rs.getInt("price"));
			list.add(p);
		}

		st.close();
		con.close();

		return list;
	}


	public int insert(Product product) throws Exception {
		Connection con=getConnection();

		PreparedStatement st=con.prepareStatement(
				"INSERT INTO PRODUCT VALUES(null, ?, ?)");

		st.setString(1, product.getName());
		st.setInt(2, product.getPrice());
		int line=st.executeUpdate();

		st.close();
		con.close();

		return line;
	}

}
