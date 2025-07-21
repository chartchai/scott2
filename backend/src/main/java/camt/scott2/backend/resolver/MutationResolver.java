package camt.scott2.backend.resolver;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

@Controller
public class MutationResolver {

    @MutationMapping
    public String echo(@Argument String message) {
        return "Echo: " + message;
    }
}