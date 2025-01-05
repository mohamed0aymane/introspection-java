package org.mql.java;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClassParserTest {

    @Test
    void testParseSimpleClass() {
        ClassParser parser = new ClassParser("java.util.ArrayList");
        String result = parser.parse();

        // Vérifiez que le nom de la classe est correct
        assertTrue(result.contains("class ArrayList"), "Le nom de la classe n'est pas trouvé dans le résultat.");

        // Vérifiez qu'une méthode connue est présente
        assertTrue(result.contains("add"), "La méthode 'add' devrait être trouvée dans le résultat.");

        // Vérifiez qu'un champ connu est présent
        assertTrue(result.contains("size"), "Le champ 'size' devrait être trouvé dans le résultat.");

        // Vérifiez que les compteurs de méthodes et de champs sont affichés
        assertTrue(result.contains("Nombre de Field"), "Le compteur des champs devrait être affiché.");
        assertTrue(result.contains("Nombre de methodes"), "Le compteur des méthodes devrait être affiché.");
    }

    @Test
    void testParseClassWithGenericType() {
        ClassParser parser = new ClassParser("java.util.HashMap");
        String result = parser.parse();

        // Vérifiez que les types génériques sont bien affichés
        assertTrue(result.contains("<K,V>"), "Les types génériques <K,V> devraient être trouvés dans le résultat.");
    }

    
    @Test
    void testParseClassWithInterfaces() {
        ClassParser parser = new ClassParser("java.util.ArrayList");
        String result = parser.parse();

        // Vérifiez que les interfaces implémentées sont correctes
        assertTrue(result.contains("implements List"), "L'interface 'List' devrait être trouvée dans le résultat.");
        assertTrue(result.contains("RandomAccess"), "L'interface 'RandomAccess' devrait être trouvée dans le résultat.");
    }
//    //verifier une classe qui n'existe pas
//    @Test
//    void testParseNonExistentClass() {
//        ClassParser parser = new ClassParser("non.existent.ClassName");
//
//        // Vérifiez que la classe inexistante retourne une erreur
//        Exception exception = assertThrows(Exception.class, parser::parse);
//        assertTrue(exception.getMessage().contains("ClassNotFoundException"), "Une exception 'ClassNotFoundException' devrait être levée.");
//    }
}
