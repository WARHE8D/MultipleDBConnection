package org.warhead.multiclient.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.warhead.multiclient.domain.cardholder.CreditCardHolder;
import org.warhead.multiclient.domain.creditcard.CreditCard;
import org.warhead.multiclient.domain.pan.CreditCardPan;
import org.warhead.multiclient.service.CreditCardService;

@RestController
@RequestMapping("/multiclient")
public class MultiClientController {
	
	private final CreditCardService ccService;

	public MultiClientController(CreditCardService ccService) {
		super();
		this.ccService = ccService;
	}
	@GetMapping("/cc/{id}")
	public ResponseEntity<CreditCard> getCreditCardDetailsById(@PathVariable("id") Long id) {
		CreditCard  card= ccService.getCreditCardDetailsById(id);
		return new ResponseEntity<>(card, HttpStatus.OK);
	}
	@GetMapping("/cch/{id}")
	public ResponseEntity<CreditCardHolder> getCreditCardHolderDetailsById(@PathVariable("id") Long id) {
		return new ResponseEntity<>(ccService.getCreditCardHolderDetailsById(id), HttpStatus.OK);
	}
	@GetMapping("/pan/{id}")
	public ResponseEntity<CreditCardPan> getPANDetailsById(@PathVariable("id") Long id) {
		return new ResponseEntity<>(ccService.getPANDetailsById(id), HttpStatus.OK);
	}
}
