import io.github.artkonr.ensure.Ensure;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class StringTest {

  @Test
  void notBlank_notBlankArgument_doesNotThrow() {
    Assertions.assertDoesNotThrow(() -> Ensure.notBlank("arg"));
  }

  @Test
  void notBlank_blankArgument_throws() {
    Assertions.assertThrows(Exceptions.BAD_ARG, () -> Ensure.notBlank(""));
  }

  @Test
  void notBlank_nullArgument_throws() {
    Assertions.assertThrows(Exceptions.BAD_ARG, () -> Ensure.notBlank(null));
  }

  @Test
  void neitherBlank_firstNull_throws() {
    Assertions.assertThrows(Exceptions.BAD_ARG, () -> Ensure.neitherBlank(null, "val"));
  }

  @Test
  void neitherBlank_firstBlank_throws() {
    Assertions.assertThrows(Exceptions.BAD_ARG, () -> Ensure.neitherBlank("", "val"));
  }

  @Test
  void neitherBlank_varargNull_doesNotThrow() {
    Assertions.assertDoesNotThrow(() -> Ensure.neitherBlank("val"));
  }

  @Test
  void neitherBlank_allNonBlank_doesNotThrow() {
    Assertions.assertDoesNotThrow(() -> Ensure.neitherBlank("val1", "val2"));
  }

  @Test
  void neitherBlank_inVarargsNull_throws() {
    Assertions.assertThrows(Exceptions.BAD_ARG, () -> Ensure.neitherBlank("val1", "val2", null));
  }

  @Test
  void neitherBlank_inVarargsBlank_throws() {
    Assertions.assertThrows(Exceptions.BAD_ARG, () -> Ensure.neitherBlank("val1", "val2", ""));
  }

  @Test
  void notBlank_blankArgument_nullArgumentName_nameNotPresentInMessage() {
    try {
      Ensure.notBlank(null, null);
    } catch (IllegalArgumentException x) {
      String message = x.getMessage();
      Assertions.assertNotNull(message);
      Assertions.assertTrue(message.startsWith("Bad arg"));
      Assertions.assertTrue(message.contains("expected=non-null&non-blank"));
      Assertions.assertTrue(message.contains("actual=null"));
      Assertions.assertFalse(message.contains("argName"));
    }
  }

  @Test
  void notBlank_blankArgument_blankArgumentName_nameNotPresentInMessage() {
    try {
      Ensure.notBlank("null", "");
    } catch (IllegalArgumentException x) {
      String message = x.getMessage();
      Assertions.assertNotNull(message);
      Assertions.assertTrue(message.startsWith("Bad arg"));
      Assertions.assertTrue(message.contains("expected=non-null&non-blank"));
      Assertions.assertTrue(message.contains("actual="));
      Assertions.assertFalse(message.contains("argName"));
    }
  }

  @Test
  void notBlank_blankArgument_withArgumentName_namePresentInMessage() {
    try {
      Ensure.notBlank(null, "arg");
    } catch (IllegalArgumentException x) {
      String message = x.getMessage();
      Assertions.assertNotNull(message);
      Assertions.assertTrue(message.startsWith("Bad arg"));
      Assertions.assertTrue(message.contains("expected=non-null&non-blank"));
      Assertions.assertTrue(message.contains("actual=null"));
      Assertions.assertTrue(message.contains("argName=arg"));
    }
  }

}
