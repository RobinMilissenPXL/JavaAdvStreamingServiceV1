package be.pxl.ja.streamingservice.model;

public class CreditCardNumber {
	private static final int LENGTH = 16;
	private static final int CVC_LENGTH = 3;

	private CreditCardType type;
	private String number;
	private String cvc;

	public CreditCardNumber(String number, String cvc) { // constructor cleanen: nummer, type en cvc moeten in apparte methoden
		this.number = validateNumber(removeBlanks(number));
		this.type = validateCreditCardType();
		this.cvc = validateCvc(removeBlanks(cvc));


	}

	private String validateCvc(String cvc) {
		if (!isNumeric(cvc) || cvc.length() != CVC_LENGTH){
			throw new IllegalArgumentException("Cvc code should be 3 digits");
		}
		return cvc;
	}

	private CreditCardType validateCreditCardType() {
		 CreditCardType creditCardType = getCreditCardType();
		if (creditCardType == null) {
			throw new IllegalArgumentException("This is not a valid credit card.");
		}
		return creditCardType;
	}

	private String validateNumber(String number) {
		if (!isNumeric(number) || number.length() != LENGTH) {
			throw new IllegalArgumentException("A card number must have " + LENGTH + " digits.");
		}
		return number;
	}

	public CreditCardType getType() {
		return type;
	}

	public String getCvc() {
		return cvc;
	}

	public String getNumber() {
		return number;
	}

	private String removeBlanks(String text) {
		return text.replaceAll("\\s","");
	}

	private boolean isNumeric(String text) {
		if (text == null || text.length() == 0) {
			return false;
		}
		try {
			Long.parseLong(text);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	private CreditCardType getCreditCardType() {
		for (CreditCardType cardType : CreditCardType.values()) {
			if (cardType.getFirstNumber() == Integer.parseInt(this.number.substring(0, 1))) {
				return cardType;
			}
		}
		return null;
	}
}
