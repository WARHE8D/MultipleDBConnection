package org.warhead.multiclient.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.warhead.multiclient.domain.cardholder.CreditCardHolder;
import org.warhead.multiclient.domain.creditcard.CreditCard;
import org.warhead.multiclient.domain.pan.CreditCardPan;
import org.warhead.multiclient.repository.cardholder.CreditCardHolderRepository;
import org.warhead.multiclient.repository.creditcard.CreditCardRepository;
import org.warhead.multiclient.repository.pan.CreditCardPANRepository;

@Service
public class CreditCardServiceImpl implements CreditCardService {
	
	private final CreditCardRepository ccRepo;
	private final CreditCardHolderRepository cchRepo;
	private final CreditCardPANRepository panRepo;
	
	public CreditCardServiceImpl(CreditCardRepository ccRepo, CreditCardHolderRepository cchRepo,
			CreditCardPANRepository panRepo) {
		super();
		this.ccRepo = ccRepo;
		this.cchRepo = cchRepo;
		this.panRepo = panRepo;
	}

	@Override
	public CreditCard getCreditCardDetailsById(Long id) {
		// TODO Auto-generated method stub
		Optional<CreditCard> card = ccRepo.findById(id);
		return card.get();
	}

	@Override
	public CreditCardHolder getCreditCardHolderDetailsById(Long id) {
		// TODO Auto-generated method stub
		
		return cchRepo.findById(id).get();
	}

	@Override
	public CreditCardPan getPANDetailsById(Long id) {
		// TODO Auto-generated method stub
		return panRepo.findById(id).get();
	}

	
   

}