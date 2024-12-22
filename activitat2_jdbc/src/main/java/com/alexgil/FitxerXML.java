package com.alexgil;

import org.w3c.dom.Document;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Element;
import org.w3c.dom.Text;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FitxerXML {

    public static void crearFitxerXMLLlibres(Connection connection) {
        String path = "./llibres_" + System.currentTimeMillis() + ".xml";
        System.out.println("Creant fitxer XML a: " + path);

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            DOMImplementation implementation = builder.getDOMImplementation();
            Document document = implementation.createDocument(null, "llibres", null);
            document.setXmlVersion("1.0");

            String query = "SELECT * FROM Llibres";
            try (PreparedStatement prepstat = connection.prepareStatement(query);
                    ResultSet resultSet = prepstat.executeQuery()) {

                while (resultSet.next()) {
                    // Crear l'element llibre amb els seus fills
                    Element llibreElement = document.createElement("llibre");
                    llibreElement.setAttribute("id", String.valueOf(resultSet.getInt("id_llibre")));
                    document.getDocumentElement().appendChild(llibreElement);

                    // Afegeix els camps del llibre com a elements fills
                    CrearElement("isbn", String.valueOf(resultSet.getLong("isbn")), llibreElement, document);
                    CrearElement("titol", resultSet.getString("titol"), llibreElement, document);
                    CrearElement("autor", resultSet.getString("autor"), llibreElement, document);
                    CrearElement("any_publicacio", String.valueOf(resultSet.getInt("any_publicacio")), llibreElement,
                            document);
                    CrearElement("disponibilitat", String.valueOf(resultSet.getBoolean("disponibilitat")),
                            llibreElement, document);
                    CrearElement("id_categoria", String.valueOf(resultSet.getInt("id_categoria")), llibreElement,
                            document);
                }
            }

            // Escriu el document XML a fitxer
            Source source = new DOMSource(document);
            Result result = new StreamResult(new FileWriter(path));
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xalan}indent-amount", "4");
            transformer.transform(source, result);

            System.out.println("Fitxer XML creat correctament.");

        } catch (Exception e) {
            System.err.println("Error al crear el fitxer XML: " + e.getMessage());
        }
    }

    private static void CrearElement(String nom, String valor, Element arrel, Document document) {
        Element elem = document.createElement(nom);
        Text text = document.createTextNode(valor);
        arrel.appendChild(elem);
        elem.appendChild(text);
    }
}
