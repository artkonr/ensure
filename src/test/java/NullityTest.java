import io.github.artkonr.ensure.Ensure;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class NullityTest {

  @Test
  void notNull_nullArgument_throws() {
    Assertions.assertThrows(Exceptions.BAD_ARG, () -> Ensure.notNull(null));
  }

  @Test
  void notNull_notNullArgument_doesNotThrow() {
    Assertions.assertDoesNotThrow(() -> Ensure.notNull(new Object()));
  }

  @Test
  void neitherNull_firstNull_throws() {
    Assertions.assertThrows(Exceptions.BAD_ARG, () -> Ensure.neitherNull(null, new Object()));
  }

  @Test
  void neitherNull_varargNull_doesNotThrow() {
    Assertions.assertDoesNotThrow(() -> Ensure.neitherNull(new Object()));
  }

  @Test
  void neitherNull_allNonNull_doesNotThrow() {
    Assertions.assertDoesNotThrow(() -> Ensure.neitherNull(new Object(), new Object()));
  }

  @Test
  void neitherNull_inVarargsNull_throws() {
    Assertions.assertThrows(Exceptions.BAD_ARG, () -> Ensure.neitherNull(new Object(), new Object(), null));
  }

  @Test
  void notNull_nullArgument_withArgumentName_namePresentInMessage() {
    try {
      Ensure.notNull(null, "arg");
    } catch (IllegalArgumentException t) {
      String message = t.getMessage();
      Assertions.assertNotNull(message);
      Assertions.assertTrue(message.startsWith("Bad arg"));
      Assertions.assertTrue(message.contains("expected=non-null"));
      Assertions.assertTrue(message.contains("actual=null"));
      Assertions.assertTrue(message.contains("argName=arg"));
    }
  }

  @Test
  void notNull_nullArgument_nullArgumentName_nameNotPresentInMessage() {
    try {
      Ensure.notNull(null, null);
    } catch (IllegalArgumentException t) {
      String message = t.getMessage();
      Assertions.assertNotNull(message);
      Assertions.assertTrue(message.startsWith("Bad arg"));
      Assertions.assertTrue(message.contains("expected=non-null"));
      Assertions.assertTrue(message.contains("actual=null"));
      Assertions.assertFalse(message.contains("argName"));
    }
  }

  @Test
  void notNull_nullArgument_blankArgumentName_nameNotPresentInMessage() {
    try {
      Ensure.notNull(null, "");
    } catch (IllegalArgumentException t) {
      String message = t.getMessage();
      Assertions.assertNotNull(message);
      Assertions.assertTrue(message.startsWith("Bad arg"));
      Assertions.assertTrue(message.contains("expected=non-null"));
      Assertions.assertTrue(message.contains("actual=null"));
      Assertions.assertFalse(message.contains("argName"));
    }
  }

}
