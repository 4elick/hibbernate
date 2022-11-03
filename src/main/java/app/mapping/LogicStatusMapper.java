package app.mapping;

import app.configuration.LogicStatusProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LogicStatusMapper {
    private final LogicStatusProperties logicStatusProperties;

    public String convertLogicStatus(String logicStatus){
        return logicStatusProperties.getLogicStatuses().containsKey(logicStatus)?
                logicStatusProperties.getLogicStatuses().get(logicStatus):logicStatusProperties.getLogicStatuses().get("default-message");
    }

}
