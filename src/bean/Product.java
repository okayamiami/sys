//テキストｐ228・229

package bean;

//「Serializable」⇒オブジェクトをファイルに保存できる(t↓ｐ228)
public class Product implements java.io.Serializable {

	/* プライベート(カプセル化) */
	private int id;
	private String name;
	private int price;


	/* ゲッター */
	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public int getPrice() {
		return price;
	}

	/* セッター */
	public void setId(int id) {
		this.id=id;
	}

	public void setName(String name) {
		this.name=name;
	}

	public void setPrice(int price) {
		this.price=price;
	}
}