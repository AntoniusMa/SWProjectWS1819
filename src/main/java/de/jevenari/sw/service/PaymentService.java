
package de.jevenari.sw.service;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.3.1-SNAPSHOT
 * Generated source version: 2.2
 * 
 */
@WebService(name = "PaymentService", targetNamespace = "http://service.sw.jevenari.de/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface PaymentService {


    /**
     * 
     * @param transaction
     * @return
     *     returns de.jevenari.sw.service.TransactionDTO
     * @throws BankingException_Exception
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "instructPayment", targetNamespace = "http://service.sw.jevenari.de/", className = "de.jevenari.sw.service.InstructPayment")
    @ResponseWrapper(localName = "instructPaymentResponse", targetNamespace = "http://service.sw.jevenari.de/", className = "de.jevenari.sw.service.InstructPaymentResponse")
    public TransactionDTO instructPayment(
        @WebParam(name = "transaction", targetNamespace = "")
        TransactionDTO transaction)
        throws BankingException_Exception
    ;

}
