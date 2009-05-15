package org.citygml4j.impl.jaxb.xal._2;

import java.util.ArrayList;
import java.util.List;

import org.citygml4j.jaxb.xal.AddressLineElement;
import org.citygml4j.jaxb.xal.PremiseElement;
import org.citygml4j.jaxb.xal.PremiseNumberElement;
import org.citygml4j.jaxb.xal.PremiseNumberPrefixElement;
import org.citygml4j.jaxb.xal.PremiseNumberSuffixElement;
import org.citygml4j.model.xal.AddressLine;
import org.citygml4j.model.xal.PremiseNumber;
import org.citygml4j.model.xal.PremiseNumberPrefix;
import org.citygml4j.model.xal.PremiseNumberRangeFrom;
import org.citygml4j.model.xal.PremiseNumberSuffix;
import org.citygml4j.model.xal.XALClass;

public class PremiseNumberRangeFromImpl extends XALBaseImpl implements PremiseNumberRangeFrom {
	private PremiseElement.PremiseNumberRange.PremiseNumberRangeFrom premiseNumberRangeFrom;

	public PremiseNumberRangeFromImpl() {
		premiseNumberRangeFrom = new PremiseElement.PremiseNumberRange.PremiseNumberRangeFrom();
	}

	public PremiseNumberRangeFromImpl(PremiseElement.PremiseNumberRange.PremiseNumberRangeFrom premiseNumberRangeFrom) {
		this.premiseNumberRangeFrom = premiseNumberRangeFrom;
	}

	@Override
	public PremiseElement.PremiseNumberRange.PremiseNumberRangeFrom getJAXBObject() {
		return premiseNumberRangeFrom;
	}

	@Override
	public XALClass getXALClass() {
		return XALClass.PREMISENUMBERRANGEFROM;
	}

	public void addAddressLine(AddressLine addressLine) {
		premiseNumberRangeFrom.getAddressLine().add(((AddressLineImpl)addressLine).getJAXBObject());
	}

	public void addPremiseNumber(PremiseNumber premiseNumber) {
		premiseNumberRangeFrom.getPremiseNumber().add(((PremiseNumberImpl)premiseNumber).getJAXBObject());
	}

	public void addPremiseNumberPrefix(PremiseNumberPrefix premiseNumberPrefix) {
		premiseNumberRangeFrom.getPremiseNumberPrefix().add(((PremiseNumberPrefixImpl)premiseNumberPrefix).getJAXBObject());
	}

	public void addPremiseNumberSuffix(PremiseNumberSuffix premiseNumberSuffix) {
		premiseNumberRangeFrom.getPremiseNumberSuffix().add(((PremiseNumberSuffixImpl)premiseNumberSuffix).getJAXBObject());
	}

	public List<AddressLine> getAddressLine() {
		List<AddressLine> addressLineList = new ArrayList<AddressLine>();

		for (AddressLineElement addressLineElem : premiseNumberRangeFrom.getAddressLine())
			addressLineList.add(new AddressLineImpl(addressLineElem));

		return addressLineList;
	}

	public List<PremiseNumber> getPremiseNumber() {
		List<PremiseNumber> premiseNumberList = new ArrayList<PremiseNumber>();

		for (PremiseNumberElement premiseNumberElem : premiseNumberRangeFrom.getPremiseNumber())
			premiseNumberList.add(new PremiseNumberImpl(premiseNumberElem));

		return premiseNumberList;
	}

	public List<PremiseNumberPrefix> getPremiseNumberPrefix() {
		List<PremiseNumberPrefix> prefixList = new ArrayList<PremiseNumberPrefix>();

		for (PremiseNumberPrefixElement prefixElem : premiseNumberRangeFrom.getPremiseNumberPrefix())
			prefixList.add(new PremiseNumberPrefixImpl(prefixElem));

		return prefixList;
	}

	public List<PremiseNumberSuffix> getPremiseNumberSuffix() {
		List<PremiseNumberSuffix> suffixList = new ArrayList<PremiseNumberSuffix>();

		for (PremiseNumberSuffixElement suffixElem : premiseNumberRangeFrom.getPremiseNumberSuffix())
			suffixList.add(new PremiseNumberSuffixImpl(suffixElem));

		return suffixList;
	}

