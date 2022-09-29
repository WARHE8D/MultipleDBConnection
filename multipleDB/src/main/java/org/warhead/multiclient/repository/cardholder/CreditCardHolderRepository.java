package org.warhead.multiclient.repository.cardholder;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.warhead.multiclient.domain.cardholder.CreditCardHolder;

public interface CreditCardHolderRepository extends JpaRepository<CreditCardHolder, Long> {

	Optional<CreditCardHolder> findById(Long id);

}
