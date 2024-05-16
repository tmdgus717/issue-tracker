package team1.issue_tracker.label;

import java.time.LocalDateTime;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJdbcTest
public class LabelRepositoryTest {

    @Autowired
    LabelRepository labelRepository;

    @DisplayName("h2 saveTest")
    @Test
    void saveTest() {
        //given
        Label label = Label.builder()
            .color("#000000")
            .createdAt(LocalDateTime.now())
            .description("pleaseSuccess")
            .name("testLabel")
            .build();

        System.out.println(label);
        assertThat(label.getColor()).isEqualTo("#000000");
        assertThat(label.getCreatedAt()).isNotNull();

        //whens
        labelRepository.save(label);
    }

}
