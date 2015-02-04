package com.boredream.nodrinkout.danmu;

public class DanMu {

	public long time;
	public String content;
	public boolean hasShow;

	public DanMu() {
	}

	public DanMu(long time, String content) {
		this.time = time;
		this.content = content;
	}

	@Override
	public String toString() {
		return "DanMu [time=" + time + ", content=" + content + "]";
	}
	
}
