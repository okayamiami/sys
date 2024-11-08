package bussystem.main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.ManageUser;
import tool.Action;

public class NewRegistAction extends Action{
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		//ローカル変数の宣言 1
		HttpSession session = req.getSession(true);// セッションを取得
		ManageUser mu = (ManageUser) session.getAttribute("user");// ログインユーザーを取得
		List<String> us= new ArrayList<String>(Arrays.asList("T","P"));//入学年度のリストを初期化
		//リクエストパラメータ―の取得 2
		//なし

		//DBからデータ取得 3


		//ビジネスロジック 4
		//なし

		//DBへデータ保存 5
		//なし

		//レスポンス値をセット 6

		req.setAttribute("user_status",us );//クラス番号のlistをセット
		session.setAttribute("user", mu);

		//JSPへフォワード 7
		req.getRequestDispatcher("newregist.jsp").forward(req, res);
	}
	}