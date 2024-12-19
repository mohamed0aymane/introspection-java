package org.mql.java;

import java.lang.reflect.*;


public class ClassParser {
	private String className;
	

	public ClassParser(String className) {
		this.className=className;
	
	}
	
	public String parse() {
		StringBuilder sb=new StringBuilder();
		 int methodCount = 0; // Compteur pour les méthodes
		 int fieldCount = 0;
		try {
			//charger la classe
			Class<?> cls=Class.forName(className);
			
			//Modifiers
			int modifiers=cls.getModifiers();
			sb.append(Modifier.toString(modifiers))
			.append(" class ")
			.append(cls.getSimpleName());
		
			//savoir si la classe est generique ou non
			if(cls.getTypeParameters().length>0) {
				sb.append("<");
				
				for(int i=0;i<cls.getTypeParameters().length;i++) {
					
					sb.append(cls.getTypeParameters()[i].getName());
					
					if(i<cls.getTypeParameters().length-1) {
						sb.append(",");
					}
				}
				sb.append(">");
			}
			
			
			//Class Parente
			Class<?> superClass=cls.getSuperclass();
			if(superClass !=null && superClass !=Object.class) {
				sb.append("extends ").append(superClass.getName());
			//	System.out.println("test");
			}
			
			
			// 		Interface
			Class<?>[] interfaces = cls.getInterfaces();
			if (interfaces.length > 0) {
			    sb.append(" implements ");
			    for (int i = 0; i < interfaces.length; i++) {
			        
			    	// Savoir si l'interface est un type generique ou nn
			        sb.append(interfaces[i].getSimpleName());
			        
			        TypeVariable<?>[] typeParameters = interfaces[i].getTypeParameters();
			        if (typeParameters.length > 0) {
			            sb.append("<");
			            for (int j = 0; j < typeParameters.length; j++) {
			                sb.append(typeParameters[j].getName());
			                if (j < typeParameters.length - 1) {
			                    sb.append(", ");
			                }
			            }
			            sb.append(">");
			        }
			        
			        if (i < interfaces.length - 1) {
			            sb.append(", ");
			        }
			    }
			}

			//field
			sb.append("\n\nFields :\n");
			for (Field field : cls.getDeclaredFields()) {
			    
			    if(field.getModifiers()!=Modifier.PRIVATE) {
			    	sb.append("  ")
			      .append(Modifier.toString(field.getModifiers())).append(" ")
			      .append(field.getGenericType().getTypeName()).append(" ")
			      .append(field.getName())
			      .append(";\n");
			    	fieldCount ++;
			    
			    	
			    }
			}
			    
	

			
			sb.append(" \n\nConstructeur : \n");
			// Constructeurs
			for (Constructor<?> constructeur : cls.getDeclaredConstructors()) {
			    sb.append("  ")
			      .append(Modifier.toString(constructeur.getModifiers())).append(" ")
			      .append(cls.getSimpleName()).append(" (")
			      .append(getParameterList(constructeur.getParameters())).append(")");
			   
			    // Affichage des exceptions pour chaque constructeur
			    Class<?>[] exceptions =constructeur.getExceptionTypes();
			    if (exceptions.length > 0) {
			        sb.append("    throws ");
			        for (int i = 0; i < exceptions.length; i++) {
			            sb.append(exceptions[i].getSimpleName());
			            if (i < exceptions.length - 1) {
			                sb.append(", ");
			            }
			        }
			      
			    }
			    sb.append(" ;\n");
			}

			
			//Methodes
			sb.append(" \n\nMethodes : \n");
			for(Method methodes:cls.getDeclaredMethods()) {
				sb.append("  ")
				.append(Modifier.toString(methodes.getModifiers())).append(" ")
				.append(methodes.getReturnType()).append(" ")
				.append(methodes.getName()).append(" (")
				.append(getParameterList(methodes.getParameters())).append(")");
				
				// Affichage des exceptions pour chaque constructeur
			    Class<?>[] exceptions =methodes.getExceptionTypes();
			    if (exceptions.length > 0) {
			        sb.append("    throws ");
			        for (int i = 0; i < exceptions.length; i++) {
			            sb.append(exceptions[i].getSimpleName());
			            if (i < exceptions.length - 1) {
			                sb.append(", ");
			            }
			        }
			       
			    }
			    sb.append(" ;\n");
			    methodCount++;
			}
			
		
			
	        sb.append("\n\n Compter les nombre des methodes et Fields:\n")
	          .append("  Nombre de Field: ").append(fieldCount).append("\n")
	          .append("  Nombre de methodes : ").append(methodCount).append("\n");
			
			
			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sb.toString();
		
	}

	//concernant constructeur et methode
	
	private String getParameterList(Parameter[] parameters) {
		StringBuilder sb=new StringBuilder();
		for(int i=0;i<parameters.length ;i++) {
			Parameter prmt=parameters[i];
			sb.append(prmt.getParameterizedType())//pour verifier si il'est generic ou non
			.append(" ");
			
			if(i<parameters.length-1) sb.append(" , ");
		}
		return sb.toString();
	}
	
	
	
	
    

}
