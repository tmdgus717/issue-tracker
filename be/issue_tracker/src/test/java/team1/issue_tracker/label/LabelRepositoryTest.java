package team1.issue_tracker.label;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
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

    @DisplayName("Label delete test")
    @Test
    void deleteTest() {
        //given
        Label label = Label.builder()
            .color("#000000")
            .createdAt(LocalDateTime.now())
            .description("pleaseSuccess")
            .name("testLabel")
            .build();

        //when
        Label savedLabel = labelRepository.save(label);
        List<Label> labelList = (List<Label>) labelRepository.findAll();
        assertThat(labelList.size()).isEqualTo(1);
        labelRepository.delete(label);

        //then
        labelList = (List<Label>) labelRepository.findAll();
        assertThat(labelList.size()).isEqualTo(0);
    }

    @DisplayName("findById test")
    @Test
    void findByIdTest() {
        Label label = Label.builder()
            .color("#000000")
            .createdAt(LocalDateTime.now())
            .description("pleaseSuccess")
            .name("testLabel")
            .build();

        Label savedLabel = labelRepository.save(label);
        Optional<Label> optionalLabel = labelRepository.findById(1L);
        //then
        assertThat(optionalLabel.get().getName()).isEqualTo(savedLabel.getName());
    }

    @DisplayName("Label update test")
    @Test
    void updateTest() {
        //given
        Label label = Label.builder()
            .color("#000000")
            .createdAt(LocalDateTime.now())
            .description("pleaseSuccess")
            .name("testLabel")
            .build();

        //when
        Label savedLabel = labelRepository.save(label);
        assertThat(savedLabel.getName()).isEqualTo(label.getName());

        Label updateLabel = Label.builder()
            .id(savedLabel.getId())
            .name("updateLabel")
            .description(savedLabel.getDescription())
            .color(savedLabel.getColor())
            .createdAt(savedLabel.getCreatedAt())
            .build();

        Label updatedLabel = labelRepository.save(label);
        assertThat(updatedLabel.getName()).isEqualTo(savedLabel.getName());
    }

}
