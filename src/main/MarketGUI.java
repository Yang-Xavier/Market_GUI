package main;

import java.util.function.Consumer;
import java.util.function.Function;

import tool.CheckingStock;
import ui.EntryGUI;

public class MarketGUI {
	// callback function
	Consumer<CheckingStock> callCheck = (stock) -> {
		System.out.println("123");
	};
	
	public void init() {
		EntryGUI entry = new EntryGUI();
		entry.setCallback(callCheck);
		entry.setVisible(true);
	}
	
	
	public static void main(String[] args) {
		new MarketGUI().init();
	}
}
