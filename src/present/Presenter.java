package present;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.MainFrame;
import view.StatsFrame;

/**
 * Presenter class
 * 
 * @author Daniel Torres
 */

public class Presenter implements ActionListener {

	/**
	 * 
	 */

	private MainFrame mainFrame;
	private StatsFrame statsFrame;

	/**
	 * Constructor method
	 */

	public Presenter() {
		mainFrame = new MainFrame(this);
		statsFrame = new StatsFrame(this);
	}

	/**
	 * actionPerformed overwrite method
	 */

	@Override
	public void actionPerformed(ActionEvent e) {
	}
}