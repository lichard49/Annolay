package com.lichard49.view;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JFrame;

import com.lichard49.presenter.Controller;

/**
 * User interface dialog to give setting options to the user
 * 
 * @author Richard
 *
 */
public class SettingsDialog extends JDialog
{
	/** interface with the controller **/
	private Controller controller;
	/** parent frame necessary for dialogs**/
	private JFrame parent;
	
	/** button to open color chooser **/
	private JButton colorChooserButton;
	/** undo button **/
	private JButton undoButton;
	
	/**
	 * Constructor sets up user interface elements
	 * 
	 * @param c
	 * @param frame
	 */
	public SettingsDialog(Controller c, JFrame frame)
	{
		super(frame, "ANNOtation overLAY");
		controller = c;
		parent = frame;
		
		addWindowListener(windowListener);
		setSize(100, 100);
		setLayout(new GridLayout(2, 1));
		
		colorChooserButton = new JButton("Choose color");
		colorChooserButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				controller.setCurrentColor(JColorChooser.showDialog(parent, 
						"Pick a Color", controller.getCurrentColor()));
			}
		});
		add(colorChooserButton);
		
		undoButton = new JButton("Undo");
		undoButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				controller.undoPath();
			}
		});
		setUndoButtonEnabled(false);
		add(undoButton);
	}
	
	/**
	 * Set the accessibility to the undo button
	 * 
	 * @param enabled
	 */
	public void setUndoButtonEnabled(boolean enabled)
	{
		undoButton.setEnabled(enabled);
	}
	
	/**
	 * Window listener ensures that closing the dialog shuts the entire
	 * application down
	 */
	private WindowAdapter windowListener = new WindowAdapter()
	{
		@Override
		public void windowClosing(WindowEvent e)
		{
			System.exit(0);
		}
	};
}
