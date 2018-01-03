package outJs;

public class 中文类名 {
	String 姓名;
	String 年龄;
	public String 得到姓名() {
		return 姓名;
	}
	public void 设置姓名(String 姓名) {
		this.姓名 = 姓名;
	}
	public String 得到年龄() {
		return 年龄;
	}
	public void 设置年龄(String 年龄) {
		this.年龄 = 年龄;
	}
	public void 说出姓名年龄(){
		System.out.println(姓名+";"+年龄);
	}
}
