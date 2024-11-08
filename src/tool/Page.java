//テキストｐ62・63
//毎度毎度 書かなくちゃいけないヘッダーやフッターをここで作る
//あとは呼び出して実行するだけ！毎回書かなくていい！楽ちん！

package tool;

import java.io.PrintWriter;

public class Page {

	//ヘッダー
	public static void header(PrintWriter out) {

		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head>");
		out.println("<meta charset='UTF-8'>");
		out.println("<title>Servlet/JSP Sample Programs</title>");
		out.println("</head>");
		out.println("<body>");
	}

	//フッター
	public static void footer(PrintWriter out) {

		out.println("</body>");
		out.println("</html>");

	}
}
