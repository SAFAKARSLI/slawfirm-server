package firm.seytihanlaw.slawfirm;

import firm.seytihanlaw.slawfirm.rest.DocumentController;
import firm.seytihanlaw.slawfirm.services.DocumentService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class SlawfirmApplicationTests {

	@Mock
	DocumentService documentService;

	@InjectMocks
	DocumentController documentController;

	@Test
	void contextLoads() {
	}

}
