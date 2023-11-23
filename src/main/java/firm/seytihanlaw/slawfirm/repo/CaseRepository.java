package firm.seytihanlaw.slawfirm.repo;

import firm.seytihanlaw.slawfirm.model.CaseInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CaseRepository extends JpaRepository<CaseInfo, Long> {
}
