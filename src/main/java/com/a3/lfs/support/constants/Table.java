package com.a3.lfs.support.constants;

import org.joda.time.DateTime;

public enum Table{

	PI_FILE("mor_pi_fil_dat"),
	PI_INSPECT("mor_pi_ins_dat");

	private String name;

	Table(String name){

		this.name = name;
	}

	public String getName(){
		return name;
	}

	public String getTemporary(){

		// temp table : suffix _temp[dayIdx]
		String temporary = String.format("%s_%s", this.name, getTemporaryName());

		return temporary;
	}

	/**
	 * dayIdx : Mon (1) ~ Sun(7)
	 * @return string "temp[dayIdx]"
	 */
	public static String getTemporaryName(){

		int dayIdx = new DateTime().minusDays(1).getDayOfWeek();

		return "temp" + dayIdx;
	}
}