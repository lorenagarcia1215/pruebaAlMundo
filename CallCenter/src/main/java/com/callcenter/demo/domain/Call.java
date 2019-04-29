package com.callcenter.demo.domain;

/**
 * 
 * @author Lorena Garcia Arias
 *
 */
public class Call {

	private int duration;
	
	

	/**
	 * @param duration
	 */
	public Call(int duration) {
		super();
		this.duration = duration;
	}

	/**
	 * @return the duration
	 */
	public int getDuration() {
		return duration;
	}

	/**
	 * @param duration the duration to set
	 */
	public void setDuration(int duration) {
		this.duration = duration;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Call [duration=" + duration + "]";
	}
	
	
	
}
