import org.apache.camel.builder.RouteBuilder;
public class SomeBean extends RouteBuilder {

    @Override
    public void configure() throws Exception {
    from("timer:sometimer?period=10000")
            .log(">>> Running >>");
    }
}
