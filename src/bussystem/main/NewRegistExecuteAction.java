package bussystem.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.OptionalInt;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.ManageUser;
import bean.ParentsUser;
import dao.ManageUserDao;
import dao.ParentsUserDao;
import tool.Action;

public class NewRegistExecuteAction extends Action{
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		//ローカル変数の宣言 1
		HttpSession session = req.getSession();//セッション
		String user_status = "";//id頭文字
		String perfect_id="";
		ParentsUserDao puDao = new ParentsUserDao();
		ManageUserDao muDao = new ManageUserDao();
		Map<String, String> errors = new HashMap<>();// エラーメッセージ
		ManageUser mu = (ManageUser) session.getAttribute("user");// ログインユーザーを取得
		LocalDate todaysDate = LocalDate.now();// LcalDateインスタンスを取得
		int year = todaysDate.getYear();// 現在の年を取得

		//リクエストパラメータ―の取得 2
		user_status=req.getParameter("user_status");//id頭文字
		String facility_id=mu.getFacility_id();
		//DBからデータ取得 3
		//なし
		//ビジネスロジック 4
		if (user_status=="T"){
		List<ManageUser> list =muDao.filter(facility_id);

		List<String> sList = new ArrayList<>();

		for(ManageUser m :list){
			sList.add(m.getUser_id());
		}

		OptionalInt maxNum = sList.stream()
				.filter(s -> s.startsWith("T"))
				.map(s -> Integer.parseInt(s.substring(3)))
				.mapToInt(Integer::intValue)
				.max();
		year=year%100;
		perfect_id=user_status+ Integer.toString(year)+Integer.toString((maxNum.getAsInt()+1));

		//DBへデータ保存 5

		muDao.newSaveManageUserInfo(perfect_id, perfect_id, mu.getFacility_id());
		}
		else if (user_status=="P"){
			List<ParentsUser> list =puDao.filter(facility_id);

			List<String> sList = new ArrayList<>();

			for(ParentsUser p :list){
				sList.add(p.getParents_id());

			OptionalInt maxNum = sList.stream()
					.filter(s -> s.startsWith("P"))
					.map(s -> Integer.parseInt(s.substring(3)))
					.mapToInt(Integer::intValue)
					.max();
			year=year%100;
			perfect_id=user_status+ Integer.toString(year)+Integer.toString((maxNum.getAsInt()+1));
			}
			puDao.newSaveParentsUserInfo(perfect_id, perfect_id, mu.getFacility_id());
		}

		//レスポンス値をセット 6

		req.setAttribute("user_status",user_status );
		session.setAttribute("perfect_id", perfect_id);

		//JSPへフォワード 7
		req.getRequestDispatcher("newregistexecute.jsp").forward(req, res);
	}

}
