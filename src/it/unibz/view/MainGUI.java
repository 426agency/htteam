package it.unibz.view;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.Dimension;

public class MainGUI extends JFrame
{

	private static final long serialVersionUID = 1L;

	private JPanel jContentPane = null;

	private JList jFollowerList = null;

	private JMenuBar jJMenuBar = null;

	/**
	 * This is the default constructor
	 */
	public MainGUI() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(668, 250);
		this.setJMenuBar(getJJMenuBar());
		this.setContentPane(getJContentPane());
		this.setTitle("JFrame");
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(new BorderLayout());
			jContentPane.add(getJFollowerList(), BorderLayout.CENTER);
		}
		return jContentPane;
	}

	/**
	 * This method initializes jFollowerList	
	 * 	
	 * @return javax.swing.JList	
	 */
	private JList getJFollowerList() {
		if (jFollowerList == null) {
			jFollowerList = new JList();
		}
		return jFollowerList;
	}

	/**
	 * This method initializes jJMenuBar	
	 * 	
	 * @return javax.swing.JMenuBar	
	 */
	private JMenuBar getJJMenuBar() {
		if (jJMenuBar == null) {
			jJMenuBar = new JMenuBar();
		}
		return jJMenuBar;
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
