package ru.pwordgen.frames;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.util.EventListener;

import javax.swing.JFrame;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

import fr.theshark34.swinger.Swinger;
import fr.theshark34.swinger.event.SwingerEvent;
import fr.theshark34.swinger.event.SwingerEventListener;
import fr.theshark34.swinger.textured.STexturedButton;

public class MainFrame extends JFrame implements SwingerEventListener,EventListener {
	
	public static final double AppVersion = 1.0;
	public static JTextField outputField = new JTextField();
	
	private STexturedButton btnFinish = new STexturedButton(Swinger.getResource("btnFinish.png"));
	private STexturedButton btnCopy = new STexturedButton(Swinger.getResource("btnCopy.png"));

    SpinnerModel value = new SpinnerNumberModel(5,5,100,1);  
	JSpinner spinner = new JSpinner(value);
	
	Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();

	/*
	 *  Main method
	 */
	public static void main(String args[])
	{
		Swinger.setSystemLookNFeel();
		Swinger.setResourcePath("/ru/pwordgen/resources/");
		
		MainFrame mf = new MainFrame();
		mf.setVisible(true);
	}
	
	/*
	 *  Constructor
	 */
	
	public MainFrame()
	{
		setLayout(null);
		setTitle("Password Generator rel- " + AppVersion);
		setSize(390,160);
		setResizable(false);
		setLocationRelativeTo(null);
		
		spinner.setBounds(330,10,40,30);
		add(spinner);
		
		btnFinish.setBounds(10,50,260,60);
		btnFinish.addEventListener(this);
		add(btnFinish);
		
		btnCopy.setBounds(275,50,100,60);
		btnCopy.addEventListener(this);
		add(btnCopy);
		
		outputField.setBounds(10,10,300,30);
		outputField.setEditable(true);
		add(outputField);
		
		setVisible(true);
	}
	
	/*
	 *  OnEvent Methode Overrided
	 */
	
	@Override
	public void onEvent(SwingerEvent e) {
		
		int spin = (int) spinner.getValue();
		
		String getText = outputField.getText();
		StringSelection strSel = new StringSelection(getText);
		
		if(e.getSource() == btnFinish)
		{
			outputField.setText(tokenize(spin));
		}
		if(e.getSource() == btnCopy)
		{
			clipboard.setContents(strSel,null);
		}
	}
		
	/*
	 *  Tokenize > Exemple : System.out.println(tokenize(20)); le 20 est la longueur de charactère que va prendre le string donné
	 *  
	 *  Exemple :
	 *  public static void main(String args[]) { tokenize(30); } 
	 */
	
	public static String tokenize(int x)
	{
		String Str = "ABCDEFGHIJKLMNOPQRSTUVXYZ" + "0123456789" + "abcdefghijklmopqrstuvxyz" + "!@#$%^&*()_+"; 
		StringBuilder sb = new StringBuilder(x);
		for(int i = 0; i < x; i++)
		{
			int index = (int) (Str.length() * Math.random());
			sb.append(Str.charAt(index));
		}
		return sb.toString();
	}
}
