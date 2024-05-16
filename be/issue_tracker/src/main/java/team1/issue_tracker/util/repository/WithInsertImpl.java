package team1.issue_tracker.util.repository;

import org.springframework.data.jdbc.core.JdbcAggregateOperations;
import org.springframework.stereotype.Component;

@Component
public class WithInsertImpl implements WithInsert{

    private final JdbcAggregateOperations jdbcAggregateOperations;

    public WithInsertImpl(JdbcAggregateOperations jdbcAggregateOperations){
		this.jdbcAggregateOperations = jdbcAggregateOperations;
	}

    @Override
    public JdbcAggregateOperations getJdbcAggregateOperations() {
        return this.jdbcAggregateOperations;
    }
}
