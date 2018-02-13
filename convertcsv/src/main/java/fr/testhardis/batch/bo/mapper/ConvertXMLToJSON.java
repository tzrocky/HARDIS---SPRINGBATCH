package fr.testhardis.batch.bo.mapper;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;

import org.codehaus.jettison.json.JSONException;
import org.json.XML;

public class ConvertXMLToJSON {

	public ConvertXMLToJSON() {
		Path xmlDocumentPath = Paths.get(getFilePath());

		// Demande de transformation du fichier XML en JSON
		try {
			transformXMLToJSON(xmlDocumentPath);
		} catch (JSONException e) {
			System.out
					.println("Erreur lors de la transformation du document XML au format JSON. Voir l'erreur suivante :"
							+ e.getMessage());
		}
	}

	 
	/**
	 * Méthode de récupération du chemin du fichier
	 * @return
	 */
	private String getFilePath() {
		File file = new File("./xml/outputs/documentXML.xml");
		String xmlFilePath = file.getAbsolutePath();

		return xmlFilePath;
	}

	
	/**
	 * Méthode de transformation du fichier XML en JSON
	 * 
	 * @param xmlDocumentPath
	 * @return
	 * @throws JSONException
	 */
	private String transformXMLToJSON(Path xmlDocumentPath) throws JSONException {

		String chaineXML = null;
		try {
			chaineXML = Files.lines(xmlDocumentPath).collect(Collectors.joining("\n"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		int spacesToIndentEachLevel = 2;
		org.json.JSONObject xmlJSONObj = XML.toJSONObject(chaineXML);
		String jsonPrettyPrintString = xmlJSONObj.toString(spacesToIndentEachLevel);
		System.out.println(jsonPrettyPrintString);
		try {
			FileWriter file = new FileWriter("./json/outputs/test.json");
			file.write(jsonPrettyPrintString);
			file.flush();
			

		} catch (IOException e) {
			System.out
			.println("Erreur lors de l'écriture du fichier au format JSON. Voir l'erreur suivante :"
					+ e.getMessage());
		}
		return jsonPrettyPrintString;
	}

}
