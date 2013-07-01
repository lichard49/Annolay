package com.lichard49.presenter;

import java.awt.Color;

import javax.swing.JFrame;

import com.lichard49.model.MousePath;
import com.lichard49.model.SettingsModel;
import com.lichard49.view.OverlayFileChooser;
import com.lichard49.view.OverlayView;
import com.lichard49.view.SettingsDialog;

/**
 * The controller acts as the bridging point between the model and the view
 * 
 * @author Richard
 *
 */
public class Controller
{
	/** model that stores information **/
	private SettingsModel settingsModel;
	
	/** overlay view where annotations are rendered **/
	private OverlayView overlayView;
	/** dialog view that gives the user settings **/
	private SettingsDialog settingsDialog;
	/** file chooser for opening and saving files **/
	private OverlayFileChooser overlayFileChooser;
	
	/**
	 * Creates the model and view and builds the appropriate relationships
	 * between each
	 * 
	 * @param frame
	 */
	public Controller(JFrame parent)
	{
		settingsModel = new SettingsModel();
		overlayView = new OverlayView(this);
		settingsDialog = new SettingsDialog(this, parent);
		overlayFileChooser = new OverlayFileChooser(parent);
	}
	
	/**
	 * Get the overlay view
	 * 
	 * @return overlay view
	 */
	public OverlayView getOverlayView()
	{
		return overlayView;
	}
	
	/**
	 * Mouse press indicates that a new path will begin, and the overlay must
	 * be painted appropriately
	 * 
	 * Note that given that there is a path on the overlay now, it is also now
	 * possible to undo, so enable that option for the user
	 * 
	 * @param x
	 * @param y
	 */
	public void mousePressed(int x, int y)
	{
		settingsModel.startNewPath(x, y);
		overlayView.setPathsToDraw(settingsModel.getPaths(), false);
		settingsDialog.setUndoButtonEnabled(true);
	}
	
	/**
	 * Mouse drag indicates that the current path will be continued, and the
	 * overlay must be painted appropriately
	 * 
	 * @param x
	 * @param y
	 */
	public void mouseDragged(int x, int y)
	{
		settingsModel.continueCurrentPath(x, y);
		overlayView.setPathsToDraw(settingsModel.getPaths(), false);
	}
	
	/**
	 * Set the visibility of the settings dialog
	 * 
	 * @param visible
	 */
	public void setSettingsDialogVisible(boolean visible)
	{
		settingsDialog.setVisible(visible);
	}
	
	/**
	 * Set the currently selected color
	 * 
	 * @param c
	 */
	public void setCurrentColor(Color c)
	{
		settingsModel.setCurrentColor(c);
	}
	
	/**
	 * Get the currently selected color 
	 * @return color
	 */
	public Color getCurrentColor()
	{
		return settingsModel.getCurrentColor();
	}
	
	public void setCurrentWidth(int w)
	{
		settingsModel.setCurrentWidth(w);
	}
	
	public int getCurrentWidth()
	{
		return settingsModel.getCurrentWidth();
	}
	
	/**
	 * Undo the last path, paint the overlay, and if that removed path was the
	 * last path in the stack, then remove the ability to undo
	 * 
	 * @return deleted path
	 */
	public MousePath undoPath()
	{
		MousePath result = settingsModel.undoPath();
		overlayView.setPathsToDraw(settingsModel.getPaths(), true);
		if(settingsModel.getPaths().size() == 0)
			settingsDialog.setUndoButtonEnabled(false);
		return result;
	}

	/**
	 * Calls upon the file chooser to save the current overlay as a file
	 * 
	 * @return save state
	 */
	public String saveOverlay()
	{
		return overlayFileChooser.saveOverlay(settingsModel.getPaths());
	}
	
	/**
	 * Calls upon the file chooser to open the overlay, which is in turned
	 * rendered here
	 */
	public void openOverlay()
	{
		settingsModel.setPaths(overlayFileChooser.openOverlay());
		overlayView.setPathsToDraw(settingsModel.getPaths(), true);
	}
}