	public void setAddressLine(List<AddressLine> addressLine) {
		List<AddressLineElement> addressLineElemList = new ArrayList<AddressLineElement>();

		for (AddressLine addressLineItem : addressLine)
			addressLineElemList.add(((AddressLineImpl)addressLineItem).getJAXBObject());

		premiseNumberRangeFrom.unsetAddressLine();
		premiseNumberRangeFrom.getAddressLine().addAll(addressLineElemList);
	}

	public void setPremiseNumber(List<PremiseNumber> premiseNumber) {
		List<PremiseNumberElement> premiseNumberElemList = new ArrayList<PremiseNumberElement>();

		for (PremiseNumber premiseNumberItem : premiseNumber)
			premiseNumberElemList.add(((PremiseNumberImpl)premiseNumberItem).getJAXBObject());

		premiseNumberRangeFrom.unsetPremiseNumber();
		premiseNumberRangeFrom.getPremiseNumber().addAll(premiseNumberElemList);
	}

	public void setPremiseNumberPrefix(List<PremiseNumberPrefix> premiseNumberPrefix) {
		List<PremiseNumberPrefixElement> prefixElemList = new ArrayList<PremiseNumberPrefixElement>();

		for (PremiseNumberPrefix prefixItem : premiseNumberPrefix)
			prefixElemList.add(((PremiseNumberPrefixImpl)prefixItem).getJAXBObject());

		premiseNumberRangeFrom.unsetPremiseNumberPrefix();
		premiseNumberRangeFrom.getPremiseNumberPrefix().addAll(prefixElemList);
	}

	public void setPremiseNumberSuffix(List<PremiseNumberSuffix> premiseNumberSuffix) {
		List<PremiseNumberSuffixElement> suffixElemList = new ArrayList<PremiseNumberSuffixElement>();

		for (PremiseNumberSuffix suffixItem : premiseNumberSuffix)
			suffixElemList.add(((PremiseNumberSuffixImpl)suffixItem).getJAXBObject());

		premiseNumberRangeFrom.unsetPremiseNumberSuffix();
		premiseNumberRangeFrom.getPremiseNumberSuffix().addAll(suffixElemList);
	}

	public boolean isSetAddressLine() {
		return premiseNumberRangeFrom.isSetAddressLine();
	}

	public boolean isSetPremiseNumber() {
		return premiseNumberRangeFrom.isSetPremiseNumber();
	}

	public boolean isSetPremiseNumberPrefix() {
		return premiseNumberRangeFrom.isSetPremiseNumberPrefix();
	}

	public boolean isSetPremiseNumberSuffix() {
		return premiseNumberRangeFrom.isSetPremiseNumberSuffix();
	}

	public void unsetAddressLine() {
		premiseNumberRangeFrom.unsetAddressLine();
	}

	public void unsetPremiseNumber() {
		premiseNumberRangeFrom.unsetPremiseNumber();
	}

	public void unsetPremiseNumberPrefix() {
		premiseNumberRangeFrom.unsetPremiseNumberPrefix();
	}

	public void unsetPremiseNumberSuffix() {
		premiseNumberRangeFrom.unsetPremiseNumberSuffix();
	}

	public boolean unsetAddressLine(AddressLine addressLine) {
		if (premiseNumberRangeFrom.isSetAddressLine())
			return premiseNumberRangeFrom.getAddressLine().remove(((AddressLineImpl)addressLine).getJAXBObject());
		
		return false;
	}

	public boolean unsetPremiseNumber(PremiseNumber premiseNumber) {
		if (premiseNumberRangeFrom.isSetPremiseNumber())
			return premiseNumberRangeFrom.getPremiseNumber().remove(((PremiseNumberImpl)premiseNumber).getJAXBObject());
		
		return false;
	}

	public boolean unsetPremiseNumberPrefix(PremiseNumberPrefix premiseNumberPrefix) {
		if (premiseNumberRangeFrom.isSetPremiseNumberPrefix())
			return premiseNumberRangeFrom.getPremiseNumberPrefix().remove(((PremiseNumberPrefixImpl)premiseNumberPrefix).getJAXBObject());
		
		return false;
	}

	public boolean unsetPremiseNumberSuffix(PremiseNumberSuffix premiseNumberSuffix) {
		if (premiseNumberRangeFrom.isSetPremiseNumberSuffix())
			return premiseNumberRangeFrom.getPremiseNumberSuffix().remove(((PremiseNumberSuffixImpl)premiseNumberSuffix).getJAXBObject());
		
		return false;
	}

}
