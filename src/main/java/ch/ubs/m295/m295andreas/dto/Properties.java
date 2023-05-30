package ch.ubs.m295.m295andreas.dto;

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