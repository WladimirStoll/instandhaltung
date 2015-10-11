package de.keag.lager.panels.frame.menu;

public class MenuModel implements IMenuModel {
	private IMenuBeobachter iMenuBeobachter;
	
	protected MenuModel() {
		super();
	}
	
	protected void setBeobachter(IMenuBeobachter iMenuBeobachter){
		this.iMenuBeobachter = iMenuBeobachter;
	}
	
}
