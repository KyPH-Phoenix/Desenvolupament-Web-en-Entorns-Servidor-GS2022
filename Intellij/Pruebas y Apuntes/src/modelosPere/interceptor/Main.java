package designpatterns.interceptor;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        FilterChain fc = new FilterChain();
        fc.addFilter(new AuthFilter());

        Client client = new Client();
        client.setFc(fc);
        client.sendRequest("http://www.esliceu.com");
    }
}

interface Filter {
    boolean execute(String request);
}

class DebugFilter implements Filter {

    @Override
    public boolean execute(String request) {
        System.out.println("Debugging request: " + request);
        return true;
    }
}

class AuthFilter implements Filter {

    @Override
    public boolean execute(String request) {
        System.out.println("Authenticating: " + request);
        return true;
    }
}

class FilterChain {
    private List<Filter> filterList = new ArrayList();
    private Target target;

    void addFilter(Filter f) {
        filterList.add(f);
    }

    void setTarget(Target t) {
        this.target = t;
    }

    boolean execute(String request) {
        return filterList.stream()
                .allMatch((f) -> f.execute(request));
    }

}

class Target {
    void run(String request) {
        System.out.println("Running request: " + request);
    }
}

class Client {
    private FilterChain fc;

    public void setFc(FilterChain fc) {
        this.fc = fc;
    }

    void sendRequest(String request) {
        this.fc.execute(request);
    }
}
