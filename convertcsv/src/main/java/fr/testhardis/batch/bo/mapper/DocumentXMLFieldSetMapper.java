package fr.testhardis.batch.bo.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

import fr.hardis.utils.Color;
import fr.testhardis.batch.bo.DocumentXML;
import fr.testhardis.batch.bo.ErrorXML;
import fr.testhardis.batch.bo.Reference;

/**
 * @author rtsane 
 * Classe Mapper dans le but de charger l'instance documentXML
 * 17-01-2018
 *
 */
public class DocumentXMLFieldSetMapper implements FieldSetMapper<DocumentXML> {

	public List<Reference> list;
	public List<ErrorXML> listError;
	public static int ligneValues = 0;
	public static String ERROR_CAUSE = "";

	public DocumentXMLFieldSetMapper() {
		list = new ArrayList<Reference>();
		listError = new ArrayList<ErrorXML>();

	}

	/* (non-Javadoc)
	 * @see org.springframework.batch.item.file.mapping.FieldSetMapper#mapFieldSet(org.springframework.batch.item.file.transform.FieldSet)
	 */
	@Override
	public DocumentXML mapFieldSet(FieldSet fieldSet) throws BindException {

		++ligneValues;
		// Instantiating the report object
		Reference reference = new Reference();
		ErrorXML error = new ErrorXML();

		DocumentXML documentXML = new DocumentXML();

		try {
			// Setting the fields
			if (getConditionColor(fieldSet.readString(1))
					&& getConditionPrice(Float.parseFloat(fieldSet.readString(2)))) {
				reference.setNumReference(fieldSet.readInt(0));
				reference.setColor(fieldSet.readString(1));
				reference.setPrice(Float.parseFloat(fieldSet.readString(2)));
				reference.setSize(Integer.parseInt(fieldSet.readString(3)));
				list.add(reference);
				documentXML.setReferences(list);
				System.out.println(ligneValues);
			} else {
				System.out.println(fieldSet.getProperties());
				
				// Construction de la ligne erronée
				StringBuffer ligneErronee = new StringBuffer();
				ligneErronee.append(fieldSet.readInt(0) + ";");
				ligneErronee.append(fieldSet.readString(1) + ";");
				ligneErronee.append(Float.parseFloat(fieldSet.readString(2)) + ";");
				ligneErronee.append(Integer.parseInt(fieldSet.readString(3)));
				System.out.println(ligneErronee.toString());
				
				
				error.setLine("" + ligneValues + "");
				error.setMessage(getExactMessage(ERROR_CAUSE));
				error.setLigneErronee(ligneErronee.toString());
				listError.add(error);
				documentXML.setErrors(listError);
			}
		} catch (Exception e) {
			System.out.println("Problème lors de la construction de l'élément documentXML : " + e.getMessage());
		}

		return documentXML;
	}

	/**
	 * @param color
	 * @return
	 */
	public boolean getConditionColor(String color) {
		boolean boolCol = (!color.isEmpty() && (Color.R.toString().equals(color) || Color.G.toString().equals(color)
				|| Color.B.toString().equals(color)));

		if (boolCol == false) {
			ERROR_CAUSE = "C";
		}

		return boolCol;
	}

	/**
	 * @param price
	 * @return
	 */
	public boolean getConditionPrice(Float price) {
		boolean bool = false;
		Pattern pattern = Pattern.compile("\\d{1,3}[,\\.]?(\\d{1,2})?");
		Matcher matcher = pattern.matcher(String.valueOf(price));
		if (matcher.find()) {
			bool = true;
		}

		if (bool == false) {
			ERROR_CAUSE = "P";
		}
		return bool;
	}

	/**
	 * @param pCause
	 * @return
	 */
	public String getExactMessage(String pCause) {
		String returnMess = "";
		if ("P".equals(pCause)) {
			returnMess = "Incorrect value for Price";
		} else if ("C".equals(pCause)) {
			returnMess = "Incorrect value for Color";
		}

		return returnMess;
	}
}