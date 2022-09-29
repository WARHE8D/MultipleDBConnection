package org.warhead.multiclient.repository.pan;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.warhead.multiclient.domain.pan.CreditCardPan;

public interface CreditCardPANRepository extends JpaRepository<CreditCardPan, Long> {

	Optional<CreditCardPan> findById(Long id);

}
