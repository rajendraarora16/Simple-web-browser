package com.RajendraArora;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;

public class MainProgram {

	private JFrame frame;
	public JTextField locationInfo;
	public JEditorPane editorPane;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					MainProgram window = new MainProgram();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public MainProgram() {
		initialize();
}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 509, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Simple web browser made by Rajendra arora");
		
		class LinkListener implements HyperlinkListener{
			
			@Override
			public void hyperlinkUpdate(HyperlinkEvent evt){
				if(evt.getEventType()==HyperlinkEvent.EventType.ACTIVATED){
					loadURL(evt.getURL());
				}
			}
			public void loadURL(URL url) {
				
				try{
					editorPane.setPage(url);
				}catch(Exception e){
					editorPane.setContentType("text/plain");
					editorPane.setText("The requested page cannot be found!");
				}
			}
			
		}
		
		class GoListener implements ActionListener{
			
			@Override
			public void actionPerformed(ActionEvent evt){
				
				URL url;
				try {
		            String location = locationInfo.getText().trim();
		            if (location.length() == 0)
		               throw new Exception();
		            if (! location.contains("://"))
		               location = "http://" + location;
		            url = new URL(location);
		         }
		         catch (Exception e) {
		            e.printStackTrace();
		            return;
		         }
		         loadURL(url);
		         locationInfo.selectAll();
		         locationInfo.requestFocus();
				}
			
			public void loadURL(URL url) {
				
				try{
					editorPane.setPage(url);
				}catch(Exception e){
					editorPane.setContentType("text/plain");
					editorPane.setText("The requested page cannot be found!");
				}
			}
		}

		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmAbout = new JMenuItem("About");
		mntmAbout.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Simple web browser made by Rajendra Arora", "About!", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		mnFile.add(mntmAbout);
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		mnFile.add(mntmExit);
		frame.getContentPane().setLayout(null);
		
		JLabel lblUrl = new JLabel("URL:");
		lblUrl.setBounds(10, 11, 46, 30);
		frame.getContentPane().add(lblUrl);
		
		GoListener goListener=new GoListener();
		
		locationInfo = new JTextField("www.google.com", 40);
		locationInfo.setBounds(39, 16, 355, 20);
		frame.getContentPane().add(locationInfo);
		locationInfo.setColumns(10);
		locationInfo.addActionListener(goListener);
		
		JButton goBtn = new JButton("Go");
		goBtn.setBounds(404, 15, 55, 23);
		frame.getContentPane().add(goBtn);
		goBtn.addActionListener(goListener);
		
		editorPane = new JEditorPane();
		editorPane.setBounds(10, 52, 473, 277);
		frame.add(new JScrollPane(editorPane));
		frame.getContentPane().add(editorPane);
	}
}
