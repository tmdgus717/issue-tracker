package team1.issuetracker.acceptance;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import team1.issuetracker.Issue.dto.IssueMakeRequest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class IssueTest {
    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 8080; // 테스트 프로파일에 맞는 포트 설정
    }

    Request request = new Request();

    @DisplayName("이슈를 등록하고 조회할 수 있다")
    @Test
    void test() {
        // given
        IssueMakeRequest issueMakeRequest = new IssueMakeRequest("제목", "내용", List.of(), List.of(), null);
        ExtractableResponse<Response> makeIssue = request.post(issueMakeRequest, "/issue");

        // when
        ExtractableResponse<Response> response = request.get("/issue/list");

        //then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.contentType()).isEqualTo(ContentType.JSON.toString());
    }
}
