package graphics.shapes.ui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import graphics.shapes.SCircle;
import graphics.shapes.SCollection;
import graphics.shapes.SRectangle;
import graphics.shapes.SImage;
import graphics.shapes.SText;
import graphics.shapes.Shape;
import graphics.shapes.ShapeException;
import graphics.shapes.attributes.ColorAttributes;
import graphics.shapes.attributes.FontAttributes;
import graphics.shapes.attributes.SelectionAttributes;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Menu extends JMenuBar {

	private JFrame frame;

	public Menu(JFrame frame) {
		this.frame = frame;
	}
	
	public JFrame getFrame() {
		return this.frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

	public void initMenu() {
		
		// Cr�ation du menu 
		
		JMenu file = new JMenu("File");
		JMenuItem newFile = new JMenuItem("New");
		JMenuItem screenshot = new JMenuItem("Screenshot"); 
		JMenuItem rename = new JMenuItem("Rename");
		JMenuItem copy = new JMenuItem("Copy & paste"); 
		JMenuItem clear = new JMenuItem("Clear");
		JMenuItem delete = new JMenuItem("Delete");
		JMenuItem importImg = new JMenuItem("Import an image"); 
		JMenuItem exit = new JMenuItem("Exit");

		file.add(newFile);
		file.add(new JSeparator());
		file.add(screenshot); 
		file.add(new JSeparator());		
		file.add(rename);
		file.add(new JSeparator());
		file.add(copy); 
		file.add(new JSeparator()); 
		file.add(clear);
		file.add(new JSeparator());
		file.add(delete);
		file.add(new JSeparator());
		file.add(importImg); 
		file.add(new JSeparator());
		file.add(exit);

		JMenu shapesOptions = new JMenu("Shapes");
		JMenuItem shapeColor = new JMenuItem("Color");
		JMenuItem addSRectangle = new JMenuItem("Add a rectangle");
		JMenuItem addSCircle = new JMenuItem("Add a circle");
		JMenuItem addSText = new JMenuItem("Add a text");

		shapesOptions.add(shapeColor);
		shapesOptions.add(new JSeparator());
		shapesOptions.add(addSRectangle);
		shapesOptions.add(new JSeparator());
		shapesOptions.add(addSCircle);
		shapesOptions.add(new JSeparator());
		shapesOptions.add(addSText);

		JMenu style = new JMenu("Style");
		JMenuItem color = new JMenuItem("Background Color");

		style.add(color);

		JMenu others = new JMenu("?");
		JMenuItem help = new JMenuItem("Help");
		JMenuItem aboutUs = new JMenuItem("About Shapes Editor");

		others.add(help);
		others.add(new JSeparator());
		others.add(aboutUs);

		this.add(file);
		this.add(shapesOptions);
		this.add(style);
		this.add(others);
		
		// Fin cr�ation et ajout des �v�nements 

		clear.addActionListener(new ActionListener() {
			Editor editor = (Editor) getFrame();

			public void actionPerformed(ActionEvent e) {

				editor.model.shapeList.removeAll(editor.model.shapeList);
				editor.repaint();

			}
		});

		delete.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				Editor editor = (Editor) getFrame();
				SelectionAttributes sa = null;
				ArrayList<Shape> shapesSelected = new ArrayList<Shape>();

				for (Shape s : editor.model.shapeList) {
					try {
						sa = (SelectionAttributes) s.getAttributes(SelectionAttributes.ID);
					} catch (ShapeException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					if (sa.isSelected()) {
						shapesSelected.add(s);
					}
				}

				editor.model.shapeList.removeAll(shapesSelected);
				editor.sview.repaint();

			}
		});


		exit.addActionListener(new ActionListener() {
			JFrame frame = getFrame();

			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});

		newFile.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				Editor.main(null);

			}

		});
		
		copy.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				Editor editor = (Editor) getFrame();
				SelectionAttributes sa = null;
				SCollection copies = new SCollection(); 
				
				for (Shape s : editor.model.shapeList) {
					try {
						sa = (SelectionAttributes) s.getAttributes(SelectionAttributes.ID);
					} catch (ShapeException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					if (sa.isSelected()) {
						try {
							copies.add(s.copy());
						} catch (ShapeException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} 
					}
					
				}
				
				editor.model.shapeList.addAll(copies.shapeList); 
				editor.sview.repaint();
				

			}

		});
		
		rename.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				JFrame frame = getFrame();
				String name = (String) JOptionPane.showInputDialog(null, "Enter your name file : ",
						JOptionPane.QUESTION_MESSAGE);
				frame.setTitle("Shapes Editor - " + name);

			}

		});

		shapeColor.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				Editor editor = (Editor) getFrame();
				SelectionAttributes sa = null;
				ColorAttributes ca = null ; 
				ArrayList<Shape> shapesSelected = new ArrayList<Shape>();
				Color colorChoosen = JColorChooser.showDialog(null, "Choose a new Color for this shape", null);
				SCollection composite = null ; 
				
				for (Shape s : editor.model.shapeList) {
					try {
						sa = (SelectionAttributes) s.getAttributes(SelectionAttributes.ID);
						ca = (ColorAttributes) s.getAttributes(ColorAttributes.ID);
					} catch (ShapeException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					if (sa.isSelected()) {
						shapesSelected.add(s);
						ca = new ColorAttributes(true,true,colorChoosen,colorChoosen); 
						s.addAttributes(ca);
						editor.sview.repaint();
					}
					
					if(sa.isSelected() && s.getClass()==(new SCollection()).getClass()){
						composite = (SCollection) s ; 

						for(int i = 0 ; i < composite.shapeList.size() ; i++){
							composite.shapeList.get(i).addAttributes(ca); 
						}
					}
				}
				
			}
		});

		addSRectangle.addActionListener(new ActionListener() {
			JFrame frame = getFrame();
			SCollection model = ((Editor) frame).model;

			public void actionPerformed(ActionEvent e) {
				SRectangle r = new SRectangle();
				r.addAttributes(new SelectionAttributes());

				model.add(r);
				frame.getContentPane().repaint();
			}
		});

		addSCircle.addActionListener(new ActionListener() {
			JFrame frame = getFrame();
			SCollection model = ((Editor) frame).model;

			public void actionPerformed(ActionEvent e) {
				SCircle c = new SCircle();
				c.addAttributes(new SelectionAttributes());

				model.add(c);
				frame.getContentPane().repaint();
			}
		});

		addSText.addActionListener(new ActionListener() {
			JFrame frame = getFrame();
			SCollection model = ((Editor) frame).model;

			public void actionPerformed(ActionEvent e) {
				SText t = new SText("HelloWorld!");
				t.addAttributes(new FontAttributes());
				t.addAttributes(new SelectionAttributes());

				model.add(t);
				frame.getContentPane().repaint();
			}
		});

		color.addActionListener(new ActionListener() {

			Editor editor = (Editor) getFrame();

			public void actionPerformed(ActionEvent e) {
				Color colorChoosen = JColorChooser.showDialog(null, "Choose a new Color for the background", null);
				editor.sview.setBackground(colorChoosen);
			}
		});

		help.addActionListener(new ActionListener() {
			JFrame helpFrame = new JFrame("showMessageDialog"); 

			public void actionPerformed(ActionEvent e) {
				String helpMenu = " Draw : ALT down / Left Click " + "\n Delete : Click + SUPPR"
						+ "\n Color Options : Right Click" + "\n Multiple selection : Shift down / Click";

				helpFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				JOptionPane.showMessageDialog(helpFrame, helpMenu, "Commands", JOptionPane.INFORMATION_MESSAGE);
			}
		});

		aboutUs.addActionListener(new ActionListener() {
			JFrame aboutUsFrame = new JFrame("showMessageDialog");

			public void actionPerformed(ActionEvent e) {
				String helpMenu = "This application has been developed by HUYNH-PHUC Axel 1A ENSISA.";
				aboutUsFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				JOptionPane.showMessageDialog(aboutUsFrame, helpMenu, "About us", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		
		screenshot.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		importImg.addActionListener(new ActionListener() {
			
			JFrame frame = getFrame();
			SCollection model = ((Editor) frame).model;

			public void actionPerformed(ActionEvent e) {
				try {
                    Editor editor = (Editor) getFrame();

                    JFileChooser chooser = new JFileChooser();
                    FileNameExtensionFilter filter = new FileNameExtensionFilter(
                            "JPG & GIF Images", "jpg", "gif");
                    chooser.setFileFilter(filter);
                    int returnVal = chooser.showDialog(null, "Choose an Image");

                    SImage si = new SImage(chooser.getSelectedFile().getAbsolutePath());
                    si.addAttributes(new SelectionAttributes());

                    model.add(si);
                    frame.getContentPane().repaint();
                } catch (IOException ex) {
                    Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
                }
			}
		});
		
		

	}

}
