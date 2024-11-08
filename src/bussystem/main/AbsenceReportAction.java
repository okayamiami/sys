package bussystem.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.ChildDao;



// 欠席報告確認ページ
public class AbsenceReportAction {
	
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
	//ローカル変数の宣言 1
	HttpSession session = req.getSession(true);// セッションを取得
	ChildDao cDao = new ChildDao(); //子供 
	Teacher teacher = (Teacher) session.getAttribute("user");// ログインユーザーを取得
	LocalDate todaysDate = LocalDate.now();// LcalDateインスタンスを取得
	int year = todaysDate.getYear();// 現在の年を取得
	List<Integer> entYearSet = new ArrayList<>();//入学年度のリストを初期化



}
