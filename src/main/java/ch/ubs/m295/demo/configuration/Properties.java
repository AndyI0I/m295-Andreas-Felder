package ch.ubs.m295.demo.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "example")
public class Properties {

      private final String sampleProperty;
      private final int sampleNumber;

      public Properties(String sampleProperty, int sampleNumber) {
            this.sampleProperty = sampleProperty;
            this.sampleNumber = sampleNumber;
      }

      public String getSampleProperty() {
            return sampleProperty;
      }

      public int getSampleNumber() {
            return sampleNumber;
      }
}