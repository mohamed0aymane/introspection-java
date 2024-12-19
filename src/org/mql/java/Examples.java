package org.mql.java;


import javax.swing.JFrame;


public class Examples extends JFrame  {


	private static final long serialVersionUID = 1L;
	
	public Examples() {
		exp02();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(600,500);
		setLocationRelativeTo(null);
		setVisible(true);
		
	}
	void exp01()  {
		ClassParser clsP=new ClassParser("java.lang.String");
		
		System.out.println(clsP.parse());
	}
	void exp02() {

		ClassParserFrame clsP=new ClassParserFrame("Parse");
		clsP.parseClass();
		setContentPane(clsP);
	}
	public static void main(String[] args) {
		new Examples();
	}
}
