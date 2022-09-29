package org.warhead.multiclient.repository.creditcard;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.warhead.multiclient.domain.creditcard.CreditCard;

public interface CreditCardRepository extends JpaRepository<CreditCard, Long> {

	Optional<CreditCard> findById(Long id);

}
