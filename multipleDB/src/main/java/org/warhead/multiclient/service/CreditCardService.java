package org.warhead.multiclient.service;

import org.warhead.multiclient.domain.cardholder.CreditCardHolder;
import org.warhead.multiclient.domain.creditcard.CreditCard;
import org.warhead.multiclient.domain.pan.CreditCardPan;

public interface CreditCardService {

    CreditCard getCreditCardDetailsById(Long id);
    CreditCardHolder getCreditCardHolderDetailsById(Long id);
    CreditCardPan getPANDetailsById(Long id);
}
