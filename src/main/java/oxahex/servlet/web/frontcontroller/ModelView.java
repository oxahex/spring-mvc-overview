package oxahex.servlet.web.frontcontroller;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter @Setter
@RequiredArgsConstructor
public class ModelView {

    private final String viewName;
    private Map<String, Object> model = new HashMap<>();
}
