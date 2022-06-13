package quoters;

import javax.annotation.PostConstruct;

@Profiling
public class TerminatorQuoter implements Quoter{
	private String msg;
	private static final String TEXT_GREEN = "\u001B[32m";
	private static final String TEXT_RESET = "\u001B[0m";

	public TerminatorQuoter(){
		System.out.println(TEXT_GREEN + "Part one" + TEXT_RESET);
	}
	@InjectRandomInt(min = 2, max = 7)
	private int repeat;

	@PostConstruct
	public void init() {
		System.out.println(TEXT_GREEN + "Part two, repeat = " + repeat + TEXT_RESET);
	}
	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	@Override
	@PostProxy
	public void sayQuote() {
		System.out.println(TEXT_GREEN +  "Part three" + TEXT_RESET);
		for (int i = 0; repeat > i; i++)
			System.out.println("Say = " + msg);
	}
}
