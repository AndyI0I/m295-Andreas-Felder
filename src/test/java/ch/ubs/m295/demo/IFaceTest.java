package ch.ubs.m295.demo;

import ch.ubs.m295.demo.interfaces.IFace;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class IFaceTest {

      @Test
      void test (IFace iFace) {
            System.out.println(iFace.doWork(1, 2));
      }
}
