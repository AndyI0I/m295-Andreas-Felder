package ch.ubs.m295.demo.configuration;

public class Sample {
    public Sample(Properties properties) {
        System.out.println(properties.getSampleProperty());
        System.out.println(properties.getSampleNumber());
    }
}
