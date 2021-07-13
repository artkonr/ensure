import io.github.artkonr.ensure.Ensure;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class OthersTest {

  @Test
  void isValidUrl_nullArgument_throws() {
    Assertions.assertThrows(Exceptions.BAD_ARG, () -> Ensure.isValidUrl(null));
  }

  @Test
  void isValidUrl_blankArgument_throws() {
    Assertions.assertThrows(Exceptions.BAD_ARG, () -> Ensure.isValidUrl(""));
  }

  @Test
  void isValidUrl_argumentIsUrl_doesNotThrow() {
    Assertions.assertDoesNotThrow(() -> Ensure.isValidUrl("http://localhost:9811"));
    Assertions.assertNotNull(Ensure.isValidUrl("http://localhost:9811"));
  }

  @Test
  void isValidUrl_argumentIsNotUrl_throws() {
    Assertions.assertThrows(Exceptions.BAD_ARG, () -> Ensure.isValidUrl("'5$% t"));
  }

  @Test
  void isValidUrl_argumentNull_withMessage_reportsNullArgument() {
    try {
      Ensure.isValidUrl(null, "arg");
    } catch (IllegalArgumentException x) {
      String message = x.getMessage();
      Assertions.assertNotNull(message);
      Assertions.assertTrue(message.startsWith("Bad arg"));
      Assertions.assertTrue(message.contains("expected=non-null&non-blank"));
      Assertions.assertFalse(message.contains("is-url"));
      Assertions.assertTrue(message.contains("argName=arg"));
    }
  }

  @Test
  void isValidUrl_argumentNull_nullMessage_reportsNullArgument() {
    try {
      Ensure.isValidUrl(null, null);
    } catch (IllegalArgumentException x) {
      String message = x.getMessage();
      Assertions.assertNotNull(message);
      Assertions.assertTrue(message.startsWith("Bad arg"));
      Assertions.assertTrue(message.contains("expected=non-null&non-blank"));
      Assertions.assertFalse(message.contains("is-url"));
      Assertions.assertFalse(message.contains("argName"));
    }
  }

  @Test
  void isValidUrl_argumentNull_blankMessage_reportsNullArgument() {
    try {
      Ensure.isValidUrl(null, "");
    } catch (IllegalArgumentException x) {
      String message = x.getMessage();
      Assertions.assertNotNull(message);
      Assertions.assertTrue(message.startsWith("Bad arg"));
      Assertions.assertTrue(message.contains("expected=non-null&non-blank"));
      Assertions.assertFalse(message.contains("is-url"));
      Assertions.assertFalse(message.contains("argName"));
    }
  }

  @Test
  void isValidUrl_argumentIsNotUrl_withMessage_reportsInvalidArgument() {
    try {
      Ensure.isValidUrl("'5$% t", "arg");
    } catch (IllegalArgumentException x) {
      String message = x.getMessage();
      Assertions.assertNotNull(message);
      Assertions.assertTrue(message.startsWith("Bad arg"));
      Assertions.assertTrue(message.contains("expected=non-null&non-blank"));
      Assertions.assertTrue(message.contains("is-url"));
      Assertions.assertTrue(message.contains("argName=arg"));
    }
  }

  @Test
  void isValidUrl_argumentIsNotUrl_nullMessage_reportsInvalidArgument() {
    try {
      Ensure.isValidUrl("'5$% t", null);
    } catch (IllegalArgumentException x) {
      String message = x.getMessage();
      Assertions.assertNotNull(message);
      Assertions.assertTrue(message.startsWith("Bad arg"));
      Assertions.assertTrue(message.contains("expected=non-null&non-blank"));
      Assertions.assertTrue(message.contains("is-url"));
      Assertions.assertFalse(message.contains("argName"));
    }
  }

  @Test
  void isValidUrl_argumentIsNotUrl_blankMessage_reportsInvalidArgument() {
    try {
      Ensure.isValidUrl("'5$% t", "");
    } catch (IllegalArgumentException x) {
      String message = x.getMessage();
      Assertions.assertNotNull(message);
      Assertions.assertTrue(message.startsWith("Bad arg"));
      Assertions.assertTrue(message.contains("expected=non-null&non-blank"));
      Assertions.assertTrue(message.contains("is-url"));
      Assertions.assertFalse(message.contains("argName"));
    }
  }


  @Test
  void isHttpStatus_isStatus_doesNotThrow() {
    Assertions.assertDoesNotThrow(() -> Ensure.isHttpStatus(500));
  }

  @Test
  void isHttpStatus_notStatus_throws() {
    Assertions.assertThrows(Exceptions.BAD_ARG, () -> Ensure.isHttpStatus(-1));
    Assertions.assertThrows(Exceptions.BAD_ARG, () -> Ensure.isHttpStatus(700));
    Assertions.assertThrows(Exceptions.BAD_ARG, () -> Ensure.isHttpStatus(50));
  }

  @Test
  void isHttpStatus_notStatus_withMessage_reportsError() {
    try {
      Ensure.isHttpStatus(-1, "arg");
    } catch (IllegalArgumentException x) {
      String message = x.getMessage();
      Assertions.assertNotNull(message);
      Assertions.assertTrue(message.startsWith("Bad arg"));
      Assertions.assertTrue(message.contains("[100;600)"));
      Assertions.assertTrue(message.contains("argName=arg"));
    }
  }

  @Test
  void isHttpStatus_notStatus_nullMessage_reportsError() {
    try {
      Ensure.isHttpStatus(-1, null);
    } catch (IllegalArgumentException x) {
      String message = x.getMessage();
      Assertions.assertNotNull(message);
      Assertions.assertTrue(message.startsWith("Bad arg"));
      Assertions.assertTrue(message.contains("[100;600)"));
      Assertions.assertFalse(message.contains("argName"));
    }
  }

  @Test
  void isHttpStatus_notStatus_blankMessage_reportsError() {
    try {
      Ensure.isHttpStatus(-1, "");
    } catch (IllegalArgumentException x) {
      String message = x.getMessage();
      Assertions.assertNotNull(message);
      Assertions.assertTrue(message.startsWith("Bad arg"));
      Assertions.assertTrue(message.contains("[100;600)"));
      Assertions.assertFalse(message.contains("argName"));
    }
  }

  @Test
  void areHttpStatuses_allValid_doesNotThrow() {
    Assertions.assertDoesNotThrow(() -> Ensure.areHttpStatuses(500, 404, 101));
  }

  @Test
  void areHttpStatuses_eitherInvalid_throws() {
    Assertions.assertThrows(Exceptions.BAD_ARG, () -> Ensure.areHttpStatuses(100, 900));
  }
}
