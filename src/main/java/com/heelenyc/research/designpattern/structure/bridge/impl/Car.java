package com.heelenyc.research.designpattern.structure.bridge.impl;

import com.heelenyc.research.designpattern.structure.bridge.api.Engin;
import com.heelenyc.research.designpattern.structure.bridge.api.Vehcile;

public class Car implements Vehcile {
	
	private Engin engin;
	
	

	@Override
	public void run() {
		getEngin().work();
		System.out.println(this.getClass().getSimpleName() + " go go go!");
	}



	public Engin getEngin() {
		return engin;
	}

	public void setEngin(Engin engin) {
		this.engin = engin;
	}



	@Override
	public void assembleEngin(Engin e) {
		setEngin(e);
	}

}
