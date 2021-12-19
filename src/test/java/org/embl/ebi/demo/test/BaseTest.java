package org.embl.ebi.demo.test;

import com.google.code.beanmatchers.BeanMatchers;
import java.time.LocalDateTime;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@Slf4j
@ExtendWith({SpringExtension.class, MockitoExtension.class})
@MockitoSettings(strictness = Strictness.LENIENT)
public abstract class BaseTest {

  @BeforeEach
  public void before(TestInfo testInfo) {
    MockitoAnnotations.openMocks(this);
    log.info("Starting test >>> {}", testInfo.getDisplayName());
    BeanMatchers.registerValueGenerator(LocalDateTime::now, LocalDateTime.class);
  }

  @AfterEach
  public void after(TestInfo testInfo) {
    log.info("End test >>> {}", testInfo.getDisplayName());
  }
}
