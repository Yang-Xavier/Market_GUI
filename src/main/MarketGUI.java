package main;

import java.util.function.Consumer;
import java.util.function.Function;

import tool.CheckingStock;
import ui.EntryGUI;
import ui.MarketView;

public class MarketGUI {
	// callback function
	Consumer<CheckingStock> callCheck = (stock) -> {
		MarketView marketView = new MarketView(stock);
//		marketView
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
