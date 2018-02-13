package fr.testhardis.batch.bo;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author rtsane 
 * 17-01-2018
 */
@XmlRootElement(name = "documentXML")
public class DocumentXML {

	// Liste des références
	private List<Reference> listReferences = new ArrayList<Reference>();

	// Liste des erreurs
	private List<ErrorXML> listErrors = new ArrayList<ErrorXML>();

	@XmlElementWrapper(name = "references")
	@XmlElements(value = { @XmlElement(name = "reference", type = Reference.class) })
	public List<Reference> getReferences() {
		return listReferences;
	}

	public void setReferences(List<Reference> pListReference) {
		this.listReferences = pListReference;
	}

	@XmlElementWrapper(name = "errors")
	@XmlElements(value = { @XmlElement(name = "error", type = ErrorXML.class) })
	public List<ErrorXML> getErrorsXML() {
		return listErrors;
	}

	public void setErrors(List<ErrorXML> pListError) {
		this.listErrors = pListError;
	}
}