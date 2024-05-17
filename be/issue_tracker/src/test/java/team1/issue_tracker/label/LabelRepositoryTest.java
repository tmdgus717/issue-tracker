package team1.issue_tracker.label;

import java.time.LocalDateTime;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJdbcTest
public class LabelRepositoryTest {

    @Autowired
    LabelRepository labelRepository;

    @DisplayName("label save test")
    @Test
    void saveTest() {
        //given
        Label label = Label.builder()
            .color("#000000")
            .createdAt(LocalDateTime.now())
            .description("pleaseSuccess")
            .name("testLabel")
            .build();

        assertThat(label.getColor()).isEqualTo("#000000");
        assertThat(label.getCreatedAt()).isNotNull();

        //when
        Label savedLabel = labelRepository.save(label);

        //then
        assertThat(savedLabel.getName()).isEqualTo(label.getName());
    }



}
