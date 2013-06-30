package com.lichard49.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

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
	/** width slider **/
	private JSlider widthSlider;
	/** save button **/
	private JButton saveButton;
	/** open button **/
	private JButton openButton;
	
	private JTabbedPane tabPane;
	
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
		setSize(150, 100);
		setUndecorated(true);
		setBackground(Color.BLUE);
		
		tabPane = new JTabbedPane()
		{
			public void paintComponent(Graphics g)
			{
				super.paintComponent(g);
				g.setColor(Color.WHITE);
				g.drawRect(130, 3, 16, 15);
				g.drawString("X", 135, 16);
			}
		};
		tabPane.addTab("File", buildFileTab());
		tabPane.addTab("Edit", buildEditTab());
		tabPane.addTab("Pen", buildPenTab());
		setContentPane(tabPane);
		
		tabPane.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent e)
			{
				if(e.getX() > 130 && e.getX() < 147 && e.getY() > 3 && e.getY() < 19)
					System.exit(0);
			}
		});
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
	
	/**
	 * Returns a JPanel containing the file tab contents: the save and open
	 * buttons
	 * 
	 * @return file tab
	 */
	private JPanel buildFileTab()
	{
		JPanel fileTab = new JPanel(new GridLayout(3, 1));
		saveButton = new JButton("Save");
		saveButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				String savedFile = controller.saveOverlay();
				System.out.println("Save: " + savedFile);
			}
		});
		fileTab.add(saveButton);
		
		openButton = new JButton("Open");
		openButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				controller.openOverlay();
			}
		});
		fileTab.add(openButton);
		return fileTab;
	}
	
	/**
	 * Returns a JPanel containing the contents of the edit tab: the undo
	 * button
	 * 
	 * @return edit tab
	 */
	private JPanel buildEditTab()
	{
		JPanel editTab = new JPanel(new GridLayout(3, 1));	
		undoButton = new JButton("Undo");
		undoButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				controller.undoPath();
			}
		});
		setUndoButtonEnabled(false);
		editTab.add(undoButton);
		return editTab;
	}
	
	/**
	 * Returns a JPanel contianing the contents of the pen tab: the color
	 * chooser button and a width slider
	 * 
	 * @return pen tab
	 */
	private JPanel buildPenTab()
	{
		JPanel penTab = new JPanel(new GridLayout(3, 1));
		colorChooserButton = new JButton("Choose color");
		colorChooserButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				controller.setCurrentColor(JColorChooser.showDialog(parent, 
						"Pick a Color", controller.getCurrentColor()));
			}
		});
		penTab.add(colorChooserButton);
		
		widthSlider = new JSlider(1, 100, 10);
		widthSlider.addChangeListener(new ChangeListener()
		{
			@Override
			public void stateChanged(ChangeEvent e)
			{
				controller.setCurrentWidth(widthSlider.getValue());
			}
			
		});
		widthSlider.setMajorTickSpacing(10);
		widthSlider.setPaintTicks(true);
		widthSlider.setSnapToTicks(true);
		penTab.add(new JLabel("Width: "));
		penTab.add(widthSlider);
		return penTab;
	}
}
