package com.lichard49.view;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Stack;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileFilter;

import com.lichard49.model.MousePath;

/**
 * Custom file chooser that only accepts *.annolay files, and handles opening
 * and saving this type of file
 * 
 * @author Richard
 *
 */
public class OverlayFileChooser extends JFileChooser
{
	/** Save state: write failed**/
	public static final String WRITE_FAILED = "Write failed";
	/** Save state: permission denied**/
	public static final String PERMISSION_DENIED = "Permission denied";
	
	/** Open state: read failed **/
	public static final String READ_FAILED = "Read failed";
	/** Open state: file not found **/
	public static final String FILE_NOT_FOUND = "File not found";
	
	/** Overlay extension *.annolay **/
	public static final String OVERLAY_EXTENSION = ".annolay";
	
	/** parent frame **/
	private JFrame parent;
	
	/**
	 * Constructor sets the extension filter
	 * 
	 * @param p parent frame
	 */
	public OverlayFileChooser(JFrame p)
	{
		setFileFilter(new OverlayFileFilter());
		parent = p;
	}
	
	/**
	 * Write the given data to a selected file, appending .annolay where
	 * necessary
	 * 
	 * @param saveData
	 * @return save state
	 */
	public String saveOverlay(Serializable saveData)
	{
		switch(showSaveDialog(parent))
		{
		case JFileChooser.APPROVE_OPTION:
			try
			{
				String path = getSelectedFile().getAbsolutePath();
				if(!path.endsWith(OVERLAY_EXTENSION))
					path.concat(OVERLAY_EXTENSION);
				ObjectOutputStream out = new ObjectOutputStream(
						new FileOutputStream(path));
				out.writeObject(saveData);
				out.close();
			}
			catch (IOException e) { return WRITE_FAILED; }
			catch (SecurityException e) { return PERMISSION_DENIED; }
			return getSelectedFile().getAbsolutePath();
		case JFileChooser.CANCEL_OPTION:
			return JFileChooser.CANCEL_SELECTION;
		}
		
		return null;
	}
	
	/**
	 * Opens a selected file and reads the overlay contents out of it
	 * 
	 * @return overlay read out of the file
	 */
	public Stack<MousePath> openOverlay()
	{
		switch(showOpenDialog(parent))
		{
		case JFileChooser.APPROVE_OPTION:
			try {
				ObjectInputStream in = new ObjectInputStream(
						new FileInputStream(getSelectedFile()));
				Stack<MousePath> readPaths = (Stack<MousePath>)in.readObject();
				in.close();
				return readPaths;
			}
			catch (FileNotFoundException e) { }
			catch (IOException e) { }
			catch (ClassNotFoundException e) { }
		}
		return null;
	}
	
	/**
	 * Custom file filter that only accepts *.annolay files
	 * 
	 * @author Richard
	 *
	 */
	private class OverlayFileFilter extends FileFilter
	{
		/**
		 * Determines whether or not a given file passes the filter - has the
		 * extension .annolay
		 * 
		 * @param f file to consider
		 * @return whether or not the file passes the filter
		 */
		@Override
		public boolean accept(File f)
		{
			return f.isDirectory() || f.getName().toLowerCase().endsWith(OVERLAY_EXTENSION);
		}

		/**
		 * Gives the description for use in the actual file chooser dialog
		 * 
		 * @return description
		 */
		@Override
		public String getDescription()
		{
			return "Annolay Files (.annolay)";
		}	
	}
}
