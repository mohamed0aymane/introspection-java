package org.mql.java;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Method;

public class ClassParserFrame extends JPanel {

    private static final long serialVersionUID = 1L;
    private String title;
    
    private JTextField classNameField;
    private JTextArea textArea;
    private JButton buttonAnalyser;
    private JPanel container;

    public ClassParserFrame(String title) {
        this.title = title;

        classNameField = new JTextField(20); 
        textArea = new JTextArea(5,10 ); 
        textArea.setEditable(false);  
        buttonAnalyser = new JButton("Analyser");

        setLayout(new BorderLayout()); //pour centrer la zone du textArea
        
        container = new JPanel();
        container.setLayout(new FlowLayout(FlowLayout.CENTER)); // Pour la zone de textarea etre en centre
        container.add(new JLabel("Nom de la classe : "));
        container.add(classNameField);
        container.add(buttonAnalyser);
        container.setBorder(new TitledBorder(
        		new LineBorder(Color.blue), 
        		" "+title+""));

        add(container, BorderLayout.SOUTH); //mettre la zone du text au sud 
        add(new JScrollPane(textArea), BorderLayout.CENTER);  // Scroll pane pour la zone de texte
        
        buttonAnalyser.addActionListener(new Action(this, getMethod("parseClass")));
    }

   

	// Methode pour analyser la classe et afficher les resultats
    public void parseClass() {
        String className = classNameField.getText().trim();  // Récupérer le texte du champ de saisie
        if (!className.isEmpty()) {
            // Utiliser ClassParser pour analyser la classe
            ClassParser classParser = new ClassParser(className);
            String result = classParser.parse();  // Récupérer le squelette de la classe
            textArea.setText(result);  // Afficher le résultat dans la zone de texte
        } 
    }
    
    private Method getMethod(String methodeName) {
		try {
			
			return this.getClass().getMethod(methodeName);
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
  
    	
		//avec l'introspection on peut tous recuperer
		private class Action implements ActionListener{
			private Object obj; // Instance de la classe contenant la méthode
			private Method method; // Méthode à invoquer

			public Action(Object obj, Method method) {
				super();
				this.obj = obj;
				this.method = method;
			}
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					// Invoque la méthode sur l'objet cible
					Object result=method.invoke(obj);
					// Affiche le résultat de l'exécution de la méthode
					System.out.println("result = "+ result);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
			
		}

	
}
