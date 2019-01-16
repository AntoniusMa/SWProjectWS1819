
package de.jevenari.sw.service;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the de.jevenari.sw.service package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _InstructPayment_QNAME = new QName("http://service.sw.jevenari.de/", "instructPayment");
    private final static QName _InstructPaymentResponse_QNAME = new QName("http://service.sw.jevenari.de/", "instructPaymentResponse");
    private final static QName _TransactionDTO_QNAME = new QName("http://service.sw.jevenari.de/", "transactionDTO");
    private final static QName _BankingException_QNAME = new QName("http://service.sw.jevenari.de/", "BankingException");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: de.jevenari.sw.service
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link InstructPayment }
     * 
     */
    public InstructPayment createInstructPayment() {
        return new InstructPayment();
    }

    /**
     * Create an instance of {@link InstructPaymentResponse }
     * 
     */
    public InstructPaymentResponse createInstructPaymentResponse() {
        return new InstructPaymentResponse();
    }

    /**
     * Create an instance of {@link TransactionDTO }
     * 
     */
    public TransactionDTO createTransactionDTO() {
        return new TransactionDTO();
    }

    /**
     * Create an instance of {@link BankingException }
     * 
     */
    public BankingException createBankingException() {
        return new BankingException();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link InstructPayment }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link InstructPayment }{@code >}
     */
    @XmlElementDecl(namespace = "http://service.sw.jevenari.de/", name = "instructPayment")
    public JAXBElement<InstructPayment> createInstructPayment(InstructPayment value) {
        return new JAXBElement<InstructPayment>(_InstructPayment_QNAME, InstructPayment.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link InstructPaymentResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link InstructPaymentResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://service.sw.jevenari.de/", name = "instructPaymentResponse")
    public JAXBElement<InstructPaymentResponse> createInstructPaymentResponse(InstructPaymentResponse value) {
        return new JAXBElement<InstructPaymentResponse>(_InstructPaymentResponse_QNAME, InstructPaymentResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TransactionDTO }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link TransactionDTO }{@code >}
     */
    @XmlElementDecl(namespace = "http://service.sw.jevenari.de/", name = "transactionDTO")
    public JAXBElement<TransactionDTO> createTransactionDTO(TransactionDTO value) {
        return new JAXBElement<TransactionDTO>(_TransactionDTO_QNAME, TransactionDTO.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BankingException }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link BankingException }{@code >}
     */
    @XmlElementDecl(namespace = "http://service.sw.jevenari.de/", name = "BankingException")
    public JAXBElement<BankingException> createBankingException(BankingException value) {
        return new JAXBElement<BankingException>(_BankingException_QNAME, BankingException.class, null, value);
    }

}
