package fr.testhardis.batch.bo;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author rtsane
 * 17-01-2018 
 */
@XmlRootElement(name = "reference")
public class Reference {
	private int numReference;
	private String color;
	private float price;
	private int size;

	@XmlAttribute(name = "numReference")
	public int getNumReference() {
		return numReference;
	}

	public void setNumReference(int pNumReference) {
		this.numReference = pNumReference;
	}

	@XmlAttribute(name = "color")
	public String getColor() {
		return color;
	}

	public void setColor(String pColor) {
		this.color = pColor;
	}

	@XmlAttribute(name = "price")
	public float getPrice() {
		return price;
	}

	public void setPrice(float pPrice) {
		this.price = pPrice;
	}

	@XmlAttribute(name = "size")
	public int getSize() {
		return size;
	}

	public void setSize(int pSize) {
		this.size = pSize;
	}

	@Override
	public String toString() {
		return "  [numéro Reference=" + numReference + ", couleur=" + color + ", price=" + price + ", Taille=" + size
				+ "]";
	}
}