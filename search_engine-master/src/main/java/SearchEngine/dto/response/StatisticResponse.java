package SearchEngine.dto.response;

import SearchEngine.dto.statistic.Statistics;
import lombok.Value;

@Value
public class StatisticResponse {
    boolean result;
    Statistics statistics;
}
